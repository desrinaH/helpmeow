const asyncHandler = require("express-async-handler");
const Firestore = require('@google-cloud/firestore');
require('dotenv').config();
const { createClient } = require('@supabase/supabase-js');

const supabaseUrl = 'https://bpsmobjqnwxpsawzapnn.supabase.co'
const supabaseKey = process.env.SUPABASE_KEY
const supabase = createClient(supabaseUrl, supabaseKey)

const db = new Firestore({
    projectId: 'helpmeow',
    keyFilename: './sa/helpmeow-a92698f4b6a6.json',
  });

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

    if (description.length > 100) {
        res.status(400).json({ message: 'Description must be no more than 100 characters'});
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
            created_at: new Date(Date.now()),
            role: role
        }])
        
    if (error) {
        console.error('Insert data error:', error);
        res.status(500).json({ error: 'Insert data failed' });
        return;
      }
          
    console.log('Inserted data:', data);
          
    res.status(201).json({ data: data });
        
});

module.exports = {
    contentCreate,
}