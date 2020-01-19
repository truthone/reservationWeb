package kr.or.connect.production.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.connect.production.service.CategoryService;

@Controller
public class CategoryController {

	@Autowired
    CategoryService categoryService;
	
	@RequestMapping(path="/mainpage")
	public String list(@RequestParam(name="start", required=false, defaultValue="0") int start,
			   ModelMap model) {
//
//		List<Category> categoryList = categoryService.categories();
//		int countAll = categoryService.getCountAll();
//		
//		model.addAttribute("categoryList", categoryList);
//		model.addAttribute("countAll", countAll);

		return "mainpage";
	}

	
}
