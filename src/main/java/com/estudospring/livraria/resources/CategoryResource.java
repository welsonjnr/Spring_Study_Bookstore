package com.estudospring.livraria.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.estudospring.livraria.domain.Category;
import com.estudospring.livraria.services.CategoryService;

@RestController
@RequestMapping(value = "/categorys")
public class CategoryResource {

	@Autowired
	private CategoryService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Category obj = service.find(id);

		return ResponseEntity.ok().body(obj);
	}

}
