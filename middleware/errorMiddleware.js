const loggingMiddleware = (req, res, next) => {
    console.log(`[${new Date().toISOString()}] ${req.method} ${req.url}`);
    next();
  };

const errorMiddleware = (err, req, res, next) => {
    //const statusCode = res.statusCode ? res.statusCode : 500
    const statusCode = 500;
    res.status(statusCode).json({ message: err.message})

    // console.error(err);
    // res.status(500).send('Internal Server Error');
  };

  module.exports = { errorMiddleware,loggingMiddleware}    
