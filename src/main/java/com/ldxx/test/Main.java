package com.ldxx.test;

import java.util.Random;

//消费者
class Consumer extends Thread {
    private Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int data;
        while (true) {
            data = buffer.consume();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        final Buffer buffer = new Buffer();

    }
}

//生产者
class Producer extends Thread {
    private Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        Random rand = new Random();
        while (true) {
            int n = rand.nextInt();
            buffer.produce(n);
        }
    }
}

class Buffer {
    public int data;
    public boolean empty;

    public Buffer() {
        this.empty = true;
    }

    public synchronized void produce(int newData) {
        while (!this.empty) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.data = newData;
        this.empty = false;
        this.notify();
        System.out.println("Produced:" + newData);
    }

    public synchronized int consume() {
        while (this.empty) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.empty = true;
        this.notify();
        System.out.println("Consumed:" + data);
        return data;
    }
}