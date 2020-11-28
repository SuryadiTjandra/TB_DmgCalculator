import React, {PureComponent} from "react";
import {ListGroup, ListGroupItem, Card, CardHeader, CardBody, Button} from "reactstrap";
import PropTypes from "prop-types";

class SkillSetup extends PureComponent{
	constructor(props){
		super(props);
		this.handleChangeButtonClick = this.handleChangeButtonClick.bind(this);
	}
	
	render(){
		let arr = [null, null, null, null];
		for (let i =0; i < 4; i++){
			arr[i] = this.props.skills[i];
		}
		const skillItems = arr
							.map(skill => skill ? skill.name : "---")
							.map(
								(name, index) => 
								<ListGroupItem className="py-2" key={index}>{name}</ListGroupItem>
							);
		
		return <Card>
			<CardHeader>
				Skill slots 
				<Button className="float-right" size="sm" color="link" onClick={this.handleChangeButtonClick }>Change</Button>
			</CardHeader>
			<ListGroup className="list-group-flush">
				{skillItems}
			</ListGroup>
		</Card>
	}
	
	handleChangeButtonClick(){
		this.props.onChangeRequest();
	}
}

SkillSetup.propType = {
		
	skills: PropTypes.arrayOf(PropTypes.shape({
		name: PropTypes.string.isRequired,
		activation: PropTypes.string.isRequired
	})).isRequired,
	
	onChangeRequest: PropTypes.func.isRequired
}

export default SkillSetup;