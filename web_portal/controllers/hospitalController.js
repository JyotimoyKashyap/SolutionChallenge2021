const Hospital = require('../models/hospitalModel');
const Doctor = require('../models/doctorModel');
const Vaccine = require('../models/vaccineModel');

// Function to get all hoipital information
exports.getAllHospitals = async (req, res) => {
  try {
    const hospitals = await Hospital.find({ isApproved: true });
    res.status(200).json({
      status: 'success',
      hospitals,
    });
  } catch (err) {
    res.status(400).json({
      status: 'fail',
      message: err,
    });
  }
};

// Function to get info of a single hospital
exports.getHospital = async (req, res) => {
  try {
    const hospital = await Hospital.findById(req.params.hospitalId);
    res.status(200).json({
      status: 'success',
      hospital,
    });
  } catch (err) {
    res.status(400).json({
      status: 'fail',
      message: err,
    });
  }
};

// Function to edit hospital informaion
exports.editHospitalDetails = async (req, res) => {
  try {
    const updatedHospital = await Hospital.findByIdAndUpdate(
      req.params.hospitalId,
      req.body,
      {
        new: true,
        runValidators: true,
      }
    );
    res.status(200).json({
      status: 'success',
      updatedHospital,
    });
  } catch (err) {
    res.status(400).json({
      status: 'fail',
      message: err,
    });
  }
};

// for portal

exports.updateDetailsInPortal = async (req, res) => {
  try {
    const hospital = await Hospital.findByIdAndUpdate(
      req.params.hospitalId,
      req.body,
      {
        new: true,
        runValidators: true,
      }
    );
    const doctors = await Doctor.find({ hospital: req.params.hospitalId });
    const vaccines = await Vaccine.find();
    res.render('portal/dashboard', { hospital, doctors, vaccines });
  } catch (err) {
    console.log(err);
    res.status(400).json({
      status: 'fail',
      message: err,
    });
  }
};

exports.addDoctorForPortal = async (req, res) => {
  try {
    const newDoctor = await Doctor.create({
      name: req.body.name,
      email: req.body.email,
      hospital: req.params.hospitalId,
      specialization: req.body.specialization,
      phone: req.body.phone,
    });
    const hospital = await Hospital.findById(req.params.hospitalId);
    const doctors = await Doctor.find({ hospital: req.params.hospitalId });
    const vaccines = await Vaccine.find();
    res.render('portal/dashboard', { hospital, doctors, vaccines });
  } catch (err) {
    res.status(400).json({
      status: 'fail',
      message: err,
    });
  }
};

exports.addVaccineForPortal = async (req, res) => {
  try {
    await Vaccine.create(req.body);
    const hospital = await Hospital.findById(req.params.hospitalId);
    const doctors = await Doctor.find({ hospital: req.params.hospitalId });
    const vaccines = await Vaccine.find();
    res.render('portal/dashboard', { hospital, doctors, vaccines });
  } catch (err) {
    res.status(400).json({
      status: 'fail',
      message: err,
    });
  }
};
