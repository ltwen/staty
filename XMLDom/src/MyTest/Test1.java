package MyTest;
import java.net.*;  
import java.io.*; 
public class Test1 {
	public static void main (String[] args) {
		try {
			URL u = new URL("http://www.zhujiwu.com"); 
	        InputStream in = u.openStream( );  
	        in = new BufferedInputStream(in);         
	        Reader r = new InputStreamReader(in); 
	        int c;  
	        while ((c = r.read( )) != -1) {  
	          System.out.print((char) c);
	          }   
	      }  
	      catch (MalformedURLException ex) {  
	    	  	ex.printStackTrace();  
	      }  
	      catch (IOException ex) {  
	    	  	System.err.println(ex);  
	      }  
 	 }
}
