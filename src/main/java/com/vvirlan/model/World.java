package com.vvirlan.model;

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

        World world = new World();

//        # projectile starts one unit above the origin.
//# velocity is normalized to 1 unit/tick.
//                p ← projectile(point(0, 1, 0), normalize(vector(1, 1, 0)))
//# gravity -0.1 unit/tick, and wind is -0.01 unit/tick.
//                e ← environment(vector(0, -0.1, 0), vector(-0.01, 0, 0))
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
