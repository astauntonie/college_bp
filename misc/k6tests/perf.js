import { check, sleep } from "k6";
import http from "k6/http";

// Export an options object to configure how k6 will behave during test execution.
//
// See https://docs.k6.io/docs/options
//
export let options = {

  stages: [
    { duration: "1m", target: 20 },            // 1 new vu every 3 seconds
    { duration: "1m", target: 20 },
    { duration: "1m", target: 0 },             // 1 less vu every 3 seconds
    { duration: "1m", target: 50 },
    { duration: "1m", target: 20 },
    { duration: "1m", target: 0 }
  ],

 	thresholds: {
    "http_req_duration": ["p(95) < 100"]
  },

  discardResponseBodies: false,

  ext: {
    loadimpact: {
      // Specify the distribution across load zones
      //
      // See https://docs.k6.io/docs/cloud-execution#section-cloud-execution-options
      //
      distribution: {
        loadZoneLabel1: { loadZone: "amazon:ie:dublin", percent: 100 },
      }
    }
  }
};

/**
 * Get a random integer between `min` and `max`.
 * 
 * @param {number} min - min number
 * @param {number} max - max number
 * @return {number} a random integer
 */

function getRandomInt(min, max) {
  return Math.floor(Math.random() * (max - min + 1) + min);
}

export default function() {

  let res = http.get("http://gc-bmicalculator-qa.azurewebsites.net/bmi", {"responseType": "text"});

  check(res, {
    "is status 200": (r) => r.status === 200
  });

  res = res.submitForm({
    fields: { systolic: getRandomInt(70, 190).toString(), diastolic: getRandomInt(40, 100).toString()}
  });

  check(res, {
    "is status 200": (r) => r.status === 200
  });

  // "think" for 3 seconds
  sleep(3);
}
