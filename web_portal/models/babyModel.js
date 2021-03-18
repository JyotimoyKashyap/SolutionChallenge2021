const mongoose = require('mongoose');

const babySchema = new mongoose.Schema({
  name: {
    type: String,
    required: [true, 'Please provide the baby name'],
    trim: true,
  },
  age: {
    type: String,
    required: [true, 'Please provide the age of your baby'],
  },
  dateOfBirth: {
    type: String,
    required: [true, 'Please provide date of birth of your baby'],
  },
  monthOfBirth: {
    type: String,
    required: [true, 'Please provide month of birth of your baby'],
  },
  yearOfBirth: {
    type: String,
    required: [true, 'Please provide year of birth of your baby'],
  },
  motherName: {
    type: String,
    required: [true, "Please provide mother's name of the baby"],
    trim: true,
  },
  fatherName: {
    type: String,
    required: [true, "Please provide father's name of the baby"],
    trim: true,
  },
  parent: {
    type: mongoose.Schema.Types.ObjectId,
    ref: 'User',
    required: [true, "Please provide information about baby's parent"],
  },
  vaccinesTaken: [
    {
      type: mongoose.Schema.Types.ObjectId,
      ref: 'Vaccine',
    },
  ],
});

module.exports = mongoose.model('Baby', babySchema);
