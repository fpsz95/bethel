import { Button } from "@material-ui/core";
import React from "react";
import { useSelector, useDispatch } from "react-redux";
import axios from "axios";
import {
  setPaytmTokenRes,
  setInvoiceDetails,
} from "../redux/actions/paytmTokenResAction";
import { withRouter } from "react-router-dom";
import {
  CheckoutProvider,
  Checkout,
  injectCheckout,
} from "paytm-blink-checkout-react";

const UserInvoiceDetails = (props) => {
  const invoiceDetail = useSelector(
    (state) => state.invoiceRes.invoiceDetailsReducer
  );

  const dispatchPaytmPaymentTokenRes = useDispatch();
  console.log(invoiceDetail);

  //******************************************   PAY  ******************************** */
  const pay = async () => {
    const config = {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    };
    const data = {
      userId: localStorage.getItem("userId"),
    };
    const response = await axios
      .post("/payment/payment-details-redirect", data, config)
      .catch((err) => {
        console.log(err);
      });
    console.log(response);
    // props.history.push("/under-construction");
    dispatchPaytmPaymentTokenRes(setPaytmTokenRes(response.data)); ///UNCOMMMENTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT
    props.history.push("/paytmPopup");
  };

  console.log(window);
  //*****************************************      PAYTM CONFIG      ******************************************************** */
  console.log(invoiceDetail);
  console.log(invoiceDetail && invoiceDetail.data.orderId);
  //console.log(paytmTokenRes);
  //console.log(paytmTokenRes && paytmTokenRes.body.txnToken);

  return (
    <div>
      <h4>Invoice Details </h4>
      <h5>
        User Id: {invoiceDetail && invoiceDetail.data.id}
        <br />
        Order Id: {invoiceDetail && invoiceDetail.data.orderId}
        <br />
        First Name: {invoiceDetail && invoiceDetail.data.firstName}
        <br />
        Last Name: {invoiceDetail && invoiceDetail.data.lastName}
        <br />
        Service Charges:
        {invoiceDetail && invoiceDetail.data.serviceCharges}
        <br />
        Water Charges: {invoiceDetail && invoiceDetail.data.waterCharges}
        <br />
        Total Charges: {invoiceDetail && invoiceDetail.data.totalCharges}
        <br />
        <br />
      </h5>
      <Button
        fullWidth
        variant="contained"
        color="primary"
        onClick={() => pay()}
      >
        Pay
      </Button>
    </div>
  );
};

export default withRouter(UserInvoiceDetails);
