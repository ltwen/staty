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
import org.xml.sax.SAXException;

import com.yc.xmldom.entity.BookInfo;

public class BookInfoDaoImplxml {
	public void find(){
		List<BookInfo> list=new ArrayList<BookInfo>();
		//1.创建一个文档构建器工厂
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=null;
		Document doc=null;
		try {
			//2.通过工厂生成DOM树
			 builder=factory.newDocumentBuilder();
			 doc=builder.parse(new File("books.xml"));
			// doc=builder.parse( this.getClass().getClassLoader().getResource("books.xml").getFile());		 
			 
			 parseElement(doc.getDocumentElement());
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void parseElement(Element e1){
		//获取元素的名字
		String tagName=e1.getNodeName();
		System.out.print("<"+tagName);
		//获取元素的所有的属性
		NamedNodeMap map=e1.getAttributes();
		
		//循环取出属性名和属性值
		if(map!=null){
			Attr attr=null;
			for(int i=0,len=map.getLength();i<len;i++){
				attr=(Attr)map.item(i);
				System.out.print(" "+attr.getName()+"="+attr.getValue());
			}
		}
		System.out.print(">");
		
		//获取子节点
		NodeList children=e1.getChildNodes();
		
		//循环判断显示子节点
		Node node=null;
		short nodeType;
		Comment comment;
		for(int i=0,len=children.getLength();i<len;i++){
			node=children.item(i);
			nodeType=node.getNodeType();
			if(nodeType==Node.ELEMENT_NODE){              //说明是元素节点
				parseElement((Element)node);
			}else if(nodeType==Node.TEXT_NODE){       //说明是文本节点
				System.out.print(node.getNodeValue());
			}else if(nodeType==Node.COMMENT_NODE){  //说明是注释
				System.out.print("<!--");
				comment=(Comment)node;
				System.out.print(comment.getData()+"-->");
			}
		}
		System.out.print("</"+tagName+">");
	}
	
	public static void main(String[] args) {
		BookInfoDaoImplxml doc=new BookInfoDaoImplxml();
		doc.find();
	}
	
}
