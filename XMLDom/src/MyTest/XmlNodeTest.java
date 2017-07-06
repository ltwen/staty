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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.yc.xml.dao.IBookInfoDao;
import com.yc.xmldom.entity.BookInfo;

public class XmlNodeTest implements IBookInfoDao {

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
			
			Element e=doc.getDocumentElement();
			NodeList nl=e.getChildNodes();
			System.out.println(nl.getLength());
			
			
			
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		 return list;
	}
	
	public static void main(String[] args) {
		XmlNodeTest test=new XmlNodeTest();
		test.find();
	}

}
