import { Button } from "@material-ui/core";
import React, { useEffect } from "react";
import axios from "axios";
import { useSelector, useDispatch } from "react-redux";
import {
  setPaytmTokenRes,
  setInvoiceDetails,
} from "../redux/actions/paytmTokenResAction";
import { withRouter } from "react-router-dom";
import UserInvoiceDetails from "./UserInvoiceDetails";

const Invoice = (props) => {
  const dispatchInvoice = useDispatch();

  const paytmTokenResp = useSelector((state) => state);

  //console.log(invoiceDetailsRes); //Guess this is the wrong place to consolelog as it is undefined for the first time as render method gets called first, and since this is an async call it gives the value the second time

  //*******************************************  FETCH INVOICE DETAILS  ********************************************************** */
  const fetchInvoiceDetails = async () => {
    const config = {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    };
    const invoiceRes = await axios
      .get(`/payment/invoice/1`, config)
      .catch((err) => {
        console.log(err);
      });
    console.log(invoiceRes);
    dispatchInvoice(setInvoiceDetails(invoiceRes));
  };
  useEffect(() => {
    fetchInvoiceDetails();
  }, []);

  return (
    <div>
      <UserInvoiceDetails />
    </div>
  );
};

export default withRouter(Invoice);
