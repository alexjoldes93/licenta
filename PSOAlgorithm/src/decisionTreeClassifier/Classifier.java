package decisionTreeClassifier;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Element;
import org.jdom2.input.DOMBuilder;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class Classifier {
	
	private String[] files;
	
	public Classifier(String[] filesName){
		this.files=filesName;
	}
	
	public List<String> getFilesFromDomain(String domain,String fileDataSET){
		List<String> resultFiles=new ArrayList<String>();
		int index=0;
		
		for(String s:files){
			//get the words from file
			String[] wordsFromFile=this.getWordsFromFile(s);
			
			Reader reader = new Reader();
			List<HashMap<String,String>> dataset=new ArrayList<>();
			
			//get the attributes name
			reader.createAttributesName(wordsFromFile.length);
			List<String> attributesName = reader.getAttributesNames();
			try {
				//dataset= reader.readFile("D:\\FacultateAN4\\RepositoryLicenta\\licenta\\PSOAlgorithm\\src\\decisionTreeClassifier\\dataset.txt");
				//initialize with infos from dataset file
				dataset= reader.readFile(fileDataSET);
				
				//create attributes (-1 deoarece exista doar cuvintele fara rezultat)
				//se citesc cele din fisirele de intrare
				List<Attribute> attributes = new ArrayList<Attribute>();
				for(int i=0;i<attributesName.size()-1;i++){
					Attribute a = new Attribute(attributesName.get(i),new ArrayList<>(Arrays.asList(wordsFromFile[i])));
					attributes.add(a);
				}
				
				DecisionTreeID3 id3 = new DecisionTreeID3(dataset);
				TreeNode root = id3.buildTree("result", attributes);
				id3.getResult(root);
				System.out.println("Articolul_"+index+" = "+id3.result.getName());
				if(id3.result.getName().equals(domain)){
					resultFiles.add(s);
				}
				
				index++;
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return resultFiles;
	}
	
	private String[] getWordsFromFile(String fileName){
		String[] result = null;
		
		try{
			File f =new File(fileName);
			org.jdom2.Document jdomDoc;
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(f);
			
			DOMBuilder ddb = new DOMBuilder();
			jdomDoc = ddb.build(doc);
			
			Element root = jdomDoc.getRootElement();
			List<Element> children = root.getChildren();
			
			for(Element child:children){
				if(child.getName().equals("domainWords")){
					List<Element> allChildren = child.getChildren();
					for(Element e:allChildren)
					{
						//System.out.println(child.getText());
						if(e.getName().equals("words")){
							String words = e.getText();
							result=words.split(",");
						}
						
					}
				}
			}
			
			
			} catch (IOException io) {
				System.out.println(io.getMessage());
			  } catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return result;
	}

	public String[] getFiles() {
		return files;
	}

	public void setFiles(String[] files) {
		this.files = files;
	}

}
