package com.mascot.medlink.model.entity;

import com.mascot.medlink.model.enums.ClinicHospital;
import com.mascot.medlink.model.enums.DoctorSpecialization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor extends User {
    private DoctorSpecialization speciality;
    private ClinicHospital clinic;
}
