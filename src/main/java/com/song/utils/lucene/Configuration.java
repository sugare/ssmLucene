package com.song.utils.lucene;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;


/** 
* @author 作者 E-mail: Sugare
* @version 创建时间：2017年12月23日 上午10:41:10 
* @description 说明：Configuration.java
*/
public class Configuration {
	private static Directory directory;
	
	private static Analyzer analyzer;
	
	private static String indexDir = "D:\\Java练习\\mvn_test\\crmv1\\indexDir";
	
	static {
		try {
			directory = FSDirectory.open(Paths.get(indexDir));
			analyzer = new SmartChineseAnalyzer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Directory getDirectory() {
		return directory;
	}
	
	public static Analyzer getAnalyzer() {
		return analyzer;
	}
}
