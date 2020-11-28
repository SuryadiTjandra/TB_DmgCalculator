package battle_units;

import java.util.stream.Collectors;

import entities.Adventurer;
import entities.LambdaAdventurer;

public class LambdaAdventurerUnit extends AbstractBattleUnit {
	LambdaAdventurer adventurer;
	
	LambdaAdventurerUnit(LambdaAdventurer adventurer) {
		this.adventurer = adventurer;
		this.activeJob = adventurer.getJobs().get(0);
		this.availableSlotSkills = this.adventurer.getBaseForm().getJobs().stream()
				.flatMap(j -> j.getJobSkills().stream())
				.collect(Collectors.toList());
	}
	
	public void setActiveJob(int jobNo) {
		throw new UnsupportedOperationException("Lambda characters can't change jobs.");
	}


	@Override
	public Adventurer getAdventurer() {
		return adventurer;
	}

}
