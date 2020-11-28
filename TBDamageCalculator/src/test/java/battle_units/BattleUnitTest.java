package battle_units;

import static enums.Area.*;
import static enums.AdventurerType.*;
import static enums.Element.*;
import static enums.SkillActivation.*;
import static enums.Stat.*;
import static enums.Weapon.*;
import static enums.Race.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import battle_units.BattleUnit;
import entities.Adventurer;
import entities.Companion;
import entities.Job;
import entities.LambdaAdventurer;
import entities.skills.DamageEffect;
import entities.skills.Skill;
import entities.skills.StatBuffEffect;

public class BattleUnitTest {

	BattleUnit unit;
	BattleUnit lambdaUnit;
	
	@Before
	public void setUpBeforeClass() throws Exception {
		List<Skill> skList = Arrays.asList(
				new Skill("Megabow, 50%", PINCER_ONLY, 50, new DamageEffect(PINCER, BOW, 1, NON_ELEMENTAL, 0)),
				new Skill("Attack +10%, Self, 30%", CHAINABLE, 30, new StatBuffEffect(SELF, 2, ATTACK, 10)),
				new Skill("Defense -10%, Wild Beast, 30%", CHAINABLE, 30),
				new Skill("Poison Arrows, Area (1), 30%", CHAINABLE, 30, new DamageEffect(AREA_1, BOW, 1, NON_ELEMENTAL, 0))
		);
		Job job = new Job(0, "Grace the Archer", 2942, 284, 220, 137, 155, BOW, NON_ELEMENTAL, skList);
		
		List<Skill> skList2 = Arrays.asList(
				new Skill("Defense +10%, Self, 30%", CHAINABLE, 30, new StatBuffEffect(SELF, 2, DEFENSE, 10)),
				new Skill("Megabow, Area (1), 30%", CHAINABLE, 30, new DamageEffect(AREA_1, BOW, 1, NON_ELEMENTAL, 0)),
				new Skill("HP +10%, Equip", EQUIP, 0, new StatBuffEffect(SELF, 0, HP, 10)),
				new Skill("Terabow, 30%", PINCER_ONLY, 30, new DamageEffect(PINCER, BOW, 3, NON_ELEMENTAL, 0))
		);
		Job job2 = new Job(1, "Grace the Markswoman", 3506, 311, 247, 165, 183, BOW, NON_ELEMENTAL, skList2);
		
		List<Skill> skList3 = Arrays.asList(
				new Skill("Attack +10%, Equip", EQUIP, 0, new StatBuffEffect(SELF, 0, ATTACK, 10)),
				new Skill("Defense +10%, Equip", EQUIP, 0, new StatBuffEffect(SELF, 0, DEFENSE, 10)),
				new Skill("Gigabow, 40%", PINCER_ONLY, 40, new DamageEffect(PINCER, BOW, 2, NON_ELEMENTAL, 0)),
				new Skill("Defense -20%, Dragon, 30%", CHAINABLE, 30)
		);
		Job job3 = new Job(2, "Grace the Ranger", 4043, 339, 275, 192, 210, BOW, NON_ELEMENTAL, skList3);
		
		Adventurer character = new Adventurer("Grace", "B", HUMAN, WARRIOR, Arrays.asList(job, job2, job3));
		unit = BattleUnit.from(character);
		
		List<Skill> skListL = Arrays.asList(
				new Skill("Gigabow, Area (1), 30%", CHAINABLE, 30, new DamageEffect(AREA_1, BOW, 2, NON_ELEMENTAL, 0)),
				new Skill("Control Time +2 Sec, EQUIP", EQUIP, 0),
				new Skill("Gigabow, Ring, 30%", CHAINABLE, 30, new DamageEffect(RING, BOW, 2, NON_ELEMENTAL, 0)),
				new Skill("Petabow, 30%", PINCER_ONLY, 30, new DamageEffect(PINCER, BOW, 3.5, NON_ELEMENTAL, 0))
		);
		Job jobL = new Job(0, "Grace ƒ©", 5220, 436, 318, 272, 291, BOW, NON_ELEMENTAL, skListL);
		LambdaAdventurer lambdaCharacter = new LambdaAdventurer("Grace ƒ©", "S", HUMAN, WARRIOR, Arrays.asList(jobL), character);
		lambdaUnit = BattleUnit.from(lambdaCharacter);
	}
	
	@Test
	public void getTest() {
		assertEquals(unit.getBaseHp(), 2942);
		assertEquals(unit.getBaseAtk(), 284);
		assertEquals(unit.getBaseDef(), 220);
		assertEquals(unit.getBaseMatk(), 137);
		assertEquals(unit.getBaseMdef(), 155);
	}
	
	@Test
	public void setJobTest() {
		unit.setActiveJob(1);
		assertEquals(unit.getBaseHp(), 3506);
		assertEquals(unit.getBaseAtk(), 311);
		assertEquals(unit.getBaseDef(), 247);
		assertEquals(unit.getBaseMatk(), 165);
		assertEquals(unit.getBaseMdef(), 183);
		
		unit.setActiveJob(2);
		assertEquals(unit.getBaseHp(), 4043);
		assertEquals(unit.getBaseAtk(), 339);
		assertEquals(unit.getBaseDef(), 275);
		assertEquals(unit.getBaseMatk(), 192);
		assertEquals(unit.getBaseMdef(), 210);
		
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void setJobTestLambda_Fail() {
		lambdaUnit.setActiveJob(1);
	}
	
	@Test
	public void companionTest() {
		Skill sk = new Skill("Attack +10%, Self, 30%", CHAINABLE, 30, new StatBuffEffect(SELF, 2, ATTACK, 10));
		Companion comp = new Companion("Skullsplitter", "B", 15, 0, 0, 0, Optional.of(sk));
		
		unit.setCompanion(comp);
		//assertEquals(unit.getEffectiveAtk(), 284 + 15);
		//assertEquals(unit.getEffectiveDef(), unit.getBaseDef() + comp.getDef());
		//assertEquals(unit.getEffectiveMatk(), unit.getBaseMatk() + comp.getMatk());
		//assertEquals(unit.getEffectiveMdef(), unit.getBaseMdef() + comp.getMdef());
		
		assertEquals(unit.getActiveSkills().size(), 5);
	}
	
	@Test
	public void equipSkillTest() {
		unit.setSlotSkill(0, 1, 1);
		assertEquals(unit.getActiveSkills().size(), 5);
		assertEquals(unit.getActiveSkills().get(4).getName(), "Megabow, Area (1), 30%");
		
		unit.setSlotSkill(1, 2, 0);
		assertEquals(unit.getActiveSkills().size(), 6);
		assertEquals(unit.getActiveSkills().get(4).getName(), "Megabow, Area (1), 30%");
		assertEquals(unit.getActiveSkills().get(5).getName(), "Attack +10%, Equip");
		
		unit.setSlotSkill(0, 2, 2);
		assertEquals(unit.getActiveSkills().size(), 6);
		assertEquals(unit.getActiveSkills().get(4).getName(), "Gigabow, 40%");
		assertEquals(unit.getActiveSkills().get(5).getName(), "Attack +10%, Equip");
	}
	
	@Test(expected = IllegalStateException.class) 
	public void equipSkillTestFail_EquipSkillMoreThanOnce() {
		unit.setSlotSkill(0, 1, 1);
		assertEquals(unit.getActiveSkills().size(), 5);
		assertEquals(unit.getActiveSkills().get(4).getName(), "Megabow, Area (1), 30%");
		
		unit.setSlotSkill(1, 1, 1);
	}
	
	@Test(expected = IllegalStateException.class) 
	public void equipSkillTestFail_EquipSkillFromActiveJob() {
		unit.setActiveJob(1);
		unit.setSlotSkill(0, 1, 3);
	}
	
	/*@Test
	public void applyStatBuffTest() {
		unit.addStatBuff(ATTACK, 30);
		assertEquals(unit.getEffectiveAtk(), 284 + 30);
		unit.addStatBuff(ATTACK, 30);
		assertEquals(unit.getEffectiveAtk(), 284 + 60);
		
		unit.addStatBuff(DEFENSE, 30);
		assertEquals(unit.getEffectiveDef(), 220 + 30);
		unit.addStatBuff(DEFENSE, 30);
		assertEquals(unit.getEffectiveDef(), 220 + 60);
		
		unit.addStatBuff(MAGIC_ATTACK, 30);
		assertEquals(unit.getEffectiveMatk(), 137 + 30);
		unit.addStatBuff(MAGIC_ATTACK, 30);
		assertEquals(unit.getEffectiveMatk(), 137 + 60);
		
		unit.addStatBuff(MAGIC_DEFENSE, 30);
		assertEquals(unit.getEffectiveMdef(), 155 + 30);
		unit.addStatBuff(MAGIC_DEFENSE, 30);
		assertEquals(unit.getEffectiveMdef(), 155 + 60);
	}
	
	@Test
	public void applyDamageBuffTest() {
		unit.addBuff(PHYSICAL_DAMAGE, 50);
		assertEquals(unit.getBuff(PHYSICAL_DAMAGE), 50);
		unit.addBuff(PHYSICAL_DAMAGE, 50);
		assertEquals(unit.getBuff(PHYSICAL_DAMAGE), 100);
	}*/

}
