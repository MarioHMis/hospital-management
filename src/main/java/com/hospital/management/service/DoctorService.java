package com.hospital.management.service;

import com.hospital.management.model.Doctor;
import com.hospital.management.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}
