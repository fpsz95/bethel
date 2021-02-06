import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import SignIn from "./components/SignIn.jsx";
import ForgotPassword from "./components/ForgotPassword.jsx";
import SignUp from "./components/SignUp.jsx";
import Profile from "./components/Profile.jsx";

function App() {
  return (
    <>
      <Router>
        <Switch>
          <Route exact path="/" component={SignIn}></Route>
          <Route exact path="/signin" component={SignIn}></Route>
          <Route
            exact
            path="/forgot_password"
            component={ForgotPassword}
          ></Route>
          <Route exact path="/signup" component={SignUp}></Route>
          <Route exact path="/profile" component={Profile}></Route>
        </Switch>
      </Router>
    </>
  );
}

export default App;
