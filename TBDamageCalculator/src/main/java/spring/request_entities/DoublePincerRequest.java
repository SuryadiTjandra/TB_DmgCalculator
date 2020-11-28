package spring.request_entities;

import enums.Direction;

public class DoublePincerRequest {
	private AdventurerSetup setup;
	private Direction direction;
	private int physTaps;
	private int magTaps;
	
	public AdventurerSetup getSetup() {
		return setup;
	}
	public Direction getDirection() {
		return direction;
	}
	public int getPhysTaps() {
		return physTaps;
	}
	public int getMagTaps() {
		return magTaps;
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
	private void setPhysTaps(int physTaps) {
		this.physTaps = physTaps;
	}
	@SuppressWarnings("unused")
	private void setMagTaps(int magTaps) {
		this.magTaps = magTaps;
	}
}
