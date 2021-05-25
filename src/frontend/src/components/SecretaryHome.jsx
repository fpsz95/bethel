import React, { useState, useEffect, useCallback } from "react";
import axios from "axios";
import { useDropzone } from "react-dropzone";
import {
  Container,
  createMuiTheme,
  makeStyles,
  ThemeProvider,
} from "@material-ui/core";
import Navbar from "./Navbar";
import Header from "./Header";
import Footer from "./Footer";
import AdminNavbar from "./AdminNavbar";

const useStyles = makeStyles({
  appProfile: {
    paddingLeft: "320px",
    width: "100%",
  },
  paper: {
    marginTop: "24px",
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
    marginBottom: "700px",
  },
});

const theme = createMuiTheme({
  pallette: {
    primary: {
      main: "#FF0000",
      light: "#3c44b126",
    },
    secondary: {
      main: "#f83245",
      light: "#f8324526",
    },
    background: {
      default: "#AF79B7",
    },
  },
  // shape: {
  //   borderRadius: "12px",
  // },
  overrides: {
    MuiAppBar: {
      root: {
        transform: "translateZ(0)",
      },
    },
  },
  props: {
    MuiIconButton: {
      disableRipple: true,
    },
  },
  list: {
    width: 250,
  },
  fullList: {
    width: "auto",
  },
});

const UserProfiles = () => {
  const [userProfiles, setUserProfiles] = useState("");
  const [userProfilePicture, setUserProfilePicture] = useState([]);

  useEffect(() => {
    const config = {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    };

    const config2 = {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
        responseType: "arraybuffer",
      },
    };
    //async function fetchUserProfiles() {
    // const response =
    //await
    axios
      .get(`http://localhost:8080/api/v1/admin/profile`, config)
      .then((response) => {
        //console.log(userProfiles);
        localStorage.setItem("userId", response.data.id);
        setUserProfiles(response.data);
      });
    // }

    //async function fetchUserProfilePicture() {
    // const response =
    //await
    const userId = localStorage.getItem("userId");
    axios
      .get(
        //`http://localhost:8080/api/v1/admin/${userProfiles.id}/image/download`,
        `http://localhost:8080/api/v1/admin/${userId}/image/download`,
        config
      )
      .then((response) => {
        //console.log(userProfiles);
        setUserProfilePicture(response.data);
      });
    //}
    //fetchUserProfilePicture();
    //fetchUserProfiles();
    console.log(userProfiles);
  }, []);

  //console.log(userProfiles);
  return (
    <div>
      <h1>Secretary Home</h1>
      <br />
      <br />
      {userProfiles.id ? (
        <img
          src={`http://localhost:8080/api/v1/admin/${userProfiles.id}/image/download`}
          alt=""
          //ref={config}
        />
      ) : null}
      {/* <Image
        src={`http://localhost:8080/api/v1/admin/${userProfiles.id}/image/download`}
      /> */}
      <h1>{userProfiles.username}</h1>
      <p>{userProfiles.id}</p>
      <Dropzone {...userProfiles} />
      {/* declare like this instead of
      userProfiles.id, userprofiles.username and all */}
      <br />
    </div>
  );
};

function Dropzone({ id }) {
  const onDrop = useCallback((acceptedFiles) => {
    const file = acceptedFiles[0];

    console.log(file);

    const formData = new FormData();
    formData.append("file", file); //goes to the RequestParam at the backend
    console.log("userprofileidbelow");
    console.log(id);
    const userId2 = localStorage.getItem("userId");
    axios
      .post(
        //`http://localhost:8080/api/v1/admin/${userId2}/image/upload`
        `http://localhost:8080/api/v1/admin/${userId2}/image/upload`,
        formData,
        {
          //this is in the form of object
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
            "Content-Type": "multipart/form-data",
          },
        }
      )
      .then(() => {
        console.log("File Upload Successfully");
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);
  const { getRootProps, getInputProps, isDragActive } = useDropzone({ onDrop });

  return (
    <div {...getRootProps()}>
      <input {...getInputProps()} />
      {isDragActive ? (
        <p>Drop the files here ...</p>
      ) : (
        <p>Drag 'n' drop some files here, or click to select files</p>
      )}
    </div>
  );
}

function Profile() {
  const classes = useStyles();
  return (
    <ThemeProvider theme={theme}>
      <AdminNavbar />
      <Header />
      <Container component="main" maxWidth="xs">
        <div className={classes.paper}> </div>
        <UserProfiles />
        {/* <h1>{user}</h1> */}
      </Container>
      <Footer />
    </ThemeProvider>
  );
}

export default Profile;
