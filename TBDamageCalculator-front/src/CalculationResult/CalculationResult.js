import React, {Fragment, PureComponent} from "react";
import {Row, Col, Button} from "reactstrap";
import PropTypes from "prop-types";
import SinglePincerCalculationResult from "./SinglePincerCalculationResult";
import DoublePincerCalculationResult from "./DoublePincerCalculationResult";

class CalculationTypeSelection extends PureComponent{
	constructor(props){
		super(props);
	}
	
	render(){
		return <Fragment>
			<Button 
				color={this.props.type === "single" ? "primary" : "link"} 
				onClick={ () => this.props.onTypeChange("single") } >
				Single Pincer
			</Button>
			<Button 
				color={this.props.type === "double" ? "primary" : "link"} 
				onClick={ () => this.props.onTypeChange("double") } >
				Steady Double Pincers
			</Button>
		</Fragment>
	}
}

class CalculationResult extends PureComponent{
	constructor(props){
		super(props);
	}
	
	render(){
		if (this.props.isLoading){
			return "Calculating...";
		}
		if (this.props.error){
			return "Error fetching data";
		}
		
		const result = (this.props.type === "single") ? 
			<SinglePincerCalculationResult results={this.props.results} /> :
			<DoublePincerCalculationResult results={this.props.results} />
		
		return <Fragment>
			<CalculationTypeSelection type={this.props.type} onTypeChange={this.props.onTypeChange} />
			{result}
		</Fragment>
	}
}

CalculationResult.propTypes = {
	type: PropTypes.string.isRequired,
	onTypeChange: PropTypes.func.isRequired,
	isLoading: PropTypes.bool.isRequired,
	
	results: PropTypes.object.isRequired
}

export default CalculationResult;