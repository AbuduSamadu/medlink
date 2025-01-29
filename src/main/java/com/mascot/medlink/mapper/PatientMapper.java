package com.mascot.medlink.mapper;

import com.mascot.medlink.model.dto.PatientDTO;
import com.mascot.medlink.model.entity.Patient;

public class PatientMapper {

    public static PatientDTO toPatientDTO(Patient patient){
        return new PatientDTO(
                patient.getMedicalHistory(),
                patient.getDateOfBirth(),
                patient.getEmergencyContact()
        );
    }
}
