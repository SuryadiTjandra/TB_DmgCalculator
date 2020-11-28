package entities;

import java.util.Optional;

import entities.skills.Skill;

public class Companion {
	private String name;
	private String rarity;
	private int atk;
	private int def;
	private int matk;
	private int mdef;
	private Optional<Skill> skill;
	
	public Companion(String name, String rarity, int atk, int def, int matk, int mdef, Optional<Skill> skill) {
		super();
		this.name = name;
		this.rarity = rarity;
		this.atk = atk;
		this.def = def;
		this.matk = matk;
		this.mdef = mdef;
		this.skill = skill;
	}
	
	public String getName() {
		return name;
	}
	public String getRarity() {
		return rarity;
	}
	public int getAtk() {
		return atk;
	}
	public int getDef() {
		return def;
	}
	public int getMatk() {
		return matk;
	}
	public int getMdef() {
		return mdef;
	}
	public Optional<Skill> getSkill() {
		return skill;
	}
}
