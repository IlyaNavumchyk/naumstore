package com.naumstore;

public class Test {

    public static void main(String[] args) {

        String regex = "^[A-Za-z][\\w.]+@[A-Za-z][A-Za-z0-9]+\\.[A-Za-z]{2,}$";

        String input = "naum.st.123@gmail.com";

        System.out.println(input.matches(regex));
    }
}
