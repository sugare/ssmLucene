package com.song.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.song.po.Blog;
import com.song.service.BlogService;
import com.song.utils.lucene.LuceneUtils;

/** 
* @author ���� E-mail: Sugare
* @version ����ʱ�䣺2017��12��20�� ����4:01:19 
* @description ˵����TestContorller.java
*/

@Controller
public class BlogContorller {
	@Autowired
	private BlogService blogService;
		
	@RequestMapping("/search")
	public ModelAndView search(String title, String content) {

		List<Blog> blogList = blogService.searchByCondition(title, content);

		System.out.println("controller: "+blogList);
		ModelAndView mav = new ModelAndView();
		mav.addObject("blogList", blogList);
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping("/lists")
	public ModelAndView lists() {
		ModelAndView mav = new ModelAndView();
		List<Blog> blogs = blogService.selectAll();
		mav.addObject("blogs", blogs);
		mav.setViewName("lists");
		return mav;
	}
	
	@RequestMapping("/insertSubmit")
	public String insertSubmit(Blog blog) {
		System.out.println(blog.getTitle());
		System.out.println(blog.getContent());
		blogService.insert(blog);
		return "redirect:lists";
	}
}
