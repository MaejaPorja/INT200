/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package int200_assignment_1;

import model.LinkedSet;

/**
 *
 * @author Student
 */
public class Driver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LinkedSet<Integer> lsi = new LinkedSet<>();
        lsi.add(4);
        lsi.add(5);
//        lsi.delete(5);
        System.out.println(lsi.find(5));
        System.out.println(lsi.find(4));
    }
    
}
