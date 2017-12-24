package com.song.utils.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

import com.song.po.Blog;

/** 
* @author ���� E-mail: Sugare
* @version ����ʱ�䣺2017��12��23�� ����11:33:32 
* @description ˵����Blog2Document.java
*/
public class BlogDocument {
	
	/**
	 * @name Blog2Document
	 * @description �� Blog ����תΪ Document����
	 * @param Blog
	 * @return Document
	 */
	public static Document Blog2Document(Blog blog) {
		Document document = new Document();
	
		document.add(new IntField("id", blog.getId(), Store.YES)); 
		document.add(new StringField("title", blog.getTitle(), Store.YES));
		document.add(new TextField("content", blog.getContent(), Store.YES));

		return document;
	}
	
	/**
	 * @name Document2Blog
	 * @description �� Document ����תΪ Blog����
	 * @param Document
	 * @return Blog
	 */
	public static Blog Document2Blog(Document document) {
		Blog blog = new Blog();
		blog.setId(Integer.parseInt(document.get("id")));
		blog.setTitle(document.get("title"));
		blog.setContent(document.get("content"));
		
		return blog;
	}
	
}
