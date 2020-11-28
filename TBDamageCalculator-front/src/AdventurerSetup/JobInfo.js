import React, {Fragment, PureComponent} from "react";
import PropTypes from "prop-types";
import {Button, ButtonGroup, Table, 
	ListGroup, ListGroupItem, 
	Card, CardBody, Collapse} from "reactstrap";

class JobSelection extends PureComponent {
	constructor(props){
		super(props);
		
		this.handleButtonClick = this.handleButtonClick.bind(this);
	}
	
	render(){
		const activeJobNo = this.props.activeJob;
		const buttons = this.props.jobs.map(
			job =>
				<Button 
					key={job.jobNo}
					color="primary" 
					active={activeJobNo === job.jobNo} 
					onClick={ () => this.handleButtonClick(job.jobNo) }>
					Job {job.jobNo + 1}
				</Button>
		)
		
		return <ButtonGroup>
			{buttons}
		</ButtonGroup>;
	}
	
	handleButtonClick(jobNo){
		this.props.onJobChange(jobNo);
	}
}

JobSelection.propTypes = {

	jobs: PropTypes.arrayOf(PropTypes.shape({
		jobNo: PropTypes.number.isRequired
	})).isRequired,

	activeJob: PropTypes.number.isRequired,
	
	onJobChange: PropTypes.func.isRequired
};

function StatInfo(props){
	return <Table size="sm">
			<thead>
			<tr>
				<th>HP</th>
				<th>Attack</th>
				<th>Defense</th>
				<th>M. Attack</th>
				<th>M. Defense</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>{props.job.hp}</td>
				<td>{props.job.atk}</td>
				<td>{props.job.def}</td>
				<td>{props.job.matk}</td>
				<td>{props.job.mdef}</td>
			</tr>
		</tbody>
	</Table>
}

StatInfo.propTypes = {
	job: PropTypes.shape({
		hp: PropTypes.number.isRequired,
		atk: PropTypes.number.isRequired,
		def: PropTypes.number.isRequired,
		matk: PropTypes.number.isRequired,
		mdef: PropTypes.number.isRequired,
	}).isRequired
}

function JobSkillInfo(props){
	const skills = props.job.jobSkills.map(
			(skill, index) => 
		<ListGroupItem className="py-2" key={[props.job.jobNo, index]}>{skill.name}</ListGroupItem>
	);
	
	return <ListGroup className="list-group-flush">{skills}</ListGroup>
}

JobSkillInfo.propTypes = {
	job: PropTypes.shape({
		jobNo: PropTypes.number.isRequired,
		jobSkills: PropTypes.arrayOf(PropTypes.shape({
			name: PropTypes.string.isRequired,
			activation: PropTypes.string.isRequired
		})).isRequired
	}).isRequired
}

class JobInfo extends PureComponent{
	constructor(props){
		super(props);
		this.state = {expanded: false};
		this.toggle = this.toggle.bind(this);
	}
	
	render(){		
		const job = this.props.jobs[this.props.activeJob];
		
		return <Fragment>
			<JobSelection 
				jobs = {this.props.jobs} 
				activeJob = {this.props.activeJob}
				onJobChange = {this.props.onJobChange}
			/>
			<div>
				<span className="h3">{job.name}</span>
				<Button color="link" onClick={this.toggle}>{this.state.expanded ? "Less" : "More"}</Button>
			</div>
			<Collapse isOpen={this.state.expanded}> <Card> <CardBody>
				<StatInfo job={job} />
				<JobSkillInfo job={job} />
			</CardBody></Card></Collapse>
		</Fragment>;
	}
	
	toggle(){
		this.setState( (prevState) => ({expanded: !prevState.expanded }) );
	}
}

JobInfo.propTypes = {
	jobs: PropTypes.arrayOf(PropTypes.shape({
		jobNo: PropTypes.number.isRequired,
		hp: PropTypes.number.isRequired,
		atk: PropTypes.number.isRequired,
		def: PropTypes.number.isRequired,
		matk: PropTypes.number.isRequired,
		mdef: PropTypes.number.isRequired,
		jobSkills: PropTypes.array.isRequired
	})).isRequired,

	activeJob: PropTypes.number.isRequired,
	
	onJobChange: PropTypes.func.isRequired
}

export default JobInfo;