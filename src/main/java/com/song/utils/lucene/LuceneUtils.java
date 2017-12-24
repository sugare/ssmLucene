package com.song.utils.lucene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
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
 * @author ���� E-mail: Sugare
 * @version ����ʱ�䣺2017��12��23�� ����11:45:44
 * @description ˵����LuceneUtils.java
 */
public class LuceneUtils {

	/**
	 * @name addIndex
	 * @description ���ݲ��뵽���ݿ�ͬʱ��ӵ�������
	 * @param Blog
	 */
	public static void addIndex(Blog blog) {

		Directory directory = Configuration.getDirectory();
		Analyzer analyzer = Configuration.getAnalyzer();
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
	
	/**
	 * @name searcherIndexByCondition
	 * @description ���ݴ��ݹ����� title ��  content �����м���
	 * @param title
	 * @param content
	 * @return Blog�����б�
	 */
	public static List<Blog> searcherIndexByCondition(String title, String content) {

		List<Blog> blogList = new ArrayList<Blog>();
		IndexReader indexReader = null;
		
		try {
			indexReader = DirectoryReader.open(Configuration.getDirectory());
			IndexSearcher indexSearcher = new IndexSearcher(indexReader);
			BooleanQuery.Builder booleanQuery=new BooleanQuery.Builder();
			
			if(null != title) {
				Term t1=new Term("title",title);
				Query query1=new TermQuery(t1);
				booleanQuery.add(query1,BooleanClause.Occur.MUST_NOT);
			}
			if(null != content) {
				Term t2=new Term("content", content);
				Query query2=new TermQuery(t2);
				booleanQuery.add(query2,BooleanClause.Occur.SHOULD);
			}
			
			
			if(null != booleanQuery) {
				
				TopDocs topDocs = indexSearcher.search(booleanQuery.build(), 10);
				System.out.println("��ѯ���ܼ�¼����" + topDocs.totalHits);
				ScoreDoc[] scoreDocs = topDocs.scoreDocs;
	
				Formatter formatter = new SimpleHTMLFormatter("<font color='red'><b>", "</b></font>");
				Scorer scorer = new QueryScorer(booleanQuery.build());
				Highlighter highlighter = new Highlighter(formatter,scorer);
				
				// ������ʾժҪ��С
				int fragmentSize = 20;
				Fragmenter fragmenter = new SimpleFragmenter(fragmentSize);
				highlighter.setTextFragmenter(fragmenter);
				
				if(scoreDocs!=null && scoreDocs.length>0){
					for(ScoreDoc scoreDoc: scoreDocs){
						System.out.println("��ضȵ÷֣�"+scoreDoc.score);
						
						// ͨ�� doc�ı������ȡ document
						Document document = indexSearcher.doc(scoreDoc.doc);
	
						String titleHighlighter = highlighter.getBestFragment(Configuration.getAnalyzer(), "title", document.get("title"));
	
						if(titleHighlighter == null){
							titleHighlighter = document.get("title");
							
							if(titleHighlighter !=null && titleHighlighter.length() > fragmentSize){
								titleHighlighter = titleHighlighter.substring(0,fragmentSize);
							}
						}
						// ��ԭ��������ɾ����������������ݴ���д�� document ��
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
						
						// document תΪ  Blog����
						Blog blog = BlogDocument.Document2Blog(document);
						blogList.add(blog);
						
					}
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
