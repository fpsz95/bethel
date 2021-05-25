import React, { useState, useEffect } from "react";
import { getProfile } from "./userservices.jsx";
import SignInHeader from "./SignInHeader";
import SignInNavbar from "./SignInNavbar";
import Footer from "./Footer";
import * as userServe from "./userservices";

import axios from "axios";
import { useForm } from "react-hook-form";
// import { useForm, Controller } from "react-hook-form";
import { useHistory } from "react-router";
import { withRouter, Link } from "react-router-dom";
import Button from "@material-ui/core/Button";
import CssBaseline from "@material-ui/core/CssBaseline";
import TextField from "@material-ui/core/TextField";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import Checkbox from "@material-ui/core/Checkbox";
//import Link from "@material-ui/core/Link";
import Grid from "@material-ui/core/Grid";
import Box from "@material-ui/core/Box";
//import LockOutlinedIcon from "@material-ui/icons/LockOutlined";
import Typography from "@material-ui/core/Typography";
import { makeStyles } from "@material-ui/core/styles";
import Container from "@material-ui/core/Container";
import MenuIcon from "@material-ui/core/Menu";

import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import IconButton from "@material-ui/core/IconButton";
import InputLabel from "@material-ui/core/InputLabel";
import MenuItem from "@material-ui/core/MenuItem";
import FormHelperText from "@material-ui/core/FormHelperText";
import FormControl from "@material-ui/core/FormControl";
import Select from "@material-ui/core/Select";
const useStyles = makeStyles((theme) => ({
  formControl: {
    margin: theme.spacing(1),
    minWidth: 220,
  },
  selectEmpty: {
    marginTop: theme.spacing(2),
  },
  paper: {
    marginTop: theme.spacing(8),
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
    marginBottom: "450px",
  },
  form: {
    width: "100%", // Fix IE 11 issue.
    marginTop: theme.spacing(1),
  },
  submit: {
    margin: theme.spacing(3, 0, 2),
  },
}));

function MaintenanceChargesForm(props) {
  const { register, handleSubmit } = useForm();
  const classes = useStyles();
  const [society, setSociety] = useState([]);
  const [values, setValues] = useState("");
  const [society2, setSoceity2] = useState("");
  const [userFormData, setUserFormData] = useState("");

  return (
    <div>
      <div>
        <Container component="main" maxWidth="xs">
          <CssBaseline />
          <div className={classes.paper}>
            <Typography component="h1" variant="h5">
              Maintenance Charges Form
            </Typography>
            <form
              onSubmit={handleSubmit(async (formData) => {
                console.log(formData);
                // const myFormData = {
                //   serviceCharges: formData.serviceCharges,
                //   waterCharges: formData.waterCharges,
                //   totalCharges: "2",
                // };
                //console.log("Myformdata" + myFormData);
                const userId = localStorage.getItem("setMaintCharges");
                const config = {
                  headers: {
                    Authorization: "Bearer " + localStorage.getItem("token"),
                  },
                };
                await axios
                  .post(
                    `/admin/user-maintenance-charges-form/1`,
                    formData,
                    config
                  )
                  .then((res) => {
                    //alert(res.data);
                    //localStorage.setItem("setMaintCharges", null);
                    console.log(res);
                    props.history.push("/mainupdatesuccessful");
                  })
                  .catch((err) => {
                    console.log("inside axios error catch");
                    console.log(err);
                  });
              })}
              className={classes.form}
            >
              <TextField
                variant="outlined"
                margin="dense"
                required
                fullWidth
                id="serviceCharges"
                label="Service Charges"
                name="serviceCharges"
                autoComplete="firstname" //value should be true or false
                autoFocus
                //ref={register()}
                inputRef={register()}
              />
              <TextField
                variant="outlined"
                margin="dense"
                required
                fullWidth
                id="waterCharges"
                label="Water Charges"
                name="waterCharges"
                type="number"
                autoComplete="lastName"
                //ref={register()}
                inputRef={register()}
              />
              <br />
              <br />
              <br />
              <br />
              <TextField
                margin="dense"
                required
                fullWidth
                id="filled-read-only-input"
                label="Read Only"
                defaultValue="Total Rs.930"
                InputProps={{
                  readOnly: true,
                }}
                variant="filled"
              />

              <Button
                type="submit"
                fullWidth
                variant="contained"
                color="primary"
                className={classes.submit}
              >
                Submit
              </Button>
            </form>
          </div>
        </Container>

        <Footer />
      </div>
    </div>
  );
}

export default withRouter(MaintenanceChargesForm);
