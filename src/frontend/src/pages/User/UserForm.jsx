import { TextField } from "@material-ui/core";
import React, { useState, useEffect } from "react";
import Input from "../../components/controls/Input";
import Select from "../../components/controls/Select";
import { useForm, Form } from "../../components/useForm";
// import * as userServe from "../../components/userservices";

const initialFValues = {
  firstName: "",
  lastName: "",
  username: "",
  password: "",
  societyName: "",
};

function UserForm() {
  const { values, setValues, handleInputChange } = useForm(initialFValues);

  return (
    <Form>
      <Input
        name="firstName"
        label="First Name"
        value={values.firstName}
        onChange={handleInputChange}
      />
      <Input
        variant="outlined"
        label="Last Name"
        name="lastName"
        value={values.lastName}
        onChange={handleInputChange}
      />
      <Input
        variant="outlined"
        label="Username"
        name="username"
        value={values.username}
        onChange={handleInputChange}
      />
      <Input
        variant="outlined"
        label="Password"
        name="password"
        value={values.password}
        onChange={handleInputChange}
      />
      <Input
        variant="outlined"
        label="Society Name"
        name="societyName"
        value={values.societyName}
        onChange={handleInputChange}
      />
      <Select
        name="societyName"
        label="Society"
        value={values.societyName}
        onChange={handleInputChange}
        // options={userServe.getUser()}
      />
    </Form>
  );
}

export default UserForm;
