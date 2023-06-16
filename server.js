const express = require('express');
const app = express();
const bodyParser = require('body-parser');
const { errorMiddleware, loggingMiddleware } = require("./middleware/errorMiddleware");
const multer = require('multer');
const PORT = 8080

const multerMiddleware = multer({
  storage: multer.memoryStorage(),
  limits: { fileSize: 5 * 1024 * 1024 }
})

app.disable('x-powered-by');
app.use(multerMiddleware.single('file'));

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: false}));

app.use(loggingMiddleware);
app.use("/", require("./routes"));
app.use(errorMiddleware);

app.listen(PORT, () => {
    console.log(`API server running on port ${PORT}`);
  });