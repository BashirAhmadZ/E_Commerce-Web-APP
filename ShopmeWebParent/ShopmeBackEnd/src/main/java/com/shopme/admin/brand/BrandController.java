package com.shopme.admin.brand;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopme.admin.FileUploadUtil;
import com.shopme.admin.category.CategoryService;
import com.shopme.admin.paging.PagingAndSortingHelper;
import com.shopme.admin.paging.PagingAndSortingParam;
import com.shopme.common.entity.Brand;
import com.shopme.common.entity.Category;

import jakarta.servlet.http.HttpServletResponse;

@RequestMapping("/brands")
@Controller
public class BrandController {

	private final BrandService brandService;
	
	private final CategoryService categoryService;

	BrandController(BrandService brandService, CategoryService categoryService) {
		this.brandService = brandService;
		this.categoryService = categoryService;
	}
	
	@GetMapping
	public String listFirstPage() {
//		Model model return listByPage(1, model, "name", "asc", null);
		return "redirect:/brands/page/1?sortField=name&sortDir=asc";
	}
	
	@GetMapping("/page/{pageNum}")
	public String listByPage(
			@PagingAndSortingParam(listName = "listBrands", moduleURL = "/brands") PagingAndSortingHelper helper,
			@PathVariable int pageNum) {
		
//		,@Param("sortField") String sortField, @Param("sortDir") String sortDir,
//		@Param("keyword") String keyword
		
		brandService.listByPage(pageNum, helper);
		
//		List<Brand> listBrands = page.getContent();
//		
//		long startCount = (pageNum - 1) * BrandService.BRAND_PER_PAGE + 1;
//		long endCount = startCount + BrandService.BRAND_PER_PAGE - 1;
//		if (endCount > page.getTotalElements()) {
//			endCount = page.getTotalElements();
//		}
//		
//		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
//				
//		model.addAttribute("currentPage", pageNum);
//		model.addAttribute("totalPages", page.getTotalPages());
//		model.addAttribute("startCount", startCount);
//		model.addAttribute("endCount", endCount);
//		model.addAttribute("totalItems", page.getTotalElements());
//		model.addAttribute("sortField", "name");
//		model.addAttribute("sortDir", sortDir);
//		model.addAttribute("reverseSortDir", reverseSortDir);
//		model.addAttribute("keyword", keyword);
//		model.addAttribute("listBrands", listBrands);
//		model.addAttribute("moduleURL", "/brands");
		
		return "brands/brands";
	}
	
//	@GetMapping("/brands")
//	public String listAll(Model model) {
//		List<Brand> listBrands = brandService.listAll();
//		model.addAttribute("listBrands", listBrands);
//		
//		return "brands/brands";
//	}
	
	@GetMapping("/new")
	public String newBrand(Model model) {
		List<Category> listCategories = categoryService.listCategoriesUsedInForm();
		
		model.addAttribute("listCategories", listCategories);
		model.addAttribute("brand", new Brand());
		model.addAttribute("pageTitle", "Create New Brand");

		return "brands/brand_form";
	}
	
	@PostMapping("/save")
	public String saveBrand(Brand brand,
			@RequestParam("fileImage") MultipartFile multipartFile,
			RedirectAttributes ra) throws IOException {
		if (!multipartFile.isEmpty()) {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			brand.setLogo(fileName);
			
			Brand savedBrand = brandService.save(brand);
			String uploadDir = "../brand-logos/" + savedBrand.getId();
			
			FileUploadUtil.cleanDir(uploadDir);
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		}else {
			brandService.save(brand);
		}
		ra.addFlashAttribute("message", "The brand has been saved successfully.");
		return "redirect:/brands";
	}
	
	@GetMapping("/edit/{id}")
	public String editBrand(@PathVariable Integer id,
			Model model,
			RedirectAttributes ra) {

		try {
			Brand brand = brandService.get(id);
			List<Category> listCategories = categoryService.listCategoriesUsedInForm();

			model.addAttribute("brand", brand);
			model.addAttribute("listCategories", listCategories);
			model.addAttribute("pageTitle", "Edit Brand (ID: " + id + ")");
			
			return "brands/brand_form";
		} catch (BrandNotFoundException ex) {
			ra.addFlashAttribute("message", ex.getMessage());
			return "redirect:/brands";
		}
	}
	
	@GetMapping("/delete/{id}")
	public String deleteBrand(@PathVariable Integer id,
			Model model,
			RedirectAttributes redirectAttributes) {
		try {
			brandService.delete(id);
			String brandDir = "../brand-logos/" + id;
			FileUploadUtil.removeDir(brandDir);
			
			redirectAttributes.addFlashAttribute("message", 
					"The brand ID " + id + "has been deleted successfully");
		} catch (BrandNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
		}
		
		return "redirect:/brands";
	}
	
	@GetMapping("/export/csv")
	public void exportToCSV(HttpServletResponse response) throws IOException {
		List<Brand> listBrands = brandService.listAll();
		BrandCsvExporter exporter = new BrandCsvExporter();
		exporter.export(listBrands, response);
	}
}
