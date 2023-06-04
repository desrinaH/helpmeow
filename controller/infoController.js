const asyncHandler = require("express-async-handler");
const Firestore = require('@google-cloud/firestore');

const db = new Firestore({
    projectId: 'helpmeow',
    keyFilename: './sa/helpmeow-a92698f4b6a6.json',
  });

const getInfo = asyncHandler ( async (req, res) => {
    const { breed } = req.body;

    try {
        const infoExists = await db
        .collection('informations')
        .where('breed', '==', breed)
        .limit(1)
        .get();
    
        if ( infoExists.empty) {
            res.status(404).json({ message: 'No data found' });
            return;
        }
    
        const documentDoc = infoExists.docs[0];
        const documentSnaps = documentDoc.data();
        
        res.status(200).json({ data: documentSnaps });

    } catch (error) {
        
            console.error('Get data error:', error);
            res.status(500).json({ error: 'Get data failed' });
            return;
           
    }

})

module.exports = {
    getInfo,
}