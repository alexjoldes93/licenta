package fitness;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Position;

public class FitnessFunction {
	public List<Double> standard;// new ArrayList<>(Arrays.asList(15.0, 85.0, 85.0, 0.0, 15.0, 0.0, 85.0, 85.0, 85.0, 85.0, 15.0, 85.0, 85.0));
	public int threshold;// 110;
	public FitnessFunction(List<Double> standardList,int threshold){
		this.standard=standardList;
		this.threshold=threshold;
	}
	
	public boolean applyFitnessFunction(Position p){
		List<Double> elements = p.getElements();
		boolean ok = true;
		int sum=0;
		int i=0;
		for(Double d : elements){
			int sub = (int) Math.abs(d-standard.get(i));
			i++;
			sum+=sub;
		}
		
		if(sum>threshold)
			ok=false;
		
		return ok;
	}
	public int applyFitnessFunction2(Position p){
		List<Double> elements = p.getElements();
		int sum=0;
		int i=0;
		for(Double d : elements){
			int sub = (int) Math.abs(d-standard.get(i));
			i++;
			sum+=sub;
		}
		return sum;
	}
}
