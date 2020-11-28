package spring.repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import entities.skills.Skill;

@Repository
public class SkillRepository {
	
	public Skill getSkill(String skillCode) {
		return SkillDB.get(skillCode);
	}
	
	public List<Skill> getSkills(String... skillCodes) {
		return Arrays.stream(skillCodes)
				.map(this::getSkill)
				.collect(Collectors.toList());
	}
}
