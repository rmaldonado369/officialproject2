package project2;

import java.util.ArrayList;
import java.util.Collections;

// Driver program to test the Polynomial class
public class PolynomialTest {
    public static void main(String[] args) {
        // Testing various constructors and methods
        Polynomial p1 = new Polynomial(new double[]{1.0, 2.5, 3.3, 4.6});
        Polynomial p2 = new Polynomial(new double[]{4.1, 5.6, 6.8});
        Polynomial p3 = p1.add(p2);
        Polynomial p4 = p1.subtract(p2);
        Polynomial p5 = p1.multiply(p2);

        System.out.println("Testing Polynomial Operations");
        System.out.println("Polynomial 1: " + p1);
        System.out.println("Polynomial 2: " + p2);
        System.out.println("Addition Result: " + p3);
        System.out.println("Subtraction Result: " + p4);
        System.out.println("Multiplication Result: " + p5);

        // Sorting polynomials
        ArrayList<Polynomial> polynomials = new ArrayList<>();
        polynomials.add(p1);
        polynomials.add(p2);
        polynomials.add(p3);
        polynomials.add(p4);
        polynomials.add(p5);
        Collections.sort(polynomials);

        System.out.println("\nSorted Polynomials:");
        for (Polynomial p : polynomials) {
            System.out.println(p);
        }
    }
}