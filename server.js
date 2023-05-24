
const express = require('express');
const app = express();
const bodyParser = require('body-parser');
const { errorMiddleware, loggingMiddleware } = require("./middleware/errorMiddleware")
const PORT = 8080
app.use(bodyParser.json());

app.use(loggingMiddleware);
app.use("/", require("./routes"));
app.use(errorMiddleware);

app.listen(PORT, () => {
    console.log(`API server running on port ${PORT}`);
  });