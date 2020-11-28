package entities;

import java.util.List;

import entities.skills.Skill;
import enums.Element;
import enums.Weapon;

public class Job {
	private int jobNo;
	private String name;
	private int hp;
	private int atk;
	private int def;
	private int matk;
	private int mdef;
	private Weapon weapon;
	private Element element;
	private List<Skill> jobSkills;
	
	List<Skill> slotSkills;

	public Job(int jobNo, String name, int hp, int atk, int def, int matk, int mdef, Weapon weapon,
			Element element, List<Skill> jobSkills) {
		super();
		this.jobNo = jobNo;
		this.name = name;
		this.hp = hp;
		this.atk = atk;
		this.def = def;
		this.matk = matk;
		this.mdef = mdef;
		this.weapon = weapon;
		this.element = element;
		this.jobSkills = jobSkills;
	}

	public int getJobNo() {
		return jobNo;
	}
	
	public String getName() {
		return name;
	}

	public int getHp() {
		return hp;
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

	public Weapon getWeapon() {
		return weapon;
	}

	public Element getElement() {
		return element;
	}

	public List<Skill> getJobSkills() {
		return jobSkills;
	}

}
