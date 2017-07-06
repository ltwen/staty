package com.yc.xml.dao.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.yc.xmldom.entity.NewsObj;

public class News {
	@Test
	public void showNews(){
		InputStream is=null;
		OutputStream os=null;
		try {
			URL url=new URL("  http://api.avatardata.cn/GuoNeiNews/Query?key=867fce5ba5c34752888571aaca8cac97&page=1&rows=10&dtype=xml");
			URLConnection connection=url.openConnection();//打开链接对象
			connection.connect();//连接到远程服务器
			is=connection.getInputStream();
			
			byte[] bt=new byte[1024];
			int len=-1;
			File file=new File("news.xml");
			os=new FileOutputStream(file,true);
			while( (len=is.read(bt))!=-1){
				os.write(bt,0,len);
				System.out.println(new String(bt,0,len));
			}
			os.flush();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(os!=null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Test
	public void downPic(){
		InputStream is=null;
		OutputStream os=null;
		try {
			URL url=new URL("http://ppt.downhot.com/d/file/p/2014/08/12/9d92575b4962a981bd9af247ef142449.jpg");
			URLConnection connection=url.openConnection();
			connection.connect();
			is=connection.getInputStream();
			
			byte[] bt=new byte[1024];
			int len=-1;
			
			File file=new File("E:\\2.jpg");
			os=new FileOutputStream(file,true);
			System.out.println(os);
			while( (len=is.read(bt))!=-1){
				os.write(bt,0,len);
			}
			os.flush();
			System.out.println("下载完成");
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(os!=null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


	public List<NewsObj> newsdom(){
		List<NewsObj> objs=new ArrayList<NewsObj>();
		try {
			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
			DocumentBuilder builder=factory.newDocumentBuilder();
			Document doc=builder.parse(new File("news.xml"));
			NodeList list=doc.getElementsByTagName("NewsObj");
			
			Element et;
			NewsObj obj=null;
			for(int i=0;i<list.getLength();i++){
				et=(Element) list.item(i);
				obj=new NewsObj();
				obj.setCtime(getInfo(et,"ctime"));
				obj.setDescription(getInfo(et,"description"));
				obj.setTitile(getInfo(et,"titile"));
				obj.setPicUrl(getInfo(et,"picUrl"));
				obj.setUrl(getInfo(et,"url"));
				objs.add(obj);
				System.out.println(obj);
			}
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return objs;
	}
	public String getInfo(Element et,String tagName){
		
		return et.getElementsByTagName(tagName).item(0).getFirstChild().getNodeValue().trim();
		
	}
	
	
	//文件的写入
	public void addNode(List<NewsObj> list){
		SAXReader reader=new SAXReader();
		org.dom4j.Document doc=null;
		File file=new File("new.xml");
		XMLWriter out=null;
		try {
			doc=reader.read(file);
			out=new XMLWriter(new FileWriter(file));
			for(NewsObj obj:list){
				org.dom4j.Element el=doc.getRootElement().addElement("News");
				el.addElement("title").setText(obj.getTitile());
				el.addElement("description").setText(obj.getDescription());
				el.addElement("ctime").setText(obj.getCtime());
				el.addElement("picUrl").setText(obj.getPicUrl());
				el.addElement("url").setText(obj.getUrl());
				out.write(doc);
			}
			out.flush();
			System.out.println("写入完成。。。");
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	public static void main(String[] args) {
	News n=new News();
//		List<NewsObj> list=n.newsdom();
//		List<NewsObj> list1=new ArrayList<NewsObj>();
//		//list1.add(list.get(0));
//		n.addNode(list);
	n.downPic();
	}
	
	
	

}
