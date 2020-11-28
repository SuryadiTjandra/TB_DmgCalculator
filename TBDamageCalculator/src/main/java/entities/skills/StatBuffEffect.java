package entities.skills;

import enums.Area;
import enums.Stat;

public class StatBuffEffect extends BuffEffect {
	private Stat buffedStat;
	private int buffPercentage;
	
	public StatBuffEffect(Area area, int duration, Stat buffedStat, int buffPercentage) {
		super(area, duration);
		this.buffedStat = buffedStat;
		this.buffPercentage = buffPercentage;
	}

	public Area getArea() {
		return area;
	}

	public Stat getBuffedStat() {
		return buffedStat;
	}

	public int getBuffPercentage() {
		return buffPercentage;
	}
	
}
