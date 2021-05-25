import React from "react";
import { MenuItem, Select as MuiSelect } from "@material-ui/core";

function Select(props) {
  const { name, label, value, onChange, options } = props;

  return (
    <div>
      <MuiSelect label={label} name={name} value={value} onChange={onChange}>
        <MenuItem value="">None</MenuItem>
        {/* {options.forEach((item) => (
          <MenuItem value={item}>{item}</MenuItem>
        ))} */}
        {options.forEach((item) => (
          <MenuItem value={item}>{item}</MenuItem>
        ))}
      </MuiSelect>
    </div>
  );
}

export default Select;
