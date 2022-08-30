package com.vvirlan;

import com.vvirlan.model.Tuple;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.HashMap;
import java.util.Map;

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
}
