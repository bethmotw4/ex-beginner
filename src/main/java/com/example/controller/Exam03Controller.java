package com.example.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.Exam03Form;

@Controller
@RequestMapping("/exam03")
public class Exam03Controller {
	@Autowired
	private ServletContext application;
	
	@ModelAttribute
	public Exam03Form setUpForm() {
		return new Exam03Form();
	}
	
	@RequestMapping("")
	public String index() {
		return "exam03";
	}
	
	@RequestMapping("/result")
	public String result(Exam03Form exam03Form) {
		int item1 = exam03Form.getItem1();
		int item2 = exam03Form.getItem2();
		int item3 = exam03Form.getItem3();
		int taxExcluded = item1 + item2 + item3;
		int taxIncluded = (int)(taxExcluded * 1.08);
		application.setAttribute("taxExcluded", taxExcluded);
		application.setAttribute("taxIncluded", taxIncluded);
		return "exam03-result";
	}
}
