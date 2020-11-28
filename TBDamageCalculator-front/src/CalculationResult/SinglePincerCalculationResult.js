import React, {Fragment, PureComponent} from "react";
import {Card, CardBody} from "reactstrap";
import PropTypes from "prop-types";
import {DirectionSelection, DistanceSelection} from "./ResultSelections";
import CalculationResultInner from "./CalculationResultInner";

class SinglePincerCalculationResult extends PureComponent{
	constructor(props){
		super(props);
		this.state = {
			direction: "HORIZONTAL",
			distance: "ADJACENT",
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
				<DistanceSelection distance={this.state.distance} onChange={this.handleDistanceChange} />
			</CardBody>
			<CardBody>
				<CalculationResultInner results={this.props.results[this.state.direction][this.state.distance]} />
			</CardBody>
		</Card>
	}
	
	handleDirectionChange(newDir){
		this.setState({direction:newDir});
	}
	
	handleDistanceChange(newDist){
		this.setState({distance: newDist});
	}
}

SinglePincerCalculationResult.propTypes = {
	results: PropTypes.shape({
		HORIZONTAL: PropTypes.shape({
			ADJACENT: PropTypes.shape({
				physicalDamages: PropTypes.object,
				magicalDamage: PropTypes.object,
				totalPhysicalDamage: PropTypes.number,
				totalMagicalDamage: PropTypes.number
			}),
			TWO_SPACES_AWAY: PropTypes.shape({
				physicalDamages: PropTypes.object,
				magicalDamage: PropTypes.object,
				totalPhysicalDamage: PropTypes.number,
				totalMagicalDamage: PropTypes.number
			}),
			THREE_SPACES_AWAY: PropTypes.shape({
				physicalDamages: PropTypes.object,
				magicalDamage: PropTypes.object,
				totalPhysicalDamage: PropTypes.number,
				totalMagicalDamage: PropTypes.number
			})
		}),
		VERTICAL: PropTypes.shape({
			ADJACENT: PropTypes.shape({
				physicalDamages: PropTypes.object,
				magicalDamage: PropTypes.object,
				totalPhysicalDamage: PropTypes.number,
				totalMagicalDamage: PropTypes.number
			}),
			TWO_SPACES_AWAY: PropTypes.shape({
				physicalDamages: PropTypes.object,
				magicalDamage: PropTypes.object,
				totalPhysicalDamage: PropTypes.number,
				totalMagicalDamage: PropTypes.number
			}),
			THREE_SPACES_AWAY: PropTypes.shape({
				physicalDamages: PropTypes.object,
				magicalDamage: PropTypes.object,
				totalPhysicalDamage: PropTypes.number,
				totalMagicalDamage: PropTypes.number
			})
		})
	}).isRequired
}

export default SinglePincerCalculationResult;