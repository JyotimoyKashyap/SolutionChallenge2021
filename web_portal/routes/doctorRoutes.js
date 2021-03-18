const express = require('express');
const doctorController = require('../controllers/doctorController');
const router = express.Router();

router.route('/all').get(doctorController.getAllDoctors);

router
  .route('/hospitalwise/:hospitalId')
  .get(doctorController.getDoctorsHospitalWise);

router.route('/single/:doctorId').get(doctorController.getDoctor);

router.route('/').post(doctorController.addDoctor);
router.route('/:doctorId').post(doctorController.editDoctor);

module.exports = router;
