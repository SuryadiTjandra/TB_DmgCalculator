package spring.response_entities;

import org.springframework.hateoas.ResourceSupport;

import entities.Adventurer;

public class AdventurerPreview extends ResourceSupport{
	private String adventurerName;
	private String rarity;
	private String race;
	private String type;

	public AdventurerPreview(String name, String rarity, String race, String type){
		this.adventurerName = name;
		this.rarity = rarity;
		this.race = race;
		this.type = type;
	}
	
	public AdventurerPreview(Adventurer adv) {
		this.adventurerName = adv.getName();
		this.rarity = adv.getRarity();
		this.race = adv.getRace().toString();
		this.type = adv.getType().toString();
	}
	
	public String getAdventurerName() {
		return adventurerName;
	}

	void setAdventurerName(String adventurerName) {
		this.adventurerName = adventurerName;
	}

	public String getRarity() {
		return rarity;
	}

	void setRarity(String rarity) {
		this.rarity = rarity;
	}

	public String getRace() {
		return race;
	}

	void setRace(String race) {
		this.race = race;
	}

	public String getType() {
		return type;
	}

	void setType(String type) {
		this.type = type;
	}
	
}
