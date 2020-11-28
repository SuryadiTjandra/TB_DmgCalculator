import React, {Component, Fragment} from "react";
import {Row, Col, Button} from "reactstrap";

import AdventurerSetup from "./AdventurerSetup/AdventurerSetup";
import CalculationResult from "./CalculationResult/CalculationResult";




class Calculator extends Component{
	constructor(props){
		/*props = {adventurer: selected adventurer}*/
		super(props);
		this.state = {
			setup : {
				activeJob: 0,
				equippedSkills: [[], [], []],
				companion: null
			},
			
			isCalculating: false,
			type: "single",
			error: false,
			results: {
				"HORIZONTAL": {
					"ADJACENT" : {
						physicalDamages: {
							"SWORD": 1000,
							"BOW" : 500
						},
						totalPhysicalDamage: 1500,
						magicalDamages: {
							"SOLAR": 300,
							"LUNAR": 800
						},
						totalMagicalDamage: 1100
					},
					"TWO_SPACES_AWAY" : {
						physicalDamages: {
							"SWORD": 100,
							"BOW" : 50
						},
						totalPhysicalDamage: 150,
						magicalDamages: {
							"SOLAR": 30,
							"LUNAR": 80
						},
						totalMagicalDamage: 110
					},
					"THREE_SPACES_AWAY" : {
						physicalDamages: {

						},
						totalPhysicalDamage: 0,
						magicalDamages: {
	
						},
						totalMagicalDamage: 0
					}
				},
				"VERTICAL": {
					"ADJACENT" : {
						physicalDamages: {
							"SPEAR": 2000,
							"BOW" : 500
						},
						totalPhysicalDamage: 2500,
						magicalDamages: {
							"PHOTON": 300,
							"GRAVITON": 700
						},
						totalMagicalDamage: 1000
					},
					"TWO_SPACES_AWAY" : {
						physicalDamages: {
							"SPEAR": 200,
							"BOW" : 50
						},
						totalPhysicalDamage: 250,
						magicalDamages: {
							"PHOTON": 30,
							"GRAVITON": 70
						},
						totalMagicalDamage: 100
					},
					"THREE_SPACES_AWAY" : {
						physicalDamages: {

						},
						totalPhysicalDamage: 0,
						magicalDamages: {
	
						},
						totalMagicalDamage: 0
					}
				}
			}			
		}
		
		this.handleSetupChange = this.handleSetupChange.bind(this);
		this.handleTypeChange = this.handleTypeChange.bind(this);
	}
	
	static getDerivedStateFromProps(nextProps, prevState){
		return {
			setup: {
				activeJob: 0,
				equippedSkills: [[], [], []],
				companion: null,
			},
			type: prevState.type,
			//results: {}
		};
	}
	
	render(){
		if (this.props.adventurer === null)
			return "Please select an adventurer";
		
		return <Row>
			<Col>
				<AdventurerSetup 
					adventurer={this.props.adventurer} 
					activeJob={this.state.setup.activeJob}
					equippedSkills={this.state.setup.equippedSkills}
					companion={this.state.setup.companion}
					onSetupChange={this.handleSetupChange}/>
			</Col>
			<Col>
				
				<CalculationResult 
					type = {this.state.type}
					results={this.state.results} 
					isLoading ={this.state.isCalculating}
					error={this.state.error}
					onTypeChange= {this.handleTypeChange}/>
			</Col>
		</Row>
	}
	
	handleSetupChange(newSetup){
		this.setState({setup: newSetup});
		this.sendCalculationRequest();
	}
	
	handleTypeChange(newType){
		this.setState({type: newType});
		this.sendCalculationRequest();
	}
	
	sendCalculationRequest(){
		this.setState({isCalculating: true});
		
		let sendData = {
			setup: {
				advName: this.props.adventurer.name,
				companionName: this.state.setup.companion ? this.state.setup.companion.name : "",
				activeJob: this.state.setup.activeJob,
				equippedSkillNo: this.state.setup.equippedSkills[this.state.setup.activeJob]
									.map((skId, index) => [index, skId.jobNo, skId.skillNo])
			}
		}
		
		fetch("http://localhost:8080/TBCalculator/calculateSingle", {
			method: "POST", 
			body: JSON.stringify(sendData),
			mode: 'cors',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			}
		})
		.then(response => 
				response.ok ? 
				response.json() : 
				Promise.reject("Error loading data"))
		.then(
			(data) => this.setState({isCalculating: false, results: data.damages, error: false}),
			(error) => this.setState({isCalculating: false, results: {}, error: true})
		)
	}
}

export default Calculator;