package com.vvirlan;

import com.vvirlan.model.Canvas;
import com.vvirlan.model.Color;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.vvirlan.StepContext.*;

public class CanvasSteps {


    public static final int HEADER_SIZE = 3;

    @Given("{word} ← canvas\\({int}, {int})")
    public void cCanvas(String key, int w, int h) {
        canvases.put(key, new Canvas(w, h));
    }

    @Then("{word}.width = {int}")
    public void cWidth(String key, int w) {
        Canvas canvas = canvases.get(key);
        assertEquals(w, canvas.width);

    }

    @And("{word}.height = {int}")
    public void cHeight(String key, int h) {
        Canvas canvas = canvases.get(key);
        assertEquals(h, canvas.height);

    }

    @And("every pixel of {word} is color\\({int}, {int}, {int})")
    public void everyPixelOfCIsColor(String key, int r, int g, int b) {
        Canvas canvas = canvases.get(key);

        for (int x = 0; x < canvas.width; x++) {
            for (int y = 0; y < canvas.height; y++) {
                assertEquals(r, canvas.pixels[y][x].red);
                assertEquals(g, canvas.pixels[y][x].green);
                assertEquals(b, canvas.pixels[y][x].blue);
            }
        }

    }

    @When("write_pixel\\({word}, {int}, {int}, {word})")
    public void write_pixelCRed(String canvasKey, int x, int y, String colorKey) {
        Canvas canvas = canvases.get(canvasKey);
        Color color = colors.get(colorKey);
        canvas.writePixel(x, y, color);

    }

    @Then("pixel_at\\({word}, {int}, {int}) = {word}")
    public void pixel_atCRed(String canvasKey, int x, int y, String colorKey) {
        Canvas canvas = canvases.get(canvasKey);
        Color color = colors.get(colorKey);
        assertEquals(color, canvas.pixelAt(x, y));

    }

    @When("{word} ← canvas_to_ppm\\({word})")
    public void ppmCanvas_to_ppmC(String fileKey, String canvasKey) {
        Canvas canvas = canvases.get(canvasKey);
        String ppm = canvas.toPpm();
        ppms.put(fileKey, ppm);

    }

    @Then("lines {int}-{int} of {word} are")
    public void linesOfPpmAre(int from, int to, String fileKey, String expLines) {
        String ppm = ppms.get(fileKey);

        String[] lines = ppm.split(System.lineSeparator());
        String[] expectedLines = expLines.split("\n");

        int offset = 0;
        if (expectedLines.length < from) {
            offset = HEADER_SIZE;
        }

        int finalOffset = offset;
        IntStream.range(from,to).forEach(i -> {
            String exp = expectedLines[i - 1 - finalOffset];
            String act = lines[i - 1];
            assertEquals(exp, act);
        });


//        for (int i = from - 1; i < to; i++) {
//            String exp = expectedLines[i - HEADER_SIZE];
//            String act = lines[i];
//            assertEquals(exp, act);
//
//        }
    }
}
