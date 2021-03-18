const Hospital = require('../models/hospitalModel');
const Doctor = require('../models/doctorModel');
const Vaccine = require('../models/vaccineModel');
const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');

// function which will sign the jwt
const signToken = (id) => {
  return jwt.sign({ id }, process.env.JWT_SECRET, {
    expiresIn: process.env.JWT_EXPIRES_IN,
  });
};

// function to create and send token on sign up and sign in , whenever the function is called
const createSendToken = async (user, statusCode, req, res) => {
  const token = signToken(user._id);

  res.cookie('jwt', token, {
    expires: new Date(
      Date.now() + process.env.JWT_COOKIE_EXPIRES_IN * 24 * 60 * 60 * 1000
    ),
    httpOnly: true,
  });
  const doctors = await Doctor.find({ hospital: user._id });
  const vaccines = await Vaccine.find();
  res
    .status(statusCode)
    .render('portal/dashboard', { hospital: user, doctors, vaccines });
};

// sign up controller function
exports.signup = async (req, res) => {
  try {
    const newHospital = await Hospital.create({
      name: req.body.name,
      phone: req.body.phone,
      email: req.body.email,
      address: req.body.address,
      isApproved: false,
      password: req.body.password,
    });
    newHospital.password = await bcrypt.hash(req.body.password, 12);
    await newHospital.save();
    createSendToken(newHospital, 201, req, res);
  } catch (err) {
    console.log(err);
    res.status(400).json({
      status: 'fail',
      message: err,
    });
  }
};

// sign in function
exports.signin = async (req, res) => {
  try {
    const hospital = await Hospital.findOne({ email: req.body.email });
    if (
      !hospital ||
      !(await hospital.correctPassword(req.body.password, hospital.password))
    ) {
      res.send('Email or password is incorrect');
    }
    createSendToken(hospital, 200, req, res);
  } catch (err) {
    console.log(err);
    res.status(400).json({
      status: 'fail',
      message: err,
    });
  }
};
// sign out function
exports.signout = async (req, res) => {
  try {
    res.clearCookie('jwt');
    res.redirect('/');
  } catch (err) {
    res.status(400).json({
      status: 'fail',
      message: err,
    });
  }
};
