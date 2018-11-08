package com.codingdojo.productscategories.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.productscategories.models.Category;

public interface CategoryRepo extends CrudRepository<Category, Long> {
	List<Category> findAll();  
	List<Category> findByNameNotIn(List<String> names);
}
