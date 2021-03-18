const User = require('../models/userModel');
const Baby = require('../models/babyModel');
const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');

// Function to sign jwt token
const signToken = (id) => {
  return jwt.sign({ id }, process.env.JWT_SECRET, {
    expiresIn: process.env.JWT_EXPIRES_IN,
  });
};

// fuction to create and send jwt token while signing up or loggig in
const createSendToken = async (user, statusCode, req, res) => {
  const token = signToken(user._id);

  res.cookie('jwt', token, {
    expires: new Date(
      Date.now() + process.env.JWT_COOKIE_EXPIRES_IN * 24 * 60 * 60 * 1000
    ),
    httpOnly: true,
  });
  user.token = token;
  await user.save();
  user.password = undefined;
  res.status(statusCode).json({
    status: 'success',
    user,
  });
};

// Function to sign up
exports.signup = async (req, res) => {
  try {
    const newUser = await User.create({
      email: req.body.email,
      uid: req.body.uid,
    });
    res.status(201).json({
      status: 'success',
      user: newUser,
    });
  } catch (err) {
    res.status(400).json({
      status: 'fail',
      message: err,
    });
  }
};

// Function to login
exports.login = async (req, res) => {
  try {
    const user = await User.findOne({ email: req.body.email });
    if (
      !user ||
      !(await user.correctPassword(req.body.password, user.password))
    ) {
      res.send('Email or password is incorrect');
    }
    createSendToken(user, 200, req, res);
  } catch (err) {
    console.log(err);
    res.status(400).json({
      status: 'fail',
      message: err,
    });
  }
};

// Function to get single user info
exports.getUser = async (req, res) => {
  try {
    const user = await User.findById(req.params.uid);
    res.status(200).json({
      status: 'success',
      user,
    });
  } catch (err) {
    res.status(400).json({
      status: 'fail',
      message: err,
    });
  }
};

// Function to edit user information
exports.editUser = async (req, res) => {
  try {
    const updatedUser = await User.findByIdAndUpdate(req.params.uid, req.body, {
      new: true,
      runValidators: true,
    });
    res.status(200).json({
      status: 'success',
      user: updatedUser,
    });
  } catch (err) {
    res.status(400).json({
      status: 'fail',
      message: err,
    });
  }
};

// Function to register a baby
exports.registerBaby = async (req, res) => {
  try {
    const newBaby = await Baby.create({
      name: req.body.name,
      age: req.body.age,
      parent: req.params.userId,
      motherName: req.body.motherName,
      fatherName: req.body.fatherName,
      dateOfBirth: req.body.dateOfBirth,
      monthOfBirth: req.body.monthOfBirth,
      yearOfBirth: req.body.yearOfBirth,
    });
    res.status(201).json({
      status: 'success',
      baby: newBaby,
    });
  } catch (err) {
    res.status(400).json({
      status: 'fail',
      message: err,
    });
  }
};
// Function to get baby details
exports.getBabyDetails = async (req, res) => {
  try {
    const baby = await Baby.findOne({ parent: req.params.parentId }).populate(
      'vaccinesTaken'
    );
    res.status(200).json({
      status: 'success',
      baby,
    });
  } catch (err) {
    res.status(400).json({
      status: 'fail',
      message: err,
    });
  }
};

// Function to edit baby details
exports.editBabyDetails = async (req, res) => {
  try {
    const updatedBaby = await Baby.findByIdAndUpdate(
      req.params.babyId,
      req.body,
      {
        new: true,
        runValidators: true,
      }
    );
    res.status(200).json({
      status: 'success',
      baby: updatedBaby,
    });
  } catch (err) {
    res.status(400).json({
      status: 'fail',
      message: err,
    });
  }
};

exports.vaccineTaken = async (req, res) => {
  try {
    const baby = await Baby.findById(req.body.babyId).populate('vaccinesTaken');
    baby.vaccinesTaken.push(req.body.vaccineId);
    await baby.save();
    res.status(200).json({
      status: 'success',
      baby,
    });
  } catch (err) {
    res.status(400).json({
      status: 'fail',
      message: err,
    });
  }
};

exports.vaccineTakenRemove = async (req, res) => {
  try {
    const baby = await Baby.findById(req.body.babyId).populate('vaccinesTaken');
    baby.vaccinesTaken.forEach((vaccine) => {
      if (vaccine._id == req.body.vaccineId) {
        baby.vaccinesTaken.splice(baby.vaccinesTaken.indexOf(vaccine), 1);
      }
    });
    await baby.save();
    res.status(200).json({
      status: 'success',
      baby,
    });
  } catch (err) {
    console.log(err);
    res.status(400).json({
      status: 'fail',
      message: err,
    });
  }
};
