import React from "react";
import { AppBar, Toolbar } from "@material-ui/core";
import { makeStyles } from "@material-ui/styles";

const useStyles = makeStyles((theme) => ({
  root: {
    background: "#003366",
    height: "40px",
    // transform: "translateZ(0)",
  },
}));

function SignInNavbar() {
  const classes = useStyles();
  return (
    <AppBar position="static" className={classes.root}>
      <Toolbar></Toolbar>
    </AppBar>
  );
}

export default SignInNavbar;
