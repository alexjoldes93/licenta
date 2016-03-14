package fitness;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Position;

public class FitnessFunction {
	public final List<Double> standard = new ArrayList<>(Arrays.asList(15.0, 85.0, 85.0, 0.0, 15.0, 0.0, 85.0, 85.0, 85.0, 85.0, 15.0, 85.0, 85.0));
	public final int threshold = 110;
	public FitnessFunction(){
		
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
}
