import React from "react";
import "./App.css";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

import Home from "./pages/home/Home";
import Profile from "./pages/profile/Profile";
import Login from "./pages/login/Login";
import Signup from "./pages/signup/Signup";
import Example from "./pages/Example";
import Restaurant from "./pages/restaurant/Restaurant";
import RestaurantService from "./services/RestaurantService";

function App() {
  return (
    <Router>
      <Switch>

        <Route path="/about">
          <About />
        </Route>
        <Route path="/dashboard">
          <Dashboard />
        </Route>
        <Route path="/profile">
          <Profile />
        </Route>
        <Route path="/restaurant"
               component={Restaurant} />
        <Route path="/login">
          <Login/>
        </Route>
        <Route path="/signup">
          <Signup/>
        </Route>
        <Route path="/test">
          <Example/>
        </Route>
        <Route exact path="/"
               component={Home} />
      </Switch>
    </Router>
  );
}

function About() {
  return (
    <div>
      <h2>About</h2>
    </div>
  );
}

function Dashboard() {
  return (
    <div>
      <h2 onClick={RestaurantService.testGet}>Dashboard</h2>
    </div>
  );
}

export default App;