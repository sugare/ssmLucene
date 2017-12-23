package com.song.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;


/** 
* @author ���� E-mail: Sugare
* @version ����ʱ�䣺2017��12��6�� ����9:49:44 
* @description ˵����
*/
public class generatorMapper {
	public void generator() throws Exception{

		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		//ָ�� ���򹤳������ļ�
		File configFile = new File("src/main/resources/generatorConfig.xml"); 
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
				callback, warnings);
		myBatisGenerator.generate(null);

	} 
	public static void main(String[] args) throws Exception {
		try {
			generatorMapper generatorSqlmap = new generatorMapper();
			generatorSqlmap.generator();
			System.out.println("generator finish!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
