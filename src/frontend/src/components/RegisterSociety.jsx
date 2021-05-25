import React from "react";
// import { getProfile } from "./userservices.jsx";
import SignInHeader from "./SignInHeader";
import SignInNavbar from "./SignInNavbar";
import Footer from "./Footer";

import axios from "axios";
import { useForm } from "react-hook-form";
// import { useForm, Controller } from "react-hook-form";
import { useHistory } from "react-router";
import { Link } from "react-router-dom";
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
// import Navbar from "../components2/Navbar.jsx";
// import Header from "../components2/codeaffection/Header";

const useStyles = makeStyles((theme) => ({
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

function RegisterSociety(props) {
  const { register, handleSubmit } = useForm();
  const history = useHistory();
  const classes = useStyles();
  const onSubmit = (data) => console.log(data);

  return (
    <div>
      <div>
        <Container component="main" maxWidth="xs">
          <CssBaseline />
          <div className={classes.paper}>
            <Typography component="h1" variant="h5">
              Society Registration
            </Typography>
            <form
              onSubmit={handleSubmit(async (formData) => {
                console.log(formData);
                await axios
                  .post("/registration/register-society", formData)
                  // userservice
                  //   .userLogin()
                  .then((res) => {
                    props.history.push("/secretary-sign-up");
                  })
                  .catch((err) => {
                    console.log("inside axios error catch");
                    console.log(err);
                  });
                //
              })}
              className={classes.form}
            >
              <TextField
                variant="outlined"
                margin="dense"
                required
                fullWidth
                id="societyName"
                label="Society Name"
                name="societyName"
                autoComplete="societyName"
                autoFocus
                //ref={register()}
                inputRef={register()}
              />
              <TextField
                variant="outlined"
                margin="dense"
                required
                fullWidth
                id="wingName"
                label="Wing Name"
                name="wingName"
                autoComplete="wingName"
                //ref={register()}
                inputRef={register()}
              />

              <TextField
                variant="outlined"
                margin="dense"
                required
                fullWidth
                id="ward"
                label="Ward"
                name="ward"
                autoComplete="ward"
                //ref={register()}
                inputRef={register()}
              />

              <TextField
                variant="outlined"
                margin="dense"
                required
                fullWidth
                name="plotNo"
                label="Plot No"
                type="text"
                id="plotNo"
                autoComplete="plotNo"
                inputRef={register()}
              />
              <TextField
                variant="outlined"
                margin="dense"
                required
                fullWidth
                name="area"
                label="Area"
                type="text"
                id="area"
                autoComplete="area"
                inputRef={register()}
              />

              <TextField
                variant="outlined"
                margin="dense"
                required
                fullWidth
                name="street"
                label="Street"
                type="text"
                id="street"
                autoComplete="street"
                inputRef={register()}
              />

              <TextField
                variant="outlined"
                margin="dense"
                required
                fullWidth
                name="city"
                label="City"
                type="text"
                id="city"
                autoComplete="city"
                inputRef={register()}
              />

              <TextField
                variant="outlined"
                margin="dense"
                required
                fullWidth
                name="pinCode"
                label="Pin Code"
                type="text"
                id="pinCode"
                autoComplete="pinCode"
                inputRef={register()}
              />

              <TextField
                variant="outlined"
                margin="dense"
                required
                fullWidth
                name="phoneNo"
                label="Phone No"
                type="Number"
                id="phoneNo"
                autoComplete="phoneNo"
                inputRef={register()}
              />

              <TextField
                variant="outlined"
                margin="dense"
                required
                fullWidth
                name="societyEmailId"
                label="Society Email Id"
                type="text"
                id="societyEmailId"
                autoComplete="societyEmailId"
                inputRef={register()}
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
              <Grid container>
                <Grid item xs>
                  <Link to="/forgot_password" href="#" variant="body2">
                    {/* Welcome */}
                  </Link>
                </Grid>
                <Grid item>
                  <Link to="/signin" href="#" variant="body2">
                    {"Already have an Account?"}
                  </Link>
                </Grid>
              </Grid>
            </form>
          </div>
        </Container>

        <Footer />
      </div>
    </div>
  );
}

export default RegisterSociety;
