package entities.skill;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Test;
import entities.skills.DamageEffect;
import entities.skills.NonStatBuffEffect;
import entities.skills.Skill;
import entities.skills.StatBuffEffect;
import enums.Area;
import enums.Buff;
import enums.Element;
import enums.SkillActivation;
import enums.Stat;
import enums.Weapon;

public class SkillTest {

	@Test
	public void skillTest() {
		Skill sk = new Skill("Mega Heal, Self", SkillActivation.CHAINABLE, 30);
		assertEquals(sk.getName(), "Mega Heal, Self");
		assertEquals(sk.getActivation(), SkillActivation.CHAINABLE);
		assertEquals(sk.getActivationRate(), 30);
		assertEquals(sk.getEffects(), Collections.emptyList());
	}
	
	@Test
	public void damageSkillTest() {
		DamageEffect ef = new DamageEffect(Area.PINCER, Weapon.SWORD, 1, Element.NON_ELEMENTAL, 0);
		assertEquals(ef.getWeapon(), Weapon.SWORD);
		assertEquals(ef.getPhysPower(), 1, 0.01);
		assertEquals(ef.getElement(), Element.NON_ELEMENTAL);
		assertEquals(ef.getMagicPower(), 0, 0.01);
		
		Skill sk = new Skill("Megasword, 50%", SkillActivation.PINCER_ONLY, 50, ef);
		assertEquals(sk.getName(), "Megasword, 50%");
		assertEquals(sk.getActivation(), SkillActivation.PINCER_ONLY);
		assertEquals(sk.getActivationRate(), 50);
		assertEquals(sk.getEffects().get(0).getArea(), Area.PINCER);
		
	}
	
	@Test
	public void damageSkillTest2() {
		DamageEffect ef = new DamageEffect(Area.PINCER_AREA_1, Weapon.UNARMED, 0, Element.FIRE, 2);
		assertEquals(ef.getWeapon(), Weapon.UNARMED);
		assertEquals(ef.getPhysPower(), 0, 0.01);
		assertEquals(ef.getElement(), Element.FIRE);
		assertEquals(ef.getMagicPower(), 2, 0.01);
		
		Skill sk = new Skill("Inferno, Pincer Area (1), 30%", SkillActivation.CHAINABLE, 30, ef);
		assertEquals(sk.getName(), "Inferno, Pincer Area (1), 30%");
		assertEquals(sk.getActivation(), SkillActivation.CHAINABLE);
		assertEquals(sk.getActivationRate(), 30);
		assertEquals(sk.getEffects().get(0).getArea(), Area.PINCER_AREA_1);
	}
	
	@Test
	public void damageSkillTest3() {
		DamageEffect ef = new DamageEffect(Area.PINCER, Weapon.SPEAR, 2.6, Element.LIGHTNING, 2.6);
		assertEquals(ef.getWeapon(), Weapon.SPEAR);
		assertEquals(ef.getPhysPower(), 2.6, 0.01);
		assertEquals(ef.getElement(), Element.LIGHTNING);
		assertEquals(ef.getMagicPower(), 2.6, 0.01);
		
		Skill sk = new Skill("Tempest & Spear, 30%", SkillActivation.PINCER_ONLY, 30, ef); 
		assertEquals(sk.getName(), "Tempest & Spear, 30%");
		assertEquals(sk.getActivation(), SkillActivation.PINCER_ONLY);
		assertEquals(sk.getActivationRate(), 30);
		assertEquals(sk.getEffects().get(0).getArea(), Area.PINCER);
	}
	
	@Test
	public void statBuffSkillTest() {
		StatBuffEffect ef = new StatBuffEffect(Area.SELF, 2, Stat.ATTACK, 10);
		assertEquals(ef.getBuffedStat(), Stat.ATTACK);
		assertEquals(ef.getBuffPercentage(), 10);
		
		Skill sk = new Skill("Attack +10%, Self, 30%", SkillActivation.CHAINABLE, 30, ef);
		assertEquals(sk.getName(), "Attack +10%, Self, 30%");
		assertEquals(sk.getActivation(), SkillActivation.CHAINABLE);
		assertEquals(sk.getActivationRate(), 30);
		assertEquals(sk.getEffects().get(0).getArea(), Area.SELF);
		
	}
	
	@Test
	public void damageBuffSkillTest() {
		NonStatBuffEffect ef = new NonStatBuffEffect(Area.SELF, 2, Buff.LIGHTNING_ATTACK, 50);
		assertEquals(ef.getBuff(), Buff.LIGHTNING_ATTACK);
		assertEquals(ef.getBuffPercentage(), 50);
		
		Skill sk = new Skill("Lightning Attack x1.5, Equip", SkillActivation.EQUIP, 0, ef);
		assertEquals(sk.getName(), "Lightning Attack x1.5, Equip");
		assertEquals(sk.getActivation(), SkillActivation.EQUIP);
		assertEquals(sk.getActivationRate(), 0);
		assertEquals(sk.getEffects().get(0).getArea(), Area.SELF);
	}
}
