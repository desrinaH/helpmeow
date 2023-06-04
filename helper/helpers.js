//const util = require('util');
const gc = require('../config/index');
const bucket = gc.bucket('img-helpmeow'); //bucket name

/**
 *
 * @param { File } object 
 * @description 
 * 
 * 
 *  
 */

const uploadImage = (file) => new Promise((resolve, reject) => {
    const { originalname, buffer } = file;
  
    const folderPath = 'contents'; // Specify the folder path
    const fileName = originalname.replace(/ /g, "_");
    const uniqueFilename = `${Date.now()}_${fileName}`;
    const filePath = `${folderPath}/${uniqueFilename}`;
  
    const blob = bucket.file(filePath);
    const blobStream = blob.createWriteStream({
      resumable: false
    });
  
    blobStream.on('finish', () => {
      const publicUrl = `https://storage.googleapis.com/${bucket.name}/${blob.name}`;
      resolve(publicUrl);
    })
    .on('error', () => {
      reject(`Unable to upload image, something went wrong`);
    })
    .end(buffer);
  });
  
  module.exports = {
    uploadImage
  };
  
  