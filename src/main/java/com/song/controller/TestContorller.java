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
* @author 作者 E-mail: Sugare
* @version 创建时间：2017年12月20日 下午4:01:19 
* @description 说明：TestContorller.java
*/

@Controller
public class TestContorller {
	@Autowired
	private BlogService blogService;
	
	@RequestMapping("/test")
	public String test() {
		System.out.println(blogService.selectByPrimaryKey(1));
		return "success";
	}

	@RequestMapping("/search")
	public String search() {
		return "search";
	}
	
	@RequestMapping("/searchSubmit")
	public ModelAndView searchSubmit(String title, String content) {
		//System.out.println("controller: " + title);
		//System.out.println("controller: " + content);
		
		List<Blog> blogList = blogService.searchByCondition(title, content);

		System.out.println("controller: "+blogList);
		ModelAndView mav = new ModelAndView();
		mav.addObject("blogList", blogList);
		mav.setViewName("searchSubmit");
		return mav;
	}
	
	@RequestMapping("/insert")
	public String insert() {
		return "insert";
	}
	
	@RequestMapping("/insertSubmit")
	public String insertSubmit(Blog blog) {
		System.out.println(blog.getTitle());
		System.out.println(blog.getContent());
		blogService.insert(blog);
		return "redirect:search";
		//return "insert";
	}
}
