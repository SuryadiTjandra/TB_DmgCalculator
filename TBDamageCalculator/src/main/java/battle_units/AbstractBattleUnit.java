package battle_units;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import entities.Companion;
import entities.Job;
import entities.skills.Skill;

abstract class AbstractBattleUnit implements BattleUnit {
	
	protected Job activeJob;
	protected List<Skill> availableSlotSkills;
	protected List<Skill> equippedSlotSkills = new ArrayList<>(4);
	protected Optional<Companion> companion = Optional.empty();

	
	//public void setAdventurer(Adventurer adventurer) {
	//	this.adventurer = adventurer;
	//}
	public Job getActiveJob() {
		return this.activeJob;
	}
	
	public void setCompanion(Companion companion) {
		this.companion = Optional.ofNullable(companion);
	}
	
	public Optional<Companion> getCompanion() {
		return this.companion;
	}
	
	public List<Skill> getActiveSkills() {
		List<Skill> skillList = new ArrayList<>(9);
		skillList.addAll(activeJob.getJobSkills());
		skillList.addAll(equippedSlotSkills);
		
		companion.flatMap(c -> c.getSkill()).ifPresent(skill -> skillList.add(skill));
		return skillList;
	}
	
	public int getBaseHp() {
		return activeJob.getHp();
	}
	
	public int getBaseAtk() {
		return activeJob.getAtk();
	}
	
	public int getBaseDef() {
		return activeJob.getDef();
	}
	
	public int getBaseMatk() {
		return activeJob.getMatk();
	}
	
	public int getBaseMdef() {
		return activeJob.getMdef();
	}
	
	
	
	public void setSlotSkill(int slotNo, int jobNo, int skillNo) {
		if (slotNo < 0 || slotNo > 3)
			throw new IllegalArgumentException("Invalid slot number");
		if (skillNo < 0 || skillNo > 3)
			throw new IllegalArgumentException("Invalid skill number");
		if (jobNo < 0 || jobNo > 2)
			throw new IllegalArgumentException("Invalid job number");
		
		Skill skill = SkillFinder.findSkill(this.getAdventurer(), jobNo, skillNo);
		equipSkill(slotNo, skill);
	}
	
	
	protected void equipSkill(int slotNo, Skill skill) {
		if (!availableSlotSkills.contains(skill)) 
			throw new IllegalArgumentException("This Adventurer doesn't have that skill");
			
		if (equippedSlotSkills.contains(skill))
			throw new IllegalStateException("Skill is already equipped");
				
		if (equippedSlotSkills.size() < 4 && equippedSlotSkills.size() < slotNo + 1) {
			equippedSlotSkills.add(skill);
		} else {
			equippedSlotSkills.set(slotNo, skill);
		}
	}
}
