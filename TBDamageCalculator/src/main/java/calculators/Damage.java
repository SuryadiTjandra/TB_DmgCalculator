package calculators;

import java.util.EnumMap;
import java.util.Map;

import enums.Element;
import enums.Weapon;

public class Damage {
	private EnumMap<Weapon, Double> physicalDamage = new EnumMap<>(Weapon.class);
	private EnumMap<Element, Double> magicalDamage = new EnumMap<>(Element.class);
	
	Damage(){}
	
	public static Damage zeroDamage() {
		return new Damage();
	}
	
	void addDamage(Weapon weapon, Double damage) {
		physicalDamage.merge(weapon, damage, Double::sum);
	}
	
	void addDamage(Element element, Double damage) {
		magicalDamage.merge(element, damage, Double::sum);
	}
	
	void addAllDamage(Damage addition) {
		for (Weapon weapon: addition.getPhysicalDamages().keySet()) {
			addDamage(weapon, addition.getDamage(weapon));
		}
		for (Element element: addition.getMagicalDamages().keySet()) {
			addDamage(element, addition.getDamage(element));
		}
	}
	
	public double getDamage(Weapon weapon) {
		return physicalDamage.getOrDefault(weapon, 0.0);
	}
	
	public double getDamage(Element element) {
		return magicalDamage.getOrDefault(element, 0.0);
	}
	
	public Map<Weapon, Double> getPhysicalDamages(){
		return physicalDamage;
	}
	
	public Map<Element, Double> getMagicalDamages(){
		return magicalDamage;
	}
	
	public double getTotalPhysicalDamage() {
		return physicalDamage.values().stream().mapToDouble(d -> d).sum();
	}
	
	public double getTotalMagicalDamage() {
		return magicalDamage.values().stream().mapToDouble(d -> d).sum();
	}
}
