import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import SignIn from "./components/SignIn.jsx";
import ForgotPassword from "./components/ForgotPassword.jsx";
import SignUp from "./components/SignUp.jsx";
import Profile from "./components/Profile.jsx";
import RegisterSociety from "./components/RegisterSociety.jsx";
import SecretarySignUp from "./components/SecretarySignUp.jsx";
import SecretaryHome from "./components/SecretaryHome.jsx";
import MemberHome from "./components/MemberHome.jsx";
import SocietyMembers from "./components/SocietyMembers";
import MaintenanceBill from "./components/MaintenanceBill.jsx";
import EnterPaytmPaymentDetails from "./components/EnterPaytmPaymentDetails";
import PaymentResult from "./components/PaymentResult";
import MaintenanceChargesForm from "./components/MaintenanceChargesForm";
import Successful from "./components/Successful";
import OfficialPaytmPayment from "./components/OfficialPaytmPayment";
import UnderConstruction from "./components/UnderConstruction";
import PaytmPopup from "./components/PaytmPopup";

function App() {
  return (
    <>
      <Router>
        <Switch>
          <Route exact path={["/", "/signin"]} component={SignIn}></Route>
          <Route
            exact
            path="/register-society"
            component={RegisterSociety}
          ></Route>
          <Route
            exact
            path="/secretary-sign-up"
            component={SecretarySignUp}
          ></Route>
          <Route
            exact
            path="/forgot-password"
            component={ForgotPassword}
          ></Route>
          <Route exact path="/signup" component={SignUp}></Route>
          <Route exact path="/secretaryhome" component={SecretaryHome}></Route>
          <Route
            exact
            path="/societymembers"
            component={SocietyMembers}
          ></Route>
          <Route exact path="/memberhome" component={MemberHome}></Route>
          <Route exact path="/profile" component={Profile}></Route>
          <Route
            exact
            path="/maintenance-charges-form"
            component={MaintenanceChargesForm}
          ></Route>
          <Route
            exact
            path="/maintenance-charges"
            component={MaintenanceBill}
          ></Route>
          <Route
            exact
            path="/mainupdatesuccessful"
            component={Successful}
          ></Route>
          <Route
            exact
            path="/enter-paytm-payment-details"
            component={EnterPaytmPaymentDetails}
          ></Route>
          OfficialPaytmPayment
          <Route
            exact
            path="/official-paytm-payment"
            component={OfficialPaytmPayment}
          ></Route>
          <Route exact path="/payment-result" component={PaymentResult}></Route>
          <Route
            exact
            path="/under-construction"
            component={UnderConstruction}
          ></Route>
          <Route exact path="/paytmPopup" component={PaytmPopup}></Route>
          {/* <Route
            exact
            path="/payment-resultd"
            component={PaymentResult}
          ></Route> */}
        </Switch>
      </Router>
    </>
  );
}

export default App;
