package entities.skills;

import enums.Area;

public abstract class SkillEffect {
	protected Area area;
	
	SkillEffect(Area area){
		this.area = area;
	}
	
	public Area getArea() {
		return area;
	}
}
