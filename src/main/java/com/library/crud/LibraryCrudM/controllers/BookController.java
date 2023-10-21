package com.library.crud.LibraryCrudM.controllers;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import com.library.crud.LibraryCrudM.service.UserService;
import com.library.crud.LibraryCrudM.dao.ProductDao;
import com.library.crud.LibraryCrudM.bean.Product;

@Controller
public class BookController {
	
    @Autowired
    private DataSource dataSource;
	
	@Autowired
	UserService userService;
	
	@Autowired
	private ProductDao productDao;
	
	@RequestMapping("/error1/{userId}/{er}")
	public String error(@PathVariable("userId") String uid,@PathVariable("er") String er,Model m)
	{
		m.addAttribute("userId", uid);
		if(er.equals("em"))
		{
			m.addAttribute("errorm","Sorry, please select an author to continue");
		}
		else if(er.equals("un")) {
			m.addAttribute("errorm","Sorry, book code must be unique");
		}
		return "error";
	}
	
	@RequestMapping(value="/handle-product",method=RequestMethod.POST)
	public RedirectView handleProduct(@ModelAttribute("product") Product product, ModelMap model)
	{
		RedirectView redirectView=new RedirectView();

		String s=productDao.createProduct(product);
		if(s=="noauthor")
		{
			redirectView.setUrl("/error1/"+product.getUser()+"/em");
			return redirectView;
		}
		if(s==null)
		{
			redirectView.setUrl("/error1/"+product.getUser()+"/un");
			return redirectView;
		}
		model.put("userId", product.getUser());
		List<Product> products=productDao.getProductList(product.getUser());
		model.addAttribute("product",products);
		redirectView.setUrl("/user?userId="+product.getUser());
		return redirectView;
	}
	
	@RequestMapping("/add-product/{userId}")
	public String addProduct(@PathVariable("userId") String uid,Model m)
	{
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT name FROM author";
        List<String> authorNames = jdbcTemplate.queryForList(sql, String.class);
        m.addAttribute("authorNames", authorNames);
		m.addAttribute("userId", uid);
		m.addAttribute("title","Add Product");
		return "add_product_form";
	}
	
	@RequestMapping(value="/update/{productId}")
	public String updateForm(@PathVariable("productId") int pid,Model model, ModelMap m)
	{
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT name FROM author";
        List<String> authorNames = jdbcTemplate.queryForList(sql, String.class);
        m.addAttribute("authorNames", authorNames);
		Product product=this.productDao.getProduct(pid);
		m.put("userId", product.getUser());
		model.addAttribute("product",product);
		return "update_form";
	}
	
	@RequestMapping(value="/update-product/{productId}",method=RequestMethod.POST)
	public RedirectView updateProduct(@ModelAttribute("product") Product product,@PathVariable("productId") int pid, ModelMap model)
	{
		model.put("userId", product.getUser());
		List<Product> products=productDao.getProductList(product.getUser());
		model.addAttribute("product",products);
		RedirectView redirectView=new RedirectView();
		String s=productDao.updateProduct(product,pid);
		if(s==null)
		{
			redirectView.setUrl("/error1/"+product.getUser());
			return redirectView;
		}
		redirectView.setUrl("/user?userId="+product.getUser());
		return redirectView;
	}
	
	@RequestMapping(value="/delete/{productId}")
	public RedirectView deleteProduct(@PathVariable("productId") int productId)
	{
		Product product=this.productDao.getProduct(productId);
		RedirectView redirectView=new RedirectView();
		redirectView.setUrl("/user?userId="+product.getUser());
		this.productDao.deleteProduct(product,productId);
		return redirectView;
	}
	
}
