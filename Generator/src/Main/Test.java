package Main;

import RandomGenerator.ArticleInitialization;
import XMLGenerate.XMLDocumentCreate;
import model.Article;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Article a = new Article();
		int n=500;
		for(int i=0;i<n;i++){
		
		ArticleInitialization init = new ArticleInitialization(a);
		
		a = init.fillArticleCharacteristicValues(Integer.toString(i));
		
		XMLDocumentCreate doc = new XMLDocumentCreate(a);
		
			doc.createXmlDocument("src\\Main\\file"+i+".xml");
		}
	}

}
