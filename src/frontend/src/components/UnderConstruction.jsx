import { Link } from "react-router-dom";
import React from "react";

function UnderConstruction() {
  return (
    <div>
      <h1>THIS SITE IS UNDER CONSTRUCTION</h1>
      <br />
      <h2>Thanks for understanding</h2>
      <br />
      <Link to="/signin">Back to Login Screen</Link>
      <br />
      <br />
      <Link to="/profile">Back to Profile</Link>
    </div>
  );
}

export default UnderConstruction;
