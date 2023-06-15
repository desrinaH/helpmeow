const express = require("express");
const { 
    userRegister, 
    userLogin, 
    userProfile, 
    userLogout 
} = require("./controller/userController");
const {
    imagePost,
    contentCreate,
    homePage,
    getRole,
    getGender,
    searchBar,
} = require("./controller/contentController");
const { getInfo,} = require("./controller/infoController");
const router = express.Router();

router.post("/register", userRegister);
router.post("/login", userLogin);
router.get("/profile/:id", userProfile);
router.get("/logout/:id", userLogout);

router.post("/create/image/:id", imagePost);
router.post("/create/:id", contentCreate);
router.get("/home", homePage);
router.post("/search/role", getRole);
router.post("/search/gender", getGender);
router.post("/search", searchBar);

router.post("/information", getInfo);

module.exports = router ;