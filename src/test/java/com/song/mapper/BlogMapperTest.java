package com.song.mapper;

import org.junit.Before;
import org.junit.Test;

/** 
* @author ���� E-mail: Sugare
* @version ����ʱ�䣺2017��12��23�� ����6:36:31 
* @description ˵����BlogMapperTest.java
*/
public class BlogMapperTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		BlogMapper blogMapper = null;
		System.out.println(blogMapper.selectByPrimaryKey(1));
		
	}

}
