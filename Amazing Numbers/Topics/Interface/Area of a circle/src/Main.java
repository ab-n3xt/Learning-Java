class Circle implements Measurable {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double area() {
        return Math.PI * Math.pow(this.radius, 2); // pi * r^2
    }
}

// do not change the interface
interface Measurable {
    double area();
}