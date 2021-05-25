import React from "react";
import {
  SwipeableDrawer as MUISwipeableDrawer,
  List,
  ListItem,
  ListItemIcon,
  ListItemText,
} from "@material-ui/core";
import { withRouter } from "react-router-dom";

const SwipableDrawer = (props) => {
  const { history } = props;
  //   const itemsList = [
  //     {***************************************************************NOT USED********************************WE ARE NOT USING THIS AT THE MOMENT******************************************************************
  //       text: "Profile Information",
  //       //   icon: <InboxIcon />,
  //       //   onClick: () => history.push("/profile"),
  //     },
  //     {
  //       text: "Maintenance Charges",
  //       //   icon: <MailIcon />,
  //       //   onClick: () => history.push("/maintenance_charges"),
  //     },
  //     {
  //       text: "Contact Us",
  //       //   icon: <MailIcon />,
  //       //   onClick: () => history.push("/signup"),
  //     },
  //     {
  //       text: "About Us",
  //       //   icon: <MailIcon />,
  //       //   onClick: () => history.push("/profile"),
  //     },
  //   ];
  const itemsList = ["Inbox", "Starred", "Send Email", "Drafts"];
  return (
    <MUISwipeableDrawer>
      <List>
        {itemsList.map((text, index) => (
          <ListItem button key={text}>
            <ListItemText primary={text} />
          </ListItem>
        ))}
        {/* <ListItem>
          <ListItemText>First</ListItemText>
          <ListItemText>Second</ListItemText>
        </ListItem>*/}
      </List>
    </MUISwipeableDrawer>
  );
};

export default withRouter(SwipableDrawer);
