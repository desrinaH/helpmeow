const asyncHandler = require("express-async-handler");
const Firestore = require('@google-cloud/firestore');
const { createClient } = require('@supabase/supabase-js');

const db = new Firestore({
    projectId: 'helpmeow',
    keyFilename: './sa/helpmeow-a92698f4b6a6.json',
  });

const supabase = createClient('https://bpsmobjqnwxpsawzapnn.supabase.co', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImJwc21vYmpxbnd4cHNhd3phcG5uIiwicm9sZSI6ImFub24iLCJpYXQiOjE2ODUyNjM1NjYsImV4cCI6MjAwMDgzOTU2Nn0.NlxGRA13HnfkrFSb52WC4BKeeKyDXw6Z1wUHRususqU');

const contentCreate = asyncHandler (async(req, res) => {
    const { id } = req.params;

    const documentRef = db.collection('users').doc(id);
    const documentSnaps = await documentRef.get();

    if (!documentSnaps.exists) {
        res.status(401).json({ message: 'User must have account to upload' }); return;
    }
    
    const documentData = documentSnaps.data();
    const upload_by_username = documentData.username;

    const {  
        photo, 
        name, 
        gender, 
        breed, 
        location,
        description,
        role,
    } = req.body;

    if (name.length > 16) {
        res.status(400).json({ message: 'Name must be no more than 16 characters'});
        return;
    }

    if (description.length > 500) {
        res.status(400).json({ message: 'Description must be no more than 1000 characters'});
        return;
    }

    const { data, error } = await supabase
        .from('contents')
        .insert([{
            photo: photo,
            name: name,
            gender: gender,
            breed: breed,
            location: location,
            description: description,
            upload_by_username: upload_by_username,
            role: role
        }])
        
    if (error) {
        res.status(500).json({ error: 'Insert data failed'}); return;
    }

    res.status(201).json({ data: data});
        
});

module.exports = {
    contentCreate,
}