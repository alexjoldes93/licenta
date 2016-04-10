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
		int nrFiles=50;
		String[] inputFiles = new String[nrFiles];
		
		for(int i=0;i<nrFiles;i++){
			inputFiles[i]="D:\\FacultateAN4\\RepositoryLicenta\\licenta\\Generator\\src\\Main\\file"+i+".xml";
		}
		
		String fileDataSet = "D:\\FacultateAN4\\RepositoryLicenta\\licenta\\PSOAlgorithm\\src\\decisionTreeClassifier\\dataset.txt";
		/*Classifier classifier = new Classifier(inputFiles);
		//classifier.getWordsFromFile("D:\\FacultateAN4\\RepositoryLicenta\\licenta\\Generator\\src\\Main\\file"+0+".xml");
		classifier.getFilesFromDomain("medicina", fileDataSet);*/
		
		//initialize standard values for fitness function
		List<Double> standard=new ArrayList<>(Arrays.asList(15.0, 85.0, 85.0, 15.0, 85.0, 85.0, 85.0, 85.0, 15.0, 85.0, 85.0));
		//initialize threshold
		int threshold=80;
		
		//create fitness function object
		FitnessFunction fitnessFunction = new FitnessFunction(standard, threshold);
		PSOAlgorithm oPSOAlg = new PSOAlgorithm(fitnessFunction);
		
		//initialize PSO variables
		int nrIterations =5;
		int cx=10;
		int cp=20;
		int cq=10;
		int nrParticles=nrFiles;
		Position p = oPSOAlg.runPsoAlgorithm(nrIterations, cx, cp, cq, nrParticles, inputFiles);
		System.out.println("Nr of best "+oPSOAlg.theBestParticles.size());
		for(int i=0;i<oPSOAlg.theBestParticles.get(oPSOAlg.theBestParticles.size()-1).getPosition().getElements().size();i++){
			System.out.print(oPSOAlg.theBestParticles.get(oPSOAlg.theBestParticles.size()-1).getPosition().getElements().get(i)+" ");
		}
		
		//export to csv
		ExportingToCSV csv = new ExportingToCSV(p);
		csv.doCsvExport("Result_nrIterations="+nrIterations+"_cx="+cx+"_cp="+cp+"_cq="+cq+"_nrParticles="+nrParticles+".csv");
		
		
		
	}

}
