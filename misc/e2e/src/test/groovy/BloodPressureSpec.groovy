package com.nerdyninja.college.bp

import geb.spock.GebSpec

class BloodPressureSpec extends GebSpec {

    def "Can load the main application page"() {
        when:
        to BPFrontPage

        then:
        at BPFrontPage
    }

    def "Test Low Blood Pressure"(){
      given:
      go()

      when:
      at BPFrontPage

      systolic.value("75")
      diastolic.value("55")
      email.value("testGeb@local.ie")
      submitButton.click()

      then:
      assert result.text() == "Low Blood Pressure"
    }

    def "Test Ideal Blood Pressure"(){
      given:
      go()

      when:
      at BPFrontPage

      systolic.value("80")
      diastolic.value("70")
      email.value("testGeb@local.ie")
      submitButton.click()

      then:
      assert result.text() == "Ideal Blood Pressure"
    }

    def "Test Pre-High Blood Pressure"(){
      given:
      go()

      when:
      at BPFrontPage

      systolic.value("130")
      diastolic.value("70")
      age_select.value("0")
      email.value("testGeb@local.ie")
      submitButton.click()

      then:
      assert result.text() == "Pre-High Blood Pressure"
    }

    def "Test High Blood Pressure -  < 80"(){
      given:
      go()

      when:
      at BPFrontPage

      systolic.value("145")
      diastolic.value("70")
      age_select.value("0")
      email.value("testGeb@local.ie")
      submitButton.click()

      then:
      assert result.text() == "High Blood Pressure"
    }

    def "Test Higher Blood Pressure -  > 79"(){
        given:
        go()

        when:
        at BPFrontPage

        systolic.value("145")
        diastolic.value("70")
        age_select.value("1")
        email.value("testGeb@local.ie")
        submitButton.click()

        then:
        assert result.text() == "Pre-High Blood Pressure"
    }

    def "Test High Blood Pressure -  > 79"(){
        given:
        go()

        when:
        at BPFrontPage

        systolic.value("150")
        diastolic.value("70")
        age_select.value("1")
        email.value("testGeb@local.ie")
        submitButton.click()

        then:
        assert result.text() == "High Blood Pressure"
    }
}
