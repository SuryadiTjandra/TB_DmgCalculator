package entities.skills;

import enums.Area;
import enums.Buff;

public class NonStatBuffEffect extends BuffEffect {
	private Buff buff;
	private int buffPercentage;
	
	public NonStatBuffEffect(Area area, int duration, Buff buff, int buffPercentage) {
		super(area, duration);
		this.buff = buff;
		this.buffPercentage = buffPercentage;
	}

	public Buff getBuff() {
		return buff;
	}

	public int getBuffPercentage() {
		return buffPercentage;
	}
	
	
}
