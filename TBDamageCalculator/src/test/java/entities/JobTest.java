package entities;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import entities.Job;
import entities.skills.DamageEffect;
import entities.skills.Skill;
import entities.skills.StatBuffEffect;
import enums.Area;

import static enums.SkillActivation.*;
import static enums.Stat.*;
import static enums.Area.*;
import static enums.Weapon.*;
import static org.junit.Assert.assertEquals;
import static enums.Element.*;

public class JobTest {

	@Test
	public void jobTest() {
		List<Skill> skList = Arrays.asList(
				new Skill("Megabow, 50%", PINCER_ONLY, 50, new DamageEffect(PINCER, BOW, 1, NON_ELEMENTAL, 0)),
				new Skill("Attack +10%, Self, 30%", CHAINABLE, 30, new StatBuffEffect(Area.SELF, 2, ATTACK, 10)),
				new Skill("Defense -10%, Wild Beast, 30%", CHAINABLE, 30),
				new Skill("Poison Arrows, Area (1), 30%", CHAINABLE, 30, new DamageEffect(AREA_1, BOW, 1, NON_ELEMENTAL, 0))
		);
		Job job = new Job(0, "Grace the Archer", 2942, 284, 220, 137, 155, BOW, NON_ELEMENTAL, skList);
		
		assertEquals(job.getJobNo(), 0);
		assertEquals(job.getName(), "Grace the Archer");
		assertEquals(job.getHp(), 2942);
		assertEquals(job.getAtk(), 284);
		assertEquals(job.getDef(), 220);
		assertEquals(job.getMatk(), 137);
		assertEquals(job.getMdef(), 155);
		assertEquals(job.getWeapon(), BOW);
		assertEquals(job.getElement(), NON_ELEMENTAL);
		assertEquals(job.getJobSkills().get(0).getName(), "Megabow, 50%");
		assertEquals(job.getJobSkills().get(3).getActivation(), CHAINABLE);
	}
}
