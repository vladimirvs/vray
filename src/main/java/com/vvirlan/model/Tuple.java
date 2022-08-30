package com.vvirlan.model;

import java.util.Objects;

import static com.vvirlan.model.Constants.EPSILON;

public class Tuple {
    public double x;
    public double y;
    public double z;
    // 1.0 means Point 0.0 means Vector
    public double w;

    public static final float W_POINT_1 = 1.0f;
    public static final float W_VECTOR_0 = 0.0f;

    public Tuple(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public static Tuple point(float x, float y, float z) {
        return new Tuple(x, y, z, W_POINT_1);
    }

    public static Tuple vector(float x, float y, float z) {
        return new Tuple(x, y, z, W_VECTOR_0);
    }

    public static Tuple add(Tuple ta, Tuple tb) {
        Objects.requireNonNull(ta);
        Objects.requireNonNull(tb);
        return new Tuple(ta.x + tb.x, ta.y + tb.y, ta.z + tb.z, ta.w + tb.w);
    }

    public static Tuple sub(Tuple ta, Tuple tb) {
        Objects.requireNonNull(ta);
        Objects.requireNonNull(tb);
        return new Tuple(ta.x - tb.x, ta.y - tb.y, ta.z - tb.z, ta.w - tb.w);
    }

    public static Tuple negate(Tuple t) {
        Objects.requireNonNull(t);
        return new Tuple(-t.x, -t.y, -t.z, -t.w);
    }

    public static Tuple mul(Tuple t, float scalar) {
        Objects.requireNonNull(t);
        return new Tuple(t.x * scalar, t.y * scalar, t.z * scalar, t.w * scalar);
    }

    public static Tuple div(Tuple t, float scalar) {
        Objects.requireNonNull(t);
        return new Tuple(t.x / scalar, t.y / scalar, t.z / scalar, t.w / scalar);
    }

    public static double magnitude(Tuple t) {
        Objects.requireNonNull(t);
        // Pythagoras theorem
        double sumOfSquares = Math.pow(t.x, 2) + Math.pow(t.y, 2) + Math.pow(t.z, 2);
        return Math.sqrt(sumOfSquares);


    }

    public static Tuple normalize(Tuple t) {
        return new Tuple(t.x / magnitude(t), t.y / magnitude(t), t.z / magnitude(t), t.w);
    }

    public static double dot(Tuple a, Tuple b) {
        return a.x * b.x + a.y * b.y + a.z * b.z + a.w * b.w;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple tuple = (Tuple) o;
        return Double.compare(tuple.x, x) == 0 && Double.compare(tuple.y, y) == 0 && Double.compare(tuple.z, z) == 0 && Double.compare(tuple.w, w) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, w);
    }

    @Override
    public String toString() {
        return "Tuple{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", w=" + w +
                '}';
    }

    public boolean isPoint() {
        return Math.abs(w - W_POINT_1) < EPSILON;
    }

    public boolean isVector() {
        return Math.abs(w - W_VECTOR_0) < EPSILON;
    }
}
