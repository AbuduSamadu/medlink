package com.mascot.medlink.model.entity;

import com.mascot.medlink.model.enums.ClinicHospital;
import com.mascot.medlink.model.enums.DoctorSpecialization;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Doctor extends User {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DoctorSpecialization speciality;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClinicHospital clinic;
}
