const express = require('express');
const authController = require('../controllers/authController');
const router = express.Router();

// main authentication routes
router.route('/signup').post(authController.signup);
router.route('/login').post(authController.signin);
router.route('/signout').get(authController.signout);

module.exports = router;
