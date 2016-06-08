package model;

import java.util.ArrayList;
import java.util.List;

public class Velocity {

private List<Double> elements;
	
	public Velocity(){
		elements = new ArrayList<Double>();
	}

	public List<Double> getElements() {
		return elements;
	}

	public void setVelocity(List<Double> velocity) {
		this.elements = velocity;
	}
	
	
}
