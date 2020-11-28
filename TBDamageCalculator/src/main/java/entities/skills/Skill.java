package entities.skills;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import enums.SkillActivation;

public class Skill {
	protected String name;
	protected SkillActivation activation;
	protected int activationRate;
	private List<SkillEffect> effects;
	
	public Skill(String name, SkillActivation activation, int activationRate, SkillEffect... effects) {
		super();
		this.name = name;
		this.activation = activation;
		this.activationRate = activationRate;
		this.effects = Arrays.asList(effects);
	}
	
	public Skill(String name, SkillActivation activation, int activationRate) {
		super();
		this.name = name;
		this.activation = activation;
		this.activationRate = activationRate;
		this.effects = Collections.emptyList();
	}
	
	public String getName() {
		return name;
	}
	public SkillActivation getActivation() {
		return activation;
	}
	public int getActivationRate() {
		return activationRate;
	}
	public List<SkillEffect> getEffects(){
		return effects;
	}
	
}
