package businessLogic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import fitness.Errors;
import fitness.FitnessFunction;
import parser.JDOMParser;
import model.Particle;
import model.Position;
import model.Velocity;

public class MainOperations {
	
	private BasicOperations operations;
	private FitnessFunction fitnessFunction;
	private Errors errors;
	private File file = new File("fitnessResults.txt");
	FileOutputStream fop;
	
	
	public MainOperations(FitnessFunction fitnessFunction){
		operations = new BasicOperations();
		this.fitnessFunction=fitnessFunction;
		errors=new Errors();
		 try {
			fop = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Velocity updateVelocity(double cx,double cp,double cq,Velocity currentVelocity,Position localBest,Position globalBest,Position currentPosition){
		Velocity newVelocity = new Velocity();
		
		Velocity term2 = new Velocity();
		term2 = operations.subtractPositions(localBest, currentPosition);
		
		Velocity term3 = new Velocity();
		term3 = operations.subtractPositions(globalBest, currentPosition);
		
		Velocity intermediateResult = new Velocity();
		intermediateResult = operations.weightAdd(cp, cq, term2, term3);
		
		newVelocity = operations.weightAdd(cx, 1, currentVelocity, intermediateResult);
		return newVelocity;
	}
	
	public Position updatePositon(Position currentPosition, Velocity newVelocity){
		Position newPosition = new Position();
		newPosition = operations.addPositonAndVelocity(currentPosition, newVelocity);
		return newPosition;
	}

	public Position updateLocalBest(Particle p){
		Position newLocalBest = new Position();
		
		if(fitnessFunction.applyFitness(p.getPosition(),0.1,0.9)<fitnessFunction.applyFitness(p.getLocalBest(),0.1,0.9))
		//if(errors.getErrorValue(p.getPosition(), fitnessFunction.standard)<errors.getErrorValue(p.getLocalBest(), fitnessFunction.standard))
			newLocalBest=p.getPosition();
		else 
			newLocalBest=p.getLocalBest();
		
		return newLocalBest;
	}
	
	public Position updateGlobalBest(Particle  p, Position currentGlobalBest){
		Position newGlobalBest = new Position();

		this.writeToFileFitnessResults(p.getArticleName()+"\n");
		this.writeToFileFitnessResults(p.getPosition().getElements().toString()+"\n");
		this.writeToFileFitnessResults(fitnessFunction.standard.toString()+"\n");
		
		double diff = fitnessFunction.applyFitness(p.getPosition(),0.1,0.9);
		this.writeToFileFitnessResults("PARTICLE:"+fitnessFunction.getDifferencies().toString()+"\n");
		
		double currentDiff=fitnessFunction.applyFitness(currentGlobalBest,0.1,0.9);
		this.writeToFileFitnessResults("CURRENT:"+fitnessFunction.getDifferencies().toString()+"\n");
		if(diff<currentDiff)
		
			{	
				newGlobalBest=p.getPosition();
				this.writeToFileFitnessResults("SETTED GLOBAL BEST\n");
			}
		else 
			newGlobalBest=currentGlobalBest;
		this.writeToFileFitnessResults("---------------------------------------------------------\n");
		return newGlobalBest;
	}
	
	private void writeToFileFitnessResults(String results){
		
		String content = results;

		try  {

			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// get the content in bytes
			byte[] contentInBytes = content.getBytes();

			fop.write(contentInBytes);
			
			//fop.flush();
			//fop.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public List<Particle> initializeParticles(int nrParticles,List<String> file) throws ParserConfigurationException, SAXException{
		List<Particle> particles = new ArrayList<>();
		JDOMParser parser =null;
		//must review
		for(int i=0;i<nrParticles;i++){
			parser = new JDOMParser(file.get(i));
			Position position = parser.parseForPositions();
			
			String articleName=parser.parseForArticleName();
			
			Velocity v = new Velocity();
			
			List<Double> inits=new ArrayList<Double>();
			for(int j = 0; j<position.getElements().size();j++){
				//Random rand = new Random();
				double  n =  BasicOperations.round(ThreadLocalRandom.current().nextDouble(0, 100),3);
					inits.add(n);
			}
			v.setVelocity(inits);
			
			Position localBest = new Position();
			localBest.setElements(position.getElements());
			Particle particle = new Particle(v, position, localBest);
			particle.setArticleName(articleName);
			particles.add(particle);
		}
		
		return particles;
	}
		//SGPSO
		public Position updateGeometricCentrePosition(int nrParticles,List<Particle> particles){
			Position centreGeometricPosition=new Position();
			
			for(Particle p:particles){
				
			}
			
			return  centreGeometricPosition;
		}
		
	
		public Velocity updateVelocitySGPSO(int w,int cp,int cq,int cx,Velocity currentVelocity,Position localBest,Position globalBest,Position currentPosition, Position geometricCentrePosition){
			Velocity newVelocity = new Velocity();
			
			Velocity term2 = new Velocity();
			term2 = operations.subtractPositions(localBest, currentPosition);
			
			Velocity term3 = new Velocity();
			term3 = operations.subtractPositions(globalBest, currentPosition);
			
			Velocity term4= new Velocity();
			term4=operations.subtractPositions(geometricCentrePosition, currentPosition);
			
			Velocity intermediateResult = new Velocity();
			intermediateResult = operations.weightAdd(cp, cq, term2, term3);
			intermediateResult=operations.weightAdd(w, 1, currentVelocity, intermediateResult);
			
			newVelocity = operations.weightAdd(cx, 1, term4, intermediateResult);
			return newVelocity;
		}
}
