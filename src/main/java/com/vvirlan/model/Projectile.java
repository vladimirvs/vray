package com.vvirlan.model;

public class Projectile {
     Tuple position;
     Tuple velocity;


     public Projectile(Tuple position, Tuple velocity) {
          this.position = position;
          this.velocity = velocity;
     }

     @Override
     public String toString() {
          return "Projectile{" +
                  "position=" + position +
                  ", velocity=" + velocity +
                  '}';
     }
}
