import React, { Component, Fragment} from "react";
import AdventurerList from "./AdventurerList/AdventurerList";
import Calculator from "./Calculator";

class App extends Component{
	constructor(props){
		super(props);
		this.state = {
			adventurer: null,
			error: null
		}
		this.handleAdventurerChange = this.handleAdventurerChange.bind(this);
	}
	
	render(){
		if (this.state.error){
			return this.state.error;
		}
		
		return	<React.Fragment>
			<div className="col-2 position-sticky">
				<AdventurerList link="http://localhost:8080/TBCalculator/adventurer" onItemClick={this.handleAdventurerChange}/>
			</div>
			<div className="col">
				<Calculator adventurer={this.state.adventurer} />
			</div>
		</React.Fragment>;
	}
	
	handleAdventurerChange(adventurerLink){
		fetch(adventurerLink, {method:"GET"})
		.then(response => 
				response.ok ? 
				response.json() : 
				Promise.reject("Error loading data"))
		.then(
			data => this.setState({adventurer: data, error: null}),
			error => this.setState({adventurer: null, error: error})
		);	
	}
}

export default App;