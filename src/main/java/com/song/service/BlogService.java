package com.song.service;

import java.util.List;

import com.song.po.Blog;

/** 
* @author ���� E-mail: Sugare
* @version ����ʱ�䣺2017��12��22�� ����4:11:38 
* @description ˵����selectByPrimaryKey.java
*/
public interface BlogService {

	public Blog selectByPrimaryKey(Integer id);
	
	public int insert(Blog record);
	
	public List<Blog> searchByCondition(String title, String content);
	
	public List<Blog> selectAll();
}
