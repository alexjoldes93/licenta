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
	
	private Element root;
	
	public JDOMParser(String file){
		File f =new File(file);
		org.jdom2.Document jdomDoc;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		Document doc = null;
		try {
			db = dbf.newDocumentBuilder();
			doc = db.parse(f);
			DOMBuilder ddb = new DOMBuilder();
			jdomDoc = ddb.build(doc);
			
			this.root = jdomDoc.getRootElement();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Position parseForPositions() throws ParserConfigurationException, SAXException{
		Position positionRead = new Position();
		
		List<Element> children = root.getChildren();
		
		for(Element child:children){
			if(child.getName().equals("procents")){
				List<Element> allChildren = child.getChildren();
				for(int i =0;i<allChildren.size();i++)
				{
					//System.out.println(child.getText());
					positionRead.getElements().add(Double.parseDouble(allChildren.get(i).getText()));
				}
			}
		}
		
		return positionRead;
	}
	
	public String parseForArticleName(){
		String name="";
		
		List<Element> children = root.getChildren();
		
		for(Element child:children){
			if(child.getName().equals("details")){
				List<Element> allChildren = child.getChildren();
				for(Element e:allChildren){
					if(e.getName().equals("articleName")){
						name=e.getText();
					}
				}
			}
		}
		
		return name;
	}

}
