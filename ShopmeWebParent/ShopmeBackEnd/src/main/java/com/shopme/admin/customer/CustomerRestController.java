package com.shopme.admin.customer;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerRestController {

	private final CustomerService service;

	CustomerRestController(CustomerService service) {
		this.service = service;
	}
	
	@PostMapping("/customers/check_email")
	public String checkDuplicateEmail(Integer id, String email) {
		if (service.isEmailUnique(id, email)) {
			return "OK";
		} else {
			return "Duplicated";
		}	
	}
}
