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
	List<Particle> particles;
	private FitnessFunction fitnessFunction;
	public List<Particle> theBestParticles;
	
	public PSOAlgorithm(FitnessFunction fitnessFunction,List<Particle> ptheBestParticles){
		this.fitnessFunction=fitnessFunction;
		operations= new MainOperations(this.fitnessFunction);
		this.theBestParticles=ptheBestParticles;
		
	}
	
	public Position runPsoAlgorithm(int nrIterations,double cx, double cp, double cq,int nrParticles,List<String> inputFile) throws ParserConfigurationException, SAXException{
		this.particles = operations.initializeParticles(nrParticles, inputFile);
		Position globalBest = new Position();
		
		globalBest.setElements(particles.get(0).getLocalBest().getElements());
		//theBestParticles.add(particles.get(0));
		for(int i=0;i<globalBest.getElements().size();i++){
			//System.out.print("Before"+globalBest.getElements().get(i)+"  ");
		}
		for(int iteration=0;iteration<nrIterations;iteration++){
			int  c = 0;
			for(Particle p : particles){
				p.setVelocity(operations.updateVelocity(cx, cp, cq, p.getVelocity(), p.getLocalBest(), globalBest, p.getPosition()));
				//System.out.println();
				c++;
				//System.out.print("viteza  "+c+" ");
				for(int i=0;i<p.getVelocity().getElements().size();i++){
					//System.out.print(p.getVelocity().getElements().get(i)+"  ");
				}
				//System.out.println();
				p.setPosition(operations.updatePositon(p.getPosition(), p.getVelocity()));
				p.setLocalBest(operations.updateLocalBest(p));
				
				Position oldGlobalBest= globalBest;
				globalBest = operations.updateGlobalBest(p,globalBest);
				
				if(oldGlobalBest!=globalBest){
					theBestParticles.add(p);
					//theBestParticles=eliminateDuplicateArticle();
				}
				
				/*
				//System.out.print("iteratia "+iteration+" ");
				for(int i=0;i<globalBest.getElements().size();i++){
					//System.out.print(globalBest.getElements().get(i)+"  ");
				}
				//System.out.println();*/
			}
		}
		return globalBest;
	}
	
	public List<Particle> getParticles() {
		return particles;
	}

	public List<Particle> eliminateDuplicateArticle(List<Particle> particleToSort){
		List<Particle> newList = new ArrayList<Particle>();
		
		for(int i=0;i<=particleToSort.size()-1;i++)
		{	boolean ok=true;
			for(int j=i+1;j<=particleToSort.size()-1;j++){
				if(particleToSort.get(i).getArticleName().equals(particleToSort.get(j).getArticleName())){
					ok=false;
					break;
				}
			}
			
			if(ok==true)
			{
				newList.add(particleToSort.get(i));
			}
		}
		
		return newList;
	}

}
