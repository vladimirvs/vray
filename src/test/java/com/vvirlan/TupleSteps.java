package com.vvirlan;

import com.vvirlan.model.Tuple;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TupleSteps {

    private Tuple tuple;

    @Given("a ← tuple\\({float}, {float}, {float}, {float})")
    public void aTuple(float x, float y, float z, float w) {
        System.out.println("given");
        tuple = new Tuple(x, y, z, w);
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

    @And("a is a point")
    public void aIsAPoint() {
        assertTrue(tuple.isPoint());
    }

    @And("a is not a vector")
    public void aIsNotAVector() {
        assertFalse(tuple.isVector());
    }

    @And("a is not a point")
    public void aIsNotAPoint() {
        assertFalse(tuple.isPoint());
    }

    @And("a is a vector")
    public void aIsAVector() {
        assertTrue(tuple.isVector());
    }
}
