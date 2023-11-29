package dev.login.jwtlogin.util;

import java.util.List;

public class Counter {
    public static int getAll(List<Object[]> list) {
        int total = 0;

        for (Object[] employee : list) {
            total += (int) employee[1];
        } return total;
    }
}
