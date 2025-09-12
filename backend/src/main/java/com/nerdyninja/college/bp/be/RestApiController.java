package com.nerdyninja.college.bp.be;

import com.nerdyninja.college.bp.be.db.BPMongoData;
import com.nerdyninja.college.bp.be.db.BPMongoRepository;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CommonsLog
public class RestApiController {
    @Autowired
    BPMongoRepository mongoRepo;
    BPMongoData mongoData;


    @GetMapping("/api")
    public BloodPressureData getCategory(@RequestParam(value = "systolic") Long systolic,
                                         @RequestParam(value = "diastolic") Long diastolic,
                                         @RequestParam(value = "email") String email,
                                         @RequestParam(value = "age_range", defaultValue = "0") int ageRange) {
        BloodPressureData bp = new BloodPressureData();
        bp.setSystolic(systolic);
        bp.setDiastolic(diastolic);
        bp.setEmail(email);
        bp.setAgerange(ageRange);
        bp.getCategoryType();
        log.info("GET Request received with the following data:\nsystolic: " + systolic
        + "\ndiastolic: " + diastolic + "\nemail: " + email + "\n");

        mongoData = new BPMongoData(bp);
        mongoRepo.save(mongoData);
        log.info("Data saved to the database");

        return bp;
    }

    @PostMapping(value = "/api", consumes = "application/json", produces = "application/json")
    public BloodPressureData getCategory(@RequestBody BloodPressureData bp){
        log.info("\nPOST Request received with the following data:\n" + bp + "\n");
        bp.getCategoryType();

        mongoData = new BPMongoData(bp);
        mongoRepo.save(mongoData);
        log.info("\nRecord saved for " + bp.getEmail() + "\n");

        return bp;
    }

    @GetMapping("/health")
    @PostMapping("/health")
    public String health(){
        return "{message: \'Backend Server is online\'}";
    }
}
