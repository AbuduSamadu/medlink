package com.mascot.medlink.model.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO extends  UserDTO{
    private String medicalHistory;
    private LocalDate dateOfBirth;
    private String emergencyContact;

    public String getMedicalHistory(){
        return  medicalHistory != null ? medicalHistory : "No medical History available";
    }

}
