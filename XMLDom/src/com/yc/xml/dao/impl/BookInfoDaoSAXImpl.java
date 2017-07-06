package com.yc.xml.dao.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.yc.xmldom.entity.BookInfo;

public class BookInfoDaoSAXImpl {

	@Test
	public void find(){
		List<BookInfo> list=new ArrayList<BookInfo>();		
		try {
			//获取sax的解析工厂
			SAXParserFactory factory=SAXParserFactory.newInstance();
			//获取SAX解析器
			SAXParser parser=factory.newSAXParser();
			
			SaxXmlContentHandler handler=new SaxXmlContentHandler();
			
			parser.parse(new File("books.xml"), handler);
			
			List<BookInfo> bfs=handler.getBooks();
			for(BookInfo bf:bfs){
				System.out.println(bf);
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

}
