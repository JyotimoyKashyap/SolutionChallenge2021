const express = require('express');
const userController = require('../controllers/userController');
const router = express.Router();

router.route('/details/:userId').get(userController.getUser);
router.route('/details/:userId').post(userController.editUser);

router.route('/signup').post(userController.signup);

router.route('/login').post(userController.login);

router.route('/baby/details/:parentId').get(userController.getBabyDetails);

router.route('/baby/register/:userId').post(userController.registerBaby);

router.route('/baby/:babyId').post(userController.editBabyDetails);

router.route('/baby/taken/vaccine').post(userController.vaccineTaken);

router.route('/baby/remove/vaccine').post(userController.vaccineTakenRemove);

module.exports = router;
