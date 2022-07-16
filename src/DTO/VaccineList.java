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
public class VaccineList extends ArrayList<Vaccine> {

    public void loadVaccineFromFile() {
        try {
            File file = new File("Vaccine.dat");
            if (!file.exists()) {
                System.out.println("Vaccine.dat is not exist!");
            }
            FileReader fr = new FileReader(file);
            BufferedReader bf = new BufferedReader(fr);
            String details;
            while ((details = bf.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(details, ",");
                String id = stk.nextToken().toUpperCase();
                String name = stk.nextToken().toUpperCase();
                Vaccine vaccine = new Vaccine(id, name);
                this.add(vaccine);
            }
            bf.close();
            fr.close();
        } catch (Exception e) {
            System.out.println("Error!");
        }
    }

    public void displayVaccine() {
        for (Vaccine vaccine : this) {
            System.out.println(vaccine.toString());
        }
    }
}
