package spring.repository;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import entities.Adventurer;

@Repository
public class AdventurerRepository {
	@Autowired
	SkillRepository skillRepo;
	
	public Adventurer getAdventurer(String adventurerId) {
		return AdventurerDB.get(adventurerId);
	}
	
	public Collection<Adventurer> getAdventurers(){
		return AdventurerDB.getAll();
	}

	public List<String> getAdventurerNames() {
		return Arrays.asList("Bahl", "Grace", "Kuscah", "Ma'curi");
	}
}
