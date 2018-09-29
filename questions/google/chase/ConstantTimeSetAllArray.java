package questions.leetcode.questions.google.chase;

public class ConstantTimeSetAllArray {
	
	Node[] nums;
	int timestamp;
	int setAllTimeStamp;
	int setAllValue;
	public ConstantTimeSetAllArray(int size) {
		nums = new Node[size];
		
		for (int i = 0; i < size; i++) {
			nums[i] = new Node(0, 0);
		}
		
		this.timestamp = 1;
		this.setAllTimeStamp = -1;
		this.setAllValue = 0;
	}
	
	public void set(int index, int newVal) {
		nums[index] = new Node(timestamp++, newVal);
	}
	
	public void setAll(int newVal) {
		setAllTimeStamp = timestamp++;
		setAllValue = newVal;
	}
	
	public int get(int index) {
		Node num = nums[index];
		
		if (num.timestamp > setAllTimeStamp) {
			return num.val;
		}
		
		return setAllValue;
	}
	
	private class Node {
		int timestamp;
		int val;
		
		public Node(int timestamp, int val) {
			this.timestamp = timestamp;
			this.val = val;
		}
	}
	
	public static void main(String[] args) {
		ConstantTimeSetAllArray arr = new ConstantTimeSetAllArray(3);
		arr.set(0, 1);
		arr.set(1, 2);
		arr.set(2, 3);
		
		for (int i = 0; i < 3; i++) {
			System.out.print(arr.get(i) + " ");
		}
		
		System.out.println();
		
		arr.setAll(4);
		
		for (int i = 0; i < 3; i++) {
			System.out.print(arr.get(i) + " ");
		}
		
		System.out.println();
		
		arr.set(0, 5);
		
		for (int i = 0; i < 3; i++) {
			System.out.print(arr.get(i) + " ");
		}
		
		System.out.println();
	}
}
