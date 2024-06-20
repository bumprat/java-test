package com.example;

public class App {
    public static void main(String[] args) {
        // UTF-8 encoded string "Hello"
        String message = "Hello";
        byte[] utf8Bytes = message.getBytes(java.nio.charset.StandardCharsets.UTF_8);

        // Call the native method
        int result = ThirdPartyLibrary.INSTANCE.printArray(0, (short)0, utf8Bytes, utf8Bytes.length);

        System.out.println("Result: " + result);
    }
}
