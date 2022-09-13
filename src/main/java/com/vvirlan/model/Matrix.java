package com.vvirlan.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Objects;

public class Matrix {
    public static final int SCALE = 5;
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

    public static Matrix transpose(Matrix matrix) {
        Matrix trans = new Matrix(matrix.rows, matrix.cols);
        for (int r = 0; r < matrix.rows; r++) {
            for (int c = 0; c < matrix.cols; c++) {
                trans.put(c, r, matrix.at(r, c));
            }
        }
        return trans;
    }

    public static int determinant(Matrix A) {
        if (A.cols == 2) {
            float a = A.data[0][0];
            float b = A.data[0][1];
            float c = A.data[1][0];
            float d = A.data[1][1];
            float det = a * d - b * c;
            return Math.round(det);
        } else {
            float det = 0;
            for (int c = 0; c < A.cols; c++) {
                det += A.data[0][c] * cofactor(A, 0, c);
            }
            return Math.round(det);
        }

    }

    public static Matrix submatrix(Matrix a, int row, int col) {
        Matrix sub = new Matrix(a.rows - 1, a.cols - 1);
        int sr = 0;
        int sc = 0;
        for (int r = 0; r < a.rows; r++) {
            if (r == row) {
                continue;
            }

            for (int c = 0; c < a.cols; c++) {
                if (c == col) {
                    continue;
                }
                sub.put(sr, sc, a.data[r][c]);
                sc++;
            }
            sc = 0;
            sr++;
        }
        return sub;
    }

    public static int cofactor(Matrix matrix, int row, int col) {
        int minor = Matrix.minor(matrix, row, col);
        return (row + col) % 2 == 1 ? -minor : minor;
    }

    public static int minor(Matrix matrix, int row, int col) {
        Matrix sub = Matrix.submatrix(matrix, row, col);
        return Matrix.determinant(sub);
    }

    public static boolean isInvertible(Matrix matrix) {
        return determinant(matrix) != 0;
    }

    public static Matrix inverse(Matrix matrix) {
        if (!isInvertible(matrix)) {
            System.out.println("Matrix " + matrix + " not invertible!");
            return null;
        }

        Matrix m2 = new Matrix(matrix.rows, matrix.cols);
        for (int row = 0; row < matrix.rows; row++) {
            for (int col = 0; col < matrix.cols; col++) {
                int c = cofactor(matrix, row, col);
                m2.data[col][row] = new BigDecimal(String.valueOf(c / (float) determinant(matrix))).setScale(SCALE, RoundingMode.HALF_EVEN).floatValue();
            }
        }


        return m2;
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
