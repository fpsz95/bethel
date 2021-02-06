import axios from "axios";

const config = {
  headers: {
    Authorization: "Bearer " + localStorage.getItem("token"),
  },
};

// export const userLogin = () => axios.post(`http://localhost:8080/login`);

// export const getProfile = () =>
//   axios.get(`http://localhost:8080/profile`, config);

export const getUser = () =>
  axios.get(`http://localhost:8080/api/v1/admin/society-list`, config);
