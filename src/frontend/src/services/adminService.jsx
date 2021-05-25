import axios from "axios";

const id = localStorage.getItem("userId");

export default function getAllSocietyMembers() {
  const config = {
    headers: {
      Authorization: "Bearer " + localStorage.getItem("token"),
    },
  };

  return axios.get(`http://localhost:8080/admin/all-society-members`, config);
}

export function viewProfileInfo() {
  const config = {
    headers: {
      Authorization: "Bearer " + localStorage.getItem("token"),
    },
  };

  return axios.get(`http://localhost:8080/admin/viewprofile/${id}`, config);
}
