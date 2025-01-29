package com.mascot.medlink.model.dto;

import com.mascot.medlink.model.enums.ClinicHospital;
import com.mascot.medlink.model.enums.DoctorSpecialization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO extends UserDTO{
    private DoctorSpecialization speciality;
    private ClinicHospital clinic;
}
