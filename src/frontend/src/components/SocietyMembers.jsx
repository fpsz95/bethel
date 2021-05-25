import React, { useState, useEffect, useCallback } from "react";
import axios from "axios";
import {
  Button,
  Container,
  createMuiTheme,
  makeStyles,
  ThemeProvider,
} from "@material-ui/core";
import Navbar from "./Navbar";
import Header from "./Header";
import Footer from "./Footer";
import AdminNavbar from "./AdminNavbar";
import useTable from "./useTable";
import * as adminService from "../services/adminService";
import { Link } from "react-router-dom";
import { withRouter } from "react-router-dom";

import PropTypes from "prop-types";
import clsx from "clsx";
import { lighten } from "@material-ui/core/styles";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableContainer from "@material-ui/core/TableContainer";
import TableHead from "@material-ui/core/TableHead";
import TablePagination from "@material-ui/core/TablePagination";
import TableRow from "@material-ui/core/TableRow";
import TableSortLabel from "@material-ui/core/TableSortLabel";
import Toolbar from "@material-ui/core/Toolbar";
import Typography from "@material-ui/core/Typography";
import Paper from "@material-ui/core/Paper";
import Checkbox from "@material-ui/core/Checkbox";
import IconButton from "@material-ui/core/IconButton";
import Tooltip from "@material-ui/core/Tooltip";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import Switch from "@material-ui/core/Switch";
import DeleteIcon from "@material-ui/icons/Delete";
import FilterListIcon from "@material-ui/icons/FilterList";
import User from "../pages/User/User";

const useStyles = makeStyles({
  appProfile: {
    paddingLeft: "320px",
    width: "100%",
  },
  paper: {
    marginTop: "24px",
    // display: "flex",
    // flexDirection: "column",
    // alignItems: "center",
    // marginBottom: "700px",
  },

  societyMembers: {
    height: "300px",
    overflowY: "auto",
  },
});

const theme = createMuiTheme({
  pallette: {
    primary: {
      main: "#FF0000",
      light: "#3c44b126",
    },
    secondary: {
      main: "#f83245",
      light: "#f8324526",
    },
    background: {
      default: "#AF79B7",
    },
  },
  overrides: {
    MuiAppBar: {
      root: {
        transform: "translateZ(0)",
      },
    },
  },
  props: {
    MuiIconButton: {
      disableRipple: true,
    },
  },
});

const useToolbarStyles = makeStyles((theme) => ({
  root: {
    paddingLeft: theme.spacing(2),
    paddingRight: theme.spacing(1),
  },
  highlight:
    theme.palette.type === "light"
      ? {
          color: theme.palette.secondary.main,
          backgroundColor: lighten(theme.palette.secondary.light, 0.85),
        }
      : {
          color: theme.palette.text.primary,
          backgroundColor: theme.palette.secondary.dark,
        },
  title: {
    flex: "1 1 100%",
  },
}));

const headCells = [
  { id: "id", label: "ID" },
  { id: "firstName", label: "First Name" },
  { id: "lastName", label: "Last Name" },
  { id: "username", label: "Email" },
  { id: "actions", label: "Action" },
  { id: "actions2", label: "Action 2" },
];

const UsersList = (props) => {
  const [userProfiles, setUserProfiles] = useState([]);
  const { TblContainer, TblHead } = useTable(userProfiles, headCells);
  const [userProfilePicture, setUserProfilePicture] = useState([]);

  useEffect(() => {
    const config = {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    };

    axios.get(`/admin/all-society-members`, config).then((response) => {
      console.log(response);
      //console.log(userProfiles);
      //localStorage.setItem("userId", response.data.id);//commented after userId was undefined
      setUserProfiles(response.data);
    });
  }, []);

  // function loadMoreSocietyMembers(e) {
  //   console.log(e);
  //   let bottom =
  //     e.target.scrollHeight - scroll.clientHeight - e.target.scrollTab;
  //   if (bottom) {
  //   }
  // }

  // const classes = useStyles();
  //const profileId = localStorage.setItem("viewProfileId", response.data.id);

  function viewProfile(id) {
    console.log("View Profile button clicked");
    const config = {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    };
    axios
      .get(`http://localhost:8080/admin/viewprofile/${id}`, config)
      .then((response) => {
        console.log(response);
        console.log("above push");
        console.log(props);
        localStorage.setItem("viewProfileId", response.data.id);
        props.history.push("/profile");
      });
  }

  function enterMaintenanceCharges(id) {
    //e.preventDefault();
    console.log("Enter Maint. Charges button clicked");
    localStorage.setItem("setMaintCharges", id);
    props.history.push("/maintenance-charges-form");
  }

  return (
    // onScroll={loadMoreSocietyMembers} className={classes.societyMembers}
    <div>
      <Paper>
        <TblContainer>
          <TblHead />
          <TableBody>
            {userProfiles.map((item) => (
              <TableRow key={item.id}>
                <TableCell>{item.id}</TableCell>
                <TableCell>{item.firstName}</TableCell>
                <TableCell>{item.lastName}</TableCell>
                <TableCell>{item.username}</TableCell>
                <TableCell>
                  <Button
                    variant="contained"
                    color="primary"
                    //value={viewProfile}
                    onClick={() => viewProfile(item.id)}
                  >
                    View Profile
                  </Button>
                  {/* <Link to="/profile" className="btn btn-primary">
                    View Profile
                  </Link> */}
                </TableCell>
                <TableCell>
                  <Button
                    variant="contained"
                    color="primary"
                    onClick={() => enterMaintenanceCharges(item.id)}
                  >
                    Maint. Charges
                  </Button>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </TblContainer>
      </Paper>
    </div>
  );
};

function SocietyMembers(props) {
  const classes = useStyles();
  //const [records, setRecords] = useState(adminService.getAllSocietyMembers());

  //console.log(records);

  return (
    <ThemeProvider theme={theme}>
      <AdminNavbar />
      <Header />
      <Container component="main" maxWidth="false">
        <div className={classes.paper}> </div>

        <UsersList {...props} />
        {/* <h1>{user}</h1> */}
      </Container>

      {/* <Footer /> */}
    </ThemeProvider>
  );
}

export default withRouter(SocietyMembers);
