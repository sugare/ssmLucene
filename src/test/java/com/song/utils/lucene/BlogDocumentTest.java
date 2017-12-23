package com.song.utils.lucene;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.song.po.Blog;

/** 
* @author 作者 E-mail: Sugare
* @version 创建时间：2017年12月23日 下午12:17:40 
* @description 说明：BlogDocumentTest.java
*/
public class BlogDocumentTest {

	@Before
	public void setUp() throws Exception {
		Blog blog = new Blog();
		blog.setId(4);
		blog.setTitle("唱歌");
		blog.setContent("大连真好看");
		LuceneUtils.addIndex(blog);
	}

	@Test
	public void test() {
		LuceneUtils.searcherIndexByCondition("唱歌" , "大连");
	}

}
