package spring.request_entities;

import calculators.Distance;
import enums.Direction;

public class SinglePincerRequest {
	private AdventurerSetup setup;
	private Direction direction;
	private Distance target;
	
	public AdventurerSetup getSetup() {
		return setup;
	}
	public Direction getDirection() {
		return direction;
	}
	public Distance getTarget() {
		return target;
	}
	
	@SuppressWarnings("unused")
	private void setSetup(AdventurerSetup setup) {
		this.setup = setup;
	}
	@SuppressWarnings("unused")
	private void setDirection(Direction direction) {
		this.direction = direction;
	}
	@SuppressWarnings("unused")
	private void setTarget(Distance target) {
		this.target = target;
	}
}
