/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;
import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Admin
 */
public class Injection implements Serializable{

    private String injectionId = "";
    private String studentId = "";
    private String vaccineId = "";
    private String firstPlace = "";
    private LocalDate firstDate = null;
    private String secondPlace = "";
    private LocalDate secondDate = null;
    
    public Injection() {
    }
    
    public Injection(String injectionId, String studentId, String vaccineId, String firstPlace, LocalDate firstDate, String secondPlace, LocalDate secondDate) {
        this.injectionId = injectionId;
        this.studentId = studentId;
        this.vaccineId = vaccineId;
        this.firstPlace = firstPlace;
        this.firstDate = firstDate;
        this.secondPlace = secondPlace;
        this.secondDate = secondDate;
    }

    public String getInjectionId() {
        return injectionId;
    }

    public void setInjectionId(String injectionId) {
        this.injectionId = injectionId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getVaccineId() {
        return vaccineId;
    }

    public void setVaccineId(String vaccineId) {
        this.vaccineId = vaccineId;
    }

    public String getFirstPlace() {
        return firstPlace;
    }

    public void setFirstPlace(String firstPlace) {
        this.firstPlace = firstPlace;
    }

    public LocalDate getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(LocalDate firstDate) {
        this.firstDate = firstDate;
    }

    public String getSecondPlace() {
        return secondPlace;
    }

    public void setSecondPlace(String secondPlace) {
        this.secondPlace = secondPlace;
    }

    public LocalDate getSecondDate() {
        return secondDate;
    }

    public void setSecondDate(LocalDate secondDate) {
        this.secondDate = secondDate;
    }

    @Override
    public String toString() {
        
        return  "-------------------------------\n" +
                "Injection ID: " + injectionId + 
                "\nStudent ID: " + studentId + 
                "\nVaccine ID: " + vaccineId + 
                "\nFirst Place: " + firstPlace + 
                "\nFirst Date: " + firstDate + 
                "\nSecond Place: " + secondPlace +
                "\nSecond Date: " + secondDate +
                "\n--------------------------------\n";
    }


}
