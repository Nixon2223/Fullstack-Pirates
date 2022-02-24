import React, {useState, useEffect} from 'react';
import {Route, Switch} from 'react-router-dom';
import Request from '../helpers/request';
import ShipList from '../components/ships/ShipList';

const ShipContainer = () => {
    const [pirates, setPirates] = useState([]);
    const [ships, setShips] = useState([]);
    const [raids, setRaids] = useState([]);
  
    const requestAll = function(){
      const request = new Request();
      const piratePromise = request.get('/api/pirates')
      const shipPromise = request.get('/api/ships')
      const raidPromise = request.get('/api/raids')
  
      Promise.all([piratePromise, shipPromise, raidPromise])
      .then((data) => {
          setPirates(data[0]);
          setShips(data[1]);
          setRaids(data[2])
      })
    }
  
    useEffect(()=>{
      requestAll()
    }, [])
  
  
    if(!ships){
      return null
    }
    return(
        <>
        <Switch>

        <Route render={() => {
          return <ShipList ships={ships}/>
        }} />
  
        </Switch>
        </>
    )
  }
  
  export default ShipContainer;