package model;

public class Particle {
	
	private Velocity velocity;
	private Position position;
	private Position localBest;
	
	public Particle(){
		
	}

	public Particle(Velocity velocity, Position position, Position localBest) {
		this.velocity = velocity;
		this.position = position;
		this.localBest = localBest;
	}

	public Velocity getVelocity() {
		return velocity;
	}

	public void setVelocity(Velocity velocity) {
		this.velocity = velocity;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Position getLocalBest() {
		return localBest;
	}

	public void setLocalBest(Position localBest) {
		this.localBest = localBest;
	}
	
	

}
