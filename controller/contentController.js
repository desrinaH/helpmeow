const asyncHandler = require("express-async-handler");
const Firestore = require('@google-cloud/firestore');
require('dotenv').config();
const { createClient } = require('@supabase/supabase-js');
const { uploadImage } = require('../helper/helpers');

const supabaseUrl = 'https://bpsmobjqnwxpsawzapnn.supabase.co'
const supabaseKey = process.env.SUPABASE_KEY
const supabase = createClient(supabaseUrl, supabaseKey)

const db = new Firestore({
    projectId: 'helpmeow',
    keyFilename: './sa/helpmeow-a92698f4b6a6.json',
  });

const contentCreate = asyncHandler (async(req, res) => {
    const { id } = req.params;

    try{
        const documentRef = db.collection('users').doc(id);
        const documentSnaps = await documentRef.get();

        if (!documentSnaps.exists) {
            res.status(401).json({ message: 'User must have account to upload' }); return;
        }
        
        const documentData = documentSnaps.data();
        const upload_by_username = documentData.username;

        const myFile = req.file; // file kalo di postman
        const {  
            //photo, 
            name, 
            gender, 
            breed,
            longitude, 
            latitude, 
            location,
            description,
            role,
        } = req.body;

        if (name.length > 16) {
            res.status(400).json({ message: 'Name must be no more than 16 characters'});
            return;
        }

        if (description.length > 100) {
            res.status(400).json({ message: 'Description must be no more than 100 characters'});
            return;
        }

        const imageUrl = await uploadImage(myFile)

        const { data, error } = await supabase
            .from('contents')
            .insert([{
                photo: imageUrl,
                name: name,
                gender: gender,
                breed: breed,
                location: location,
                description: description,
                upload_by_username: upload_by_username,
                created_at: new Date(Date.now()),
                role: role,
                longitude: longitude,
                latitude: latitude,
            }])
            
        if (error) {
            console.error('Insert data error:', error);
            res.status(500).json({ error: 'Insert data failed' });
            return;
        }
            
        console.log('Inserted data:', data);
            
        res.status(201).json({ data: data });

    } catch (error) {
        console.error('Content creation error:', error);
        res.status(500).json({ error: 'Content creation failed' });
    }
        
});

const homePage = asyncHandler(async (req, res) => {
//GETALL
    const { data, error } = await supabase
    .from('contents')
    .select()

    if (error) {
        console.error('Get data error:', error);
        res.status(500).json({ error: 'Get data failed' });
        return;
    }

    res.status(200).json({ data: data});

});

const getBreed = asyncHandler(async (req, res) => {
    const { breed } = req.params;
//GETBreed

    const { data, error } = await supabase
    .from('contents')
    .select()
    .textSearch('breed', `${breed}`)

    if (error) {
        console.error('Get data error:', error);
        res.status(500).json({ error: 'Get data failed' });
        return;
    }

    res.status(200).json({ data: data});

});

const getGender = asyncHandler(async (req, res) => {
    const { gender } = req.params;
//GETGender

    const { data, error } = await supabase
    .from('contents')
    .select()
    .textSearch('gender', `${gender}`)

    if (error) {
        console.error('Get data error:', error);
        res.status(500).json({ error: 'Get data failed' });
        return;
    }

    res.status(200).json({ data: data});

});


module.exports = {
    contentCreate,
    homePage,
    getBreed,
    getGender,
}