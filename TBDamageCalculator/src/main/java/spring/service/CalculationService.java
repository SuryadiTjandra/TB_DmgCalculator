package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import battle_units.BattleUnit;
import calculators.BattleCalculator;
import calculators.Distance;
import enums.Direction;
import spring.request_entities.AdventurerSetup;
import spring.response_entities.DoubleCalculationResult;
import spring.response_entities.SingleCalculationResult;
import util.AdventurerUtil;

@Service
public class CalculationService {
	@Autowired
	AdventurerService advService;
	@Autowired
	CompanionService compService;
	
	public BattleUnit buildBattleUnit(AdventurerSetup setup) {
		String advId = AdventurerUtil.getIdFromName(setup.getAdvName());
		BattleUnit bu = BattleUnit.from(advService.getAdventurer(advId));
		bu.setCompanion(compService.getCompanion(setup.getCompanionName()));
		bu.setActiveJob(setup.getActiveJob());
		
		int[][] skillSetup = setup.getEquippedSkillNo();
		for(int i = 0; i < Math.min(skillSetup.length, 4); i++) {
			bu.setSlotSkill(skillSetup[i][0], skillSetup[i][1], skillSetup[i][2]);
		}
		return bu;
	}
	
	public SingleCalculationResult calculateSingle(BattleUnit unit) {
		BattleCalculator calc = new BattleCalculator(unit);
		
		SingleCalculationResult.Builder builder = new SingleCalculationResult.Builder();
		for (Direction dir : Direction.values()) {
			for (Distance target : Distance.values()) {
				builder.setDamage(dir, target, 
						calc.calculateSinglePincerPower(dir, target));
			}
		}
		return builder.build();
	}
	
	public DoubleCalculationResult calculateDouble(BattleUnit unit, int physTaps, int magTaps) {
		BattleCalculator calc = new BattleCalculator(unit);
		
		DoubleCalculationResult.Builder builder = new DoubleCalculationResult.Builder();
		for (Direction dir : Direction.values()) {
			builder.setDamage(dir, 
					calc.calculateSteadyDoublePincerPower(dir, physTaps, magTaps));
		}
		return builder.build();
	}
}
