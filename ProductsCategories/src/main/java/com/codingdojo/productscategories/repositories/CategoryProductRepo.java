package com.codingdojo.productscategories.repositories;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.productscategories.models.CategoryProduct;

public interface CategoryProductRepo extends CrudRepository<CategoryProduct, Long> {

}
