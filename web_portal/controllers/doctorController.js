const Doctor = require('../models/doctorModel');

// function to retrieve all doctors info
exports.getAllDoctors = async (req, res) => {
  try {
    const doctors = await Doctor.find();
    res.status(200).json({
      status: 'success',
      doctors,
    });
  } catch (err) {
    res.status(400).json({
      status: 'fail',
      message: err,
    });
  }
};

// Function to get info of a single doctor
exports.getDoctor = async (req, res) => {
  try {
    const doctor = await Doctor.findById(req.params.doctorId);
    res.status(200).json({
      status: 'success',
      doctor,
    });
  } catch (err) {
    res.status(400).json({
      status: 'fail',
      message: err,
    });
  }
};

// Function to get list of doctoes hospital wise
exports.getDoctorsHospitalWise = async (req, res) => {
  try {
    const doctors = await Doctor.find({ hospital: req.params.hospitalId });
    res.status(200).json({
      status: 'success',
      doctors,
    });
  } catch (err) {
    console.log(err);
    res.status(400).json({
      status: 'fail',
      message: err,
    });
  }
};

// Function for adding new doctor to the list of doctors
exports.addDoctor = async (req, res) => {
  try {
    const newDoctor = await Doctor.create(req.body);
    res.status(201).json({
      status: 'success',
      doctor: newDoctor,
    });
  } catch (err) {
    res.status(400).json({
      status: 'fail',
      message: err,
    });
  }
};

// Function for editing doctor information
exports.editDoctor = async (req, res) => {
  try {
    const updatedDoctor = await Doctor.findByIdAndUpdate(
      req.params.doctorId,
      req.body,
      {
        new: true,
        runValidators: true,
      }
    );
    res.status(201).json({
      status: 'success',
      doctor: updatedDoctor,
    });
  } catch (err) {
    res.status(400).json({
      status: 'fail',
      message: err,
    });
  }
};
