package entities.skills;

import enums.Area;

public abstract class BuffEffect extends SkillEffect{
	private int duration;
	
	BuffEffect(Area area, int duration) {
		super(area);
		this.duration = duration;
	}
	
	public int getDuration() {
		return duration;
	}

}
