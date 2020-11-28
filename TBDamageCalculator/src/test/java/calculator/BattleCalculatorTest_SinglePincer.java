package calculator;

import static org.junit.Assert.*;
import static enums.AdventurerType.*;
import static enums.Element.*;
import static enums.Race.*;
import static enums.SkillActivation.*;
import static enums.Weapon.*;
import static enums.Area.*;
import static enums.Direction.*;
import static enums.Stat.*;
import static enums.Buff.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import battle_units.BattleUnit;
import calculators.BattleCalculator;
import calculators.Damage;
import calculators.Distance;
import entities.Adventurer;
import entities.Companion;
import entities.Job;
import entities.skills.DamageEffect;
import entities.skills.NonStatBuffEffect;
import entities.skills.Skill;
import entities.skills.StatBuffEffect;
import enums.Direction;


@RunWith(Parameterized.class)
public class BattleCalculatorTest_SinglePincer {

	BattleCalculator bcal;
	Damage dmg;
	
	@Parameter(0)
	public BattleUnit unit;
	@Parameter(1) 
	public Direction direction;
	@Parameter(2)
	public Distance target;
	
	@Parameter(3) public double swordDmg;
	@Parameter(4) public double spearDmg;
	@Parameter(5) public double bowDmg;
	@Parameter(6) public double staffDmg;
	@Parameter(7) public double unarmedDmg;
	@Parameter(8) public double physDmg;
	@Parameter(9) public double fireDmg;
	@Parameter(10) public double iceDmg;
	@Parameter(11) public double lightningDmg;
	@Parameter(12) public double darknessDmg;
	@Parameter(13) public double solarDmg;
	@Parameter(14) public double lunarDmg;
	@Parameter(15) public double photonDmg;
	@Parameter(16) public double gravitonDmg;
	@Parameter(17) public double nonelemDmg;
	@Parameter(18) public double magicDmg;
	
	@Parameters()
	public static Collection<Object[]> params(){
		
		BattleUnit testUnit = BattleUnit.from(
			new Adventurer("Test", "B", OTHER, WARRIOR, Arrays.asList(
				new Job(0, "Test Job", 10, 1, 1, 1, 1, UNARMED, NON_ELEMENTAL, Arrays.asList(
					new Skill("Megasword, 1 Row, 30%", PINCER_ONLY, 30, new DamageEffect(ROW_1, SWORD, 1, NON_ELEMENTAL, 0)),
					new Skill("Gigaspear, 1 Column, 30%", PINCER_ONLY, 30, new DamageEffect(COLUMN_1, SPEAR, 2, NON_ELEMENTAL, 0)),
					new Skill("Terabow, Area (1), 30%", PINCER_ONLY, 30, new DamageEffect(AREA_1, BOW, 3, NON_ELEMENTAL, 0)),
					new Skill("HP+20%, Equip", EQUIP, 0, new StatBuffEffect(SELF, 0, HP, 20))
				))
			))
		);
		
		Object[] obj0 = new Object[] {testUnit, HORIZONTAL, Distance.ADJACENT			, 1.395	, 0		, 4.185	, 0	, 0	, 5.580	, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; 
		Object[] obj1 = new Object[] {testUnit, VERTICAL, Distance.ADJACENT			, 0		, 2.790	, 4.185	, 0	, 0	, 6.975	, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; 
		Object[] obj2 = new Object[] {testUnit, HORIZONTAL, Distance.TWO_SPACE_AWAY	, 1.395	, 0		, 0		, 0	, 0	, 1.395	, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; 
		Object[] obj3 = new Object[] {testUnit, VERTICAL, Distance.TWO_SPACE_AWAY		, 0		, 2.790	, 0		, 0	, 0	, 2.790	, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; 
		Object[] obj4 = new Object[] {testUnit, HORIZONTAL, Distance.THREE_SPACE_AWAY	, 1.395	, 0		, 0		, 0	, 0	, 1.395	, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; 
		Object[] obj5 = new Object[] {testUnit, VERTICAL, Distance.THREE_SPACE_AWAY	, 0		, 2.790	, 0		, 0	, 0	, 2.790	, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; 

		BattleUnit testUnit2 = BattleUnit.from(
				new Adventurer("Test2", "B", OTHER, SPELLBLADE, Arrays.asList(
					new Job(0, "Test2 the Job1", 100, 5, 5, 5, 5, SWORD, FIRE, Arrays.asList(
						new Skill("Fire Blade, 80%", PINCER_ONLY, 80, new DamageEffect(PINCER, SWORD, 0.3, FIRE, 1)),
						new Skill("Attack +10%, Equip", EQUIP, 0, new StatBuffEffect(SELF, 0, ATTACK, 10)),
						new Skill("Blazing Counter, 30%", COUNTER, 30),
						new Skill("Inferno, Pincer Area(1), 50%", CHAINABLE, 0, new DamageEffect(PINCER_AREA_1, UNARMED, 0, FIRE, 2))
					)),
					new Job(1, "Test2 the Job2", 150, 8, 8, 8, 8, BOW, LIGHTNING, Arrays.asList(
						new Skill("Lightning Arrows, Area (1), 30%", CHAINABLE, 80, new DamageEffect(AREA_1, BOW, 0.5, LIGHTNING, 2)),
						new Skill("Levitation, Equip", EQUIP, 0),
						new Skill("Thunder & Arrows, Ring, 30%", CHAINABLE, 80, new DamageEffect(RING, BOW, 0.8, LIGHTNING, 0.8)),
						new Skill("Demoralization Guard", EQUIP, 0)
					)),
					new Job(2, "Test2 the Job3", 200, 10, 10, 10, 10, STAFF, HEALING, Arrays.asList(
						new Skill("Solar Flare Blast, 30%", CHAINABLE, 30, new DamageEffect(PINCER, CHARACTER_WEAPON, 1.3, SOLAR, 3.5)),
						new Skill("Holy Blast, 40%", CHAINABLE, 30, new DamageEffect(PINCER, CHARACTER_WEAPON, 1.3, PHOTON, 3.5)),
						new Skill("Tera Heal, 30%", CHAINABLE, 30),
						new Skill("Megasword, 50%", PINCER_ONLY, 50, new DamageEffect(PINCER, SWORD, 1, NON_ELEMENTAL, 0))
					))
				))
			);
		testUnit2.setCompanion(new Companion("Memorial Bow", "A", 0, 0, 40, 0, Optional.empty()));
		testUnit2.setActiveJob(2);
		testUnit2.setSlotSkill(0, 1, 0);
		testUnit2.setSlotSkill(1, 0, 3);
		testUnit2.setSlotSkill(2, 1, 2);
		testUnit2.setSlotSkill(3, 0, 0);
		
		Object[] obj6 = new Object[] {testUnit2, HORIZONTAL, Distance.ADJACENT		, 90.891, 0, 34.958, 181.783, 0, 307.632, 3479.057, 0, 2319.371, 0, 4058.900, 0, 4058.900, 0, 0, 13916.227}; 
		Object[] obj7 = new Object[] {testUnit2, HORIZONTAL, Distance.TWO_SPACE_AWAY	, 90.891, 0, 55.933, 181.783, 0, 328.607, 3479.057, 0,  927.748, 0, 4058.900, 0, 4058.900, 0, 0, 12524.605};
		Object[] obj8 = new Object[] {testUnit2, HORIZONTAL, Distance.THREE_SPACE_AWAY, 90.891, 0,      0, 181.783, 0, 272.674, 3479.057, 0,        0, 0, 4058.900, 0, 4058.900, 0, 0, 11596.856}; 
		
		BattleUnit testUnit3 = BattleUnit.from(
				new Adventurer("Test3", "B", OTHER, SPELLBLADE, Arrays.asList(
					new Job(0, "Test3 Job", 1000, 100, 100, 100, 100, UNARMED, NON_ELEMENTAL, Arrays.asList(
						new Skill("Oblivion & Sword, 30%", PINCER_ONLY, 30, new DamageEffect(PINCER, SWORD, 3, DARKNESS, 3)),
						new Skill("Magic Attack +10%, Self, 30%", CHAINABLE, 30, new StatBuffEffect(SELF, 2, MAGIC_ATTACK, 10)),
						new Skill("Attack +10%, Equip", EQUIP, 30, new StatBuffEffect(SELF, 0, ATTACK, 10)),
						new Skill("Physical Damage x1.5, Self, 30%", CHAINABLE, 30, new NonStatBuffEffect(SELF, 2, PHYSICAL_DAMAGE, 50))
					))
				))
			);
		testUnit3.setCompanion(new Companion("Peyrna Sword", "Z", 30, 25, 0, 0, Optional.of(
									new Skill("Attack +15%, Self, 100%", CHAINABLE, 100, 
										new StatBuffEffect(SELF, 2, ATTACK, 15)))));
		
		Object[] obj9 = new Object[] {testUnit3, HORIZONTAL, Distance.ADJACENT, 33216.619, 0, 0, 0, 0, 33216.619, 0, 0, 0, 13291.686, 0, 0, 0, 0, 0, 13291.686}; 
		return Arrays.asList(obj0, obj1, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
	}
	
	@Before
	public void setUp() throws Exception {
		bcal = new BattleCalculator(unit);
		dmg = bcal.calculateSinglePincerPower(direction, target);
	}

	@Test
	public void test() {
		assertEquals(swordDmg, dmg.getDamage(SWORD), 0.01);
		assertEquals(spearDmg, dmg.getDamage(SPEAR), 0.01);
		assertEquals(bowDmg, dmg.getDamage(BOW), 0.01);
		assertEquals(staffDmg, dmg.getDamage(STAFF), 0.01);
		assertEquals(unarmedDmg, dmg.getDamage(UNARMED), 0.01);
		assertEquals(physDmg, dmg.getTotalPhysicalDamage(), 0.01);
		assertEquals(fireDmg, dmg.getDamage(FIRE), 0.01);
		assertEquals(iceDmg, dmg.getDamage(ICE), 0.01);
		assertEquals(lightningDmg, dmg.getDamage(LIGHTNING), 0.01);
		assertEquals(darknessDmg, dmg.getDamage(DARKNESS), 0.01);
		assertEquals(solarDmg, dmg.getDamage(SOLAR), 0.01);
		assertEquals(lunarDmg, dmg.getDamage(LUNAR), 0.01);
		assertEquals(photonDmg, dmg.getDamage(PHOTON), 0.01);
		assertEquals(gravitonDmg, dmg.getDamage(GRAVITON), 0.01);
		assertEquals(nonelemDmg, dmg.getDamage(NON_ELEMENTAL), 0.01);
		assertEquals(magicDmg, dmg.getTotalMagicalDamage(), 0.01);
	}

}
