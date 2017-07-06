package com.yc.xml.dao.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.Attribute;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.DOMReader;
import org.dom4j.io.SAXReader;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.yc.xml.dao.IBookInfoDao;
import com.yc.xmldom.entity.BookInfo;

public class BookInfoDom4j  implements IBookInfoDao{

	@Override
	public List<BookInfo> find() {
		List<BookInfo> list=new ArrayList();
		
		try {
//			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
//			DocumentBuilder builder=factory.newDocumentBuilder();
//			Document document=builder.parse(new File("books.xml"));
//			DOMReader reader=new DOMReader();
//			//dom4j中的dom
//			org.dom4j.Document doc=reader.read(document);
			
			SAXReader reader1=new SAXReader();
			org.dom4j.Document doc=reader1.read(new File("books.xml"));
			
			Element root=doc.getRootElement();
			
			//
			Iterator iterator=root.elementIterator();
			Element bookElement;
			BookInfo bf;
			Attribute attr;
			Element et;
			
			while(iterator.hasNext()){
				bookElement=(Element) iterator.next();
				if("Book".equals(bookElement.getName())){
					bf=new BookInfo();
					
					for(Iterator attrs=bookElement.attributeIterator();attrs.hasNext();){
						attr=(Attribute) attrs.next();
						if("id".equals(attr.getName())){
							bf.setId(Integer.parseInt(attr.getValue()));
						}
						
					}
					for(Iterator ittr=bookElement.elementIterator();ittr.hasNext();){
						et=(Element) ittr.next();
						if("bookName".equals(et.getName())){
							bf.setBookName(et.getTextTrim());
						}else if("bookAuthor".equals(et.getName())){
							bf.setBookAuthor(et.getTextTrim());
						}else if("bookISBN".equals(et.getName())){
							bf.setBookISBN(et.getTextTrim());
						}else if("bookPrice".equals(et.getName())){
							bf.setBookPrice(Double.parseDouble(et.getTextTrim()));
						}
					}
					list.add(bf);
				}
			}
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return list;
	}
	public static void main(String[] args) {
		BookInfoDom4j book=new BookInfoDom4j();
		List<BookInfo> list=book.find();
		for(BookInfo b:list){
			System.out.println(b);
		}
	}

	
}
