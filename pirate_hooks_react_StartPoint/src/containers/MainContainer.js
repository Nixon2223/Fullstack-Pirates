import React from 'react';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import NavBar from '../components/NavBar.js';
import PirateContainer from './PirateContainer';
import ShipContainer from './ShipContainer.js';

const MainContainer = () => {

    return (
      <Router>
      <>
      <NavBar/>
      <Switch>
        <Route path="/pirates" component={PirateContainer}/>
        <Route path="/ships" component={ShipContainer}/>
      </Switch>
      </>
      </Router>
    )
}

export default MainContainer;