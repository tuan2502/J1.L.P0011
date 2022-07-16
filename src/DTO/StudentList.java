/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author Admin
 */
public class StudentList extends ArrayList<Student> {

    public void loadStudentFromFile() {
        try {
            File file = new File("Student.dat");
            if (!file.exists()) {
                System.out.println(file + "is not exist!");
            }
            FileReader fr = new FileReader(file);
            BufferedReader bf = new BufferedReader(fr);
            String details;
            while ((details = bf.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(details, ",");
                String studentId = stk.nextToken().toUpperCase();
                String studentName = stk.nextToken().toUpperCase();
                Student st = new Student(studentId, studentName);
                this.add(st);
            }
            bf.close();
            fr.close();
        } catch (Exception e) {
            System.out.println("Error!");
        }
    }
    public void displayStudent() {
        for(Student st : this) {
            System.out.println(st.toString());
        }
    }
}

        
