import React, {Component, Fragment} from "react";
import {ListGroup, ListGroupItem, Card, CardBody, Button, FormGroup, Form, Input} from "reactstrap";
import 'bootstrap/dist/css/bootstrap.min.css';

class AdventurerListItem extends React.Component {
	constructor(props){
		/*props = {
		 * item: a single adventurer data
		 * onLinkClick: handler function when a link is clicked
		 *}*/
		super(props);
		this.handleLinkClick = this.handleLinkClick.bind(this);
	}
	
	handleLinkClick(){
		this.props.onLinkClick(this.props.item._links.self.href);
	}
	
	render(){
		return <ListGroupItem className="py-1">
			<a href="#" onClick={this.handleLinkClick}>{this.props.item.adventurerName}</a>
		</ListGroupItem>;
	}
}

class AdventurerFilter extends React.Component {
	constructor(props){
		super(props);
		this.state = {
			name: ""
		};
		
		this.handleNameInput = this.handleNameInput.bind(this);
	}
	
	render(){
		return <Form>
			<FormGroup>
				<Input name="name" type="text" placeholder="Search" onInput={this.handleNameInput} />
			</FormGroup>
		</Form>;
	}
	
	handleNameInput(event){
		let filters = this.state;
		filters.name = event.target.value;
		
		this.setState(filters);
		this.props.onFilterChange(filters);
	}
}

class AdventurerListInner extends React.Component {
	constructor(props){
		/* props = {
		 * 	items: an array of adventurers to be shown
		 * 	onItemClick: handler function to handle item click event
		 * }*/
		super(props);
		this.state = {
			shownItems: this.props.items
		};
		
		this.handleFilterChange = this.handleFilterChange.bind(this);
		this.handleItemClick = this.handleItemClick.bind(this);
	}
	
	render(){
		return <Fragment>
			<AdventurerFilter onFilterChange={this.handleFilterChange}/>
			<div style={{overflowY:"auto", bottom:0, maxHeight:"calc(100vh - 4rem)"}}>
				<Card>
					{this.renderList("Z")}
					{this.renderList("SS")}
					{this.renderList("S")}
					{this.renderList("A")}
					{this.renderList("B")}
				</Card>
			</div>
		</Fragment>;
	}
	
	renderList(rarity){
		const advList = this.state.shownItems
						.filter(item => item.rarity === rarity)
						.sort()
						.map(item =>
						<AdventurerListItem key={item._links.self.href} item={item} onLinkClick={this.handleItemClick} />);
		
		if (advList.length === 0) return null;
						
		return <Fragment>
			<CardBody className="py-2">Rarity {rarity}</CardBody>
			<ListGroup className="list-group-flush">{advList}</ListGroup>
		</Fragment>;
	}
	
	handleFilterChange(filterObject){
		this.setState({shownItems: this.props.items.filter(item => this.checkFilter(filterObject, item))});
	}
	
	checkFilter(filterObject, item){
		let result = true;
		
		if (filterObject.name.length > 0){
			result = item.adventurerName.toUpperCase().startsWith(filterObject.name.toUpperCase());
		}
		
		return result;
	}
	
	handleItemClick(link){
		this.props.onItemClick(link);
	}
}

class AdventurerList extends React.Component{
	constructor(props){
		/*
		 * props = {
		 * 		link: the link to fetch the available adventurers.
		 * 		onItemClick: event handler when a list item is clicked. Handler receives a link to the clicked adventurer's resource.
		 * }
		 */
		
		super(props);
		this.state = {
			error: false,
			isLoading: false,
			items: [],
		};
		
		this.handleRetry = this.handleRetry.bind(this);
		this.handleItemClick = this.handleItemClick.bind(this);
	}
	
	componentDidMount(){
		this.fetchData();
	}
	
	fetchData(){
		this.setState({isLoading: true});
		
		fetch(this.props.link, {
			method: "GET"
		})
		.then(
			response => 
				response.ok ? 
				response.json() : 
				Promise.reject("Error loading data"))
		.then(
			(data) => this.setState({isLoading: false, items: data, error: false}),
			(error) => this.setState({isLoading: false, error: true, items: []})
		);
	}
	
	render(){
		if (this.state.isLoading){
			return "Loading...";
		} else if (this.state.error){
			return <div>
				Loading failed
				<Button color="primary" onClick={this.handleRetry}>Retry</Button>
			</div>;
		} else {
			return <AdventurerListInner items={this.state.items} onItemClick={this.handleItemClick} />
		}
		
	}
	
	handleRetry(){
		this.fetchData();
	}
	
	handleItemClick(itemLink){
		this.props.onItemClick(itemLink);
	}
	
}

export default AdventurerList;