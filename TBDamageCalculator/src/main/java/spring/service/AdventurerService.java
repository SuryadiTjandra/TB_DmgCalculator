package spring.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entities.Adventurer;
import spring.repository.AdventurerRepository;

@Service
public class AdventurerService {
	
	@Autowired
	private AdventurerRepository repository;
	
	public Adventurer getAdventurer(String adventurerId) {
		return repository.getAdventurer(adventurerId);
	}
	
	public Collection<Adventurer> getAdventurers(){
		return repository.getAdventurers();
	}
	
	public List<String> getAdventurerNames(){
		return repository.getAdventurerNames();
	}
}
