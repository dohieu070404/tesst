package test;


import java.util.ArrayList;

public class CircuitCalculator {
    private String circuitType;

    public CircuitCalculator(String circuitType) {
        this.circuitType = circuitType;
    }

    public double calculateTotalResistance(ArrayList<CircuitElement> elements) {
        double totalResistance = 0.0;
        if (circuitType.equals("Nối tiếp")) {
            for (CircuitElement element : elements) {
                if (element.type.equals("R")) {
                    totalResistance += element.value;
                }
            }
        } else if (circuitType.equals("Song song")) {
            double reciprocalResistance = 0.0;
            for (CircuitElement element : elements) {
                if (element.type.equals("R")) {
                    reciprocalResistance += 1 / element.value;
                }
            }
            if (reciprocalResistance != 0) {
                totalResistance = 1 / reciprocalResistance;
            }
        }
        return totalResistance;
    }

    public double calculateTotalCapacitance(ArrayList<CircuitElement> elements) {
        double totalCapacitance = 0.0;
        if (circuitType.equals("Nối tiếp")) {
            double reciprocalCapacitance = 0.0;
            for (CircuitElement element : elements) {
                if (element.type.equals("C")) {
                    reciprocalCapacitance += 1 / element.value;
                }
            }
            if (reciprocalCapacitance != 0) {
                totalCapacitance = 1 / reciprocalCapacitance;
            }
        } else if (circuitType.equals("Song song")) {
            for (CircuitElement element : elements) {
                if (element.type.equals("C")) {
                    totalCapacitance += element.value;
                }
            }
        }
        return totalCapacitance;
    }

    public double calculateTotalInductance(ArrayList<CircuitElement> elements) {
        double totalInductance = 0.0;
        if (circuitType.equals("Nối tiếp")) {
            for (CircuitElement element : elements) {
                if (element.type.equals("L")) {
                    totalInductance += element.value;
                }
            }
        } else if (circuitType.equals("Song song")) {
            double reciprocalInductance = 0.0;
            for (CircuitElement element : elements) {
                if (element.type.equals("L")) {
                    reciprocalInductance += 1 / element.value;
                }
            }
            if (reciprocalInductance != 0) {
                totalInductance = 1 / reciprocalInductance;
            }
        }
        return totalInductance;
    }
}
