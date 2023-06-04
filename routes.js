const express = require("express");
const { 
    userRegister, 
    userLogin, 
    userProfile, 
    userLogout 
} = require("./controller/userController");
const {
    contentCreate,
    homePage,
    getBreed,
    getGender,
} = require("./controller/contentController");
const router = express.Router();

router.post("/register", userRegister);
router.post("/login", userLogin);
router.get("/profile/:id", userProfile);
router.get("/logout/:id", userLogout);

router.post("/:id/create", contentCreate);
router.get("/home", homePage);
router.get("/breed/:breed", getBreed);
router.get("/gender/:gender", getGender);

module.exports = router ;