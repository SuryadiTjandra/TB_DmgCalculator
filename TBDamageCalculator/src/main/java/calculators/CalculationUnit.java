package calculators;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Function;
import battle_units.BattleUnit;
import entities.Companion;
import entities.skills.Skill;
import enums.Buff;
import enums.Element;
import enums.Stat;
import enums.Weapon;

import static enums.Stat.*;

class CalculationUnit {
	private BattleUnit unit;
	
	
	private EnumMap<Stat, Integer> statBuffs = new EnumMap<>(Stat.class);
	private EnumMap<Stat, Integer> tapStatBuffs = new EnumMap<>(Stat.class);
	private EnumMap<Buff, Integer> nonStatBuffs = new EnumMap<>(Buff.class);
	
	CalculationUnit(BattleUnit unit){
		this.unit = unit;
		applyEquipSkills();
	}
	
	public void applyEquipSkills() {
		
	}
	
	public void applyBuffs() {
		
	}
	
	public List<Skill> getEquippedSkills(){
		return unit.getActiveSkills();
	}
	
	public void addStatBuff(Stat stat, int buffAmount) {
		statBuffs.merge(stat, buffAmount, (cur, plus) -> cur + plus);
		//normal buff is capped at 100%
		statBuffs.compute(stat, (st, buff) -> Math.min(buff, getBaseStat(st)));
	}
	
	public void addBuff(Buff buff, int buffPercentage) {
		nonStatBuffs.merge(buff, buffPercentage, (cur, plus) -> cur + plus);
	}
	
	public void addStatBuffByPercentage(Stat stat, int buffPercentage) {
		int baseStat = getBaseStat(stat);		
		addStatBuff(stat, calculateStatBuff(baseStat, buffPercentage));
	}
	
	public void addTapBuff(Stat stat) {
		int baseStat = getBaseStat(stat);
		int buffAmount = calculateStatBuff(baseStat, 30);
		
		tapStatBuffs.merge(stat, buffAmount, (cur, plus) -> cur + plus);
		//tap buff is capped at 200%
		tapStatBuffs.compute(stat, (st, buff) -> Math.min(buff, 2*getBaseStat(st)));
	}
	
	private int getBaseStat(Stat stat) {
		switch (stat) {
			case HP : return unit.getBaseHp(); 
			case ATTACK : return unit.getBaseAtk();
			case DEFENSE : return unit.getBaseDef(); 
			case MAGIC_ATTACK : return unit.getBaseMatk();
			case MAGIC_DEFENSE : return unit.getBaseMdef(); 
			default: return 0;
		}
	}
	
	private int calculateStatBuff(int statValue, int statBuffPercentage) {
		return (int) Math.ceil(statValue * statBuffPercentage / 100.0);
	}
	
	public Weapon getWeapon() {
		return this.unit.getActiveJob().getWeapon();
	}
	
	public Element getElement() {
		return this.unit.getActiveJob().getElement();
	}
	
	public int getBuff(Buff buff) {
		return nonStatBuffs.getOrDefault(buff, 0);
	}
	
	public int getEffectiveHp() {
		return this.getEffectiveStat(unit, HP);
	}
	
	public int getEffectiveAtk() {
		return this.getEffectiveStat(unit, ATTACK);
	}
	
	public int getEffectiveDef() {
		return this.getEffectiveStat(unit, DEFENSE);
	}
	
	public int getEffectiveMatk() {
		return this.getEffectiveStat(unit, MAGIC_ATTACK);
	}
	
	public int getEffectiveMdef() {
		return this.getEffectiveStat(unit, MAGIC_DEFENSE);
	}
	
	private int getEffectiveStat(BattleUnit unit, Stat stat) {
		int baseStat = getBaseStat(stat);
		int buffStat = Math.min(
				statBuffs.getOrDefault(stat, 0) + tapStatBuffs.getOrDefault(stat, 0),
				2 * baseStat);
		
		Function<Companion, Integer> compStatGetter;		
		switch(stat) {
			case HP: compStatGetter = c -> 0;break;
			case ATTACK: compStatGetter = Companion::getAtk;break;
			case DEFENSE: compStatGetter = Companion::getDef;break;
			case MAGIC_ATTACK: compStatGetter = Companion::getMatk;	break;
			case MAGIC_DEFENSE: compStatGetter = Companion::getMdef; break;
			default: compStatGetter = c -> 0;
		}
		
		int compStat = unit.getCompanion().map(compStatGetter).orElse(0);		
		return baseStat + buffStat + compStat;
	}
}
