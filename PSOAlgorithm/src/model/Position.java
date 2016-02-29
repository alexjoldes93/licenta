package model;

import java.util.ArrayList;
import java.util.List;

public class Position {

	private List<Double> position;
	
	public Position(){
		position = new ArrayList();
	}

	public List<Double> getPosition() {
		return position;
	}

	public void setPosition(List<Double> position) {
		this.position = position;
	}
	
	
}
