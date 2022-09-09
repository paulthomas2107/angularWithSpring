package io.getarrays.server;

import java.nio.file.Paths;

public class Scratch {

    public static void main(String[] args) {

        System.out.println(System.getProperty("user.home"));
        String fileName = "1.png";

        System.out.println(Paths.get(System.getProperty("user.home") + "/images/" + fileName));
    }
}
