import React, {Fragment, PureComponent} from "react";
import {Row, Col, Button, Collapse} from "reactstrap";
import PropTypes from "prop-types";

class CalculationResultInner extends PureComponent {
	constructor(props){
		super(props);
		this.state = {expanded: false};
	}
	
	toggleExpanded(){
		this.setState({expanded: !this.state.expanded});
	}
	
	render(){
		const results = this.props.results;
		
		return <Fragment>
			<h5 className="text-muted">Total Damage: 
				<span style={{fontSize:"large"}} className="text-primary">
					{results.totalPhysicalDamage + results.totalMagicalDamage}
				</span>
			</h5>
			<Row>
				<Col>
					<h6>Physical Damage:
						<span style={{fontSize:"large"}} className="text-primary">
							{results.totalPhysicalDamage}
						</span>
					</h6>
					<Collapse isOpen={this.state.expanded}>
						<ul>
							<li>Sword: {results.physicalDamages["SWORD"]}</li>
							<li>Spear: {results.physicalDamages["SPEAR"]}</li>
							<li>Bow: {results.physicalDamages["BOW"]}</li>
							<li>Staff: {results.physicalDamages["STAFF"]}</li>
							<li>Unarmed: {results.physicalDamages["UNARMED"]}</li>
						</ul>
					</Collapse>
				</Col>
				<Col>
					<h6>Magical Damage:
						<span style={{fontSize:"large"}} className="text-primary">
							{results.totalMagicalDamage}
						</span>
					</h6>
					<Collapse isOpen={this.state.expanded}>
						<ul>
							<li>Fire: {results.magicalDamages["FIRE"]}</li>
							<li>Ice: {results.magicalDamages["ICE"]}</li>
							<li>Lightning: {results.magicalDamages["LIGHTNING"]}</li>
							<li>Darkness: {results.magicalDamages["DARKNESS"]}</li>
							<li>Solar: {results.magicalDamages["SOLAR"]}</li>
							<li>Lunar: {results.magicalDamages["LUNAR"]}</li>
							<li>Photon: {results.magicalDamages["PHOTON"]}</li>
							<li>Graviton: {results.magicalDamages["GRAVITON"]}</li>
							<li>Non-Elem: {results.magicalDamages["NON_ELEMENTAL"]}</li>
						</ul>
					</Collapse>
				</Col>
			</Row>
			<Button color="link" onClick={ () => this.toggleExpanded() }>
				{this.state.expanded ? "Less" : "More"}
			</Button>
		</Fragment>
	}
	
}

export default CalculationResultInner;