package com.nerdyninja.college.bp.be;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;



class BloodPressureDataTest {
    private static BloodPressureData bloodPressureData;
    private static Validator validator;

    @BeforeEach
    public void setData(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        bloodPressureData = new BloodPressureData();
        bloodPressureData.setAgerange(0);
        bloodPressureData.setEmail("testJUnit@local.ie");
    }


    @Test
    void testSetSystolic40() {
        bloodPressureData.setSystolic(40L);
        bloodPressureData.setDiastolic(50L);

        Set<ConstraintViolation<BloodPressureData>> constraintViolations = validator.validate( bloodPressureData );

        assertEquals(1, constraintViolations.size() );
        assertEquals("Systolic must be greater than or equal to 70mmHG", constraintViolations.iterator().next().getMessage());
    }

    @Test
    void testSetSystolic200() {
        bloodPressureData.setSystolic(200L);
        bloodPressureData.setDiastolic(50L);
        Set<ConstraintViolation<BloodPressureData>> constraintViolations = validator.validate( bloodPressureData );

        assertEquals( 1, constraintViolations.size() );
        assertEquals( "Systolic must be less than or equal to 190mmHG", constraintViolations.iterator().next().getMessage());
    }

    @Test
    void testSetSystolic70() {
        bloodPressureData.setSystolic(70L);
        bloodPressureData.setDiastolic(50L);
        Set<ConstraintViolation<BloodPressureData>> constraintViolations =  validator.validate( bloodPressureData );

        assertEquals( 0, constraintViolations.size() );
        assertEquals( 70, bloodPressureData.getSystolic());
    }

    @Test
    void testSetDiastolic20() {
        bloodPressureData.setDiastolic(20L);
        bloodPressureData.setSystolic(70L);
        Set<ConstraintViolation<BloodPressureData>> constraintViolations = validator.validate( bloodPressureData );

        assertEquals(1, constraintViolations.size() );
        assertEquals("Diastolic must be greater than or equal to 40mmHG", constraintViolations.iterator().next().getMessage());
    }

    @Test
    void testSetDiastolic110() {
        bloodPressureData.setDiastolic(110L);
        bloodPressureData.setSystolic(70L);
        Set<ConstraintViolation<BloodPressureData>> constraintViolations = validator.validate( bloodPressureData );

        assertEquals(1, constraintViolations.size() );
        assertEquals("Diastolic must be less than or equal to 100mmHG", constraintViolations.iterator().next().getMessage());
    }

    @Test
    void testSetDiastolic70() {
        bloodPressureData.setDiastolic(70L);
        bloodPressureData.setSystolic(70L);
        Set<ConstraintViolation<BloodPressureData>> constraintViolations =
                validator.validate( bloodPressureData );

        assertEquals( 0, constraintViolations.size() );
        assertEquals( 70, bloodPressureData.getDiastolic());
    }
}