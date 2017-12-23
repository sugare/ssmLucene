package com.song.utils.lucene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;

import com.song.po.Blog;

/**
 * @author 作者 E-mail: Sugare
 * @version 创建时间：2017年12月23日 上午11:45:44
 * @description 说明：LuceneUtils.java
 */
public class LuceneUtils {

	public static void addIndex(Blog blog) {

		Directory directory = Configuration.getDirectory();
		Analyzer analyzer = Configuration.getAnalyzer();
		System.out.println(blog);
		Document document = BlogDocument.Blog2Document(blog);
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);

		try {
			IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
			indexWriter.addDocument(document);
			indexWriter.close();
			System.out.println("indexWriter is closed");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static List<Blog> searcherIndexByCondition(String title, String content) {

		List<Blog> blogList = new ArrayList<Blog>();
		IndexReader indexReader = null;
		IndexSearcher indexSearcher = null;
		Analyzer analyzer = Configuration.getAnalyzer();
		
		try {
			indexReader = DirectoryReader.open(Configuration.getDirectory());
			indexSearcher = new IndexSearcher(indexReader);
			
			QueryParser parser = new MultiFieldQueryParser( new String[]{"title","content"}, analyzer );
			Query query = parser.parse(title+content);
			
			
			TopDocs topDocs = indexSearcher.search(query, 10);
			System.out.println("查询的总记录数：" + topDocs.totalHits);
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;

			Formatter formatter = new SimpleHTMLFormatter("<font color='red'><b>", "</b></font>");
			Scorer scorer = new QueryScorer(query);
			Highlighter highlighter = new Highlighter(formatter,scorer);
			
			int fragmentSize = 20;
			Fragmenter fragmenter = new SimpleFragmenter(fragmentSize);
			highlighter.setTextFragmenter(fragmenter);
			
			if(scoreDocs!=null && scoreDocs.length>0){
				for(ScoreDoc scoreDoc: scoreDocs){
					System.out.println("相关度得分："+scoreDoc.score);
					
					// 通过 doc的编号来创建 document
					Document document = indexSearcher.doc(scoreDoc.doc);

					String titleHighlighter = highlighter.getBestFragment(Configuration.getAnalyzer(), "title", document.get("title"));

					if(titleHighlighter == null){
						titleHighlighter = document.get("title");
						
						if(titleHighlighter !=null && titleHighlighter.length() > fragmentSize){
							titleHighlighter = titleHighlighter.substring(0,fragmentSize);
						}
					}

					document.removeField("title");
					document.add(new StringField("title", titleHighlighter, Store.YES));
					
					String contentHighlighter = highlighter.getBestFragment(Configuration.getAnalyzer(), "content", document.get("content"));
					
					if(contentHighlighter == null){
						contentHighlighter = document.get("content");
						
						if(contentHighlighter !=null && contentHighlighter.length() > fragmentSize){
							contentHighlighter = contentHighlighter.substring(0,fragmentSize);
						}
					}

					document.removeField("content");
					document.add(new StringField("content", contentHighlighter, Store.YES));
					
					Blog blog = BlogDocument.Document2Blog(document);
					blogList.add(blog);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				indexReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return blogList;
	}

}
