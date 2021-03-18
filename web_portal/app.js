const express = require('express');
const path = require('path');
const cookieParser = require('cookie-parser');

const viewRouter = require('./routes/viewRoutes');
const authRouter = require('./routes/authRoutes');
const doctorRouter = require('./routes/doctorRoutes');
const hospitalRouter = require('./routes/hospitalRoutes');
const vaccineRouter = require('./routes/vaccineRoutes');
const userRouter = require('./routes/userRoutes');

const app = express();

app.use(express.json());
app.use(cookieParser());
app.use(express.static(__dirname));
app.use(express.urlencoded({ extended: true }));
app.use(express.static(__dirname + '/public'));
app.set('view engine', 'ejs');
app.set('views', path.join(__dirname, 'views'));

app.use('/', viewRouter);
app.use('/auth', authRouter);
app.use('/api/hospital', hospitalRouter);
app.use('/api/doctor', doctorRouter);
app.use('/api/vaccine', vaccineRouter);
app.use('/api/user', userRouter);

module.exports = app;
