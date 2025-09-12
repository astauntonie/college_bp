package com.nerdyninja.college.bp.fe;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@ToString
public class BloodPressureData {
    @NotNull
    @Min(value=70, message="Systolic must be greater than or equal to 70mmHG")
    @Max(value=190, message="Systolic must be less than or equal to 190mmHG")
    private Long systolic; // mmHG

    @NotNull
    @Min(value=40, message="Diastolic must be greater than or equal to 40mmHG")
    @Max(value=100, message="Diastolic must be less than or equal to 100mmHG")
    private Long diastolic; // mmHG

    @NotNull
    private int agerange; // mmHG
    @NotNull
    private String email;

    private String category;

    public String toJSON(){
        return "{ \"systolic\": \"" + systolic + "\",\"diastolic\": \"" + diastolic + "\" , \"agerange\":\"" +
                agerange + "\", \"email\":\"" + email + "\"}";
    }

    public void getCategoryType(){
        if(systolic < 70 || diastolic < 40 ){
            category =  null;
        }
        else if(systolic < 90 && diastolic < 60){
            category =  "Low Blood Pressure";
        } else if (systolic < 120 && diastolic < 80){
            category =  "Ideal Blood Pressure";
        } else if (systolic < 140 && diastolic < 90) {
            category = "Pre-High Blood Pressure";
        } else if (systolic < 150 && diastolic < 90 && agerange==0) {
            category = "High Blood Pressure";
        }         else if (systolic < 150 && diastolic < 90 && agerange==1) {
            category = "Pre-High Blood Pressure";
        } else if (systolic < 190 && diastolic < 100){
            category =  "High Blood Pressure";
        } else {
            category =  null;
        }
    }
}
