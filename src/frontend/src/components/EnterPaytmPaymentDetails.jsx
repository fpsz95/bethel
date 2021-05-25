import React, { useState, useEffect } from "react";
import { getProfile } from "./userservices.jsx";
import SignInHeader from "./SignInHeader";
import SignInNavbar from "./SignInNavbar";
import Footer from "./Footer";
import * as userServe from "./userservices";
import { withRouter } from "react-router-dom";

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

function EnterPaytmPaymentDetails(props) {
  const { register, handleSubmit } = useForm();
  const classes = useStyles();

  function consoleItem(item, index, arr) {
    //console.log(item);
    return <MenuItem value={item}>{item}</MenuItem>;
  }

  function pay() {
    console.log("pay clicked");
    var config = {
      root: "",
      flow: "DEFAULT",
      data: {
        orderId: "ORDER_ID_698122" /* update order id */,
        token: localStorage.getItem("txnToken") /* update token value */,
        tokenType: "TXN_TOKEN",
        amount: "1" /* update amount */,
      },
      handler: {
        notifyMerchant: function (eventName, data) {
          console.log("notifyMerchant handler function called");
          console.log("eventName => ", eventName);
          console.log("data => ", data);
        },
      },
    };
    console.log(
      window.Paytm //+ " and " + window.Paytm.CheckoutJS
    );
    if (window.Paytm && window.Paytm.CheckoutJS) {
      window.Paytm.CheckoutJS.onLoad(function excecuteAfterCompleteLoad() {
        // initialze configuration using init method
        window.Paytm.CheckoutJS.init(config)
          .then(function onSuccess() {
            // after successfully updating configuration, invoke JS Checkout
            console.log("Invoking onSuccess");
            window.Paytm.CheckoutJS.invoke();
          })
          .catch(function onError(error) {
            console.log("error => ", error);
          });
      });
    }
  }

  const onSubmit = (data) => console.log(data);

  return (
    <div>
      <div>
        <Container component="main" maxWidth="xs">
          <CssBaseline />
          <div className={classes.paper}>
            <Typography component="h1" variant="h5">
              Paytm Payment Details
            </Typography>
            <form
              onSubmit={handleSubmit(async (formData) => {
                //console.log(formData);
                const config = {
                  headers: {
                    Authorization: "Bearer " + localStorage.getItem("token"),
                    // ContentType: "application/json",
                  },
                };
                axios
                  .post(
                    "/payment/payment-details-redirect",
                    //"http://localhost:8081/payment/pgredirect",
                    formData,
                    config
                  )
                  .then((res) => {
                    console.log(res);
                    console.log(res.data);
                    console.log(res.data.body.txnToken);
                    localStorage.setItem("txnToken", res.data.body.txnToken);
                    // var config = {
                    //   root: "",
                    //   flow: "DEFAULT",
                    //   data: {
                    //     orderId: "ORDER_ID_698122" /* update order id */,
                    //     token: res.data.body.txnToken /* update token value */,
                    //     tokenType: "TXN_TOKEN",
                    //     amount: "1" /* update amount */,
                    //   },
                    //   handler: {
                    //     notifyMerchant: function (eventName, data) {
                    //       console.log("notifyMerchant handler function called");
                    //       console.log("eventName => ", eventName);
                    //       console.log("data => ", data);
                    //     },
                    //   },
                    // };
                    // if (window.Paytm && window.Paytm.CheckoutJS) {
                    //   window.Paytm.CheckoutJS.onLoad(
                    //     function excecuteAfterCompleteLoad() {
                    //       // initialze configuration using init method
                    //       window.Paytm.CheckoutJS.init(config)
                    //         .then(function onSuccess() {
                    //           // after successfully updating configuration, invoke JS Checkout
                    //           console.log("Invoking onSuccess");
                    //           window.Paytm.CheckoutJS.invoke();
                    //         })
                    //         .catch(function onError(error) {
                    //           console.log("error => ", error);
                    //         });
                    //     }
                    //   );
                    // }
                  });
              })}
              className={classes.form}
            >
              <TextField
                variant="outlined"
                margin="dense"
                required
                fullWidth
                id="orderId"
                label="Order Id"
                name="orderId"
                //autoComplete="" //value should be true or false
                autoFocus
                //ref={register()}
                inputRef={register()}
              />
              <TextField
                variant="outlined"
                margin="dense"
                required
                fullWidth
                //id="userId"
                label="User Id"
                name="userId"
                type="number"
                autoComplete="lastName"
                //ref={register()}
                inputRef={register()}
              />
              <TextField
                variant="outlined"
                margin="dense"
                required
                fullWidth
                name="transactionAmount"
                label="Amount"
                type="number"
                //id="transactionAmount"
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
                Pay with Paytm
              </Button>
            </form>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              color="secondary"
              onClick={pay}
              className={classes.submit}
            >
              PAY
            </Button>
          </div>
        </Container>
        <Footer />
      </div>
      <script
        type="application/html"
        crossorigin="anonymous"
        src="https://securegw-stage.paytm.in/merchantpgpui/checkoutjs/merchants/CGdjWI85481997714720.js"
        onload={pay()}
      ></script>
    </div>
  );
}

export default withRouter(EnterPaytmPaymentDetails);
