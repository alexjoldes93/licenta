package fitness;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Position;

public class FitnessFunction {
	public List<Double> standard;// new ArrayList<>(Arrays.asList(15.0, 85.0, 85.0, 0.0, 15.0, 0.0, 85.0, 85.0, 85.0, 85.0, 15.0, 85.0, 85.0));
	public int threshold;// 110;
	private Errors errors;
	private List<Double> diffs;
	public FitnessFunction(List<Double> standardList){
		this.standard=standardList;
		this.errors= new Errors();
		

	}
	public List<Double> getDifferencies(){
		return this.diffs;
	}
	
	public double applyFitnessFunction(Position p){
		List<Double> elements = p.getElements();
		diffs=new ArrayList<Double>();
		Double sum=0.0;
		int i=0;
		for(Double d : elements){
			Double sub = Math.abs(d-standard.get(i));
			diffs.add(sub);
			i++;
			sum+=sub;
		}
		
		return sum;
	}
	
	public double applyFitness(Position p, double lambda1, double lambda2){
		Double sum1,sum2;
		sum1=this.applyFitnessFunction(p);
		sum1=lambda1*sum1;
		
		sum2=errors.getErrorValue(p, standard);
		sum2=lambda2*sum2;
		
		return sum1+sum2;
	}
	
	
	
	//15.0, 85.0, 85.0, 15.0, 85.0, 85.0, 85.0, 85.0, 15.0, 85.0, 85.0
	public FitnessFunction adaptFitnessFunction(FitnessFunction function){
		
		for(int i=0;i<function.standard.size();i++){
			if((i==0) || (i==3) || (i==8)){
				function.standard.set(i,function.standard.get(i)+5);
			}
			function.standard.set(i,function.standard.get(i)-5);
		}
		
		return function;
		
	}
}
