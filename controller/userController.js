const asyncHandler = require("express-async-handler");
const Firestore = require('@google-cloud/firestore');
//var jwt = require('jsonwebtoken');

const db = new Firestore({
    projectId: 'helpmeow',
    keyFilename: './sa/helpmeow-a92698f4b6a6.json',
  });

const userRegister = asyncHandler (async(req, res) => {
    const { email, username, password, /*confirmPass*/ } = req.body;
    const documentRef = db.collection('users').doc();

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if(!emailRegex.test(email)) {
        res.status(400).json({ message: 'Invalid email format' });
        return;
    }

    const emailExists = await db
        .collection('users')
        .where('email', '==', email)
        .get();

    const usernameExists = await db
        .collection('users')
        .where('email', '==', email)
        .get();

    if ( !emailExists.empty) {
        res.status(400).json({ message: 'Email already exists' });
        return;
    }

    if ( !usernameExists.empty) {
        res.status(400).json({ message: 'Username is already taken' });
        return;
    }

    if (password.length < 8) {
        res.status(400).json({ message: 'Password must be at least 8 characters long' });
        return;
    }

    // if (password !== confirmPass) {
    //     res.status(400).json({ message: 'Please make sure the password and confirmation match' });
    //     return;
    // }

    //const token = '';
    const isRevoked = true;

    const documentData = { 
        email, 
        username, 
        password, 
       // token, 
        isRevoked 
    };

    await documentRef.set(documentData);

    res.status(201).json({ username: username});
})

const userLogin = asyncHandler (async(req, res) => {
    const { email, password } = req.body;

    const emailExists = await db
        .collection('users')
        .where('email', '==', email)
        .limit(1)
        .get();

    if ( emailExists.empty) {
        res.status(404).json({ message: 'Email not registered' });
        return;
    }

    const userDoc = emailExists.docs[0];
    const userData = userDoc.data();

    if (userData.password !== password) {
        res.status(401).json({ message: 'Invalid password' });
        return;
    }

    // const tokenPayload = { email, timestamp: Date.now() };

    // const token = jwt.sign(tokenPayload, 'ketoprak', { expiresIn: '1h' });
    const isRevoked = false;

    await userDoc.ref.update({  isRevoked });

    res.status(200).json({ email: userData.email, your_id: userDoc.id, username: userData.username, message: 'Login successful' });

});

const userProfile = asyncHandler(async(req, res) => {
    const { id } = req.params;
    //const { authorization } = req.headers;

    // if (!authorization || !authorization.startsWith('Bearer ')) {
    //     res.status(401).json({ message: 'Unauthorized' }); return;
    // }

    // const token = authorization.split(' ')[1];

    try {
        //const decodedToken = jwt.verify( token, 'ketoprak' );
        const documentRef = db.collection('users').doc(id);
        const documentSnaps = await documentRef.get();

        if (!documentSnaps.exists) {
            res.status(404).json({ message: 'User not found' }); return;
        }

        const documentData = documentSnaps.data();

        // if (decodedToken.email !== documentData.email) {
        //     res.status(401).json({ message: 'Unauthorized' });
        //     return;
        // }

        if (documentData.isRevoked == true) {
            res.status(401).json({ message: 'Unauthorized' }); return;
        }

        res.status(200).json({
            email: documentData.email, 
            username: documentData.username,
            //id: documentSnaps.id,
            //token: documentData.token
        });

    } catch (error) {
        res.status(401).json({ message: 'Unauthorized' });
    }

});

const userLogout = asyncHandler(async(req, res) => {
    const { id } = req.params;
    //const { authorization } = req.headers;

    // if (!authorization || !authorization.startsWith('Bearer ')) {
    //     res.status(401).json({ message: 'Unauthorized' }); return;
    // }

    // const token = authorization.split(' ')[1];

    try {
        //const decodedToken = jwt.verify( token, 'ketoprak' );
        const documentRef = db.collection('users').doc(id);
        const documentSnaps = await documentRef.get();

        if (!documentSnaps.exists) {
            res.status(404).json({ message: 'User not found' }); return;
        }

        const documentData = documentSnaps.data();

        // if (decodedToken.email !== documentData.email) {
        //     res.status(401).json({ message: 'Unauthorized' });
        //     return;
        // }

        if (documentData.isRevoked == true) {
            res.status(401).json({ message: 'Unauthorized' }); return;
        }

        //const newtoken = '';
        const isRevoked = true;

        await documentSnaps.ref.update({ isRevoked });

        res.status(200).json({ message: 'Logged out' });

    } catch (error) {
        res.status(401).json({ message: 'Unauthorized' });
    }

});

module.exports = {
    userRegister,
    userLogin,
    userProfile,
    userLogout,
}