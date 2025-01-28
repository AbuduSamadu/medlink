package com.mascot.medlink.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient extends User {
    private String medicalHistory;
    private Date dateOfBirth;
    private int emergencyContact;
}
