package entities;

import static enums.Area.*;
import static enums.Element.*;
import static enums.SkillActivation.*;
import static enums.Stat.*;
import static enums.Weapon.*;
import static enums.AdventurerType.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import entities.skills.DamageEffect;
import entities.skills.Skill;
import entities.skills.StatBuffEffect;
import enums.Area;
import enums.Race;

public class AdventurerTest {

	Adventurer character;
	LambdaAdventurer lambdaCharacter;
	
	@Before
	public void setUpBefore() throws Exception {
		List<Skill> skList = Arrays.asList(
				new Skill("Megabow, 50%", PINCER_ONLY, 50, new DamageEffect(PINCER, BOW, 1, NON_ELEMENTAL, 0)),
				new Skill("Attack +10%, Self, 30%", CHAINABLE, 30, new StatBuffEffect(Area.SELF, 2, ATTACK, 10)),
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
		
		character = new Adventurer("Grace", "B", Race.HUMAN, WARRIOR, Arrays.asList(job, job2, job3));
		
		
		List<Skill> skListL = Arrays.asList(
				new Skill("Gigabow, Area (1), 30%", CHAINABLE, 30, new DamageEffect(AREA_1, BOW, 2, NON_ELEMENTAL, 0)),
				new Skill("Control Time +2 Sec, EQUIP", EQUIP, 0),
				new Skill("Gigabow, Ring, 30%", CHAINABLE, 30, new DamageEffect(RING, BOW, 2, NON_ELEMENTAL, 0)),
				new Skill("Petabow, 30%", PINCER_ONLY, 30, new DamageEffect(PINCER, BOW, 3.5, NON_ELEMENTAL, 0))
		);
		Job jobL = new Job(0, "Grace ƒ©", 5220, 436, 318, 272, 291, BOW, NON_ELEMENTAL, skListL);
		lambdaCharacter = new LambdaAdventurer("Grace ƒ©", "S", Race.HUMAN, WARRIOR, Arrays.asList(jobL), character);
	}

	@Test
	public void characterTest() {
		assertEquals(character.getName(), "Grace");
		assertEquals(character.getRarity(), "B");
		assertEquals(character.getJobs().get(0).getName(), "Grace the Archer");
		assertEquals(character.getJobs().get(0).getJobSkills().get(0).getName(), "Megabow, 50%");
	}
	
	@Test
	public void lambdaCharacterTest() {
		assertEquals(lambdaCharacter.getName(), "Grace ƒ©");
		assertEquals(lambdaCharacter.getRarity(), "S");
		assertEquals(lambdaCharacter.getJobs().get(0).getName(), "Grace ƒ©");
		assertEquals(lambdaCharacter.getJobs().get(0).getJobSkills().get(0).getName(), "Gigabow, Area (1), 30%");
		assertEquals(lambdaCharacter.getBaseForm().getName(), "Grace");
		assertEquals(lambdaCharacter.getBaseForm().getJobs().get(0).getName(), "Grace the Archer");
	}

}
