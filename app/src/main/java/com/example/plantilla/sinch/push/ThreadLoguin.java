package com.example.plantilla.sinch.push;

public class ThreadLoguin extends Thread {
    public ThreadLoguin(Runnable str) {
        super(str);
    }
    public void run() {
        for (int i = 0; i < 1000 ; i++)
            System.out.println(i + " " + getName());
        System.out.println("Termina thread " + getName());
    }
}
