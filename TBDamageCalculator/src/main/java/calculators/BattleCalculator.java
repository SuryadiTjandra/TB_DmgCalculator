package calculators;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import battle_units.BattleUnit;
import entities.skills.BuffEffect;
import entities.skills.DamageEffect;
import entities.skills.NonStatBuffEffect;
import entities.skills.Skill;
import entities.skills.SkillEffect;
import entities.skills.StatBuffEffect;
import enums.Area;
import enums.Buff;
import enums.Direction;
import enums.Element;
import enums.Stat;
import enums.Weapon;

import static enums.SkillActivation.*;
import static enums.Weapon.*;
import static enums.Buff.*;

public class BattleCalculator {
	
	private BattleUnit unit;
	
	public BattleCalculator(BattleUnit unit) {
		this.unit = unit;
	}
	
	private Stream<Skill> equipSkills(){
		return this.unit.getActiveSkills().stream()
				.filter(skill -> skill.getActivation() == EQUIP);
	}
	
	private Stream<Skill> pincerSkills(){
		return this.unit.getActiveSkills().stream()
				.filter(skill -> skill.getActivation() == PINCER_ONLY || skill.getActivation() == CHAINABLE);
	}
	
	private Stream<Skill> chainSkills(){
		return this.unit.getActiveSkills().stream()
				.filter(skill -> skill.getActivation() == CHAINABLE);
	}
	
	private List<DamageEffect> getDamageEffects(Stream<Skill> skills, Predicate<SkillEffect> targetPredicate){
		return skills
				.flatMap(sk -> sk.getEffects().stream())
				.filter(ef -> ef instanceof DamageEffect)
				.map(ef -> (DamageEffect)ef)
				.filter(targetPredicate)
				.collect(Collectors.toList());
	}
	
	private List<BuffEffect> getBuffEffects(Stream<Skill> skills){
		return skills
				.flatMap(sk -> sk.getEffects().stream())
				.filter(ef -> ef instanceof BuffEffect)
				.map(ef -> (BuffEffect)ef)
				.collect(Collectors.toList());
	}
	
	private Damage calculateDamage(List<DamageEffect> attacks, CalculationUnit calc) {
		Damage dmg = new Damage();
		
		for(DamageEffect attack: attacks) {
			Weapon weapon = attack.getWeapon() != CHARACTER_WEAPON ? attack.getWeapon() : calc.getWeapon();
			double basePhysDmg = Formula.physicalDamage(attack.getPhysPower(), calc.getEffectiveAtk(), 1);
			double physMultiplier = 1 + (calc.getBuff(PHYSICAL_DAMAGE)/100.0);
			
			dmg.addDamage(weapon, basePhysDmg * physMultiplier);
			
			Element element = attack.getElement();
			double baseMagDmg = Formula.magicalDamage(attack.getMagicPower(), calc.getEffectiveMatk(), 1);
			double magMultiplier = 1 + (calc.getBuff(getElementBuff(element)) / 100.0);
			
			dmg.addDamage(element, baseMagDmg * magMultiplier);
		}
		
		return dmg;
	}
	
	private void applyBuff(BuffEffect buff, CalculationUnit calc) {
		if (buff instanceof StatBuffEffect) {
			calc.addStatBuffByPercentage(
					((StatBuffEffect)buff).getBuffedStat(), 
					((StatBuffEffect)buff).getBuffPercentage()
			);
		} else if (buff instanceof NonStatBuffEffect) {
			calc.addBuff(
					((NonStatBuffEffect)buff).getBuff(),
					((NonStatBuffEffect)buff).getBuffPercentage()
			);
		}
	}
	
	private void applySteadyBuff(BuffEffect buff, CalculationUnit calc) {
		for (int i = 0; i < buff.getDuration() - 1; i++) {
			applyBuff(buff, calc);
		}
	}
	
	public Damage calculateSinglePincerPower(Direction direction, Distance target) {
		CalculationUnit calc = new CalculationUnit(unit);
		
		//apply equip skills
		List<BuffEffect> equipBuffs = getBuffEffects(equipSkills());
		equipBuffs.forEach(buff -> applyBuff(buff, calc));
		
		//apply buffs
		List<BuffEffect> activeBuffs = getBuffEffects(pincerSkills());
		activeBuffs.forEach(buff -> applyBuff(buff, calc));

		//calculate damage
		List<DamageEffect> attacks = getDamageEffects(pincerSkills(), ef -> getHittingLogic(target).test(ef.getArea(), direction));
		return calculateDamage(attacks, calc);
	}
	
	public Damage calculateSteadyDoublePincerPower(Direction direction, int noOfPhysTaps, int noOfMagicTaps) {
		CalculationUnit calc = new CalculationUnit(unit);
		Predicate<SkillEffect> targetPredicate = ef -> getHittingLogic(direction).test(ef.getArea());
		Damage dmg = new Damage();
		
		List<BuffEffect> chainBuffs = getBuffEffects(chainSkills());
		List<BuffEffect> pincerBuffs = getBuffEffects(pincerSkills());
		List<DamageEffect> chainAttacks = getDamageEffects(chainSkills(), targetPredicate);
		List<DamageEffect> pincerAttacks = getDamageEffects(pincerSkills(), targetPredicate);
		
		//apply equip skills
		List<BuffEffect> equipBuffs = getBuffEffects(equipSkills());
		equipBuffs.forEach(buff -> applyBuff(buff, calc));
		
		//apply buffs activated in previous turns
		chainBuffs.forEach(buff -> applySteadyBuff(buff, calc));
		pincerBuffs.forEach(buff -> applySteadyBuff(buff, calc));
		
		//apply tap buffs
		for (int i = 0; i < noOfPhysTaps; i++) {
			calc.addTapBuff(Stat.ATTACK);
		}
		for (int i = 0; i < noOfMagicTaps; i++) {
			calc.addTapBuff(Stat.MAGIC_ATTACK);
		}
		
		//first pincer, unit is not leading, only activating chain skills
		chainBuffs.forEach(buff -> applyBuff(buff, calc));
		dmg.addAllDamage(calculateDamage(chainAttacks, calc));
		
		//second pincer, unit is leading pincer
		pincerBuffs.forEach(buff -> applyBuff(buff, calc));
		dmg.addAllDamage(calculateDamage(pincerAttacks, calc));

		return dmg;
	}
	
	//helper methods below, probably should be moved to a helper class later
	private Buff getElementBuff(Element element) {
		switch(element) {
			case FIRE: return FIRE_ATTACK;
			case ICE: return ICE_ATTACK;
			case LIGHTNING: return LIGHTNING_ATTACK;
			case DARKNESS: return DARKNESS_ATTACK;
			case SOLAR: return SOLAR_ATTACK;
			case LUNAR: return LUNAR_ATTACK;
			case PHOTON: return PHOTON_ATTACK;
			case GRAVITON: return GRAVITON_ATTACK;
			case NON_ELEMENTAL: return NON_ELEMENTAL_ATTACK;
			default: return null;
		}
	}
	
	private BiPredicate<Area, Direction> getHittingLogic(Distance target){
		switch(target) {
			case ADJACENT: return HittingLogic::hitsAdjacent;
			case TWO_SPACE_AWAY: return HittingLogic::hitsTwoSpacesAway;
			case THREE_SPACE_AWAY: return HittingLogic::hitsThreePlusSpacesAway;
			default : return (a,d) -> false;
		}
	}
	
	private Predicate<Area> getHittingLogic(Direction direction){
		switch(direction) {
			case HORIZONTAL: return HittingLogic::hitsHorizontal;
			case VERTICAL: return HittingLogic::hitsVertical;
			default : return a -> false;
		}
	}

}
