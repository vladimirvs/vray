package com.vvirlan.model;

import java.util.Arrays;
import java.util.Objects;

public class Matrix {
    private int rows;
    private int cols;

    private float[][] data;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        data = new float[rows][cols];
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
