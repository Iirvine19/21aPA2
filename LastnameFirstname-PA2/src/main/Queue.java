package main;

import java.lang.reflect.Array;

public class Queue<T> {
	private int front;
	private int tail;
	int size = 0;
	private T[] arr;
	public Queue() {
		arr = (T[])new Object[50];
	}
	
	public void enqueue(T data) {
		if(size >= arr.length) {
			T[] tmp = arr;
			arr = (T[])new Object[tmp.length * 2];
			for(int i = 0; i < tmp.length; i++) {
				arr[i] = tmp[i];
			}
		}
		arr[tail] = data;
		increaseTail();
		size++;
	}

	private void increaseTail() {
		if (tail == arr.length - 1) {
			tail = 0;
		}else {
			tail++;
		}
	}
	
	private void increaseFront() {
		if (front == arr.length - 1) {
			front = 0;
		}else {
			front++;
		}
	}

	public T dequeue() {
		T temp = arr[front];
		arr[front] = null;
		increaseFront();
		size--;
		return temp;
	}
	
	public T front() {
		return arr[front];
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
}
