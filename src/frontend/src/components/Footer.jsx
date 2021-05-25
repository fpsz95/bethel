import React from "react";
import {
  AppBar,
  Container,
  Grid,
  Paper,
  Toolbar,
  Typography,
} from "@material-ui/core";
import { makeStyles } from "@material-ui/styles";

const useStyles = makeStyles((theme) => ({
  root: {
    background: "#242526",
    minHeight: "300px",
    padding: "30px",
    alignItems: "center",
    // transform: "translateY(0)",
  },
  footerHeading: {
    color: "#B7D7F1",
    //padding: "5px",
    // transform: "translateY(0)",
  },
  footerDescription: {
    color: "#B7D7F1",
    //padding: "5px",
    // transform: "translateY(0)",
  },
}));

function Footer() {
  const classes = useStyles();
  return (
    <Paper className={classes.root} elevation={0} square>
      <Grid container alignItems="center">
        <Grid item sm></Grid>
        <Grid item>
          <Typography
            variant="h5"
            align="center"
            className={classes.footerHeading}
          >
            Peter Fernandes
          </Typography>
          <Typography variant="h6" className={classes.footerDescription}>
            {/* FernTech - All Rights Reserved */}
          </Typography>
        </Grid>
        <Grid item sm></Grid>
      </Grid>
    </Paper>
  );
}

export default Footer;
