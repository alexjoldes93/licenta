package model;

import java.util.ArrayList;
import java.util.List;

public class Velocity {

private List<Integer> velocity;
	
	public Velocity(){
		velocity = new ArrayList();
	}

	public List<Integer> getVelocity() {
		return velocity;
	}

	public void setVelocity(List<Integer> velocity) {
		this.velocity = velocity;
	}
	
	
}
