const express = require("express");
const { 
    userRegister, 
    userLogin, 
    userProfile, 
    userLogout 
} = require("./controller/userController");
const router = express.Router();

router.post("/register", userRegister);
router.post("/login", userLogin);
router.get("/profile/:id", userProfile);
router.get("/logout/:id", userLogout);

module.exports = router ;