package spring.repository;

import static enums.Element.*;
import static enums.AdventurerType.*;
import static enums.Weapon.*;
import static enums.Race.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import entities.Adventurer;
import entities.Job;
import entities.LambdaAdventurer;

class AdventurerDB {
	private static Map<String, Adventurer> storage;
	
	private static void init(){
		storage = new HashMap<>();
		
		//B Rank adventurers + recodes
		storage.put("Bahl", new Adventurer("Bahl", "B", HUMAN, WARRIOR, Arrays.asList(
					new Job(0, "Bahl the Dark Duelist", 2957, 284, 238, 119, 138, SWORD, NON_ELEMENTAL, SkillDB.get(
						"counter_1.5", 
						"atk_buff_10_active", 
						"gigasword", 
						"counter_2"
					)),
					new Job(1, "Bahl the Dark Warrior", 3546, 302, 283, 146, 165, SWORD, NON_ELEMENTAL, SkillDB.get(
						"def_buff_10_active", 
						"megasword", 
						"hp_buff_10_equip", 
						"phys_buff_50_active"
					)),
					new Job(2, "Bahl the Dark Hero", 4072, 356, 293, 174, 193, SWORD, NON_ELEMENTAL, SkillDB.get(
						"atk_buff_10_equip",
						"def_buff_10_equip",
						"counter_1.5_35", 
						"terasword"
					))
				)
			));
		storage.put("Bahl_ƒ©", new LambdaAdventurer("Bahl ƒ©", "S", HUMAN, WARRIOR, Arrays.asList(
				new Job(0, "Bahl ƒ©", 5091, 418, 318, 264, 264, SWORD, NON_ELEMENTAL, SkillDB.get(
					"gigasword_1row", 
					"demoral_ward", 
					"cross_cleave", 
					"sword_of_valor"
				))
			),get("Bahl")
		));
		
		storage.put("Grace", new Adventurer("Grace", "B", HUMAN, WARRIOR, Arrays.asList(
				new Job(0, "Grace the Archer", 2942, 284, 220, 137, 155, BOW, NON_ELEMENTAL, SkillDB.get(
					"megabow", 
					"atk_buff_10_active", 
					"def_debuff_10_wildbeast", 
					"poison_arrows_area1_chain"
				)),
				new Job(1, "Grace the Markswoman", 3506, 311, 247, 165, 183, BOW, NON_ELEMENTAL, SkillDB.get(
					"def_buff_10_active", 
					"megabow_area1_chain", 
					"hp_buff_10_equip", 
					"terabow"
				)),
				new Job(2, "Grace the Ranger", 4043, 339, 275, 192, 210, BOW, NON_ELEMENTAL, SkillDB.get(
					"atk_buff_10_equip",
					"def_buff_10_equip",
					"gigabow", 
					"def_debuff_20_dragon"
				))
			)
		));
		storage.put("Grace_ƒ©", new LambdaAdventurer("Grace ƒ©", "S", HUMAN, WARRIOR, Arrays.asList(
				new Job(0, "Grace ƒ©", 5220, 436, 318, 272, 291, BOW, NON_ELEMENTAL, SkillDB.get(
					"gigabow_area1", 
					"ct2_equip", 
					"gigabow_ring", 
					"petabow"
				))
			),get("Grace")
		));
		
		
		
		storage = Collections.unmodifiableMap(storage);
	}
	
	public static Adventurer get(String id) {
		if (storage == null) {
			init();
		}
		return storage.get(id);
	}
	
	public static Collection<Adventurer> getAll(){
		if (storage == null) {
			init();
		}
		return Collections.unmodifiableCollection(storage.values());
	}
}
