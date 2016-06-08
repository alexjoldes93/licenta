package RandomGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import decisionTreeClassifier.RandomWords;
import decisionTreeClassifier.Reader;
import model.Article;

public class ArticleInitialization {
	
	private Article article;
	
	public ArticleInitialization(Article article){
		this.article=article;
	}
	
	public Article fillArticleCharacteristicValues(String name){
		
		//intialize article name
		this.article.setName("Article_"+name);
		
		Random rand = new Random();
		int  n = rand.nextInt(100);
		System.out.println("\n"+n);
		this.article.setProcentArticleSize(n);
		/*
		n = rand.nextInt(100);
		if(n>=50){
			this.article.setProcentArticleTagged(100);
		}else
		{
			this.article.setProcentArticleTagged(0);
		}
		
		*/
		n = rand.nextInt(100);
		System.out.println("\n"+n);
		this.article.setProcentAuthorInformation(n);
		
		n = rand.nextInt(100);
		System.out.println("\n"+n);
		this.article.setProcentClearOfStruture(n);
		
		n = rand.nextInt(100);
		System.out.println("\n"+n);
		this.article.setProcentLanguageProblems(n);
		
		n = rand.nextInt(100);
		System.out.println("\n"+n);
		this.article.setProcentLeadSectionUnderstandable(n);
		
		n = rand.nextInt(100);
		this.article.setProcentProportionParagraphsWithCitations(n);
		
		n = rand.nextInt(100);
		this.article.setProcentProportionRegisteredEditorsWithEditFrequency(n);
		
		n = rand.nextInt(100);
		this.article.setProcentProportionUnregisteredEditorsWithSingleEdit(n);
		
		n = rand.nextInt(100);
		this.article.setProcentReferencesToReliableSources(n);
		
		n = rand.nextInt(100);
		this.article.setProcentRevisionCount(n);
		
		n = rand.nextInt(100);
		this.article.setProcentRevisionHistory(n);
		
		//words
		RandomWords randomWords= new RandomWords();
		int nrOfAttributes=4;
		Reader reader = new Reader();
		reader.createAttributesName(nrOfAttributes);
		List<String> attributesName=reader.getAttributesNames();
		List<String> wordsForArticle=new ArrayList<String>();
		String fileName="D:\\FacultateAN4\\RepositoryLicenta\\licenta\\words.txt";
		try {
			randomWords.readFromFileWords(fileName, attributesName);
			for(int i=0;i<nrOfAttributes;i++){
				String word=randomWords.generateRadomWord(randomWords.getWords().size(), attributesName.get(i));
				System.out.println(word);
				wordsForArticle.add(word);
				
			}
			String result="";
			for(String s:wordsForArticle){
				result+=s+",";
			}
			this.article.setWords(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		n = rand.nextInt(100);
		if(n>=50){
			this.article.setProcentWwarningBanneraAtTop(100);
		}else
		{
			this.article.setProcentWwarningBanneraAtTop(0);
		}
		*/
		return article;
	}
	

}
