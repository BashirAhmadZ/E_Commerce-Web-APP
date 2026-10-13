package com.shopme.admin.brand;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopme.common.entity.Brand;
import com.shopme.common.entity.Category;


@RequestMapping("/brands")
@RestController
public class BrandRestController {

	private final BrandService service;

	BrandRestController(BrandService service) {
		this.service = service;
	}
	
	@PostMapping("/check_unique")
	public String checkUnique(Integer id, String name) {
		return service.checkUnique(id, name);	
	}
	
	@GetMapping("/{id}/categories")
	public List<CategoryDTO> listCategoryByBrand(
			@PathVariable(name = "id") Integer brandId) throws BrandNotFoundException {
		
		List<CategoryDTO> listCategories = new ArrayList<>();
		
		try {
			Brand brand = service.get(brandId);
			Set<Category> categories = brand.getCategories();
			
			for (Category category : categories) {
				CategoryDTO dto = new CategoryDTO(category.getId(), category.getName());
				listCategories.add(dto);
			}
			
			return listCategories;
		} catch (BrandNotFoundException e) {
			throw new BrandNotFoundException();
		}
		
		//return null;
	}
	
}
