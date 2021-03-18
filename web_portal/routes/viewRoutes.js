const express = require('express');
const Hospital = require('../models/hospitalModel');
const router = express.Router();

router.get('/', (req, res) => {
  res.render('portal/homepage');
});

router.get('/signup', (req, res) => {
  res.render('portal/signup');
});

router.get('/login', (req, res) => {
  res.render('portal/login');
});

router.get('/hospital/update/contact/:hospitalId', async (req, res) => {
  const hospital = await Hospital.findById(req.params.hospitalId);
  res.render('portal/updateContact', { hospital });
});

router.get('/hospital/update/bio/:hospitalId', async (req, res) => {
  const hospital = await Hospital.findById(req.params.hospitalId);
  res.render('portal/updateBio', { hospital });
});

router.get('/hospital/add/doctor/:hospitalId', async (req, res) => {
  const hospital = await Hospital.findById(req.params.hospitalId);
  res.render('portal/addDoctor', { hospital });
});

router.get('/hospital/add/vaccine/:hospitalId', async (req, res) => {
  const hospital = await Hospital.findById(req.params.hospitalId);
  res.render('portal/addVaccine', { hospital });
});

module.exports = router;
