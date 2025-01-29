package com.mascot.medlink.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO extends  UserDTO{
    private String medicalHistory;
    private LocalDate dateOfBirth;
    private int emergencyContact;
}
