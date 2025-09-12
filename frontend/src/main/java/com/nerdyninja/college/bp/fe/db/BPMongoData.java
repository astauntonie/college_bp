package com.nerdyninja.college.bp.fe.db;

import com.nerdyninja.college.bp.fe.BloodPressureData;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bp")
@Data
@NoArgsConstructor
public class BPMongoData {

    @Id
    private String id;
    private Long systolic; // mmHG
    private Long diastolic; // mmHG
    private String email;
    private String category;


    public BPMongoData(BloodPressureData bpData){
        this.systolic = bpData.getSystolic();
        this.diastolic = bpData.getDiastolic();
        this.email = bpData.getEmail();
        this.category = bpData.getCategory();
    }
}


