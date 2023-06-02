const util = require('util');
const gc = require('../config/index');
const bucket = gc.bucket('img-helpmeow'); // should be your bucket name

/**
 *
 * @param { File } object file object that will be uploaded
 * @description - This function does the following
 * - It uploads a file to the image bucket on Google Cloud
 * - It accepts an object as an argument with the
 *   "originalname" and "buffer" as keys
 */

const uploadImage = (file) => new Promise((resolve, reject) => {
    const { originalname, buffer } = file;
  
    const folderPath = 'contents'; // Specify the folder path
    const fileName = originalname.replace(/ /g, "_");
    const filePath = `${folderPath}/${fileName}`;
  
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
  
  