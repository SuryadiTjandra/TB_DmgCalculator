package battle_units;

import java.util.stream.Collectors;

import entities.Adventurer;

public class AdventurerUnit extends AbstractBattleUnit {
	
	protected Adventurer adventurer;
	
	AdventurerUnit(Adventurer adventurer) {
		this.adventurer = adventurer;
		setActiveJob(0);
	}
	
	
	public Adventurer getAdventurer() {
		return adventurer;
	}
	
	@Override
	public void setSlotSkill(int slotNo, int jobNo, int skillNo) {
		if (jobNo == this.activeJob.getJobNo())
			throw new IllegalStateException("Cannot equip skills from active job");
		super.setSlotSkill(slotNo, jobNo, skillNo);
	}
	
	@Override
	public void setActiveJob(int jobNo) {
		this.activeJob = this.adventurer.getJobs().get(jobNo);
		this.availableSlotSkills = this.adventurer.getJobs().stream()
				.filter(j -> j.getJobNo() != jobNo)
				.flatMap(j -> j.getJobSkills().stream())
				.collect(Collectors.toList());
	}
	
	
}
