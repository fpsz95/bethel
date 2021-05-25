import React from "react";
import { AppBar, Toolbar } from "@material-ui/core";
import { makeStyles } from "@material-ui/styles";

const useStyles = makeStyles((theme) => ({
  root: {
    background: "#1261A0",
    height: "60px",
    // transform: "translateZ(0)",
  },
}));

function SignInHeader() {
  const classes = useStyles();
  return (
    <AppBar position="static" className={classes.root}>
      <Toolbar></Toolbar>
    </AppBar>
  );
}

export default SignInHeader;
