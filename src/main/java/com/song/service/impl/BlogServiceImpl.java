package com.song.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.song.mapper.BlogMapper;
import com.song.po.Blog;
import com.song.service.BlogService;
import com.song.utils.lucene.LuceneUtils;

/** 
* @author 作者 E-mail: Sugare
* @version 创建时间：2017年12月22日 下午4:13:49 
* @description 说明：SelcetImpl.java
*/
public class BlogServiceImpl implements BlogService {

	@Autowired
	public BlogMapper blogMapper;
	
	@Override
	public Blog selectByPrimaryKey(Integer id) {
		return blogMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Blog> searchByCondition(String title, String content) {
		List<Blog> blogList = new ArrayList<>();
		
		List<Blog> list =  LuceneUtils.searcherIndexByCondition(title, content);
		if(list!=null && list.size()>0) {
			for(Blog blog: list ) {
				Integer id = blog.getId();
				Blog b = blogMapper.selectByPrimaryKey(id);
				b.setTitle(blog.getTitle());
				b.setContent(blog.getContent());
				blogList.add(b);
			}
		}
		
		return blogList;
	}

	@Override
	public int insert(Blog record) {
		
		blogMapper.insert(record);
		LuceneUtils.addIndex(record);
		return 0;
	}

	@Override
	public List<Blog> selectAll() {

		return blogMapper.selectAll();
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		blogMapper.deleteByPrimaryKey(id);
		LuceneUtils.deleteIndex(id);
		return 0;
	}
	
	
}
