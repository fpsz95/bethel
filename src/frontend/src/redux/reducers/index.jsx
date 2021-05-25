import { paytmTokenResReducer } from "../reducers/paytmTokenResReducer";
import { invoiceDetailsReducer } from "../reducers/invoiceDetailsReducer";

import { combineReducers } from "redux";

//step 3

const rootReducer = combineReducers({
  paytmRes: paytmTokenResReducer,
  invoiceRes: invoiceDetailsReducer,
});

export default rootReducer;
