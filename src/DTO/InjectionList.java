/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import Validation.Inputter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class InjectionList extends ArrayList<Injection> {

    public InjectionList() {
        super();
    }

    public boolean loadFromFile(String fName) {
        try {
            File f = new File(fName);
            if (!f.exists()) {
                return false;
            }
            FileInputStream fi = new FileInputStream(f);
            ObjectInputStream os = new ObjectInputStream(fi);
            while (fi.available() > 0) {
                Injection injection = (Injection) os.readObject();
                this.add(injection);
            }
            os.close();
            fi.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }

    public void saveToFile(String fileName) {
        try {
            FileOutputStream fo = new FileOutputStream(fileName);
            ObjectOutputStream os = new ObjectOutputStream(fo);
            for (Injection injection : this) {
                os.writeObject(injection);
            }
            fo.close();
            os.close();
            System.out.println("Saving succesfully");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addInjection() {
        StudentList studentList = new StudentList();
        VaccineList vaccineList = new VaccineList();
        studentList.loadStudentFromFile();
        vaccineList.loadVaccineFromFile();
        String newIjtId = null, newStuId = null, newVacId = null, newfPlace = null, newsPlace = null;
        LocalDate newfDate = null, newsDate = null;
        boolean IdDuplicated = false;
        boolean checkExist = false;
        boolean cont = false;
        long checkDate;
        String ans;
        System.out.println("Enter new Injection information ");
        do {
            try {
                do {
                    newIjtId = Inputter.inputStr("Input new Injection ID: ");
                    newIjtId = newIjtId.trim().toUpperCase();
                    IdDuplicated = isCodeDuplicatedIjt(newIjtId);
                    if (IdDuplicated) {
                        System.out.println("Injection ID is duplicated!");
                    }
                } while (IdDuplicated == true);
                System.out.println("Student List");
                studentList.displayStudent();
                do {
                    newStuId = Inputter.inputStr("Input Student ID: ");
                    newStuId = newStuId.trim().toUpperCase();
                    IdDuplicated = isCodeDuplicatedStudent(newStuId);
                    checkExist = existStudent(newStuId);
                    if (IdDuplicated) {
                        System.out.println("Student ID is duplicated!");
                    }
                    if (checkExist) {
                        System.out.println("Student ID is not exist!");
                    }
                } while (IdDuplicated == true || checkExist == true);
                System.out.println("Vaccine List");
                vaccineList.displayVaccine();
                do {
                    newVacId = Inputter.inputStr("Input Vaccine ID: ");
                    newVacId = newVacId.trim().toUpperCase();
                    checkExist = existVaccine(newVacId);
                    if (checkExist) {
                        System.out.println("Vaccine ID is not exist!");
                    }
                } while (checkExist == true);
                newfPlace = Inputter.inputStr("Input first place: ");
                newfDate = Inputter.inputNonBlankStr_Date("Input first Date (dd/mm/yyyy): ");
                if (Inputter.Answer("Do you want to add second injection? (Y/N) ")) {
                    String checkVaccineType;
                    do {
                        checkVaccineType = Inputter.inputNonBlankStr("Input second Vaccine ID: ");
                        if (!checkVaccineType.equalsIgnoreCase(newVacId)) {
                            System.out.println("The 1st dose and 2nd dose must have the same type!!!");
                        }
                    } while (!checkVaccineType.equalsIgnoreCase(newVacId));

                    newsPlace = Inputter.inputNonBlankStr("Input second place: ");
                    do {
                        newsDate = Inputter.inputNonBlankStr_Date("Input second date (dd/mm/yyyy): ");
                        checkDate = DAYS.between(newfDate, newsDate);
                        if ((checkDate < 28 || checkDate > 84)) {
                            System.out.println("Two injections 4 to 12 weeks apart.");
                        }
                    } while (checkDate < 28 || checkDate > 84);
                }
                Injection injection = new Injection(newIjtId, newStuId, newVacId, newfPlace, newfDate, newsPlace, newsDate);
                this.add(injection);
                do {
                    ans = Inputter.inputNonBlankStr("Do you want to continue adding another vaccine? (Y/N): ");
                    if (ans.equalsIgnoreCase("Y")) {
                        cont = true;
                    }
                    if (ans.equalsIgnoreCase("N")) {
                        cont = false;
                    }
                } while (!("Y".equalsIgnoreCase(ans)) && !("N".equalsIgnoreCase(ans)));
            } catch (Exception e) {
                System.out.println(e);
                cont = true;
            }
        } while (cont);
    }

    public void updateInjection() {
        boolean cont = false;
        LocalDate newsDate = null;
        long checkDate;
        do {
            try {
                if (this.isEmpty()) {
                    System.out.println("Empty list!");
                } else {
                    String uID = Inputter.inputStr("Input Injection ID for update: ");
                    Injection ijt = this.searchInjectionID(uID);
                    if (ijt == null) {
                        System.out.println("Injection " + uID + " doesn't existed.");
                    } else {
                        String oldsPlace = ijt.getSecondPlace();
                        String msg = "Old second place: " + oldsPlace + ", New second place: ";
                        String newsPlace = Inputter.inputNonBlankStr(msg);
                        ijt.setSecondPlace(newsPlace);
                        do {
                            newsDate = Inputter.inputNonBlankStr_Date("Input second date (dd/mm/yyyy): ");
                            checkDate = DAYS.between(ijt.getFirstDate(), newsDate);
                            if ((checkDate < 28 || checkDate > 84)) {
                                System.out.println("Two injections 4 to 12 weeks apart.");
                            }
                        } while (checkDate < 28 || checkDate > 84);
                        ijt.setSecondDate(newsDate);
                        System.out.println(ijt.toString());
                        if ((ijt.getFirstDate() != null && ijt.getFirstPlace() != null) && (ijt.getSecondDate() != null && ijt.getSecondPlace() != null)) {
                            System.out.println("Student has completed 2 injections!");
                        }
                    }
                }
                cont = false;
            } catch (Exception e) {
                System.out.println(e);
                cont = true;
            }
        } while (cont);
    }

    public void removeInjection() {
        boolean cont = false;
        String ans;
        do {
            try {
                if (this.isEmpty()) {
                    System.out.println("Empty list!");
                } else {
                    String rID = Inputter.inputStr("Input Injection ID for remove: ");
                    Injection ijt = this.searchInjectionID(rID);
                    if (ijt == null) {
                        System.out.println("Injection " + rID + "doesn't existed.");
                    } else {
                        do {
                            ans = Inputter.inputNonBlankStr("Do you want to remove this injection? (Y/N): ");
                            if (ans.equalsIgnoreCase("Y")) {
                                this.remove(ijt);
                                System.out.println("Injection " + rID + " has been removed.");
                                cont = false;
                            }
                            if (ans.equalsIgnoreCase("N")) {
                                System.out.println("Remove fail!");
                                cont = false;
                            }
                        } while (!("Y".equalsIgnoreCase(ans)) && !("N".equalsIgnoreCase(ans)));
                    }
                }
                cont = false;
            } catch (Exception e) {
                System.out.println(e);
                cont = true;
            }
        } while (cont);

    }

    public void searchInjectionByStudentID() {
        boolean cont = false;
        do {
            try {
                if (this.isEmpty()) {
                    System.out.println("Empty list!");
                } else {
                    LocalDate sID = Inputter.inputNonBlankStr_Date("Input date for search: ");
                    Injection st = this.searchDate(sID);
                    if (st == null) {
                        System.out.println("Student ID " + sID + " doesn't existed.");
                    } else {
                        System.out.println(st.toString());
                    }
                }
                cont = false;
            } catch (Exception e) {
                System.out.println(e);
                cont = true;
            }
        } while (cont);
    }

    public Injection searchStudentID(String StudentID) {
        StudentID = StudentID.trim().toUpperCase();
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getStudentId().equalsIgnoreCase(StudentID)) {
                return this.get(i);
            }
        }
        return null;
    }

    private boolean isCodeDuplicatedStudent(String StudentID) {
        StudentID = StudentID.trim().toUpperCase();
        return searchStudentID(StudentID) != null;
    }

    public Injection searchInjectionID(String IjtID) {
        IjtID = IjtID.trim().toUpperCase();
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getInjectionId().equalsIgnoreCase(IjtID)) {
                return this.get(i);
            }
        }
        return null;
    }

    private boolean isCodeDuplicatedIjt(String IjtID) {
        IjtID = IjtID.trim().toUpperCase();
        return searchInjectionID(IjtID) != null;
    }

    public Injection searchVaccineID(String VacID) {
        VacID = VacID.trim().toUpperCase();
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getVaccineId().equalsIgnoreCase(VacID)) {
                return this.get(i);
            }
        }
        return null;
    }

    private boolean existStudent(String studentID) {
        StudentList studentList = new StudentList();
        studentList.loadStudentFromFile();
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getIdStudent().equalsIgnoreCase(studentID)) {
                return false;
            }
        }
        return true;
    }

    private boolean existVaccine(String vaccineID) {
        VaccineList vaccineList = new VaccineList();
        vaccineList.loadVaccineFromFile();
        for (int i = 0; i < vaccineList.size(); i++) {
            if (vaccineList.get(i).getVaccineId().equalsIgnoreCase(vaccineID)) {
                return false;
            }
        }
        return true;
    }

    public void displayInjected() {
        if (this.isEmpty()) {
            System.out.println("Injection list is empty!!!");
        } else {
            for (Injection injection : this) {
                System.out.println(injection.toString());
            }
        }
    }
   public Injection searchDate(LocalDate Date) {
       boolean check;
        for (int i = 0; i < this.size(); i++) {
            if ((Date.equals(this.get(i).getFirstDate())) || (Date.equals(this.get(i).getSecondDate()))){
                System.out.println( this.get(i).toString());
            }
        }
        return null;
    }

}
