package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import spring.request_entities.DoublePincerRequest;
import spring.request_entities.SinglePincerRequest;
import spring.response_entities.DoubleCalculationResult;
import spring.response_entities.SingleCalculationResult;
import spring.service.CalculationService;

@RestController
@CrossOrigin(origins="*")
public class CalculationController {
	@Autowired
	CalculationService service;
	
	@PostMapping("/calculateSingle")
	public SingleCalculationResult calculateSingle(@RequestBody SinglePincerRequest req) {
		return service.calculateSingle(service.buildBattleUnit(req.getSetup()));
	}
	
	@PostMapping("/calculateDouble")
	public DoubleCalculationResult calculateDouble(@RequestBody DoublePincerRequest req) {
		return service.calculateDouble(
				service.buildBattleUnit(req.getSetup()),
				req.getPhysTaps(), req.getMagTaps());
	}
}
