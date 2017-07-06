package com.yc.xml.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.yc.xmldom.entity.BookInfo;

public class SaxXmlContentHandler  extends DefaultHandler{

	private List<BookInfo> list=null;
	private BookInfo bf=null;
	private String preTag=null;
	
	/**
	 * 获取所有书的信息
	 * @return
	 */
	public List<BookInfo> getBooks(){
		return list;
	}
	
	/**
	 * 文档解析开始时，会自动调用此方法
	 */
	public void startDocument() throws SAXException{
		//初始化集合，用来准备存放所有的书本信息
		list=new ArrayList<BookInfo>();
	}
	
	/**
	 * 元素解析开始时，会自动调用此方法
	 */
	public void startElement(String uri,String localName,String qName,Attributes attributes){
		//当遇到一个BOOK节点时，应该实例化一个BookINFO对象
		if("Book".equals(qName)){
			bf=new BookInfo();//实例化一个BookInfo对象，准备存放书本信息
			
			//接下来，我们还要读取Book节点的属性信息
			bf.setId(Integer.parseInt(attributes.getValue("id")));
		}
		preTag=qName;
	}
	
	/**
	 * 当遇到文本节点时会自动调用此方法
	 */
	public void characters(char[] ch,int start,int length) throws SAXException{
		
		if(bf!=null){//说明已经解析过这行
			//先取出文本值
			String content=new String(ch,start,length);
			if("bookName".equals(preTag)){
				bf.setBookName(content);
			}else if("bookAuthor".equals(preTag)){
				bf.setBookAuthor(content);
			}else if("bookISBN".equals(preTag)){
				bf.setBookISBN(content);
			}else if("bookPrice".equals(preTag)){
				bf.setBookPrice(Double.parseDouble(content));
			}
		}
	}
	
	public void endElement(String uri,String localName,String qName){
		
		if("Book".equals(qName)){
			list.add(bf);
			bf=null;
		}
		preTag=null;
	}

	
}