package spring.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import entities.Adventurer;
import spring.response_entities.AdventurerPreview;
import spring.service.AdventurerService;
import util.AdventurerUtil;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/adventurer")
@ExposesResourceFor(Adventurer.class)
public class AdventurerController {

	@Autowired
	AdventurerService service;
	@Autowired
	EntityLinks links;
	

	@GetMapping("")
	public List<AdventurerPreview> allAdventurer(){
		
		return service.getAdventurers().stream()
				.map(AdventurerPreview::new)
				.map((AdventurerPreview ap) -> {
					String id = AdventurerUtil.getIdFromName(ap.getAdventurerName());
					Link link = links.linkToSingleResource(Adventurer.class, id);
					ap.add(link);
					return ap;
				})
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public Adventurer adventurer(@PathVariable("id") String advId) {
		Adventurer adv = service.getAdventurer(advId);
		if (adv != null) {
			adv.add(links.linkToSingleResource(Adventurer.class, advId));
			return adv;
		} else
			throw new AdventurerNotFoundException("No adventurers with the name " + advId);
	}

}
