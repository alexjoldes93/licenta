package XMLGenerate;
import org.jdom2.*;
import org.jdom2.output.*;

import model.Article;

import java.io.FileWriter;
import java.io.IOException;

public class XMLDocumentCreate {
	
	private Article article;
	
	public XMLDocumentCreate (Article article){
		this.article=article;
	}
	
	public void createXmlDocument(String filePath){
		try{
			Document XMLDoc = new Document();
			Element root = new Element("article");
			XMLDoc.setRootElement(root);
			
			Element procents = new Element("procents");
			Element details = new Element("details");
			Element domainWords = new Element("domainWords");
			
			
			
			Element name= new Element("articleName");
			name.addContent(String.valueOf(this.article.getName()));
			
			details.addContent(name);
			
			Element element1 = new Element("procentLeadSectionUnderstandable");
			element1.addContent(String.valueOf(this.article.getProcentLeadSectionUnderstandable()));
			
			Element element2 = new Element("procentClearOfStruture");
			element2.addContent(String.valueOf(this.article.getProcentClearOfStruture()));
			
			Element element3 = new Element("procentReferencesToReliableSources");
			element3.addContent(String.valueOf(this.article.getProcentReferencesToReliableSources()));
			
			//Element element4 = new Element("procentWwarningBanneraAtTop");
			//element4.addContent(String.valueOf(this.article.getProcentWwarningBanneraAtTop()));
			
			Element element5 = new Element("procentLanguageProblems");
			element5.addContent(String.valueOf(this.article.getProcentLanguageProblems()));
			
			//Element element6 = new Element("procentArticleTagged");
			//element6.addContent(String.valueOf(this.article.getProcentArticleTagged()));
			
			Element element7 = new Element("procentRevisionHistory");
			element7.addContent(String.valueOf(this.article.getProcentRevisionHistory()));
			
			Element element8 = new Element("procentAuthorInformation");
			element8.addContent(String.valueOf(this.article.getProcentAuthorInformation()));
			
			Element element9 = new Element("procentProportionParagraphsWithCitations");
			element9.addContent(String.valueOf(this.article.getProcentProportionParagraphsWithCitations()));
			
			Element element10 = new Element("procentArticleSize");
			element10.addContent(String.valueOf(this.article.getProcentArticleSize()));
			
			Element element11 = new Element("procentProportionUnregisteredEditorsWithSingleEdit");
			element11.addContent(String.valueOf(this.article.getProcentProportionUnregisteredEditorsWithSingleEdit()));
			
			Element element12 = new Element("procentProportionRegisteredEditorsWithEditFrequency");
			element12.addContent(String.valueOf(this.article.getProcentProportionRegisteredEditorsWithEditFrequency()));
			
			Element element13 = new Element("procentRevisionCount");
			element13.addContent(String.valueOf(this.article.getProcentRevisionCount()));
			
			Element element14 = new Element("words");
			element14.addContent(String.valueOf(this.article.getWords()));
			domainWords.addContent(element14);
			
			procents.addContent(element1);
			procents.addContent(element2);
			procents.addContent(element3);
			//root.addContent(element4);
			procents.addContent(element5);
			//root.addContent(element6);
			procents.addContent(element7);
			procents.addContent(element8);
			procents.addContent(element9);
			procents.addContent(element10);
			procents.addContent(element11);
			procents.addContent(element12);
			procents.addContent(element13);
			
			
			root.addContent(details);
			root.addContent(procents);
			root.addContent(domainWords);
			
			
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(XMLDoc, new FileWriter(filePath));
			//xmlOutput.output(XMLDoc, new FileWriter("src\\JDOM\\file.xml"));
			System.out.println("File Saved!");
		}catch (IOException io) {
			System.out.println(io.getMessage());
		  }
		
	}
	
}
