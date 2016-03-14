package businessLogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import fitness.FitnessFunction;
import parser.JDOMParser;
import model.Particle;
import model.Position;
import model.Velocity;

public class MainOperations {
	
	private BasicOperations operations;
	
	
	public MainOperations(){
		operations = new BasicOperations();
	}
	
	public Velocity updateVelocity(int cx,int cp,int cq,Velocity currentVelocity,Position localBest,Position globalBest,Position currentPosition){
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
		FitnessFunction fitnessFunction = new FitnessFunction();
		boolean result = fitnessFunction.applyFitnessFunction(p.getPosition());
		
		if(result)
			newLocalBest=p.getPosition();
		else 
			newLocalBest=p.getLocalBest();
		
		return newLocalBest;
	}
	
	public Position updateGlobalBest(Particle  p, Position currentGlobalBest){
		Position newGlobalBest = new Position();
		
		FitnessFunction fitnessFunction = new FitnessFunction();
		boolean result = fitnessFunction.applyFitnessFunction(p.getPosition());
		
		if(result)
			newGlobalBest=p.getPosition();
		else 
			newGlobalBest=currentGlobalBest;
		
		return newGlobalBest;
	}
	
	public List<Particle> initializeParticles(int nrParticles,String file) throws ParserConfigurationException, SAXException{
		List<Particle> particles = new ArrayList<>();
		JDOMParser parser = new JDOMParser();
		//must review
		for(int i=0;i<nrParticles;i++){
			Position position = parser.parse(file);
			
			Velocity v = new Velocity();
			
			List<Integer> inits=new ArrayList<Integer>();
			for(int j = 0; j<position.getElements().size();j++)
				inits.add(0);
			v.setVelocity(inits);
			
			Position localBest = new Position();
			localBest.setElements(position.getElements());
			Particle particle = new Particle(v, position, localBest);
			particles.add(particle);
		}
		
		return particles;
		
	}
}
