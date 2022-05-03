import React from 'react';
import {
	BrowserRouter as Router,
	Switch,
	Route,
} from "react-router-dom";

import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';

import { Home, NavBar, RegistrationPage, ProfilePage, SearchPage } from './components';

function App() {
	return (
		<Router>

			<NavBar />

			<Switch>
				<Route path='/' exact component={Home} />
				<Route path='/register' component={RegistrationPage} />
				<Route path='/profile' component={ProfilePage} />
				<Route path='/search' component={SearchPage} />
			</Switch>

		</Router>
	);
}

export default App;
