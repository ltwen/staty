package MyTest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.yc.xml.dao.IBookInfoDao;
import com.yc.xmldom.entity.BookInfo;

public class BookInfoDaoImplTest implements IBookInfoDao {

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
			//doc=builder.parse( this.getClass().getClassLoader().getResource("books.xml").getFile());		 
			 
			 NodeList nodeList=doc.getElementsByTagName("Book");
			 Element node=null;
			 BookInfo bf=null;
			 //nodeType:1为元素节点  2为属性节点  3为文本节点
			 for(int i=0,len=nodeList.getLength();i<len;i++){
				 bf=new BookInfo();
				 node=(Element) nodeList.item(i);
				 
				 
				 bf.setBookName(getInfo(node,"bookName"));
				 bf.setBookAuthor(getInfo(node,"bookAuthor"));
				 bf.setBookISBN(getInfo(node,"bookISBN"));
				 bf.setBookPrice(Double.parseDouble(getInfo(node,"bookPrice")));
				 bf.setId(Integer.parseInt(node.getAttribute("id")));
				 list.add(bf);
			 }
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public String getInfo(Element e1,String tagName){
		String str=null;
		str=e1.getElementsByTagName(tagName).item(0).getFirstChild().getNodeValue().trim();
		return str;
	}

	public static void main(String[] args){
		BookInfoDaoImplTest book=new BookInfoDaoImplTest();
		List<BookInfo> bf=book.find();
		for(BookInfo b:bf){
			System.out.println(b);
		}
	}
}
