package entities;

import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import enums.AdventurerType;
import enums.Race;
import util.AdventurerUtil;

public class Adventurer extends ResourceSupport{
	private String name;
	private String rarity;
	private Race race;
	protected List<Job> jobs;
	private AdventurerType type;
	
	public Adventurer(String name, String rarity, Race race, AdventurerType type, List<Job> jobs) {
		super();
		this.name = name;
		this.rarity = rarity;
		this.race = race;
		this.type = type;
		this.jobs = jobs;
	}
	
	public String getAdventurerId() {
		return AdventurerUtil.getIdFromName(this.name);
	}

	public String getName() {
		return name;
	}
	
	public String getRarity() {
		return rarity;
	}
	
	public Race getRace() {
		return race;
	}
	
	public AdventurerType getType() {
		return type;
	}
	
	public List<Job> getJobs(){
		return jobs;
	}
	
	public String toString() {
		return this.name.replaceAll(" ", "_");
	}


	
}
