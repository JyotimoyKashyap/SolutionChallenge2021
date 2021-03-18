const mongoose = require('mongoose');
const validator = require('validator');
const bcrypt = require('bcryptjs');

const hospitalSchema = new mongoose.Schema(
  {
    name: {
      type: String,
      required: [true, 'Please provide the name of the hospital'],
      trim: true,
    },
    phone: {
      type: String,
      required: [true, 'Please provide phone number'],
    },
    email: {
      type: String,
      required: [true, 'Please provide your email'],
      unique: true,
      lowercase: true,
      validate: [validator.isEmail, 'Please provide a valid email'],
    },
    isApproved: {
      type: Boolean,
      default: false,
    },
    address: {
      type: String,
      required: [true, 'Please provide hospital address'],
    },
    password: {
      type: String,
    },
    description: {
      type: String,
    },
    vaccines: [
      {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'Vaccine',
      },
    ],
    image: {
      type: String,
    },
    roomAvailableCount: {
      type: String,
    },
  },
  {
    timestamps: true,
  }
);

hospitalSchema.methods.correctPassword = async function (
  candidatePassword,
  userPassword
) {
  return await bcrypt.compare(candidatePassword, userPassword);
};

module.exports = mongoose.model('Hospital', hospitalSchema);
