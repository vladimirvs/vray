package com.vvirlan.model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class World {

    Projectile tick(Environment environment, Projectile projectile) {
//        function tick(env, proj)
//        position ← proj.position + proj.velocity
//        velocity ← proj.velocity + env.gravity + env.wind
//        return projectile(position, velocity)
//        end function

        Tuple position = Tuple.add(projectile.position, projectile.velocity);
        Tuple velocity = Tuple.add(Tuple.add(projectile.velocity, environment.gravity), environment.wind);
        return new Projectile(position, velocity);
    }

    public static void main(String[] args) {
        //test1();
        test2();

    }

    private static void test2() {
//        start ← point(0, 1, 0)
//        velocity ← normalize(vector(1, 1.8, 0)) * 11.25
//        p ← projectile(start, velocity)
//        gravity ← vector(0, -0.1, 0)
//        wind ← vector(-0.01, 0, 0)
//        e ← environment(gravity, wind)
//        c ← canvas(900, 550)
        World world = new World();
        Tuple start = Tuple.point(0, 1, 0);
        Tuple velocity = Tuple.mul(Tuple.normalize(Tuple.vector(1, 4.8, 0)), 11.45f);
        Projectile p = new Projectile(start, velocity);
        Tuple gravity = Tuple.vector(0, -0.4, 0);
        Tuple wind = Tuple.vector(-0.01, 0, 0);
        Environment e = new Environment(gravity, wind);
        Canvas c = new Canvas(1000, 550);
        //Projectile p = new Projectile(Tuple.point(0, 1, 0), Tuple.mul(Tuple.normalize(Tuple.vector(1, 1, 0)), 40));
        // Environment e = new Environment(Tuple.vector(0, -0.1, 0), Tuple.vector(-0.01, 0, 0));

        int ticks = 0;
        while (p.position.y >= 0) {
            p = world.tick(e, p);
            System.out.println(p);
            Tuple pos = p.position;
            int x = (int) Math.round(pos.x);
            int y = (int) Math.round(c.height - pos.y);
            if (x > 0 && y > 0 && x < c.width && y < c.height) {
                c.writePixel(x,  y, new Color(255, 0, 0));
            }
            ticks++;
        }

        System.out.println("Ticks = " + ticks);
        String ppm = c.toPpm();
        Random random = new Random();

        writeFile(Paths.get("C:\\work\\vray\\ppms\\file_" + random.nextInt(10000) + ".ppm"), ppm);
        //System.out.println(ppm);
    }

    private static void writeFile(Path file, String contents) {
        try (PrintWriter out = new PrintWriter(file.toString())) {
            out.println(contents);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void test1() {
        World world = new World();
        Projectile p = new Projectile(Tuple.point(0, 1, 0), Tuple.mul(Tuple.normalize(Tuple.vector(1, 1, 0)), 40));
        Environment e = new Environment(Tuple.vector(0, -0.1, 0), Tuple.vector(-0.01, 0, 0));

        int ticks = 0;
        while (p.position.y >= 0) {
            p = world.tick(e, p);
            System.out.println(p);
            ticks++;
        }

        System.out.println("Ticks = " + ticks);
    }
}
