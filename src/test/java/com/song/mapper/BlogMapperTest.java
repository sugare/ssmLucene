package com.song.mapper;

import org.junit.Before;
import org.junit.Test;

/** 
* @author 作者 E-mail: Sugare
* @version 创建时间：2017年12月23日 下午6:36:31 
* @description 说明：BlogMapperTest.java
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
