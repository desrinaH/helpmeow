const Cloud = require('@google-cloud/storage')

const { Storage } = Cloud
 const storage = new Storage({
     projectId: 'helpmeow',
     keyFilename: 'sa/helpmeow-a8664564cf49.json',
 });

module.exports = storage