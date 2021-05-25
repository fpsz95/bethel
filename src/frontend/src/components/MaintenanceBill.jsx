import { Button, Container, Typography } from "@material-ui/core";
import React, { useEffect, useState } from "react";
import Header from "./Header";
import useTable from "./useTable";
import Navbar from "./Navbar";
import { withRouter } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import {
  setPaytmTokenRes,
  setInvoiceDetails,
} from "../redux/actions/paytmTokenResAction";
import {
  CheckoutProvider,
  Checkout,
  injectCheckout,
} from "paytm-blink-checkout-react";
import axios from "axios";
import Invoice from "./Invoice";

const MaintenanceBill = () => {
  return (
    <div>
      <Navbar />
      <Header />
      <Container component="main" maxWidth="xs">
        <Typography component="h4" variant="h4">
          Maintenance Bill
        </Typography>
        <Invoice />

        {/* <PaytmPayment /> */}
      </Container>
    </div>
  );
};

export default withRouter(MaintenanceBill);
