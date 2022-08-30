package com.vvirlan;

import com.vvirlan.model.Tuple;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashMap;
import java.util.Map;

import static com.vvirlan.model.Constants.EPSILON;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TupleSteps {

    private Tuple tuple;
    private Map<String, Tuple> tuples = new HashMap<>();

    @Given("{word} ← tuple\\({float}, {float}, {float}, {float})")
    public void aTuple(String variable, float x, float y, float z, float w) {
        tuple = new Tuple(x, y, z, w);
        tuples.put(variable, tuple);
    }

    @Then("a.x = {float}")
    public void aX(float x) {
        assertEquals(tuple.x, x);
    }

    @And("a.y = {float}")
    public void aY(float y) {
        assertEquals(tuple.y, y);
    }

    @And("a.z = {float}")
    public void aZ(float z) {
        assertEquals(tuple.z, z);
    }

    @And("a.w = {float}")
    public void aW(float w) {
        assertEquals(tuple.w, w);
    }

    @And("{word} is a point")
    public void aIsAPoint(String key) {
        assertTrue(tuple.isPoint());
    }

    @And("{word} is not a vector")
    public void aIsNotAVector(String key) {
        assertFalse(tuple.isVector());
    }

    @And("{word} is not a point")
    public void aIsNotAPoint(String key) {
        assertFalse(tuple.isPoint());
    }

    @And("{word} is a vector")
    public void aIsAVector(String key) {
        assertTrue(tuple.isVector());
    }

    @Given("{word} ← point\\({float}, {float}, {float})")
    public void pPoint(String key, float x, float y, float z) {
        tuple = Tuple.point(x, y, z);
        tuples.put(key, tuple);
    }

    @Then("{word} = tuple\\({float}, {float}, {float}, {float})")
    public void pTuple(String key, float x, float y, float z, float w) {
        assertEquals(x, tuple.x);
        assertEquals(y, tuple.y);
        assertEquals(z, tuple.z);
        assertEquals(w, tuple.w);
    }

    @Given("{word} ← vector\\({float}, {float}, {float})")
    public void vVector(String key, float x, float y, float z) {
        tuple = Tuple.vector(x, y, z);
        tuples.put(key, tuple);
    }


    @Then("{word} + {word} = tuple\\({float}, {float}, {float}, {float})")
    public void aATuple(String keyA, String keyB, float x, float y, float z, float w) {
        Tuple ta = tuples.get(keyA);
        Tuple tb = tuples.get(keyB);
        Tuple sum = Tuple.add(ta, tb);
        assertEquals(x, sum.x);
        assertEquals(y, sum.y);
        assertEquals(z, sum.z);
        assertEquals(w, sum.w);
    }

    @Then("{word} - {word} = vector\\({float}, {float}, {float})")
    public void pPVector(String keyA, String keyB, float x, float y, float z) {
        Tuple ta = tuples.get(keyA);
        Tuple tb = tuples.get(keyB);
        Tuple dif = Tuple.sub(ta, tb);
        assertEquals(x, dif.x);
        assertEquals(y, dif.y);
        assertEquals(z, dif.z);
        assertEquals(Tuple.W_VECTOR_0, dif.w);
    }

    @Then("{word} - {word} = point\\({float}, {float}, {float})")
    public void pVPoint(String keyA, String keyB, float x, float y, float z) {
        Tuple ta = tuples.get(keyA);
        Tuple tb = tuples.get(keyB);
        Tuple dif = Tuple.sub(ta, tb);
        assertEquals(x, dif.x);
        assertEquals(y, dif.y);
        assertEquals(z, dif.z);
        assertEquals(Tuple.W_POINT_1, dif.w);
    }

    @Then("- {word} = tuple\\({float}, {float}, {float}, {float})")
    public void negateTuple(String keyA, float x, float y, float z, float w) {
        Tuple t = tuples.get(keyA);
        Tuple negT = Tuple.negate(t);
        assertEquals(x, negT.x);
        assertEquals(y, negT.y);
        assertEquals(z, negT.z);
        assertEquals(w, negT.w);
    }

    @Then("{word} * {float} = tuple\\({float}, {float}, {float}, {float})")
    public void mul(String keyA, float scalar, float x, float y, float z, float w) {
        Tuple t = tuples.get(keyA);
        Tuple mult = Tuple.mul(t, scalar);
        assertEquals(x, mult.x);
        assertEquals(y, mult.y);
        assertEquals(z, mult.z);
        assertEquals(w, mult.w);
    }

    @Then("{word} \\/ {float} = tuple\\({float}, {float}, {float}, {float})")
    public void div(String keyA, float scalar, float x, float y, float z, float w) {
        Tuple t = tuples.get(keyA);
        Tuple div = Tuple.div(t, scalar);
        assertEquals(x, div.x);
        assertEquals(y, div.y);
        assertEquals(z, div.z);
        assertEquals(w, div.w);
    }

    @Then("magnitude\\({word}) = {float}")
    public void magnitudeV(String key, float m) {
        Tuple t = tuples.get(key);
        assertEquals(m, Tuple.magnitude(t));
    }

    @Then("magnitude\\({word}) = √{float}")
    public void magnitudeVSqrt(String key, float m) {
        Tuple t = tuples.get(key);
        assertEquals(Math.sqrt(m), Tuple.magnitude(t));
    }

    @Then("normalize\\({word}) = vector\\({float}, {float}, {float})")
    public void normalizeVVector(String key, float x, float y, float z) {
        Tuple t = tuples.get(key);
        Tuple norm = Tuple.normalize(t);
        assertEquals(x, norm.x);
        assertEquals(y, norm.y);
        assertEquals(z, norm.z);
        assertEquals(Tuple.W_VECTOR_0, norm.w);
    }

    @Then("normalize\\({word}) = approximately vector\\({double}, {double}, {double})")
    public void normalizeVApproximatelyVector(String key, double x, double y, double z) {
        Tuple t = tuples.get(key);
        Tuple norm = Tuple.normalize(t);
        assertEquals(x, norm.x, EPSILON);
        assertEquals(y, norm.y, EPSILON);
        assertEquals(z, norm.z, EPSILON);
        assertEquals(Tuple.W_VECTOR_0, norm.w);
    }

    @When("{word} ← normalize\\({word})")
    public void normNormalizeV(String dest, String key) {
        Tuple t = tuples.get(key);
        Tuple norm = Tuple.normalize(t);
        tuples.put(dest, norm);
    }

    @Then("dot\\({word}, {word}) = {double}")
    public void dotAB(String keyA, String keyB, double res) {

       assertEquals(res, Tuple.dot(tuples.get(keyA), tuples.get(keyB)));
    }
}
