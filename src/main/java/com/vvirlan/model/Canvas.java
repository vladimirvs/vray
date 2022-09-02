package com.vvirlan.model;


public class Canvas {
    public int width;
    public int height;
    public Color[][] pixels;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new Color[height][width];
        init();
    }

    public void init() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                pixels[row][col] = new Color(0, 0, 0);
            }
        }
    }

    public void writePixel(int x, int y, Color color) {
        pixels[y][x] = color;
    }

    public Color pixelAt(int x, int y) {
        return pixels[y][x];
    }

    public String toPpm() {
        StringBuilder header = new StringBuilder();
        header
                .append("P3").append(System.lineSeparator())
                .append(width).append(" ").append(height).append(System.lineSeparator())
                .append(255).append(System.lineSeparator());

        StringBuilder body = new StringBuilder();
        for (int r = 0; r < height; r++) {
            StringBuilder line = new StringBuilder();
            for (int c = 0; c < width; c++) {
                line.append(pixels[r][c].asString()).append(" ");
            }
            body.append(line.toString().trim());
            body.append(System.lineSeparator());
        }

        return header.append(body).toString();
    }
}
