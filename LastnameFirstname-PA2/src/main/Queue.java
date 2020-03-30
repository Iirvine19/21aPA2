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
		if(size > arr.length) {
			size++;
			T[] tmp = arr;
			arr = (T[])new Object[tmp.length * 2];
			for(int i = 0; i < tmp.length; i++) {
				arr[i] = tmp[i];
			}
		}
		decreaseTail();
		arr[tail] = data;
		
	}

	private void decreaseTail() {
		if (tail == 0) {
			tail = arr.length;
		}else {
			tail--;
		}
	}
	
	private void decreaseFront() {
		if (front == 0) {
			front = arr.length;
		}else {
			front--;
		}
	}

	public T dequeue() {
		T temp = arr[front];
		arr[front] = null;
		decreaseFront();
		size--;
		return temp;
	}
	
	public T front() {
		return arr[front];
	}
	
	public int size() {
		return size;
	}
}
