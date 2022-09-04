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

    public String asString() {
        return clamp(red) + " " + clamp(green) + " " + clamp(blue);
    }

    private int clamp(double d) {
        if (d > 1.0d) return 255;
        if (d < 0.0d) return 0;
        return (int) Math.round(255 * d);
    }

    @Override
    public String toString() {
        return "Color{" +
                "red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                '}';
    }
}
