package com.nerdyninja.college.bp.be

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.*
@SpringBootTest
class BloodPressureCategoryTestSpec extends Specification {

    BloodPressureData testData

    def "Testing the different category types"(){
        given: "We create a new BloodPressure Object"
        testData = new BloodPressureData()

        when: "We set the Systolic and Diastolic Correctly"
        testData.setSystolic(systolic)
        testData.setDiastolic(diastolic)
        testData.setAgerange(agerange)
        testData.setEmail(email)
        testData.getCategoryType()

        then:
        testData.category == result

        where:
        systolic || diastolic || agerange || email || result
        60L || 30L || 0 || "testSpock@local.ie" || null
        60L || 50L || 0 || "testSpock@local.ie" || null
        80L || 50L || 0 || "testSpock@local.ie" || "Low Blood Pressure"
        80L || 70L || 0 || "testSpock@local.ie" || "Ideal Blood Pressure"
        80L || 82L || 0 || "testSpock@local.ie" || "Pre-High Blood Pressure"
        80L || 92L || 0 || "testSpock@local.ie" || "High Blood Pressure"
        80L || 100L || 0 || "testSpock@local.ie" || null
        80L || 30L || 0 || "testSpock@local.ie" || null
        100L || 70L || 0 || "testSpock@local.ie" || "Ideal Blood Pressure"
        130L || 70l || 0 || "testSpock@local.ie" || "Pre-High Blood Pressure"
        145L || 70L || 0 || "testSpock@local.ie" || "High Blood Pressure"
        145L || 70L || 1 || "testSpock@local.ie" || "Pre-High Blood Pressure"
        155L || 70L || 1 || "testSpock@local.ie" || "High Blood Pressure"
        190L || 70L || 0 || "testSpock@local.ie" || null
    }
}

