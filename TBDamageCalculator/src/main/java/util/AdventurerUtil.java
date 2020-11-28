package util;

import entities.Adventurer;

public class AdventurerUtil {

	private AdventurerUtil() {};
	
	public static String getIdFromName(String advName) {
		return advName.replace(" ", "_");
	}
	
	public static String getId(Adventurer adv) {
		return getIdFromName(adv.getName());
	}
}
