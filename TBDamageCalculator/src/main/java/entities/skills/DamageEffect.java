package entities.skills;

import enums.Area;
import enums.Element;
import enums.Weapon;

public class DamageEffect extends SkillEffect {
	
	private Weapon weapon;
	private double physPower;	
	private Element element;
	private double magicPower;
	
	public DamageEffect(Area area, Weapon weapon, double physPower,Element element, double magicPower) {
		super(area);
		this.weapon = weapon;
		this.physPower = physPower;
		this.element = element;
		this.magicPower = magicPower;
	}
	
	public Weapon getWeapon() {
		return weapon;
	}
	public double getPhysPower() {
		return physPower;
	}
	public Element getElement() {
		return element;
	}
	public double getMagicPower() {
		return magicPower;
	}
}
