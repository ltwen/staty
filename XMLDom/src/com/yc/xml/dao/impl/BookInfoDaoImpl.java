package com.yc.xml.dao.impl;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import com.yc.xml.dao.IBookInfoDao;
import com.yc.xmldom.entity.BookInfo;

public class BookInfoDaoImpl implements IBookInfoDao{

	@Override
	public List<BookInfo> find() {
		List<BookInfo> list=new ArrayList<BookInfo>();
		//1.创建一个文档构建器工厂，解析对象
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		//2.通过工厂生成DOM树
		DocumentBuilder builder=null;
		Document doc=null;
		try {
			 builder=factory.newDocumentBuilder();
			 doc=builder.parse(new File("books.xml")); 
			 
			 System.out.println(doc.getDocumentElement());
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static void main(String[] args) {
		BookInfoDaoImpl book=new BookInfoDaoImpl();
		book.find();
	}
}

