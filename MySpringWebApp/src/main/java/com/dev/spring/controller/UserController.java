package com.dev.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dev.spring.beans.User;
import com.dev.spring.service.UserServices;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserServices services;
	
	@RequestMapping(value="/getUser", method=RequestMethod.GET)
	public ModelAndView getUserById(HttpServletRequest req, 
			ModelAndView mv){
		Integer id = Integer.parseInt(req.getParameter("id"));
		User user = services.searchUser(id);
		System.out.println(user);
		mv.addObject("user", user); //req.setAttribute("user",user)
		mv.setViewName("user");
		return mv;
	}
	@RequestMapping(value = "/add",method = RequestMethod.GET)
	public String getAddUserPage()
	{
		return "addUser";
	}
	
	@RequestMapping(value= "/add",method = RequestMethod.POST)
	public ModelAndView addUser(HttpServletRequest req , ModelAndView mv)
	{
		User user = new User();
		user.setFirstName(req.getParameter("firstName"));
		user.setLastName(req.getParameter("lastName"));
		user.setPassword(req.getParameter("password"));
		System.out.println(user);
		Boolean b = services.createUser(user);
		System.out.println(b);
		String msg ="Profile Creation Failed";
		if(b)
		{
		msg = "Profile Created Succesfull";
		mv.addObject("msg", msg);
		mv.setViewName("msg");
		return mv;
		}
		else
		{
			return mv;
		}
		
	}
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public String getLoginPage()
	{
		return "login";
	}
	@RequestMapping(value = "/login" , method = RequestMethod.POST)
	public ModelAndView loginUser(HttpServletRequest req, ModelAndView mv)
	{
		Integer userId = Integer.parseInt(req.getParameter("userId"));
		String password = req.getParameter("password");
		User user = services.login(userId, password);
		if(user != null)
		{
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			mv.setViewName("redirect:./userHome");
		}
		else
		{
			mv.setViewName("redirect:./login");
		}
		return mv;
	}
	@GetMapping("/userHome")
	public String userHomePage(){
		return "userHome";
	}
}
