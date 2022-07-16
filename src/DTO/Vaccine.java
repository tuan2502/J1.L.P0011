/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Admin
 */
public class Vaccine {

    private String vaccineId = "";
    private String vaccineName = "";

    public Vaccine() {
    }

    public Vaccine(String VaccineId, String VaccineName) {
        this.vaccineId = VaccineId;
        this.vaccineName = VaccineName;
    }

    public String getVaccineId() {
        return vaccineId;
    }

    public void setVaccineId(String vaccineId) {
        this.vaccineId = vaccineId;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    @Override
    public String toString() {
        return vaccineId + ", " + vaccineName ;
    }

}
