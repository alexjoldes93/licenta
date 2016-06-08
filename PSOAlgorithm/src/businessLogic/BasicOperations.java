package businessLogic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import model.Position;
import model.Velocity;

public class BasicOperations {
	
	public BasicOperations(){
		
	}
	
	public Velocity subtractPositions (Position a, Position b){
		Velocity result = new Velocity();
		for(int i=0;i<a.getElements().size();i++){
			if(a.getElements().get(i)==b.getElements().get(i)){
				result.getElements().addAll(a.getElements());
			}else{
				result.getElements().add(Math.abs(round(a.getElements().get(i)-b.getElements().get(i),3)));
			}
		}
		
		return result;
	}
	
	public Velocity weightAdd(double cp, double cq, Velocity vp, Velocity vq){
		Velocity result = new Velocity();
		
		if(testEqualTwoVelocities(vp, vq)){
			result = vp;
		}else{
			double sumVp = getSumOfVelocity(vp);
			double sumVq=getSumOfVelocity(vq);
			
			if(sumVp>sumVq){
				result = ApplyMutation(cp,vp);
			}else{
				result = ApplyMutation(cq,vq);
			}
		}
		
		return result;
	}

	private Boolean testEqualTwoVelocities(Velocity a, Velocity b){
		Boolean result= true;
		for(int i=0;i<a.getElements().size();i++){
			if(a.getElements().get(i)!= b.getElements().get(i)){
				result= false;
				break;
			}
		}
		return result;
		
	}
	
	public Double getSumOfVelocity(Velocity v){
		double sum=0.0;
		
		for(Double d:v.getElements())
		{
			sum+=d;
		}
		return sum;
	}
	
	
	private Velocity ApplyMutation(double c, Velocity v){
		Velocity result = new Velocity();
		result = scalarMultiply(c, v);
		
		Random rand = new Random();
		int  n1 = rand.nextInt(v.getElements().size()-1);
		int  n2 = rand.nextInt(v.getElements().size()-1);
		
		Double auxiliar = result.getElements().get(n1);
		v.getElements().set(n1, v.getElements().get(n2));
		v.getElements().set(n2, auxiliar);
		
		return result;
	}
	
	private Velocity scalarMultiply(double c, Velocity v){
		Velocity result = new Velocity();
		
		for(int i=0;i<v.getElements().size();i++){
			double rez = c*v.getElements().get(i);
			if(rez>100)
				rez=100.0;
			result.getElements().add(rez);
		}
		
		return result;
	}
	
	public Position addPositonAndVelocity(Position p, Velocity v){
		Position result = new Position();
		
		for(int i=0;i<p.getElements().size();i++){
			if(v.getElements().get(i)<=50)
				result.getElements().add(p.getElements().get(i));
			else{
				if(v.getElements().get(i)>50){
					result.getElements().add(RandomValue());
				}
			}
		}
		
		return result;
	}
	
	private double RandomValue(){
		Random rand = new Random();
		double  n =  BasicOperations.round(ThreadLocalRandom.current().nextDouble(0, 100),3);
		
		return n;
	}
	
	//rotungire numar
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
}
