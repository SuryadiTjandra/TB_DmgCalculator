package entities;

import java.util.List;

import enums.AdventurerType;
import enums.Race;

public class LambdaAdventurer extends Adventurer {

	Adventurer baseForm;
	
	public LambdaAdventurer(String name, String rarity, Race race, AdventurerType type, List<Job> jobs, Adventurer baseForm) {
		super(name, rarity, race, type, jobs);
		this.baseForm = baseForm;
	}
	
	public Adventurer getBaseForm() {
		return baseForm;
	}
}
