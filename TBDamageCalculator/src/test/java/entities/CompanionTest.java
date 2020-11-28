package entities;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;

import entities.skills.DamageEffect;
import entities.skills.Skill;

import static enums.Weapon.*;
import static enums.Area.PINCER;
import static enums.Element.*;
import static enums.SkillActivation.*;

public class CompanionTest {

	@Test
	public void companionTest() {
		Skill sk = new Skill("Petaspear, 100%", CHAINABLE, 100, new DamageEffect(PINCER, SPEAR, 3.5, NON_ELEMENTAL, 0));
		Companion comp = new Companion("Grand Rune", "Z", 30, 25, 0, 0, Optional.of(sk));
		
		assertEquals(comp.getName(), "Grand Rune");
		assertEquals(comp.getRarity(), "Z");
		assertEquals(comp.getAtk(), 30);
		assertEquals(comp.getDef(), 25);
		assertEquals(comp.getMatk(), 0);
		assertEquals(comp.getMdef(), 0);
		assertEquals(comp.getSkill().get().getName(), "Petaspear, 100%");
	}
	
	@Test
	public void companionTest2() {
		Companion comp = new Companion("Earth Sword", "S", 80, 0, 0, 0, Optional.empty());
		
		assertEquals(comp.getName(), "Earth Sword");
		assertEquals(comp.getRarity(), "S");
		assertEquals(comp.getAtk(), 80);
		assertEquals(comp.getDef(), 0);
		assertEquals(comp.getMatk(), 0);
		assertEquals(comp.getMdef(), 0);
		assertFalse(comp.getSkill().isPresent());
	}

}
