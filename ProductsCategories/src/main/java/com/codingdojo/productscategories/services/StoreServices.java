package com.codingdojo.productscategories.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.productscategories.models.Category;
import com.codingdojo.productscategories.models.CategoryProduct;
import com.codingdojo.productscategories.models.Product;
import com.codingdojo.productscategories.repositories.CategoryProductRepo;
import com.codingdojo.productscategories.repositories.CategoryRepo;
import com.codingdojo.productscategories.repositories.ProductRepo;

@Service
public class StoreServices {
	private final CategoryRepo cR;
	private final ProductRepo pR;
	private final CategoryProductRepo cpR;
	
	public StoreServices(CategoryRepo cR, ProductRepo pR, CategoryProductRepo cpR) {
		this.cR = cR;
		this.pR = pR;
		this.cpR = cpR;
	}
	
	public List<Product> getAllProducts(){
		return (List<Product>) pR.findAll();
	}
	
	public List<Category> getAllCategories() {
		return (List<Category>) cR.findAll();
	}
	
	//changed return from void to Category so we could we could include as redirect
	public Product createProduct(Product product) {
		return pR.save(product);
	}
	
	//changed return from void to Category so we could we could include as redirect
	public Category createCategory(Category category) {
		return cR.save(category);
	}
	
	public Product getProductById(Long id) {
		Optional<Product> optionalProduct = pR.findById(id);
		if(optionalProduct.isPresent()) {
			return optionalProduct.get();
		} else {
			return null;
		}
	}
	
	public List<Category> getAvailableCategories(Product p) {
		List<Category> currentCats = p.getCategories();
		List<String> names = new ArrayList<String>();
		
		if(currentCats.isEmpty()) {
			names.add("");
		}
		for(Category c : currentCats) {
			names.add(c.getName());
		}
		return cR.findByNameNotIn(names);
	}
	
	public CategoryProduct joinCategoryAndProduct(CategoryProduct cp) {
		return cpR.save(cp);
	}

	public Category getCategoryById(Long id) {
		Optional<Category> optionalCategory = cR.findById(id);
		if(optionalCategory.isPresent()) {
			return optionalCategory.get();
		} else {
			return null;
		}
	}

	public List<Product> getAvailableProducts(Category c) {
		List<Product> currentProds = c.getProducts();
		List<String> names = new ArrayList<String>();
		
		if(currentProds.isEmpty()) {
			names.add("");
		}
		for(Product p : currentProds) {
			names.add(p.getName());
		}
		return pR.findByNameNotIn(names);
	}
		
}
