import React, {Fragment, PureComponent} from "react";
import {Button} from "reactstrap";
import PropTypes from "prop-types";

function DirectionSelection(props){
	return <Fragment>
		<Button 
			color={props.direction === "HORIZONTAL" ? "primary" : "link"} 
			onClick={ () => props.onChange("HORIZONTAL") } >
			Horizontal
		</Button>
		<Button 
			color={props.direction === "VERTICAL" ? "primary" : "link"} 
			onClick={ () => props.onChange("VERTICAL") } >
			Vertical
		</Button>
	</Fragment>;
}

function DistanceSelection(props){
	return <Fragment>
		<Button 
			color={props.distance === "ADJACENT" ? "primary" : "link"} 
			onClick={ () => props.onChange("ADJACENT") } >
			Adjacent
		</Button>
		<Button 
			color={props.distance === "TWO_SPACES_AWAY" ? "primary" : "link"} 
			onClick={ () => props.onChange("TWO_SPACES_AWAY") } >
			Two Spaces Away
		</Button>
		<Button 
			color={props.distance === "THREE_SPACES_AWAY" ? "primary" : "link"} 
			onClick={ () => props.onChange("THREE_SPACES_AWAY") } >
			Three Spaces Away
		</Button>
	</Fragment>;
}

DirectionSelection.propTypes = {
	direction: PropTypes.oneOf(["HORIZONTAL", "VERTICAL"]).isRequired,
	onChange: PropTypes.func.isRequired
}

DistanceSelection.propTypes = {
	distance: PropTypes.oneOf(["ADJACENT", "TWO_SPACES_AWAY", "THREE_SPACES_AWAY"]).isRequired,
	onChange: PropTypes.func.isRequired
}

export { DirectionSelection, DistanceSelection };