package com.test;

public class lock {
    public static void main(String [] args) throws Exception {
     Runtime.getRuntime().exec("RunDll32.exe user32.dll,LockWorkStation");
    }
}
