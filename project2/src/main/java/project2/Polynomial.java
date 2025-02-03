package project2;

import java.util.ArrayList;
import java.util.Random;

public class Polynomial implements Comparable<Polynomial> {
    private ArrayList<Double> coefficients;  // Using ArrayList for dynamic size handling

    // Default constructor
    public Polynomial() {
        this.coefficients = new ArrayList<>();
        this.coefficients.add(0.0);  // Represents the zero polynomial correctly
    }

    // Constructor with coefficients array
    public Polynomial(double[] coeffs) {
        this.coefficients = new ArrayList<>();
        for (double coeff : coeffs) {
            this.coefficients.add(coeff);
        }
        this.trim();
    }

    // Constructor with specific degree and random coefficients
    public Polynomial(int degree) {
        Random rand = new Random();
        this.coefficients = new ArrayList<>();
        for (int i = 0; i <= degree; i++) {
            this.coefficients.add(rand.nextDouble() * 30 - 15);  // Range [-15, 15]
        }
        this.trim();
    }

    // Trim the array to remove trailing zeros
    private void trim() {
        while (this.coefficients.size() > 1 && this.coefficients.get(this.coefficients.size() - 1) == 0) {
            this.coefficients.remove(this.coefficients.size() - 1);
        }
        if (this.coefficients.isEmpty()) {
            this.coefficients.add(0.0);  // Ensure zero polynomial is handled correctly
        }
    }

    // Get the degree of the polynomial
    public int getDegree() {
        if (this.coefficients.size() == 1 && this.coefficients.get(0) == 0) {
            return -1;  // Degree of the zero polynomial
        }
        return this.coefficients.size() - 1;
    }

    // Set a specific coefficient
    public void setCoefficient(int power, double value) {
        while (power >= this.coefficients.size()) {
            this.coefficients.add(0.0);
        }
        this.coefficients.set(power, value);
        this.trim();
    }

    // Evaluate the polynomial at a given x
    public double evaluate(double x) {
        double result = 0;
        double powerX = 1;
        for (double coeff : this.coefficients) {
            result += coeff * powerX;
            powerX *= x;
        }
        return result;
    }

    // Add another polynomial to this one
    public Polynomial add(Polynomial p) {
        int maxDegree = Math.max(this.getDegree(), p.getDegree());
        double[] result = new double[maxDegree + 1];
        for (int i = 0; i <= maxDegree; i++) {
            double thisCoeff = i < this.coefficients.size() ? this.coefficients.get(i) : 0;
            double pCoeff = i < p.coefficients.size() ? p.coefficients.get(i) : 0;
            result[i] = thisCoeff + pCoeff;
        }
        return new Polynomial(result);
    }

    // Subtract another polynomial from this one
    public Polynomial subtract(Polynomial p) {
        int maxDegree = Math.max(this.getDegree(), p.getDegree());
        double[] result = new double[maxDegree + 1];
        for (int i = 0; i <= maxDegree; i++) {
            double thisCoeff = i < this.coefficients.size() ? this.coefficients.get(i) : 0;
            double pCoeff = i < p.coefficients.size() ? p.coefficients.get(i) : 0;
            result[i] = thisCoeff - pCoeff;
        }
        return new Polynomial(result);
    }

    // Multiply another polynomial with this one
    public Polynomial multiply(Polynomial p) {
        int newDegree = this.getDegree() + p.getDegree();
        double[] result = new double[newDegree + 1];

        for (int i = 0; i <= this.getDegree(); i++) {
            for (int j = 0; j <= p.getDegree(); j++) {
                result[i + j] += this.coefficients.get(i) * p.coefficients.get(j);
            }
        }
        return new Polynomial(result);
    }

    // Implement compareTo for Comparable interface
    @Override
    public int compareTo(Polynomial other) {
        if (this.getDegree() > other.getDegree()) return 1;
        if (this.getDegree() < other.getDegree()) return -1;
        
        for (int i = this.getDegree(); i >= 0; i--) {
            if (this.coefficients.get(i) > other.coefficients.get(i)) return 1;
            if (this.coefficients.get(i) < other.coefficients.get(i)) return -1;
        }
        return 0;  // If all coefficients are the same
    }

    // Override toString to display the polynomial in standard form
    @Override
    public String toString() {
        if (this.coefficients.isEmpty() || (this.coefficients.size() == 1 && this.coefficients.get(0) == 0)) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = this.coefficients.size() - 1; i >= 0; i--) {
            if (this.coefficients.get(i) != 0) {
                if (sb.length() > 0 && this.coefficients.get(i) > 0) {
                    sb.append(" + ");
                } else if (this.coefficients.get(i) < 0) {
                    sb.append(" - ");
                }
                double absCoeff = Math.abs(this.coefficients.get(i));
                if (i == 0) {
                    sb.append(String.format("%.2f", absCoeff));
                } else if (i == 1) {
                    sb.append(String.format("%.2f*x", absCoeff));
                } else {
                    sb.append(String.format("%.2f*x^%d", absCoeff, i));
                }
            }
        }
        return sb.toString();
    }
}
