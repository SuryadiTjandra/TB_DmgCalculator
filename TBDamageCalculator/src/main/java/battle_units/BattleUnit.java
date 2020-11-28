package battle_units;

import java.util.List;
import java.util.Optional;

import entities.Adventurer;
import entities.Companion;
import entities.Job;
import entities.LambdaAdventurer;
import entities.skills.Skill;

public interface BattleUnit {
	
	public static BattleUnit from(Adventurer adventurer) {
		return new AdventurerUnit(adventurer);
	}
	
	public static BattleUnit from(LambdaAdventurer adventurer) {
		return new LambdaAdventurerUnit(adventurer);
	}
	
	public Adventurer getAdventurer();
	
	public void setActiveJob(int jobNo);
	public void setCompanion(Companion companion);
	public void setSlotSkill(int slotNo, int jobNo, int skillNo);
	
	public Job getActiveJob();
	public List<Skill> getActiveSkills();
	public Optional<Companion> getCompanion();

	public int getBaseHp();
	public int getBaseAtk();
	public int getBaseDef();
	public int getBaseMatk();
	public int getBaseMdef();
	
}
