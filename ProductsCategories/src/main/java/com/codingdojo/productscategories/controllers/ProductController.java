package com.codingdojo.productscategories.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.productscategories.models.CategoryProduct;
import com.codingdojo.productscategories.models.Product;
import com.codingdojo.productscategories.services.StoreServices;

@Controller
@RequestMapping("/products")
public class ProductController {
	
	private final StoreServices sS;

	public ProductController(StoreServices sS) {
		this.sS = sS;
	}

	//show form: create new product
	@GetMapping("/new")
	//"productObj" = instance of the product = object we are trying to catch from JSP
	public String newProduct(@ModelAttribute("productObj") Product product) {
		return "/products/newProduct.jsp";
	}
	
	//process: create new product
	@PostMapping("/new")
	public String createProduct(@Valid @ModelAttribute("productObj") Product product, BindingResult result, RedirectAttributes errors) {
		if(result.hasErrors()) {
			errors.addFlashAttribute("errors", result.getAllErrors());
			//need to redirect, otherwise errors will not show
			return "redirect:/products/new";
		} else {
			//create product			
//			sS.createProduct(product);
			Product p = sS.createProduct(product);
			return "redirect:/products/" + p.getId();
		}
	}
	//show product by ID
	@GetMapping("/{productId}")
	public String showProduct(@ModelAttribute("cpObj") CategoryProduct cp, @PathVariable("productId") Long id, Model model) {
		Product p = sS.getProductById(id);
		model.addAttribute("product", p);
		model.addAttribute("availableCategories", sS.getAvailableCategories(p));
		System.out.println("Hit here");
		return "/products/showProduct.jsp";
	}
	
	//process: add categories to a specific product (showProduct.jsp)
	@PostMapping("/{productId}")
	public String addCategory(@ModelAttribute("cpObj") CategoryProduct cp) {
		System.out.println(cp.getCategory().getName());
		System.out.println(cp.getProduct().getName());
		sS.joinCategoryAndProduct(cp);
		return "redirect:/products/" + cp.getProduct().getId();
	}
}