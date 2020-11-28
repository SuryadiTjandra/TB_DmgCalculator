import React, {Fragment, PureComponent} from "react";
import {Card, CardBody} from "reactstrap";
import PropTypes from "prop-types";
import {DirectionSelection} from "./ResultSelections"

class DoublePincerCalculationResult extends PureComponent{
	constructor(props){
		super(props);
		this.state = {
			direction: "HORIZONTAL",
		}
		this.handleDirectionChange = this.handleDirectionChange.bind(this);
		this.handleDistanceChange = this.handleDistanceChange.bind(this);
	}
	
	render(){
		return <Card>
			<CardBody>
				<DirectionSelection direction={this.state.direction} onChange={this.handleDirectionChange} />
			</CardBody>
			<CardBody>
				<CalculationResultInner results={this.props.results[this.state.direction]} />
			</CardBody>
		</Card>
	}
}

export default DoublePincerCalculationResult;