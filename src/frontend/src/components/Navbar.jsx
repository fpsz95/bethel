import React, { useState } from "react";
import {
  AppBar,
  Button,
  Grid,
  IconButton,
  List,
  ListItem,
  ListItemIcon,
  ListItemText,
  Menu,
  Toolbar,
  Typography,
} from "@material-ui/core";
import InboxIcon from "@material-ui/icons/MoveToInbox";
import MailIcon from "@material-ui/icons/Mail";
import { Badge } from "@material-ui/core";
import { makeStyles } from "@material-ui/styles";
import NotificationsNoneIcon from "@material-ui/icons/NotificationsNone";
import PowerSettingsNewIcon from "@material-ui/icons/PowerSettingsNew";
import ChatBubbleOutlineIcon from "@material-ui/icons/ChatBubbleOutline";
import SwipeableDrawer from "@material-ui/core/SwipeableDrawer";
import { withRouter } from "react-router-dom";

const useStyles = makeStyles((theme) => ({
  root: {
    background: "#003366",
    height: "40px",
    // transform: "translateZ(0)",
  },
  projectName: {
    marginBottom: "24px",
    marginRight: "24px",
  },
  drawerPaper: {
    drawerPaper: { width: "inherit" },
  },
  swipeable: {
    marginTop: "40px",
  },
}));

function Navbar(props) {
  const { history } = props; //with this line u can history.push,  u cld also do without this line like props.history.push
  const classes = useStyles();
  const [open, setOpen] = useState(false);
  const handleSwipeableDrawer = () => {
    setOpen(true);
  };
  console.log(props);
  const itemsList1 = [
    {
      text: "Profile Information",
      icon: <InboxIcon />,
      onClick: () => history.push("/profile"),
    },
    {
      text: "Maintenance Charges",
      icon: <MailIcon />,
      onClick: () => history.push("/maintenance-charges"),
    },
    {
      text: "Contact Us",
      icon: <MailIcon />,
      onClick: () => props.history.push("/signup"),
    },
    {
      text: "About Us",
      icon: <MailIcon />,
      onClick: () => props.history.push("/profile"),
    },
  ];

  function logout() {
    localStorage.clear();
    props.history.push("/signin");
  }
  return (
    <div>
      <AppBar position="static" className={classes.root}>
        <Toolbar>
          <Grid container alignItems="center">
            <Grid item>
              <IconButton onClick={handleSwipeableDrawer}>
                <Menu />
              </IconButton>
            </Grid>
            <Grid item sm>
              <Typography variant="h6" className={classes.projectName}>
                Bethel
              </Typography>
            </Grid>
            <Grid item>
              <IconButton
              // classes={{ root: classes.btnRoot, label: classes.btnLabel }}
              >
                <Badge badgeContent={4} color="secondary">
                  <NotificationsNoneIcon fontSize="small" />
                </Badge>
              </IconButton>
              <IconButton>
                <Badge badgeContent={4} color="secondary">
                  <ChatBubbleOutlineIcon fontSize="small" />
                </Badge>
              </IconButton>
              <IconButton>
                <PowerSettingsNewIcon fontSize="small" />
              </IconButton>
            </Grid>
            <Grid item>
              <Button onClick={() => logout()}></Button>
            </Grid>
          </Grid>
        </Toolbar>
      </AppBar>

      <SwipeableDrawer anchor="left" open={open} onClose={() => setOpen(false)}>
        <Toolbar />
        <List>
          {itemsList1.map((item, index) => {
            const { text, icon, onClick } = item;
            return (
              <ListItem button key={text} onClick={onClick}>
                {<ListItemIcon>{icon}</ListItemIcon>}
                <ListItemText primary={text} />
              </ListItem>
            );
          })}
        </List>
      </SwipeableDrawer>
    </div>
  );
}

export default withRouter(Navbar);
