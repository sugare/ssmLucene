package com.song.utils.lucene;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.song.po.Blog;

/** 
* @author ���� E-mail: Sugare
* @version ����ʱ�䣺2017��12��23�� ����12:17:40 
* @description ˵����BlogDocumentTest.java
*/
public class BlogDocumentTest {

	@Before
	public void setUp() throws Exception {
		Blog blog = new Blog();
		blog.setId(4);
		blog.setTitle("����");
		blog.setContent("������ÿ�");
		//LuceneUtils.addIndex(blog);
	}

	@Test
	public void test() {
		LuceneUtils.searcherIndexByCondition("����" , "����");
	}

}
