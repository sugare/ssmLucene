package com.song.service;

import java.util.List;

import com.song.po.Blog;

/** 
* @author 作者 E-mail: Sugare
* @version 创建时间：2017年12月22日 下午4:11:38 
* @description 说明：selectByPrimaryKey.java
*/
public interface BlogService {

	public Blog selectByPrimaryKey(Integer id);
	
	public int insert(Blog record);
	
	public List<Blog> searchByCondition(String title, String content);
}
