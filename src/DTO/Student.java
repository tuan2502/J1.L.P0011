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
public class Student {

    private String nameStudent = "";
    private String idStudent = "";

    public Student() {
    }

    public Student(String IdStudent, String NameStudent) {
        this.idStudent = IdStudent;
        this.nameStudent = NameStudent;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    @Override
    public String toString() {
        return this.idStudent + ", " + this.nameStudent;
    }

}
