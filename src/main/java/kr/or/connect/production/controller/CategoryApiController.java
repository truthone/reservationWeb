package kr.or.connect.production.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.production.dto.Category;
import kr.or.connect.production.dto.Product;
import kr.or.connect.production.dto.Promotion;
import kr.or.connect.production.service.CategoryService;
import kr.or.connect.production.service.ProductService;
import kr.or.connect.production.service.PromotionService;


@RestController

public class CategoryApiController {
	
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	@Autowired
	PromotionService promotionService;
		
	@GetMapping
	@RequestMapping(path = "api/categories")
	public Map<String, Object> Categories(){
		List<Category> categoryList = categoryService.categories();
		Map<String, Object> map = new HashMap<>();
		map.put("categoryList",categoryList);
		
		return map;
		
	}
	
	@GetMapping
	@RequestMapping(path = "api/promotions")
	public Map<String, Object> Promotions(){
	    List<Promotion> promotionList = promotionService.getPromotionInfo();
	    Map<String, Object> map = new HashMap<>();
	    map.put("promotionList",promotionList);
    
    return map;
		
	}
	
	@GetMapping
	@RequestMapping(path = "api/products")
	 public Map<String, Object> Products(
			 @RequestParam(name="start", required=false, defaultValue="0") int start,		 
	         @RequestParam(name="categoryId", required=false, defaultValue="0") int categoryId){
		
		Map<String, Object> map = new HashMap<>();
		
		if(categoryId == 0) {
			int count = categoryService.getCountProductsAll();
			int pageCount = count / productService.LIMIT;
			List<Product> productList = productService.productsAll(start);
		
			if (count % productService.LIMIT > 0)
				pageCount++;
	
			List<Integer> pageStartList = new ArrayList<>();
			for (int i = 0; i < pageCount; i++) {
				pageStartList.add(i * productService.LIMIT);
			}
			
			map.put("pageStartList",pageStartList);
			map.put("productList", productList);
			map.put("totalCount", count);
			
		}else if(categoryId != 0) {
			  
			  int count = categoryService.getCountProductsByCategory(categoryId);
			  int pageCount = count / productService.LIMIT;
			  List<Product> productList = productService.productsByCategory(start, categoryId);
			  List<Integer> pageStartList = new ArrayList<>();
				
				if (count % productService.LIMIT > 0) pageCount++;
				
				for (int i = 0; i < pageCount; i++) {
					pageStartList.add(i * productService.LIMIT);
				}
				
				map.put("pageStartList",pageStartList);
				map.put("productList", productList);
				map.put("totalCount", count);
			
	  }
			return map;
	 }
	
	@GetMapping( "api/products/{displayInfoId}")
	public Map<String,Object> productDetail(@PathVariable(name="displayInfoId")int displayInfoId){
		
		Map<String, Object> map = new HashMap<>();
		
		int avarageScore = 0;
		
		return map;
	}
	
}
