import React, {Fragment, PureComponent} from "react";
import {Modal, ModalHeader, ModalBody, ModalFooter,
	Button, Row, Col, ListGroupItem, ListGroup, 
	Form, FormGroup, Input, Label} from "reactstrap";
import PropTypes from "prop-types";

class SkillSelectionModal extends PureComponent {
	constructor(props){
		super(props);
		
		this.state = {
			sourceJobs: [],
			equippedSkills:[null, null, null, null]
		}
		this.handleSelect = this.handleSelect.bind(this);
	}
	
	static getDerivedStateFromProps(nextProps, prevState){
		let jobs = nextProps.adventurer.baseForm ? 
					nextProps.adventurer.baseForm.jobs :
					nextProps.adventurer.jobs.filter(j => j.jobNo != nextProps.activeJob);
		
		let arr = [null, null, null, null];
		for (let i = 0; i < nextProps.equippedSkills.length; i++){
			arr[i] = nextProps.equippedSkills[i];
		}
		return {
			sourceJobs: jobs,
			equippedSkills: arr
		};
	}
	
	render(){
		return <Modal isOpen={this.props.modalOpen} backdrop="static" toggle={this.props.toggle} size="lg">
			<ModalHeader toggle={this.props.toggle}>Select Skills</ModalHeader>
			<ModalBody>
				<Row>
					<Col>
						{this.renderEquippedSkills()}
						
					</Col>
					<Col>
						{this.renderSelectableSkills()}
					</Col>
				</Row>
			</ModalBody>
			<ModalFooter>
				<Button color="secondary" onClick={this.props.toggle}>Cancel</Button>
				<Button color="primary" onClick={this.handleSelect}>Confirm</Button>
			</ModalFooter>
		</Modal>;
	}
	
	renderEquippedSkills(){
		const equipped = this.state.equippedSkills
			.map(skId => skId ? this.findSkillFrom(skId.jobNo, skId.skillNo) : null)
			.map((sk, index) => (
			<ListGroupItem 
				className="py-2" 
				key={index}>
				{sk ? sk.name : "---"}
				{sk ? <Button 
						className="float-right" 
						size="sm" 
						color="link" 
						onClick={ () => this.removeSkill(index) }>
							Remove
						</Button> : ""
				}
			</ListGroupItem>))
		
		return <Fragment>
			<ListGroup className="list-group-flush">
				{equipped}
				</ListGroup>
			<Button 
				className="float-right" 
				size="sm" 
				color="link" 
				onClick={ () => this.removeAllSkills() }>
				Remove All
			</Button> 
		</Fragment>;
	}
	
	renderSelectableSkills(){
		const jobSkills = this.state.sourceJobs.map(job => this.renderSelectableSkillsFromJob(job));
		return <Form>
			{jobSkills}
		</Form>
	}
	
	renderSelectableSkillsFromJob(job){
		const checkboxes = job.jobSkills
							.map((sk, idx) => {
								const eqp = this.state.equippedSkills
											.some(eqpSk => eqpSk && eqpSk.jobNo === job.jobNo && eqpSk.skillNo === idx);
								
								return <Label key={[job.jobNo, idx]} className="d-block">
									<Input type="checkbox" checked={eqp}
										onChange={() => this.handleSkillSelect(job.jobNo, idx)} />
									{sk.name}
								</Label>
							});
		
		return <FormGroup key={job.jobNo} tag="fieldset">
			<legend>{job.name}</legend>
			{checkboxes}
		</FormGroup>
	}
	
	findSkillFrom(jobNo, skillNo){
		return this.state.sourceJobs.find(j => j.jobNo === jobNo).jobSkills[skillNo];
	}
	
	addSkill(jobNo, skillNo){
		let emptySpot = this.state.equippedSkills.indexOf(null);
		if (emptySpot === -1)
			return;
		
		let arr = [].concat(this.state.equippedSkills);
		arr[emptySpot] = {jobNo: jobNo, skillNo: skillNo};
		this.setState({equippedSkills: arr});
	}
	
	removeSkill(index){
		let arr = [].concat(this.state.equippedSkills);
		arr[index] = null;
		this.setState({equippedSkills: arr});
	}
	
	removeAllSkills(){
		this.setState({equippedSkills: [null, null, null, null]});
	}
	
	handleSkillSelect(jobNo, skillNo){
		let index = this.state.equippedSkills
					.findIndex(skId => skId && skId.jobNo === jobNo && skId.skillNo === skillNo);
		
		if (index === -1){
			this.addSkill(jobNo, skillNo);
		} else {
			this.removeSkill(index);
		}
	}
	
	handleSelect(){
		this.props.onSelect(this.state.equippedSkills);
		this.props.toggle();
	}

}

SkillSelectionModal.propTypes = {
		
	modalOpen: PropTypes.bool.isRequired,
	toggle: PropTypes.func.isRequired,
	
	adventurer: PropTypes.shape({
		jobs: PropTypes.arrayOf(PropTypes.shape({
			jobNo: PropTypes.number.isRequired,
			name: PropTypes.string.isRequired,
			jobSkills: PropTypes.arrayOf(PropTypes.shape({
				name: PropTypes.string.isRequired,
				activation: PropTypes.string.isRequired
			})).isRequired
		})),
		
		baseForm: PropTypes.shape({
			jobs: PropTypes.arrayOf(PropTypes.shape({
				jobNo: PropTypes.number.isRequired,
				name: PropTypes.string.isRequired,
				jobSkills: PropTypes.arrayOf(PropTypes.shape({
					name: PropTypes.string.isRequired,
					activation: PropTypes.string.isRequired
				})).isRequired
			})).isRequired,
		})
	}).isRequired,

	activeJob: PropTypes.number.isRequired,
	equippedSkills: PropTypes.arrayOf(PropTypes.shape({
		jobNo: PropTypes.number.isRequired,
		skillNo: PropTypes.number.isRequired
	})).isRequired,
	
	onSelect: PropTypes.func.isRequired
	
}

export default SkillSelectionModal;