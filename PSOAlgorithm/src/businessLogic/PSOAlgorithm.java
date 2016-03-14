package businessLogic;

import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import model.Particle;
import model.Position;

import org.xml.sax.SAXException;

public class PSOAlgorithm {
	
	MainOperations operations;
	
	public PSOAlgorithm(){
		operations= new MainOperations();
	}
	
	public Position runPsoAlgorithm(int nrIterations,int cx, int cp, int cq,int nrParticles,String inputFile) throws ParserConfigurationException, SAXException{
		List<Particle> particles = operations.initializeParticles(nrParticles, inputFile);
		Position globalBest = new Position();
		globalBest.setElements(particles.get(0).getLocalBest().getElements());
		for(int iteration=0;iteration<nrIterations;iteration++){
			for(Particle p : particles){
				p.setVelocity(operations.updateVelocity(cx, cp, cq, p.getVelocity(), p.getLocalBest(), globalBest, p.getPosition()));
				p.setPosition(operations.updatePositon(p.getPosition(), p.getVelocity()));
				p.setLocalBest(operations.updateLocalBest(p));
				globalBest = operations.updateGlobalBest(p,globalBest);
			}
		}
		return globalBest;
	}

}
