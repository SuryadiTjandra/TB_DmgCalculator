import React, {PureComponent} from "react";
import {ListGroup, ListGroupItem, Card, CardHeader, CardBody, Button} from "reactstrap";
import PropTypes from "prop-types";
import JobInfo from "./JobInfo";
import CompanionInfo from "./CompanionInfo";
import SkillSetup from "./SkillSlotInfo";
import SkillSelectionModal from "./Modals/SkillSelectionModal";

class AdventurerSetup extends PureComponent {
	constructor(props){
		/*props = {adventurer: selected adventurer}*/
		super(props);
		this.state = {
			skillModalOpen: false,
			compModalOpen: false
		}
		
		this.handleJobChange = this.handleJobChange.bind(this);
		this.toggleCompanionModal = this.toggleCompanionModal.bind(this);
		this.toggleSkillModal = this.toggleSkillModal.bind(this);
		this.handleSkillChange = this.handleSkillChange.bind(this);
		this.handleCompanionChange = this.handleCompanionChange.bind(this);
	}
	
	render(){
		const jobs = this.props.adventurer.jobs;
		const curJob = this.props.adventurer.jobs[this.props.activeJob];
		const skills = this.props.equippedSkills[this.props.activeJob]
			.map(sk => sk ? this.findSkillFrom(sk.jobNo, sk.skillNo) : null);
		
		return <React.Fragment>
			<h4>{this.props.adventurer.name}</h4>
			<JobInfo jobs={jobs} activeJob={this.props.activeJob} onJobChange={this.handleJobChange} />
			<CompanionInfo companion={this.props.companion} onChangeRequest={this.toggleCompanionModal} />
			<SkillSetup skills={skills} onChangeRequest={this.toggleSkillModal}/>
			<SkillSelectionModal 
				modalOpen= {this.state.skillModalOpen}
				toggle = {this.toggleSkillModal}
				adventurer = {this.props.adventurer}
				activeJob={this.props.activeJob}
				equippedSkills={this.props.equippedSkills[this.props.activeJob]} 
				onSelect={this.handleSkillChange} />
		
		</React.Fragment>;
		//<CompanionSelectionModal onSelect={this.handleCompanionChange} />
		
	}
	
	findSkillFrom(jobNo, skillNo){
		let jobs = this.props.adventurer.baseForm ? 
				this.props.adventurer.baseForm.jobs : this.props.adventurer.jobs;
		return jobs.find(j => j.jobNo === jobNo).jobSkills[skillNo];
	}
	
	toggleCompanionModal(){
		this.setState( {compModalOpen: !this.state.compModalOpen} );
	}
	
	toggleSkillModal(){
		this.setState({skillModalOpen: !this.state.skillModalOpen });
	}
	
	handleJobChange(jobNo){
		this.props.onSetupChange({
			activeJob: jobNo,
			equippedSkills: this.props.equippedSkills,
			companion: this.props.companion
		});
	}
	
	handleCompanionChange(companion){
		this.props.onSetupChange({
			activeJob: this.props.activeJob,
			equippedSkills: this.props.equippedSkills,
			companion: companion
		});
	}
	
	handleSkillChange(newSkills){
		let arr = [].concat(this.props.equippedSkills);
		arr[this.props.activeJob] = newSkills;
		
		this.props.onSetupChange({
			activeJob: this.props.activeJob,
			equippedSkills: arr,
			companion: this.props.companion
		});
	}
}

AdventurerSetup.propTypes = {
	adventurer: PropTypes.shape({
		name: PropTypes.string.isRequired,
		rarity: PropTypes.string.isRequired,
		race: PropTypes.string.isRequired,
		jobs: PropTypes.array.isRequired,
		baseForm: PropTypes.shape({
			jobs: PropTypes.array.isRequired,
		})
	}).isRequired,
	
	activeJob: PropTypes.number.isRequired,
	equippedSkills: PropTypes.array.isRequired,
	companion: PropTypes.object,
	
	onSetupChange: PropTypes.func.isRequired
}

export default AdventurerSetup;