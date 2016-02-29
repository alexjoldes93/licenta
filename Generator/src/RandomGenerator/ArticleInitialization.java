package RandomGenerator;

import java.util.Random;

import model.Article;

public class ArticleInitialization {
	
	private Article article;
	
	public ArticleInitialization(Article article){
		this.article=article;
	}
	
	public Article fillArticleCharacteristicValues(){
		Random rand = new Random();
		int  n = rand.nextInt(100);
		System.out.println("\n"+n);
		this.article.setProcentArticleSize(n);
		
		n = rand.nextInt(100);
		if(n>=50){
			this.article.setProcentArticleTagged(100);
		}else
		{
			this.article.setProcentArticleTagged(0);
		}
		
		
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
		
		n = rand.nextInt(100);
		if(n>=50){
			this.article.setProcentWwarningBanneraAtTop(100);
		}else
		{
			this.article.setProcentWwarningBanneraAtTop(0);
		}
		
		return article;
	}
	

}
