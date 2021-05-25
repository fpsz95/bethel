import { ActionTypes } from "../constants/action-types";

//Step 2
const initialState = {
  invoiceDetails: ["Inital State of Invoice Details Response "],
};
export const invoiceDetailsReducer = (
  state = initialState,
  { type, payload }
) => {
  switch (type) {
    case ActionTypes.SET_INVOICE_DETAILS_RESPONSE:
      return { ...state, invoiceDetailsReducer: payload };
    default:
      return state;
  }
};
