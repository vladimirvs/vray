package com.vvirlan.model;

public class Color {
    public double red;
    public double green;
    public double blue;

    public Color(double red, double green, double blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }


    public static Color add(Color c1, Color c2) {
        return new Color(c1.red + c2.red, c1.green + c2.green, c1.blue + c2.blue);
    }

    public static Color sub(Color c1, Color c2) {
        return new Color(c1.red - c2.red, c1.green - c2.green, c1.blue - c2.blue);
    }

    public static Color dot(Color c1, Color c2) {
        return new Color(c1.red * c2.red, c1.green * c2.green, c1.blue * c2.blue);
    }

    public static Color mul(Color c1, double scalar) {
        return new Color(c1.red * scalar, c1.green * scalar, c1.blue * scalar);
    }
}
