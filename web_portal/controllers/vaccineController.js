const Vaccine = require('../models/vaccineModel');
const puppeteer = require('puppeteer');

// Function to add vaccine
exports.addVaccine = async (req, res) => {
  try {
    const newVaccine = await Vaccine.create(req.body);
    res.status(201).json({
      status: 'success',
      vaccine: newVaccine,
    });
  } catch (err) {
    res.status(400).json({
      status: 'fail',
      message: err,
    });
  }
};

// Function to get list of vaccines
exports.getAllVaccines = async (req, res) => {
  try {
    // initialized empty array for storing result of scrapper
    let final = [];
    finalFiltered = [];
    // main function which scraps
    async function scrap() {
      // launch puppeteer and add some args to fix error in heroku
      const browser = await puppeteer.launch({
        args: ['--no-sandbox', '--disable-setuid-sandbox'],
      });
      const page = await browser.newPage();
      await page.goto('http://www.nrhmhp.gov.in/content/immunisation');
      const data = await page.evaluate(() => {
        // pick all the contents with query selector "tr"
        const tds = Array.from(document.querySelectorAll('tr'));
        return tds.map((td) => td.innerText);
      });
      // initialize an empty model for storing the scrapped data
      data.forEach((entry) => {
        let finalObj = {
          name: '',
          whenToGive: '',
          dose: '',
          route: '',
          site: '',
        };
        // split the array of data to get indiidual column
        let temp = entry.split('\t');
        finalObj.name = temp[0];
        finalObj.whenToGive = temp[1];
        finalObj.dose = temp[2];
        finalObj.route = temp[3];
        finalObj.site = temp[4];
        final.push(finalObj);
      });
      final.forEach((entry) => {
        if (entry.name && entry.whenToGive && entry.site !== 'Site') {
          finalFiltered.push(entry);
        }
      });
      await browser.close();
      return finalFiltered;
    }
    // let vaccines = await scrap();
    const vaccines = await Vaccine.find();
    res.status(200).json({
      status: 'success',
      vaccines,
    });
  } catch (err) {
    res.status(400).json({
      status: 'fail',
      message: err,
    });
  }
};

exports.getVaccine = async (req, res) => {
  try {
    const vaccine = await Vaccine.findById(req.params.vaccineId);
    res.status(200).json({
      status: 'success',
      vaccine,
    });
  } catch (err) {
    res.status(400).json({
      status: 'fail',
      message: err,
    });
  }
};

exports.editVaccine = async (req, res) => {
  try {
    const updatedVaccine = await Vaccine.findByIdAndUpdate(
      req.params.vaccineId,
      req.body,
      {
        new: true,
        runValidators: true,
      }
    );
    res.status(200).json({
      status: 'success',
      vaccine: updatedVaccine,
    });
  } catch (err) {
    res.status(400).json({
      status: 'fail',
      message: err,
    });
  }
};

exports.getVaccinesHospitalWise = async (req, res) => {
  try {
    // initialized empty array for storing result of scrapper
    let final = [];
    finalFiltered = [];
    // main function which scraps
    async function scrap() {
      // launch puppeteer and add some args to fix error in heroku
      const browser = await puppeteer.launch({
        args: ['--no-sandbox', '--disable-setuid-sandbox'],
      });
      const page = await browser.newPage();
      await page.goto('http://www.nrhmhp.gov.in/content/immunisation');
      const data = await page.evaluate(() => {
        // pick all the contents with query selector "tr"
        const tds = Array.from(document.querySelectorAll('tr'));
        return tds.map((td) => td.innerText);
      });
      // initialize an empty model for storing the scrapped data
      data.forEach((entry) => {
        let finalObj = {
          name: '',
          whenToGive: '',
          dose: '',
          route: '',
          site: '',
        };
        // split the array of data to get indiidual column
        let temp = entry.split('\t');
        finalObj.name = temp[0];
        finalObj.whenToGive = temp[1];
        finalObj.dose = temp[2];
        finalObj.route = temp[3];
        finalObj.site = temp[4];
        final.push(finalObj);
      });
      final.forEach((entry) => {
        if (entry.name && entry.whenToGive && entry.site !== 'Site') {
          finalFiltered.push(entry);
        }
      });
      await browser.close();
      return finalFiltered;
    }
    let vaccines = await scrap();
    // const vaccines = await Vaccine.find();
    res.status(200).json({
      status: 'success',
      vaccines,
    });
  } catch (err) {
    res.status(400).json({
      status: 'fail',
      message: err,
    });
  }
};
