import React from "react";
import {
  AppBar,
  Toolbar,
  Grid,
  IconButton,
  InputBase,
} from "@material-ui/core";
import ChatBubbleOutlineIcon from "@material-ui/icons/ChatBubbleOutline";
import { Badge } from "@material-ui/core";
import NotificationsNoneIcon from "@material-ui/icons/NotificationsNone";
import PowerSettingsNewIcon from "@material-ui/icons/PowerSettingsNew";
import { makeStyles } from "@material-ui/styles";
import SearchIcon from "@material-ui/icons/Search";

const useStyles = makeStyles((theme) => ({
  root: {
    background: "#fff",
    //transform: "translateZ(0)",
    //zIndex: 111111111,
  },
  searchInput: {
    opacity: "0.6",
    padding: "0px 8px",
    // padding: `0px ${theme.spacing(1)}px`,
    fontSize: "0.8rem",
    "&:hover": {
      background: "#f2f2f2",
    },
    "& .MuiSvgIcon-root": {
      marginRight: "8px",
      // marginRight: theme.spacing(1),
    },
  },
  // btnRoot: {
  //   backgroundColor: "green",
  // },
  // btnLabel: {
  //   backgroundColor: "red",
  // },
}));

function Header() {
  const classes = useStyles();
  return (
    <AppBar position="static" className={classes.appBar}>
      <Toolbar>
        <Grid container alignItems="center">
          <Grid item>
            <InputBase
              placeholder="Search..."
              className={classes.searchInput}
              startAdornment={<SearchIcon fontSize="small" />}
            />
          </Grid>
          <Grid item sm></Grid>
          <Grid item></Grid>
        </Grid>
      </Toolbar>
    </AppBar>
  );
}

export default Header;
