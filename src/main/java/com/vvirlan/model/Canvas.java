package com.vvirlan.model;


public class Canvas {
    public int width;
    public int height;
    public Color[][] pixels;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new Color[height][width];
        init(0, 0, 0);
    }

    public void init(double r, double g, double b) {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                pixels[row][col] = new Color(r, g, b);
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
        StringBuilder header = getHeader();

        StringBuilder body = new StringBuilder();

        for (int r = 0; r < height; r++) {
            StringBuilder line = new StringBuilder();
            for (int c = 0; c < width; c++) {
                String tmpLine = line + pixels[r][c].asString() + " ";
                if (tmpLine.length() > 70) {
                    if (line.toString().endsWith(" ")) {
                        line.deleteCharAt(line.length()-1);
                    }
                    line.append(System.lineSeparator()).append(pixels[r][c].asString()).append(" ");
                    body.append(line.toString());
                    line = new StringBuilder();
                } else {
                    line.append(pixels[r][c].asString()).append(" ");
                }

            }

            body.append(line.toString().trim());
            body.append(System.lineSeparator());
        }
//        if (!carryOver.isEmpty()) {
//            body.append(carryOver);
//        }

        return header.append(body).toString();
    }

    private StringBuilder getHeader() {
        StringBuilder header = new StringBuilder();
        header
                .append("P3").append(System.lineSeparator())
                .append(width).append(" ").append(height).append(System.lineSeparator())
                .append(255).append(System.lineSeparator());
        return header;
    }
}
