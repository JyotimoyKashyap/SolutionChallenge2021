const express = require('express');
const hospitalController = require('../controllers/hospitalController');
const router = express.Router();

router.route('/all').get(hospitalController.getAllHospitals);
router.route('/:hospitalId').get(hospitalController.getHospital);
router.route('/:hospitalId').post(hospitalController.editHospitalDetails);

// For Portal
router
  .route('/update/info/:hospitalId')
  .post(hospitalController.updateDetailsInPortal);

router
  .route('/add/doctor/:hospitalId')
  .post(hospitalController.addDoctorForPortal);

router
  .route('/add/vaccine/:hospitalId')
  .post(hospitalController.addVaccineForPortal);

module.exports = router;
