package com.library.crud.LibraryCrudM.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.library.crud.LibraryCrudM.constants.Constants;
import com.library.crud.LibraryCrudM.bean.User;
import com.library.crud.LibraryCrudM.service.UserService;

import com.library.crud.LibraryCrudM.dao.ProductDao;

import com.library.crud.LibraryCrudM.bean.Product;

@Controller
public class MainController {
	

	@Autowired
	UserService userService;
	
	@Autowired
	private ProductDao productDao;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String startPage() {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView getLogin() {
		  
        return new ModelAndView("redirect:login");
    }
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ModelAndView welcomePage(ModelMap model,@ModelAttribute("product") Product product,@RequestParam String userId) 
	{
		ModelAndView modelAndView = new ModelAndView("user");
		model.put("userId", product.getUser());
		List<Product> products=productDao.getProductList(userId);
		model.addAttribute("product",products);
		return modelAndView;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginPage(ModelMap model, @RequestParam String userId, @RequestParam String password) 
	{
		User user = userService.getUserByUserId(userId);
		 ModelAndView modelAndView = new ModelAndView("login");
	     modelAndView = new ModelAndView("redirect:user");
		System.out.println("User ye ha controller pe: "+user);
		if (user.getPassword().equals(password)) 
		{
				model.put("userId", userId);
				List<Product> products=productDao.getProductList(userId);
				model.addAttribute("product",products);
		        modelAndView.addObject("userId", userId);
				return modelAndView;
		}
		model.put("errorMsg", "Please provide the correct userid and password");
		return modelAndView;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerPage(Model model) 
	{
		User user = new User();
		model.addAttribute("user", user);
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") User user, ModelMap model) 
	{
		String out = userService.createNewUser(user);
		if (out.equals(Constants.REGISTER_FAILED)) {
			model.put("errorMsg", "Some issue occured with registration");
			return "register";
		} 
		else if (out.equals(Constants.REGISTER_FAILED_DUPLICATE_USERID)) {
			model.put("errorMsg", Constants.REGISTER_FAILED_DUPLICATE_USERID);
			return "register";
		}
		model.put("successMsg", "User created successfully!!");
		return "login";
	}
}
