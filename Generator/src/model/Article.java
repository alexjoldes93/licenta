package model;

import java.util.List;

public class Article {
	
	private String name;
	
	private double procentLeadSectionUnderstandable;
	private double procentClearOfStruture;
	private double procentReferencesToReliableSources;
	//private double procentWwarningBanneraAtTop;
	private double procentLanguageProblems;
	//private double procentArticleTagged;
	private double procentRevisionHistory;
	private double procentAuthorInformation;
	private double procentProportionParagraphsWithCitations;
	private double procentArticleSize;
	private double procentProportionUnregisteredEditorsWithSingleEdit;
	private double procentProportionRegisteredEditorsWithEditFrequency;
	private double procentRevisionCount;
	private String words;
	
	public Article(){}
	
	public Article(String name,double procentLeadSectionUnderstandable, double procentClearOfStruture,
			double procentReferencesToReliableSources, 
			double procentLanguageProblems, double procentRevisionHistory,
			double procentAuthorInformation, double procentProportionParagraphsWithCitations, double procentArticleSize,
			double procentProportionUnregisteredEditorsWithSingleEdit,
			double procentProportionRegisteredEditorsWithEditFrequency, double procentRevisionCount, String words) {
		super();
		this.name=name;
		this.procentLeadSectionUnderstandable = procentLeadSectionUnderstandable;
		this.procentClearOfStruture = procentClearOfStruture;
		this.procentReferencesToReliableSources = procentReferencesToReliableSources;
		//this.procentWwarningBanneraAtTop = procentWwarningBanneraAtTop;
		this.procentLanguageProblems = procentLanguageProblems;
		//this.procentArticleTagged = procentArticleTagged;
		this.procentRevisionHistory = procentRevisionHistory;
		this.procentAuthorInformation = procentAuthorInformation;
		this.procentProportionParagraphsWithCitations = procentProportionParagraphsWithCitations;
		this.procentArticleSize = procentArticleSize;
		this.procentProportionUnregisteredEditorsWithSingleEdit = procentProportionUnregisteredEditorsWithSingleEdit;
		this.procentProportionRegisteredEditorsWithEditFrequency = procentProportionRegisteredEditorsWithEditFrequency;
		this.procentRevisionCount = procentRevisionCount;
		this.words=words;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWords() {
		return words;
	}

	public void setWords(String words) {
		this.words = words;
	}

	public double getProcentLeadSectionUnderstandable() {
		return procentLeadSectionUnderstandable;
	}

	public void setProcentLeadSectionUnderstandable(double procentLeadSectionUnderstandable) {
		this.procentLeadSectionUnderstandable = procentLeadSectionUnderstandable;
	}

	public double getProcentClearOfStruture() {
		return procentClearOfStruture;
	}

	public void setProcentClearOfStruture(double procentClearOfStruture) {
		this.procentClearOfStruture = procentClearOfStruture;
	}

	public double getProcentReferencesToReliableSources() {
		return procentReferencesToReliableSources;
	}

	public void setProcentReferencesToReliableSources(double procentReferencesToReliableSources) {
		this.procentReferencesToReliableSources = procentReferencesToReliableSources;
	}
	/*
	public double getProcentWwarningBanneraAtTop() {
		return procentWwarningBanneraAtTop;
	}

	public void setProcentWwarningBanneraAtTop(double procentWwarningBanneraAtTop) {
		this.procentWwarningBanneraAtTop = procentWwarningBanneraAtTop;
	}
	*/
	public double getProcentLanguageProblems() {
		return procentLanguageProblems;
	}

	public void setProcentLanguageProblems(double procentLanguageProblems) {
		this.procentLanguageProblems = procentLanguageProblems;
	}

	/*
	public double getProcentArticleTagged() {
		return procentArticleTagged;
	}

	public void setProcentArticleTagged(double procentArticleTagged) {
		this.procentArticleTagged = procentArticleTagged;
	}
	*/
	public double getProcentRevisionHistory() {
		return procentRevisionHistory;
	}

	public void setProcentRevisionHistory(double procentRevisionHistory) {
		this.procentRevisionHistory = procentRevisionHistory;
	}

	public double getProcentAuthorInformation() {
		return procentAuthorInformation;
	}

	public void setProcentAuthorInformation(double procentAuthorInformation) {
		this.procentAuthorInformation = procentAuthorInformation;
	}

	public double getProcentProportionParagraphsWithCitations() {
		return procentProportionParagraphsWithCitations;
	}

	public void setProcentProportionParagraphsWithCitations(double procentProportionParagraphsWithCitations) {
		this.procentProportionParagraphsWithCitations = procentProportionParagraphsWithCitations;
	}

	public double getProcentArticleSize() {
		return procentArticleSize;
	}

	public void setProcentArticleSize(double procentArticleSize) {
		this.procentArticleSize = procentArticleSize;
	}

	public double getProcentProportionUnregisteredEditorsWithSingleEdit() {
		return procentProportionUnregisteredEditorsWithSingleEdit;
	}

	public void setProcentProportionUnregisteredEditorsWithSingleEdit(
			double procentProportionUnregisteredEditorsWithSingleEdit) {
		this.procentProportionUnregisteredEditorsWithSingleEdit = procentProportionUnregisteredEditorsWithSingleEdit;
	}

	public double getProcentProportionRegisteredEditorsWithEditFrequency() {
		return procentProportionRegisteredEditorsWithEditFrequency;
	}

	public void setProcentProportionRegisteredEditorsWithEditFrequency(
			double procentProportionRegisteredEditorsWithEditFrequency) {
		this.procentProportionRegisteredEditorsWithEditFrequency = procentProportionRegisteredEditorsWithEditFrequency;
	}

	public double getProcentRevisionCount() {
		return procentRevisionCount;
	}

	public void setProcentRevisionCount(double procentRevisionCount) {
		this.procentRevisionCount = procentRevisionCount;
	}
	
	
	
	

}
