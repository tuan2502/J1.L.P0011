/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DTO.InjectionList;

/**
 *
 * @author Admin
 */
public class VaccineManagement {
    public static void main(String[] args) {
        InjectionList IList= new InjectionList();
        String[] options = {"Show information all students have been injected.", "Add student's vaccine injection information.", "Updating information of students' vaccine injection."
        , "Delete student vaccine injection information.", "Search for injection information by studentID.", "Save injection information to file.","Quit."};
        String fName = "Injection.dat";
        IList.loadFromFile(fName);
        int choice;
        Menu menu = new Menu();
        boolean cont = false;
        do{
            try{
                do{
                    System.out.println("\nVaccine Manager Program\n--------------------------------");
                    choice = menu.int_getChoice(options);
                    switch(choice){
                        case 1: IList.displayInjected();break;
                        case 2: IList.addInjection();break;
                        case 3: IList.updateInjection();break;
                        case 4: IList.removeInjection(); break;
                        case 5: IList.searchInjectionByStudentID(); break;
                        case 6: IList.saveToFile(fName); break;
                        case 7: System.out.println("Bye");System.exit(0); break;
                        default: System.out.println("Incorrect!");break;
                    }
                }while(choice>0 || choice <9);
                cont = false;
            }catch (Exception e){
                System.out.println("Syntax Error!");
                cont = true;
            }
        }while(cont);
    }
    
}
