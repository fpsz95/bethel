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
import { StarsSharp } from "@material-ui/icons";

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

function SignIn(props) {
  const { register, handleSubmit } = useForm();
  const history = useHistory();
  const classes = useStyles();
  //const [authority, setAuthority] = useState([]);
  const onSubmit = (data) => console.log(data);

  // const api = axios.create({
  //   baseURL: `http://localhost:8080/login`,
  // });

  const config = {
    headers: {
      Authorization: "Bearer " + localStorage.getItem("token"),
    },
  };

  return (
    <div>
      <SignInNavbar />
      <SignInHeader />
      <div>
        <Container component="main" maxWidth="xs">
          <CssBaseline />
          <div className={classes.paper}>
            <h3>
              <center>This Site is Under Construction</center>
            </h3>

            <Typography component="h1" variant="h5">
              Sign In
            </Typography>
            <form
              onSubmit={handleSubmit(async (formData) => {
                console.log(formData);
                await axios
                  .post("/login", formData)
                  //setAuthority(res.data.authorities)

                  .then((res) => {
                    localStorage.setItem("token", res.data.token);
                    // localStorage.setItem("firstName", res.data.firstName);
                    // localStorage.setItem("lastName", res.data.lastName);
                    if (res.data.authorities[0] == "ADMIN") {
                      props.history.push("/secretaryhome");
                    } else {
                      props.history.push("/memberhome");
                    }
                    // console.log(authority);
                    // console.log("Res Variable" + res);
                    // console.log("Res.data.token Variable" + res.data.token);
                    // console.log(res.data.authorities);
                    // localStorage.setItem("token", res.data.token);
                    // res.data.authorities.then(
                    //   props.history.push("/secretaryhome")
                    // );
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
                id="username"
                label="Email Address"
                name="username"
                autoComplete="email"
                autoFocus
                autoComplete="true"
                //ref={register()}
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
              <FormControlLabel
                control={<Checkbox value="remember" color="primary" />}
                label="Remember me"
              />

              <Button
                type="submit"
                fullWidth
                variant="contained"
                color="primary"
                className={classes.submit}
              >
                Sign In
              </Button>
              <Grid container>
                <Grid item xs>
                  <Link to="/forgot_password" href="#" variant="body2">
                    Forgot password?
                  </Link>
                </Grid>
                <Grid item>
                  <Link to="/signup" href="#" variant="body2">
                    {/* {"Don't have an account? Sign Up"} */}
                  </Link>
                </Grid>
              </Grid>
            </form>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              color="secondary"
              className={classes.submit}
              onClick={() => props.history.push("/signup")}
            >
              User Sign Up
            </Button>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              color="secondary"
              onClick={() => props.history.push("/register-society")}
              className={classes.submit}
            >
              Society & Secretary Sign Up
            </Button>
          </div>
        </Container>

        <Footer />
      </div>
    </div>
  );
}

export default SignIn;
