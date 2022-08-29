package com.vvirlan.model;

import java.util.Objects;

import static com.vvirlan.model.Constants.EPSILON;

public class Tuple {
    public float x;
    public float y;
    public float z;
    public float w;

    public Tuple(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
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
        return Math.abs(w - 1.0f) < EPSILON;
    }

    public boolean isVector() {
        return Math.abs(w - 0.0f) < EPSILON;
    }
}
