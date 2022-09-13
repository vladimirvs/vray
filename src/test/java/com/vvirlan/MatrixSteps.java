package com.vvirlan;

import com.vvirlan.model.Matrix;
import com.vvirlan.model.Tuple;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.it.Ma;

import java.util.List;

import static com.vvirlan.StepContext.matrices;
import static com.vvirlan.StepContext.tuples;
import static org.junit.jupiter.api.Assertions.*;

public class MatrixSteps {

    @Given("the following {int}x{int} matrix {word}:")
    public void theFollowingXMatrixM(int rows, int cols, String matrixName, DataTable dataTable) {
        System.out.println(dataTable);
        Matrix matrix = new Matrix(rows, cols);

        List<List<String>> lists = dataTable.asLists();
        System.out.println(lists);

        for (int r = 0; r < lists.size(); r++) {
            for (int c = 0; c < lists.get(0).size(); c++) {
                matrix.put(r, c, Float.parseFloat(lists.get(r).get(c)));
            }
        }


        matrices.put(matrixName, matrix);
    }


    @Then("{word}[{int},{int}] = {float}")
    public void m(String matrixName, int r, int c, float val) {
        Matrix matrix = matrices.get(matrixName);
        assertEquals(val, matrix.at(r, c));
    }

    @Given("the following matrix {word}:")
    public void theFollowingMatrixA(String matrixName, DataTable dataTable) {
        Matrix matrix = getMatrix(dataTable);
        matrices.put(matrixName, matrix);

    }

    private static Matrix getMatrix(DataTable dataTable) {
        List<List<String>> lists = dataTable.asLists();
        int rows = lists.size();
        int cols = lists.size();
        Matrix matrix = new Matrix(rows, cols);

        for (int r = 0; r < lists.size(); r++) {
            for (int c = 0; c < lists.get(0).size(); c++) {
                matrix.put(r, c, Float.parseFloat(lists.get(r).get(c)));
            }
        }
        return matrix;
    }

    @Then("{word} == {word}")
    public void aB(String keyA, String keyB) {
        Matrix a = matrices.get(keyA);
        Matrix b = matrices.get(keyB);
        assertEquals(a, b);
    }

    @Then("{word} != {word}")
    public void notEqual(String keyA, String keyB) {
        Matrix a = matrices.get(keyA);
        Matrix b = matrices.get(keyB);
        assertNotEquals(a, b);
    }

    @Then("{word} * {word} is the following {int}x{int} matrix:")
    public void aBIsTheFollowingXMatrix(String aKye, String bKey, int rows, int cols, DataTable dataTable) {
        Matrix A = matrices.get(aKye);
        Matrix B = matrices.get(bKey);
        Matrix C = Matrix.mul(A, B);

        Matrix matrix = getMatrix(rows, cols, dataTable);

        System.out.println(matrix);
        assertEquals(matrix, C);


    }

    private static Matrix getMatrix(int rows, int cols, DataTable dataTable) {
        List<List<String>> lists = dataTable.asLists();
        Matrix matrix = new Matrix(rows, cols);

        for (int r = 0; r < lists.size(); r++) {
            for (int c = 0; c < lists.get(0).size(); c++) {
                matrix.put(r, c, Float.parseFloat(lists.get(r).get(c)));
            }
        }
        return matrix;
    }

    @Then("{word} * {word} = tuple\\({int}, {int}, {int}, {int})")
    public void aBTuple(String aKey, String bKey, int x, int y, int z, int w) {
        Matrix matrix = matrices.get(aKey);
        Tuple tuple = tuples.get(bKey);
        Tuple mul = Matrix.mul(matrix, tuple);
        assertEquals(new Tuple(x, y, z, w), mul);
    }

    @Then("{word} * identity_matrix = {word}")
    public void aIdentity_matrixA(String aKey, String bKey) {
        Matrix a = matrices.get(aKey);
        Matrix expected = matrices.get(bKey);

        Matrix identity = Matrix.identity(a.rows);
        System.out.println(identity);
        assertEquals(expected, Matrix.mul(a, identity));


    }

    @Then("identity_matrix * {word} = {word}")
    public void identity_matrixAA(String aKey, String bKey) {
        Tuple a = tuples.get(aKey);
        Matrix identity = Matrix.identity(4);
        assertEquals(a, Matrix.mul(identity, a));

    }

    @Then("transpose\\({word}) is the following matrix:")
    public void transposeAIsTheFollowingMatrix(String aKey, DataTable dataTable) {
        Matrix a = matrices.get(aKey);
        Matrix matrix = getMatrix(dataTable);
        Matrix transpose = Matrix.transpose(a);

        System.out.println(matrix);
        System.out.println(transpose);
        assertEquals(matrix, transpose);
    }

    @Given("{word} ← transpose\\(identity_matrix)")
    public void aTransposeIdentity_matrix(String aKey) {
        Matrix a = Matrix.transpose(Matrix.identity(4));
        matrices.put(aKey, a);
    }

    @Then("{word} = identity_matrix")
    public void aIdentity_matrix(String aKey) {
        Matrix a = matrices.get(aKey);
        assertEquals(Matrix.identity(4), a);
    }

    @Then("determinant\\({word}) = {int}")
    public void determinantA(String aKey, int det) {
        Matrix a = matrices.get(aKey);
        assertEquals(det, Matrix.determinant(a));

    }

    @Then("submatrix\\({word}, {int}, {int}) is the following {int}x{int} matrix:")
    public void submatrixAIsTheFollowingXMatrix(String aKey, int row, int col, int rows, int cols, DataTable dataTable) {
        Matrix a = matrices.get(aKey);
        Matrix sub = Matrix.submatrix(a, row, col);
        Matrix exp = getMatrix(dataTable);
        assertEquals(exp, sub);
        assertEquals(rows, sub.rows);
        assertEquals(cols, sub.cols);
    }

    @And("{word} ← submatrix\\({word}, {int}, {int})")
    public void bSubmatrixA(String bKey, String aKey, int row, int col) {
        Matrix a = matrices.get(aKey);
        Matrix b = Matrix.submatrix(a, row, col);
        System.out.println(b);
        matrices.put(bKey, b);

    }

    @And("minor\\({word}, {int}, {int}) = {int}")
    public void minorA(String aKey, int row, int col, int exp) {
        Matrix matrix = matrices.get(aKey);
        int minor = Matrix.minor(matrix, row, col);
        assertEquals(exp, minor);
    }

    @And("cofactor\\({word}, {int}, {int}) = {int}")
    public void cofactorA(String aKey, int row, int col, int exp) {
        Matrix matrix = matrices.get(aKey);
        int cofactor = Matrix.cofactor(matrix, row, col);
        assertEquals(exp, cofactor);
    }

    @And("{word} is invertible")
    public void aIsInvertible(String aKey) {
        Matrix matrix = matrices.get(aKey);
        assertTrue(Matrix.isInvertible(matrix));

    }

    @And("{word} is not invertible")
    public void aIsNotInvertible(String aKey) {
        Matrix matrix = matrices.get(aKey);
        assertFalse(Matrix.isInvertible(matrix));
    }

    @And("{word} ← inverse\\({word})")
    public void bInverseA(String bKey, String aKey) {
        Matrix matrix = matrices.get(aKey);
        Matrix inv = Matrix.inverse(matrix);
        matrices.put(bKey, inv);

    }


    @And("{word} is the following {int}x{int} matrix:")
    public void bIsTheFollowingXMatrix(String aKey, int rows, int cols, DataTable dataTable) {
        Matrix a = matrices.get(aKey);
        Matrix exp = getMatrix(dataTable);
        assertEquals(exp, a);

    }
}
