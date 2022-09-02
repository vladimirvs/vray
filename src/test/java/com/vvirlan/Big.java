package com.vvirlan;

import java.math.BigDecimal;

public class Big {
    public static void main(String[] args) {
        BigDecimal a = BigDecimal.valueOf(100);
        System.out.println("Scale of a is "+a.scale());

        BigDecimal b = BigDecimal.valueOf(100.0);
        System.out.println("Scale of b is "+b.scale());

        BigDecimal c = BigDecimal.valueOf(-100.0);
        System.out.println("Scale of c is "+c.scale());
    }
}
