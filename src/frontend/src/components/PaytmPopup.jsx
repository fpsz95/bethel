import React, { useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import {
  setPaytmTokenRes,
  setInvoiceDetails,
} from "../redux/actions/paytmTokenResAction";

function PaytmPopup() {
  //***********************************************************************     SCRIPT START      ************************************************************************************* */
  (function merchantCheckoutFile() {
    console.log("merchantCheckoutFile START");
    var MID = "CGdjWI85481997714720";
    var URL =
      "https://securegw-stage.paytm.in/merchantpgpui/checkoutjs/CGdjWI85481997714720";
    var merchantCallback = null;
    var createDOMElements = function (input) {
      var scriptEle = document.createElement("script"),
        cssEle = document.createElement("link"),
        iframeEle = document.createElement("iframe");

      if (cssEle) {
        cssEle.href = input.style;
        cssEle.rel = "stylesheet";
        cssEle.type = "text/css";
        document.head.appendChild(cssEle);
      }
      if (scriptEle) {
        scriptEle.async = true;
        scriptEle.src = input.js;
        scriptEle.type = "application/javascript";
        scriptEle.crossOrigin = "anonymous";
        scriptEle.onload = function () {
          if (window.CheckoutJS) {
            if (window.Paytm && window.Paytm.CheckoutJS) {
              for (var key in window.CheckoutJS) {
                if (window.CheckoutJS.hasOwnProperty(key)) {
                  window.Paytm.CheckoutJS[key] = window.CheckoutJS[key];
                }
              }
            }
            try {
              delete window.CheckoutJS; // remove CheckoutJS from window scope
            } catch (e) {}
            if (window.Paytm.CheckoutJS.initLib) {
              window.Paytm.CheckoutJS.initLib(MID).then(function () {
                if (merchantCallback) {
                  merchantCallback.call();
                }
              });
            }
          }
        };
        document.body.appendChild(scriptEle);
      }
    };

    var post = function () {
      var xhr = new XMLHttpRequest();
      xhr.onreadystatechange = function () {
        if (this.readyState === 4) {
          var data = JSON.parse(this.responseText);
          createDOMElements(data);
        }
      };
      xhr.open("GET", URL, true);
      xhr.setRequestHeader(
        "Content-Type",
        "application/javascript; charset=utf-8"
      );
      xhr.send(null);
    };

    post();

    if (!window.Paytm) {
      // check if window.Paytm exists or not
      window.Paytm = {};
    }

    window.Paytm.CheckoutJS = {
      onLoad: function (callback) {
        if (!callback || callback.constructor !== Function) {
          throw new Error(
            "callback in onLoad function should be of function type"
          );
        }
        merchantCallback = callback;
      },
    };
    console.log("merchantCheckoutFile END");
  })();

  //*****************************************************       PAYTM onScriptLoad Function          ******************************************************************** */
  const paytmTokenRes = useSelector(
    (state) => state.paytmRes.paytmTokenResponse
  );

  const invoiceDetail = useSelector(
    (state) => state.invoiceRes.invoiceDetailsReducer
  );

  function onScriptLoad() {
    console.log("Inside onScriptLoad START");
    // alert("Hello Peter");
    console.log(invoiceDetail && invoiceDetail.data.orderId);
    console.log(paytmTokenRes && paytmTokenRes.body.txnToken);
    console.log(invoiceDetail && invoiceDetail.data.totalCharges);
    const config = {
      root: "",
      flow: "DEFAULT",
      data: {
        orderId: "1",
        //invoiceDetail && invoiceDetail.data.orderId /* update order id */,
        //"550a07a0f05148e6b1b2b5e94e26f9111621866567575",
        token: "14e78e556290473ebee93435f9a25a601621873318428",
        // paytmTokenRes && paytmTokenRes.body.txnToken /* update token value */,
        tokenType: "TXN_TOKEN",
        amount: "1",
        //invoiceDetail && invoiceDetail.data.totalCharges /* update amount */,
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

    // window.JustAText.Text =
    //   "Oh My God, I`ve just added an object in my window global object";

    console.log(window);
    console.log(window.Paytm);
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
    console.log("Inside onScriptLoad END");
  }

  //***********************************************************************     SCRIPT END      ************************************************************************************* */
  return (
    <div>
      <script
        async
        type="application/text"
        crossOrigin="anonymous"
        //src="https://securegw-stage.paytm.in/merchantpgpui/checkoutjs/merchants/CGdjWI85481997714720.js"
        onLoad={onScriptLoad()}
      />
    </div>
  );
}

export default PaytmPopup;
