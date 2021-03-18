const mongoose = require('mongoose');

const vaccineSchema = new mongoose.Schema(
  {
    name: {
      type: String,
      trim: true,
      required: [true, 'Please provide vaccine name'],
    },
    whenToGive: {
      type: String,
      required: [true, 'Please specify the time to take the vaccine'],
    },
    dose: {
      type: String,
      required: [true, 'Please specify the dose amount of the vaccine'],
    },
    smallDescription: {
      type: String,
      trim: true,
    },
    description: {
      type: String,
      trim: true,
    },
    route: {
      type: String,
      required: true,
      trim: true,
    },
    site: {
      type: String,
      required: true,
      trim: true,
    },
  },
  {
    timestamps: true,
  }
);

module.exports = mongoose.model('Vaccine', vaccineSchema);
