package de.emeraldmc.playerinfo.tasks;

public class TaskHistory {
    private double[] values;
    private int head = 0;
    private int tail = 0;
    private int size;
    private int currSize;

    public TaskHistory(int size, double firstValue) {
        values = new double[size];
        values[0] = firstValue;
        this.size = size;
        currSize = 1;
    }
    public TaskHistory(int size) {
        values = new double[size];
        this.size = size;
        currSize = 0;
    }

    public double[] getValues() {
        return values;
    }
    public double average() {
        double allValues = 0D;
        for (double v : values) {
            allValues += v;
        }
        return allValues / currSize;
    }
    public double round(double v) {
        v = v*1000;
        v = Math.round(v);
        v = v/1000;
        return v;
    }
    /**
     * Removes the last entry (fifo = first in first out)
     */
    private void removeLast() {
        head = (head + 1) % size;
    }

    /**
     * Puts a new Object at the end of the queue
     */
    public void put(double value) {
        if (currSize < size) {
            currSize++;
        }
        boolean overflow = (tail + 1) % size == head;
        if (overflow) removeLast();
        values[tail] = value;
        tail = (tail + 1) % size;
    }

}
