/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package test;

/**
 *
 * @author apa16
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        int random_int = (int)Math.floor(Math.random()*(100-(-100)+1)+(-100));
        double randomFin = (double) random_int / 10000.00;
        
        System.out.println(randomFin);
    }
    
}
