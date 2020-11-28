package battle_units;

import java.util.List;
import java.util.stream.Collectors;

import entities.Adventurer;
import entities.LambdaAdventurer;
import entities.skills.Skill;

class SkillFinder {
	static List<Skill> findAvailableSlotSkills(Adventurer adventurer, int jobNo){
		return adventurer.getJobs().stream()
					.filter(j -> j.getJobNo() != jobNo)
					.flatMap(j -> j.getJobSkills().stream())
					.collect(Collectors.toList());
	}
	
	static List<Skill> findAvailableSlotSkills(LambdaAdventurer adventurer){
		return adventurer.getBaseForm().getJobs().stream()
				.flatMap(j -> j.getJobSkills().stream())
				.collect(Collectors.toList());
	}
	
	static Skill findSkill(Adventurer adventurer, int jobNo, int skillNo) {
		return adventurer.getJobs().get(jobNo).getJobSkills().get(skillNo);
	}
	
	static Skill findSkill(LambdaAdventurer adventurer, int jobNo, int skillNo) {
		return adventurer.getBaseForm().getJobs().get(jobNo).getJobSkills().get(skillNo);
	}
}
