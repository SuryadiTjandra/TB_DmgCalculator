package calculators;

public class Formula {
	public static double physicalDamage(double power, double attack, double defense) {
		return (1.1 * power * Math.pow(1.15 * attack, 1.7)) / Math.pow(defense, 0.7);
	}
	
	public static double magicalDamage(double power, double attack, double defense) {
		return (1.5 * power * Math.pow(attack, 1.7)) / Math.pow(defense, 0.7);
	}
}
