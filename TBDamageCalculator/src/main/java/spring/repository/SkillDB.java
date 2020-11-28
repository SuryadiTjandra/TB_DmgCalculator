package spring.repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import entities.skills.DamageEffect;
import entities.skills.NonStatBuffEffect;
import entities.skills.Skill;
import entities.skills.StatBuffEffect;

import static enums.SkillActivation.*;
import static enums.Weapon.*;
import static enums.Element.*;
import static enums.Area.*;
import static enums.Stat.*;
import static enums.Buff.*;

class SkillDB {
	
	private static Map<String, Skill> storage;
	
	private static void init(){
		storage = new HashMap<String, Skill>();
		
		//physical skills
		storage.put("megasword", new Skill("Megasword, 50%", PINCER_ONLY, 50, new DamageEffect(PINCER, SWORD, 1, NON_ELEMENTAL, 0)));
		storage.put("megaspear", new Skill("Megaspear, 50%", PINCER_ONLY, 50, new DamageEffect(PINCER, SPEAR, 1, NON_ELEMENTAL, 0)));
		storage.put("megabow", new Skill("Megabow, 50%", PINCER_ONLY, 50, new DamageEffect(PINCER, BOW, 1, NON_ELEMENTAL, 0)));
		
		storage.put("gigasword", new Skill("Gigasword, 40%", PINCER_ONLY, 40, new DamageEffect(PINCER, SWORD, 2, NON_ELEMENTAL, 0)));
		storage.put("gigaspear", new Skill("Gigaspear, 40%", PINCER_ONLY, 40, new DamageEffect(PINCER, SPEAR, 2, NON_ELEMENTAL, 0)));
		storage.put("gigabow", new Skill("Gigabow, 40%", PINCER_ONLY, 40, new DamageEffect(PINCER, BOW, 2, NON_ELEMENTAL, 0)));
		
		storage.put("terasword", new Skill("Terasword, 30%", PINCER_ONLY, 30, new DamageEffect(PINCER, SWORD, 3, NON_ELEMENTAL, 0)));
		storage.put("teraspear", new Skill("Teraspear, 30%", PINCER_ONLY, 30, new DamageEffect(PINCER, SPEAR, 3, NON_ELEMENTAL, 0)));
		storage.put("terabow", new Skill("Terabow, 30%", PINCER_ONLY, 30, new DamageEffect(PINCER, BOW, 3, NON_ELEMENTAL, 0)));
		
		storage.put("petasword", new Skill("Petasword, 30%", PINCER_ONLY, 30, new DamageEffect(PINCER, SWORD, 3.5, NON_ELEMENTAL, 0)));
		storage.put("petaspear", new Skill("Petaspear, 30%", PINCER_ONLY, 30, new DamageEffect(PINCER, SPEAR, 3.5, NON_ELEMENTAL, 0)));
		storage.put("petabow", new Skill("Petabow, 30%", PINCER_ONLY, 30, new DamageEffect(PINCER, BOW, 3.5, NON_ELEMENTAL, 0)));
		
		storage.put("petasword_chain", new Skill("Petasword, 30%", CHAINABLE, 30, new DamageEffect(PINCER, SWORD, 3.5, NON_ELEMENTAL, 0)));
		storage.put("petaspear_chain", new Skill("Petaspear, 30%", CHAINABLE, 30, new DamageEffect(PINCER, SPEAR, 3.5, NON_ELEMENTAL, 0)));
		storage.put("petabow_chain", new Skill("Petabow, 30%", CHAINABLE, 30, new DamageEffect(PINCER, BOW, 3.5, NON_ELEMENTAL, 0)));
		
		storage.put("megabow_area1_chain", new Skill("Megabow, Area (1), 30%", CHAINABLE, 30, new DamageEffect(AREA_1, BOW, 1, NON_ELEMENTAL, 0)));
		storage.put("poison_arrows_area1_chain", new Skill("Poison Arrows, Area (1), 30%", CHAINABLE, 30, new DamageEffect(AREA_1, BOW, 1, NON_ELEMENTAL, 0)));
		
		storage.put("gigasword_1row", new Skill("Gigasword, 1 Row, 30%", PINCER_ONLY, 30, new DamageEffect(ROW_1, SWORD, 2, NON_ELEMENTAL, 0)));
		storage.put("gigaspear_1column", new Skill("Gigaspear, 1 Column, 30%", PINCER_ONLY, 30, new DamageEffect(COLUMN_1, SPEAR, 2, NON_ELEMENTAL, 0)));
		storage.put("gigabow_area1", new Skill("Gigabow, Area(1), 30%", CHAINABLE, 30, new DamageEffect(AREA_1, BOW, 2, NON_ELEMENTAL, 0)));
		
		storage.put("cross_cleave", new Skill("Cross Cleave, Cross (All), 30%", CHAINABLE, 30, new DamageEffect(CROSS_ALL, SWORD, 2, NON_ELEMENTAL, 0)));
		storage.put("gigaspear_crossall", new Skill("Gigaspear, Cross (All), 30%", CHAINABLE, 30, new DamageEffect(CROSS_ALL, SPEAR, 2, NON_ELEMENTAL, 0)));
		storage.put("gigabow_ring", new Skill("Gigabow, Ring, 30%", CHAINABLE, 30, new DamageEffect(RING, BOW, 2, NON_ELEMENTAL, 0)));
		
		storage.put("sword_of_valor", new Skill("Sword of Valor, 3 Rows, 30%", PINCER_ONLY, 30, new DamageEffect(ROW_3, SWORD, 2.6, NON_ELEMENTAL, 0)));
		
		
		//magical skill
		
		
		
		//buff
		storage.put("hp_buff_5_equip", new Skill("HP +5%, Equip", EQUIP, 0, new StatBuffEffect(SELF, 0, HP, 5)));
		storage.put("hp_buff_10_equip", new Skill("HP +10%, Equip", EQUIP, 0, new StatBuffEffect(SELF, 0, HP, 10)));
		storage.put("hp_buff_15_equip", new Skill("HP +15%, Equip", EQUIP, 0, new StatBuffEffect(SELF, 0, HP, 15)));
		storage.put("hp_buff_20_equip", new Skill("HP +20%, Equip", EQUIP, 0, new StatBuffEffect(SELF, 0, HP, 20)));
		
		storage.put("atk_buff_2_active", new Skill("Attack +2%, Self, 30%", CHAINABLE, 30, new StatBuffEffect(SELF, 2, ATTACK, 2)));
		storage.put("atk_buff_5_active", new Skill("Attack +5%, Self, 30%", CHAINABLE, 30, new StatBuffEffect(SELF, 2, ATTACK, 5)));
		storage.put("atk_buff_10_active", new Skill("Attack +10%, Self, 30%", CHAINABLE, 30, new StatBuffEffect(SELF, 2, ATTACK, 10)));
		storage.put("atk_buff_15_active", new Skill("Attack +15%, Self, 30%", CHAINABLE, 30, new StatBuffEffect(SELF, 2, ATTACK, 15)));
		storage.put("atk_buff_5_equip", new Skill("Attack +5%, Equip", EQUIP, 0, new StatBuffEffect(SELF, 0, ATTACK, 5)));
		storage.put("atk_buff_10_equip", new Skill("Attack +10%, Equip", EQUIP, 0, new StatBuffEffect(SELF, 0, ATTACK, 10)));
		
		storage.put("def_buff_2_active", new Skill("Defense +2%, Self, 30%", CHAINABLE, 30, new StatBuffEffect(SELF, 2, DEFENSE, 2)));
		storage.put("def_buff_5_active", new Skill("Defense +5%, Self, 30%", CHAINABLE, 30, new StatBuffEffect(SELF, 2, DEFENSE, 5)));
		storage.put("def_buff_10_active", new Skill("Defense +10%, Self, 30%", CHAINABLE, 30, new StatBuffEffect(SELF, 2, DEFENSE, 10)));
		storage.put("def_buff_15_active", new Skill("Defense +15%, Self, 30%", CHAINABLE, 30, new StatBuffEffect(SELF, 2, DEFENSE, 15)));
		storage.put("def_buff_5_equip", new Skill("Defense +5%, Equip", EQUIP, 0, new StatBuffEffect(SELF, 0, DEFENSE, 5)));
		storage.put("def_buff_10_equip", new Skill("Defense +10%, Equip", EQUIP, 0, new StatBuffEffect(SELF, 0, DEFENSE, 10)));

		storage.put("matk_buff_2_active", new Skill("Magic Attack +2%, Self, 30%", CHAINABLE, 30, new StatBuffEffect(SELF, 2, MAGIC_ATTACK, 2)));
		storage.put("matk_buff_5_active", new Skill("Magic Attack +5%, Self, 30%", CHAINABLE, 30, new StatBuffEffect(SELF, 2, MAGIC_ATTACK, 5)));
		storage.put("matk_buff_10_active", new Skill("Magic Attack +10%, Self, 30%", CHAINABLE, 30, new StatBuffEffect(SELF, 2, MAGIC_ATTACK, 10)));
		storage.put("matk_buff_15_active", new Skill("Magic Attack +15%, Self, 30%", CHAINABLE, 30, new StatBuffEffect(SELF, 2, MAGIC_ATTACK, 15)));
		storage.put("matk_buff_5_equip", new Skill("Magic Attack +5%, Equip", EQUIP, 0, new StatBuffEffect(SELF, 0, MAGIC_ATTACK, 5)));
		storage.put("matk_buff_10_equip", new Skill("Magic Attack +10%, Equip", EQUIP, 0, new StatBuffEffect(SELF, 0, MAGIC_ATTACK, 10)));
		
		storage.put("mdef_buff_2_active", new Skill("Magic Defense +2%, Self, 30%", CHAINABLE, 30, new StatBuffEffect(SELF, 2, MAGIC_DEFENSE, 2)));
		storage.put("mdef_buff_5_active", new Skill("Magic Defense +5%, Self, 30%", CHAINABLE, 30, new StatBuffEffect(SELF, 2, MAGIC_DEFENSE, 5)));
		storage.put("mdef_buff_10_active", new Skill("Magic Defense +10%, Self, 30%", CHAINABLE, 30, new StatBuffEffect(SELF, 2, MAGIC_DEFENSE, 10)));
		storage.put("mdef_buff_15_active", new Skill("Magic Defense +15%, Self, 30%", CHAINABLE, 30, new StatBuffEffect(SELF, 2, MAGIC_DEFENSE, 15)));
		storage.put("mdef_buff_5_equip", new Skill("Magic Defense +5%, Equip", EQUIP, 0, new StatBuffEffect(SELF, 0, MAGIC_DEFENSE, 5)));
		storage.put("mdef_buff_10_equip", new Skill("Magic Defense +10%, Equip", EQUIP, 0, new StatBuffEffect(SELF, 0, MAGIC_DEFENSE, 10)));

		//stat debuff
		storage.put("def_debuff_10_wildbeast", new Skill("Defense -10%, Wild Beast, 30%", CHAINABLE, 30));
		storage.put("def_debuff_20_wildbeast", new Skill("Defense -20%, Wild Beast, 30%", CHAINABLE, 30));
		storage.put("def_debuff_20_dragon", new Skill("Defense -20%, Dragon, 30%", CHAINABLE, 30));
		storage.put("def_debuff_20_machine", new Skill("Defense -20%, Machine, 30%", CHAINABLE, 30));
		storage.put("def_debuff_20_oxcesian", new Skill("Defense -20%, Oxcesian, 30%", CHAINABLE, 30));
		
		//damage buff
		storage.put("phys_buff_20_active", new Skill("Physical Damage x1.2, Self, 30%", CHAINABLE, 30, new NonStatBuffEffect(SELF, 2, PHYSICAL_DAMAGE, 20)));
		storage.put("phys_buff_50_active", new Skill("Physical Damage x1.5, Self, 30%", CHAINABLE, 30, new NonStatBuffEffect(SELF, 2, PHYSICAL_DAMAGE, 50)));

		storage.put("lightning_buff_50_active", new Skill("Lightning Attack x1.5, Self, 30%", CHAINABLE, 30, new NonStatBuffEffect(SELF, 2, LIGHTNING_ATTACK, 50)));
		storage.put("fire_buff_50_equip", new Skill("Fire Attack x1.5, Equip", EQUIP, 0, new NonStatBuffEffect(SELF, 0, FIRE_ATTACK, 20)));
		storage.put("ice_buff_50_equip", new Skill("Ice Attack x1.5, Equip", EQUIP, 0, new NonStatBuffEffect(SELF, 0, ICE_ATTACK, 20)));
		storage.put("lightning_buff_50_equip", new Skill("Lightning Attack x1.5, Equip", EQUIP, 0, new NonStatBuffEffect(SELF, 0, LIGHTNING_ATTACK, 20)));
		storage.put("dark_buff_50_equip", new Skill("Darkness Attack x1.5, Equip", EQUIP, 0, new NonStatBuffEffect(SELF, 0, DARKNESS_ATTACK, 20)));

		//shields
		
		//control time
		storage.put("ct1_equip", new Skill("Control Time +1 Sec, Equip", EQUIP, 0));
		storage.put("ct2_equip", new Skill("Control Time +2 Sec, Equip", EQUIP, 0));
		storage.put("ct4_equip", new Skill("Control Time +4 Sec, Equip", EQUIP, 0));
		storage.put("ct2_chain", new Skill("Control Time +2 Sec, Chain, 30%", CHAINABLE, 0));
		
		
		//ailments
		storage.put("demoral_ward", new Skill("Demoralization Ward, Equip", EQUIP, 0));
		
		//counter
		storage.put("counter_1.5", new Skill("Counterattack, 30%", COUNTER, 30));
		storage.put("counter_2", new Skill("Counterattack, 30%", COUNTER, 30));
		storage.put("counter_1.5_35", new Skill("Counterattack, 35%", COUNTER, 35));
		
		storage = Collections.unmodifiableMap(storage);
	}
	
	public static Skill get(String skillCode) {
		if (storage == null) {
			init();
		}
		return storage.get(skillCode);
	}
	
	public static List<Skill> get(String... skillCodes){
		return Arrays.stream(skillCodes)
				.map(SkillDB::get)
				.collect(Collectors.toList());
	}
}
