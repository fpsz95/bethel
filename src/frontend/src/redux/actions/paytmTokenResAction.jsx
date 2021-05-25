import { ActionTypes } from "../constants/action-types";

//Step 1
export const setPaytmTokenRes = (paytmTokenRes) => {
  console.log(paytmTokenRes);
  return {
    type: ActionTypes.SET_PAYTM_PAYMENT_RESPONSE,
    payload: paytmTokenRes,
  };
};

export const setInvoiceDetails = (invoiceDetails) => {
  console.log(invoiceDetails);
  return {
    type: ActionTypes.SET_INVOICE_DETAILS_RESPONSE,
    payload: invoiceDetails,
  };
};
