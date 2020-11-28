import React, {PureComponent} from "react";
import {InputGroup, InputGroupAddon, Input, Button} from "reactstrap";
import PropTypes from "prop-types";

class CompanionInfo extends PureComponent{
	constructor(props){
		super(props);
		this.handleChangeButtonClick = this.handleChangeButtonClick.bind(this);
	}
	
	render(){
		const compName = this.props.companion ? this.props.companion.name : "" ;
		
		return <InputGroup className="my-1" size="sm">
			<InputGroupAddon addonType="prepend">
				Companion
			</InputGroupAddon>
			<Input type="text" readOnly value={compName} />
			<InputGroupAddon addonType="append">
				<Button color="link" onClick={this.handleChangeButtonClick}>Change</Button>
			</InputGroupAddon>
		</InputGroup>
	}
	
	handleChangeButtonClick(){
		this.props.onChangeRequest();
	}
}

CompanionInfo.propTypes = {
	companion: PropTypes.shape({
		name: PropTypes.string.isRequired,
		rarity: PropTypes.oneOf(["Z", "SS", "S", "A", "B", "C", "D"]).isRequired,
		atk: PropTypes.number.isRequired,
		matk: PropTypes.number.isRequired,
		def: PropTypes.number.isRequired,
		mdef: PropTypes.number.isRequired,
		skill: PropTypes.shape({
			name: PropTypes.string.isRequired,
			activation: PropTypes.string.isRequired
		})
	}),
	
	onChangeRequest: PropTypes.func.isRequired
}

export default CompanionInfo;