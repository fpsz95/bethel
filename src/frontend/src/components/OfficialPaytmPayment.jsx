import { ContactSupportOutlined } from "@material-ui/icons";
import { Callbacks } from "jquery";
import React, { useRef, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import ScriptTag from "react-script-tag";
import { paytmTokenResReducer } from "../redux/reducers/paytmTokenResReducer";

function OfficialPaytmPayment() {
  const invoiceDetail = useSelector(
    (state) => state.invoiceRes.invoiceDetailsReducer
  );
  const paytmTokenRes = useSelector(
    (state) => state.paytmRes.paytmTokenResponse
  );
  //   useEffect(() => {
  //     const paytm = document.createElement("button");

  //     paytm.innerHTML = "CLICK ME PLEASE";

  //     document.body.appendChild(paytm);

  //     paytm.addEventListener("click", () => {
  //       alert("Thanks for clicking!");
  //     });
  //   }, []);

  const oid = invoiceDetail && invoiceDetail.data.orderId;
  console.log(oid);
  const tkn = paytmTokenRes && paytmTokenRes.body.txnToken;
  console.log(tkn);
  const amt = invoiceDetail && invoiceDetail.data.totalCharges;
  console.log(amt);

  const instance = useRef(null);
  // useEffect(() => {
  function onScriptLoad() {
    console.log("Inside onScriptLoad");
    alert("Hello Peter");
    const config = {
      root: "",
      flow: "DEFAULT",
      data: {
        orderId:
          invoiceDetail && invoiceDetail.data.orderId /* update order id */,
        token:
          paytmTokenRes && paytmTokenRes.body.txnToken /* update token value */,
        tokenType: "TXN_TOKEN",
        amount:
          invoiceDetail && invoiceDetail.data.totalCharges /* update amount */,
      },

      handler: {
        notifyMerchant: function (eventName, data) {
          console.log("notifyMerchant handler function called");
          console.log("eventName => ", eventName);
          console.log("data => ", data);
          //location.reload();
        },
      },
    };

    console.log(window);
    console.log(window.Paytm);

    // loadjs(
    //   "https://securegw-stage.paytm.in/merchantpgpui/checkoutjs/merchants/CGdjWI85481997714720.js"
    // );

    if (window.Paytm && window.Paytm.CheckoutJS) {
      window.Paytm.CheckoutJS.onLoad(function excecuteAfterCompleteLoad() {
        // initialze configuration using init method
        window.Paytm.CheckoutJS.init(config)
          .then(function onSuccess() {
            // after successfully updating configuration, invoke JS Checkout
            window.Paytm.CheckoutJS.invoke();
          })
          .catch(function onError(error) {
            console.log("error => ", error);
          });
      });
    }
    console.log(window);
    console.log(window.Paytm);
  }
  // const root = document.getElementById("root");
  // console.log(root);
  // const scriptTag = document.createElement("script");
  // scriptTag.type = "application/html";
  // scriptTag.crossOrigin = "anonymous";
  // scriptTag.src =
  //   "https://securegw-stage.paytm.in/merchantpgpui/checkoutjs/merchants/CGdjWI85481997714720.js";
  // // scriptTag.onload = function () {
  // //   alert("onscriptload");
  // //   onScriptLoad();

  // //   console.log("onscriptload");
  // // };
  // document.body.appendChild(scriptTag);
  // // instance.current.appendChild(scriptTag);
  // console.log(scriptTag);
  // // }, []);

  return (
    <div>
      <h1>Official Paytm Payment Method</h1>
      {/* <div ref={instance} /> */}
      <script
        async
        type="application/html"
        crossOrigin="anonymous"
        src="https://securegw-stage.paytm.in/merchantpgpui/checkoutjs/merchants/CGdjWI85481997714720.js"
        onLoad={onScriptLoad()}
      />
      {/* <ScriptTag
        //isHydrating={true}
        type="application/html"
        src="https://securegw-stage.paytm.in/merchantpgpui/checkoutjs/merchants/CGdjWI85481997714720.js"
        crossOrigin="anonymous"
        onLoad={onScriptLoad()}
      /> */}
    </div>
  );
}

export default OfficialPaytmPayment;
