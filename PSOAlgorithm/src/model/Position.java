package model;

import java.util.ArrayList;
import java.util.List;

public class Position {

	private List<Double> elements;
	
	public Position(){
		elements = new ArrayList<Double>();
	}

	public List<Double> getElements() {
		return elements;
	}

	public void setElements(List<Double> position) {
		this.elements = position;
	}
	
	
}
