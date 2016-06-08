package main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import model.Particle;
import model.Position;

import org.xml.sax.SAXException;

import businessLogic.ExportingToCSV;
import businessLogic.PSOAlgorithm;
import decisionTreeClassifier.Attribute;
import decisionTreeClassifier.Classifier;
import decisionTreeClassifier.DecisionTreeID3;
import decisionTreeClassifier.Reader;
import decisionTreeClassifier.TreeNode;
import fitness.FitnessFunction;
import parser.JDOMParser;


public class Test {
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, FileNotFoundException {
		PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
		System.setOut(out); 
		// TODO Auto-generated method stub
		//JDOMParser parser = new JDOMParser();
		//parser.parse("D:\\FacultateAN4\\Licenta\\Generator\\src\\Main\\file1.xml");
		int nrFiles=20;
		
		String[] inputFiles = new String[nrFiles];
		
		for(int i=0;i<nrFiles;i++){
			inputFiles[i]="D:\\FacultateAN4\\RepositoryLicenta\\licenta\\Generator\\src\\Main\\file"+i+".xml";
		}
		List<String> listFiles= new ArrayList<String>();
		for(int i=0;i<nrFiles;i++){
			listFiles.add(inputFiles[i]);
		}
		/*
		String fileDataSet = "D:\\FacultateAN4\\RepositoryLicenta\\licenta\\PSOAlgorithm\\src\\decisionTreeClassifier\\dataset.txt";
		Classifier classifier = new Classifier(inputFiles);
		//classifier.getWordsFromFile("D:\\FacultateAN4\\RepositoryLicenta\\licenta\\Generator\\src\\Main\\file"+0+".xml");
		List<String> resultFiles=classifier.getFilesFromDomain("medicina", fileDataSet);
		System.out.println("No of files = "+resultFiles.size());*/
		
		
		//initialize standard values for fitness function
		List<Double> standard=new ArrayList<>(Arrays.asList(90.0,90.0,90.0,90.0,90.0,90.0,90.0));
		
		//initialize results in HashMap
		HashMap<String,Integer> resultsOfAlg = new HashMap<String,Integer>();
		List<Particle> particles=new ArrayList<>();
		
		//initialize PSO variables
		int nrIterations =100;
		double cx=2.0;
		double cp=1.5;
		double cq=1.5;
		int nrParticles=nrFiles;
		
		int nrIteratiiAlg=500;
		List<Integer> counts=new ArrayList<Integer>();
		for(int c=0;c<nrParticles;c++){
			counts.add(0);
		}
		for(int pAlg=0;pAlg<nrIteratiiAlg;pAlg++){
		
		
		List<Particle> theBestParticles = new ArrayList<Particle>();
		
		//create fitness function object
		FitnessFunction fitnessFunction = new FitnessFunction(standard);
		PSOAlgorithm oPSOAlg = new PSOAlgorithm(fitnessFunction,theBestParticles);
		
		Position p = oPSOAlg.runPsoAlgorithm(nrIterations, cx, cp, cq, nrParticles, listFiles);
		theBestParticles=oPSOAlg.eliminateDuplicateArticle(theBestParticles);
		//System.out.println("Nr articles "+theBestParticles.size()+ " "+pAlg);
		for(int i=0;i<theBestParticles.size();i++){
			//System.out.println(theBestParticles.get(i).getArticleName());
		}
		
		particles=oPSOAlg.getParticles();
		int index=particles.indexOf(theBestParticles.get(theBestParticles.size()-1));
		Integer oldValue=counts.get(index);
		oldValue++;
		counts.set(index,oldValue);
		}
		
		for(int p=0;p<particles.size();p++){
			resultsOfAlg.put(particles.get(p).getArticleName(), counts.get(p));
		}
		
		for (String name: resultsOfAlg.keySet()){

            String key =name.toString();
            Integer value = resultsOfAlg.get(name);  
            System.out.println(key + " " + value);  


} 
		/*
		while(theBestParticles.size()<10){
			
			fitnessFunction=fitnessFunction.adaptFitnessFunction(fitnessFunction);
			oPSOAlg = new PSOAlgorithm(fitnessFunction,theBestParticles);
			p = oPSOAlg.runPsoAlgorithm(nrIterations, cx, cp, cq, nrParticles, resultFiles);
			theBestParticles=oPSOAlg.eliminateDuplicateArticle(theBestParticles);
			System.out.println("Nr articles "+theBestParticles.size());
			for(int i=0;i<theBestParticles.size();i++){
				System.out.println(theBestParticles.get(i).getArticleName());
			}
		}
		
		*/
		
		
		//export to csv
		//ExportingToCSV csv = new ExportingToCSV(p);
		//csv.doCsvExport("Result_nrIterations="+nrIterations+"_cx="+cx+"_cp="+cp+"_cq="+cq+"_nrParticles="+nrParticles+".csv");
	}

}
