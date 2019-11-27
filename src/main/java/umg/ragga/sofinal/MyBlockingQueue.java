/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umg.ragga.sofinal;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ragga
 */
public class MyBlockingQueue {
    private Queue<Integer> list;
    private int size;
    public MyBlockingQueue(int size) {
        this.list = new LinkedList<>();
        this.size = size;
    }
    public void put(int value) throws InterruptedException {
        synchronized (this) {
            while (list.size() >= size) {
                wait();
            }
            list.add(value);
            notify();
        }
    }
    public int take() throws InterruptedException {
        synchronized (this) {
            while (list.isEmpty()) {
                wait();
            }
            int value = list.poll();
            notify();
            return value;
        }
    }
    
    public int size(){
        return list.size();
    }
}

