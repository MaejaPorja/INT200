/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package int200_assignment_1;

import java.util.Iterator;
import model.ArraySet;
import model.LinkedSet;
import model.base.GenericSet;

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
        GenericSet<Integer> gsi = new ArraySet<>();
        
////////////////////////////////////////////////////////////////////////////////
        lsi.add(4);
        lsi.add(5);
//        lsi.delete(5);
        System.out.println(lsi.find(5));
        System.out.println(lsi.find(4));
////////////////////////////////////////////////////////////////////////////////
        printDelimeter('*');
////////////////////////////////////////////////////////////////////////////////    
        addInt(gsi, 5, 5);
        Iterator<Integer> iitr = gsi.iterator();
        while(iitr.hasNext()){
            System.out.println(iitr.next());
        }
        findInt(gsi,5);
        gsi.delete(2);
        gsi.delete(4);
        gsi.delete(5);
        findInt(gsi,5);
////////////////////////////////////////////////////////////////////////////////
    }
    
    private static void addInt(GenericSet set, int times,int upperBound){
        int counter = 0;
        while(counter < times){
            int num = (int)(Math.random()*upperBound+1);
            set.add(num);
            counter++;
        }
    }
    
    private static void printDelimeter(char delimeter){
        for(int i=0;i<99;i++){System.out.print(delimeter);}
        System.out.println(delimeter);
    }
    
    private static void findInt(GenericSet set, int target){
        System.out.println("Find result of "+target+": "+set.find(target));
    }
}
