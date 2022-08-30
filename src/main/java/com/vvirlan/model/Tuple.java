package com.vvirlan.model;

import java.util.Objects;

import static com.vvirlan.model.Constants.EPSILON;

public class Tuple {
    public float x;
    public float y;
    public float z;
    // 1.0 means Point 0.0 means Vector
    public float w;

    public static final float W_POINT_1 = 1.0f;
    public static final float W_VECTOR_0 = 0.0f;

    public Tuple(float x, float y, float z, float w) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple tuple = (Tuple) o;
        return Float.compare(tuple.x, x) == 0 && Float.compare(tuple.y, y) == 0 && Float.compare(tuple.z, z) == 0 && Float.compare(tuple.w, w) == 0;
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
