package com.vvirlan;

import com.vvirlan.model.Matrix;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.it.Ma;

import java.util.List;

import static com.vvirlan.StepContext.matrices;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
        List<List<String>> lists = dataTable.asLists();
        int rows = lists.size();
        int cols = lists.size();
        Matrix matrix = new Matrix(rows, cols);

        System.out.println(lists);

        for (int r = 0; r < lists.size(); r++) {
            for (int c = 0; c < lists.get(0).size(); c++) {
                matrix.put(r, c, Float.parseFloat(lists.get(r).get(c)));
            }
        }
        matrices.put(matrixName, matrix);

    }

    @Then("{word} == {word}")
    public void aB(String keyA, String keyB) {
        Matrix a = matrices.get(keyA);
        Matrix b = matrices.get(keyB);
        assertEquals(a,b);
    }

    @Then("{word} != {word}")
    public void notEqual(String keyA, String keyB) {
        Matrix a = matrices.get(keyA);
        Matrix b = matrices.get(keyB);
        assertNotEquals(a,b);
    }

    @Then("{word} * {word} is the following {int}x{int} matrix:")
    public void aBIsTheFollowingXMatrix(String aKye, String bKey, int rows, int cols, DataTable dataTable) {
        Matrix A = matrices.get(aKye);
        Matrix B = matrices.get(bKey);
        Matrix C = Matrix.mul(A,B);

        List<List<String>> lists = dataTable.asLists();
        Matrix matrix = new Matrix(rows, cols);

        for (int r = 0; r < lists.size(); r++) {
            for (int c = 0; c < lists.get(0).size(); c++) {
                matrix.put(r, c, Float.parseFloat(lists.get(r).get(c)));
            }
        }

        System.out.println(matrix);
        assertEquals(matrix, C);


    }
}
