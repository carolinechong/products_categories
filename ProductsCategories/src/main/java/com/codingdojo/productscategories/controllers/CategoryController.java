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

import com.codingdojo.productscategories.models.Category;
import com.codingdojo.productscategories.models.CategoryProduct;
import com.codingdojo.productscategories.services.StoreServices;

@Controller
@RequestMapping("/")
public class CategoryController {
	private final StoreServices sS;

	public CategoryController(StoreServices sS) {
		this.sS = sS;
	}

	//show index page: all categories and all products
	@RequestMapping("")
	public String index(Model model) {
		model.addAttribute("categories", sS.getAllCategories());
		model.addAttribute("products", sS.getAllProducts());
		return "/products/index.jsp";
	}
	
	//show form: create new category
	@GetMapping("/categories/new")
	public String newCategory(@ModelAttribute("categoryObj") Category category) {
		return "/categories/newCategory.jsp";
	}
	
	//process: create new category
	@PostMapping("/categories/new")
	public String createCategory(@Valid @ModelAttribute("categoryObj") Category category, BindingResult result, RedirectAttributes errors) {
		if(result.hasErrors()) {
			errors.addFlashAttribute("errors", result.getAllErrors());
			//need to redirect, otherwise errors will not show
			return "redirect:/categories/new";
		} else {
			//create category
//			sS.createCategory(category);
			Category c = sS.createCategory(category);
			return "redirect:/categories/" + c.getId();
		}
	}
	//show category by ID
	@GetMapping("/categories/{categoryId}")
	public String showCategory(@ModelAttribute("cpObj") CategoryProduct cp, @PathVariable("categoryId") Long id, Model model) {
		Category c = sS.getCategoryById(id);
		model.addAttribute("category", c);
		model.addAttribute("availableProducts", sS.getAvailableProducts(c));
		return "/categories/showCategory.jsp";
	}
	
	//process: add products to a specific category (showCategory.jsp)
	@PostMapping("/categories/{categoryId}")
	public String addProduct(@ModelAttribute("cpObj") CategoryProduct cp) {
		System.out.println(cp.getCategory().getName());
		System.out.println(cp.getProduct().getName());
		sS.joinCategoryAndProduct(cp);
		return "redirect:/categories/" + cp.getCategory().getId();
	}
	
}