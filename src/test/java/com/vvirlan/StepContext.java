package com.vvirlan;

import com.vvirlan.model.Canvas;
import com.vvirlan.model.Color;
import com.vvirlan.model.Matrix;
import com.vvirlan.model.Tuple;

import java.util.HashMap;
import java.util.Map;

public class StepContext {
    public static Tuple tuple;
    public static Map<String, Tuple> tuples = new HashMap<>();
    public static Map<String, Color> colors = new HashMap<>();
    public static Map<String, Canvas> canvases = new HashMap<>();
    public static Map<String, String> ppms = new HashMap<>();
    public static Map<String, Matrix> matrices = new HashMap<>();
}
