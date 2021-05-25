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

function SignUp(props) {
  const { register, handleSubmit } = useForm();
  const classes = useStyles();
  const [society, setSociety] = useState([]);
  const [values, setValues] = useState("");
  const [society2, setSoceity2] = useState("");
  const [userFormData, setUserFormData] = useState("");
  // const handleInputChange = (e) => {
  //   const { name, value } = e.target;
  //   setValues({
  //     ...values,
  //     [name]: value,
  //   });
  // };

  const getSocietyNames = async () => {
    await axios
      .get(`/api/v1/admin/society-list`)
      // .then((res) =>
      //   res.json().then((societ) => {
      //     console.log(societ);
      //   })
      // );
      .then((res) => {
        console.log(res);
        setSociety(res.data);
        console.log(society);
      });
  };

  const onSocietySelection = (event) => {
    //event.preventDefault();
    //const { name, value } = event.target;
    // setValues({ ...values, [name]: value });

    const selectedSociety = event.target.value;
    localStorage.setItem("selectedSocietyValue", selectedSociety);
    setValues(selectedSociety);

    //console.log(values);
    //const soclist = event.target.value
  };

  useEffect(() => {
    getSocietyNames();
  }, []);

  function consoleItem(item, index, arr) {
    //console.log(item);
    return <MenuItem value={item}>{item}</MenuItem>;
  }

  console.log(society);
  const onSubmit = (data) => console.log(data);

  return (
    <div>
      <div>
        <Container component="main" maxWidth="xs">
          <CssBaseline />
          <div className={classes.paper}>
            <Typography component="h1" variant="h5">
              Sign Up
            </Typography>
            <form
              onSubmit={handleSubmit(async (formData) => {
                console.log(formData);
                const myFormData = {
                  firstName: formData.firstName,
                  lastName: formData.lastName,
                  username: formData.userName,
                  password: formData.password,
                  //roles: formData.roles,
                  society: {
                    societyId: formData.societyId,
                  },
                };
                console.log(myFormData);
                await axios
                  .post("/registration/register-user", myFormData)
                  // userservice
                  //   .userLogin()
                  .then((res) => {
                    props.history.push("/signin");
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
                id="firstName"
                label="First Name"
                name="firstName"
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
                id="lastName"
                label="Last Name"
                name="lastName"
                type="lastName"
                autoComplete="lastName"
                //ref={register()}
                inputRef={register()}
              />
              <TextField
                variant="outlined"
                margin="dense"
                required
                fullWidth
                name="userName"
                label="Username"
                type="userName"
                id="userName"
                autoComplete="userName"
                inputRef={register()}
              />
              <TextField
                variant="outlined"
                margin="dense"
                required
                fullWidth
                name="password"
                label="Password"
                type="password"
                id="password"
                autoComplete="current-password"
                inputRef={register()}
              />

              {/* <TextField
                variant="outlined"
                margin="dense"
                required
                fullWidth
                name="roles"
                label="Roles"
                type="roles"
                id="roles"
                autoComplete="roles"
                inputRef={register()}
              /> */}

              <TextField
                variant="outlined"
                margin="dense"
                required
                fullWidth
                name="societyId"
                label="Society Id"
                type="societyId"
                id="societyId"
                autoComplete="societyId"
                inputRef={register()}
              />

              <Button
                type="submit"
                fullWidth
                variant="contained"
                color="primary"
                className={classes.submit}
              >
                Sign Up
              </Button>
              <Grid container>
                <Grid item xs>
                  <Link to="/forgot_password" href="#" variant="body2">
                    Welcome
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

export default SignUp;
