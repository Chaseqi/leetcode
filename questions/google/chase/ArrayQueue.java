package questions.leetcode.questions.google.chase;

import java.util.Queue;

// Use an array to implement Queue
public class ArrayQueue {
	
	int[] queue;
	int head;
	int tail;
	
	public ArrayQueue(int n) {
		this.queue = new int[n];
		this.head = -1;
		this.tail = -1;
	}
	
	public void offer(int num) throws Exception {
		if (isFull()) {
			throw new Exception();
		}
		
		if (isEmpty()) {
			head = 0;
			tail = 0;
		} else {
			tail = (tail + 1) % queue.length;
		}
		
		queue[tail] = num;
	}
	
	public int poll() throws Exception {
		if (isEmpty()) {
			throw new Exception();
		}
		
		int result = queue[head];
		
		if (head == tail) {
			head = -1;
			tail = -1;
		} else {
			head = (head + 1) % queue.length;
		}
		
		return result;
	}
	
	public int peek() throws Exception {
		return queue[head];
	}
	
	private boolean isEmpty() {
		return head == -1 && tail == -1;
	}
	
	private boolean isFull() {
		return (tail + 1) % queue.length == head;
	}
	
	public static void main(final String[] args) throws Exception {
		ArrayQueue arrayQueue = new ArrayQueue(3);
		arrayQueue.offer(1);
		arrayQueue.offer(2);
		System.out.println(arrayQueue.poll());
		arrayQueue.offer(3);
		arrayQueue.offer(4);
		System.out.println(arrayQueue.poll());
		System.out.println(arrayQueue.poll());
		System.out.println(arrayQueue.poll());
	}
}
