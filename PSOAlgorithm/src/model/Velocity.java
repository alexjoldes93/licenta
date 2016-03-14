package model;

import java.util.ArrayList;
import java.util.List;

public class Velocity {

private List<Integer> elements;
	
	public Velocity(){
		elements = new ArrayList();
	}

	public List<Integer> getVelocity() {
		return elements;
	}

	public void setVelocity(List<Integer> velocity) {
		this.elements = velocity;
	}
	
	
}
