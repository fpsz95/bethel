import { ActionTypes } from "../constants/action-types";

//Step 2
const initialState = {
  paytmResponse: ["Inital State of Paytm Token Response"],
};
export const paytmTokenResReducer = (
  state = initialState,
  { type, payload }
) => {
  switch (type) {
    case ActionTypes.SET_PAYTM_PAYMENT_RESPONSE:
      return { ...state, paytmTokenResponse: payload };
    default:
      return state;
  }
};
