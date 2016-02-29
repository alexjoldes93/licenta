package businessLogic;

import java.util.Random;

import model.Position;
import model.Velocity;

public class BasicOperations {
	
	public BasicOperations(){
		
	}
	
	public Velocity subtractPositions (Position a, Position b){
		Velocity result = new Velocity();
		for(int i=0;i<a.getPosition().size();i++){
			if(a.getPosition().get(i)==b.getPosition().get(i)){
				result.getVelocity().add(0);
			}else{
				if(Math.abs(a.getPosition().get(i) - b.getPosition().get(i))>50){
					result.getVelocity().add(-1);
				}
				else
				{
					result.getVelocity().add(1);
				}
			}
		}
		
		return result;
	}
	
	public Velocity weightAdd(int cp, int cq, Velocity vp, Velocity vq){
		Velocity result = new Velocity();
		
		if(testEqualTwoVelocities(vp, vq)){
			result = vp;
		}else{
			int countNegativeOneValueVp = countNegativeOneValue(vp);
			int countNegativeOneValueVq = countNegativeOneValue(vq);
			
			if(countNegativeOneValueVp>countNegativeOneValueVq){
				ApplyMutation(cp,vp);
			}else{
				ApplyMutation(cq,vq);
			}
		}
		
		return result;
	}

	private Boolean testEqualTwoVelocities(Velocity a, Velocity b){
		Boolean result= true;
		for(int i=0;i<a.getVelocity().size();i++){
			if(a.getVelocity().get(i)!= b.getVelocity().get(i)){
				result= false;
				break;
			}
		}
		return result;
		
	}
	
	private int countNegativeOneValue(Velocity a){
		int count = 0;
		for(int i=0;i<a.getVelocity().size();i++){
			if(a.getVelocity().get(i)==-1){
				count++;
			}
		}
		return count;
	}
	
	private Velocity ApplyMutation(int c, Velocity v){
		Velocity result = new Velocity();
		result = scalarMultiply(c, v);
		
		Random rand = new Random();
		int  n1 = rand.nextInt(v.getVelocity().size()-1);
		int  n2 = rand.nextInt(v.getVelocity().size()-1);
		
		int auxiliar = result.getVelocity().get(n1);
		v.getVelocity().set(n1, v.getVelocity().get(n2));
		v.getVelocity().set(n2, auxiliar);
		
		return result;
	}
	
	private Velocity scalarMultiply(int c, Velocity v){
		Velocity result = new Velocity();
		
		for(int i=0;i<v.getVelocity().size();i++){
			int rez = c*v.getVelocity().get(i);
			result.getVelocity().add(rez);
		}
		
		return result;
	}
	
	public Position addPositonAndVelocity(Position p, Velocity v){
		Position result = new Position();
		
		for(int i=0;i<p.getPosition().size();i++){
			if(v.getVelocity().get(i)==1)
				result.getPosition().add(p.getPosition().get(i));
			else{
				if(v.getVelocity().get(i)==-1){
					result.getPosition().add(0.0);
				}
				else{
					result.getPosition().add((double) RandomValue());
				}
			}
		}
		
		return result;
	}
	
	private int RandomValue(){
		Random rand = new Random();
		int  result = rand.nextInt(100);
		
		return result;
	}
}
