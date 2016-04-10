package businessLogic;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import model.Particle;
import model.Position;

import org.xml.sax.SAXException;

import fitness.FitnessFunction;

public class PSOAlgorithm {
	
	MainOperations operations;
	private FitnessFunction fitnessFunction;
	public List<Particle> theBestParticles= new ArrayList<Particle>();
	
	public PSOAlgorithm(FitnessFunction fitnessFunction){
		this.fitnessFunction=fitnessFunction;
		operations= new MainOperations(this.fitnessFunction);
	}
	
	public Position runPsoAlgorithm(int nrIterations,int cx, int cp, int cq,int nrParticles,String[] inputFile) throws ParserConfigurationException, SAXException{
		List<Particle> particles = operations.initializeParticles(nrParticles, inputFile);
		Position globalBest = new Position();
		
		globalBest.setElements(particles.get(0).getLocalBest().getElements());
		for(int i=0;i<globalBest.getElements().size();i++){
			System.out.print("Before"+globalBest.getElements().get(i)+"  ");
		}
		for(int iteration=0;iteration<nrIterations;iteration++){
			int  c = 0;
			for(Particle p : particles){
				p.setVelocity(operations.updateVelocity(cx, cp, cq, p.getVelocity(), p.getLocalBest(), globalBest, p.getPosition()));
				System.out.println();
				c++;
				System.out.print("viteza  "+c+" ");
				for(int i=0;i<p.getVelocity().getVelocity().size();i++){
					System.out.print(p.getVelocity().getVelocity().get(i)+"  ");
				}
				System.out.println();
				p.setPosition(operations.updatePositon(p.getPosition(), p.getVelocity()));
				p.setLocalBest(operations.updateLocalBest(p));
				
				Position oldGlobalBest= globalBest;
				globalBest = operations.updateGlobalBest(p,globalBest);
				
				if(oldGlobalBest!=globalBest){
					theBestParticles.add(p);
				}
				
				System.out.print("iteratia "+iteration+" ");
				for(int i=0;i<globalBest.getElements().size();i++){
					System.out.print(globalBest.getElements().get(i)+"  ");
				}
				System.out.println();
			}
		}
		return globalBest;
	}

}
