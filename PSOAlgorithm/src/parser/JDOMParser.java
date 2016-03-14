package parser;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import model.Position;

import org.jdom2.Element;
import org.jdom2.input.DOMBuilder;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class JDOMParser {
	
	public JDOMParser(){
		
	}
	
	public Position parse(String file) throws ParserConfigurationException, SAXException{
		Position positionRead = new Position();
		
		try{
			File f =new File(file);
			org.jdom2.Document jdomDoc;
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(f);
			
			DOMBuilder ddb = new DOMBuilder();
			jdomDoc = ddb.build(doc);
			
			Element root = jdomDoc.getRootElement();
			List<Element> allChildren = root.getChildren();
			
			for(Element child : allChildren)
			{
				System.out.println(child.getText());
				positionRead.getElements().add(Double.parseDouble(child.getText()));
			}
			
			} catch (IOException io) {
				System.out.println(io.getMessage());
			  }
		
		return positionRead;
	}

}
