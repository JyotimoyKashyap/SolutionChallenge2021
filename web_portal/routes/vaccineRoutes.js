const express = require('express');
const vaccineController = require('../controllers/vaccineController');
const router = express.Router();

router
  .route('/hospitalwise/all')
  .get(vaccineController.getVaccinesHospitalWise);

router.route('/').get(vaccineController.getAllVaccines);
router.route('/:vaccineId').get(vaccineController.getVaccine);

router.route('/').post(vaccineController.addVaccine);
router.route('/:vaccineId').post(vaccineController.editVaccine);

module.exports = router;
