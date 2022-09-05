package com.vvirlan.model;

import java.util.Arrays;
import java.util.Objects;

public class Matrix {
    public int rows;
    public int cols;

    private float[][] data;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        data = new float[rows][cols];
    }

    public static Tuple mul(Matrix a, Tuple b) {
        double x = a.data[0][0] * b.x + a.data[0][1] * b.y + a.data[0][2] * b.z + a.data[0][3] * b.w;
        double y = a.data[1][0] * b.x + a.data[1][1] * b.y + a.data[1][2] * b.z + a.data[1][3] * b.w;
        double z = a.data[2][0] * b.x + a.data[2][1] * b.y + a.data[2][2] * b.z + a.data[2][3] * b.w;
        double w = a.data[3][0] * b.x + a.data[3][1] * b.y + a.data[3][2] * b.z + a.data[3][3] * b.w;
        Tuple res = new Tuple(x, y, z, w);
        return res;
    }

    public static Matrix mul(Matrix a, Matrix b) {
        Matrix p = new Matrix(a.rows, a.cols);
        for (int r = 0; r < a.rows; r++) {
            for (int c = 0; c < a.cols; c++) {
                p.data[r][c] = a.data[r][0] * b.data[0][c] +
                        a.data[r][1] * b.data[1][c] +
                        a.data[r][2] * b.data[2][c] +
                        a.data[r][3] * b.data[3][c];
            }
        }
        return p;
    }

    public static Matrix identity(int rows) {
        Matrix a = new Matrix(rows, rows);
        for (int r = 0; r < rows; r++) {
                a.put(r, r, 1.0f);
        }
        return a;
    }

    public float at(int r, int c) {
        return data[r][c];
    }

    public void put(int r, int c, float val) {
        data[r][c] = val;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix = (Matrix) o;
        return rows == matrix.rows && cols == matrix.cols && Arrays.deepEquals(data, matrix.data);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(rows, cols);
        result = 31 * result + Arrays.deepHashCode(data);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                sb.append(data[r][c]).append(" ");
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
