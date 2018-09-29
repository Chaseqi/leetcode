package questions.leetcode.questions.google.chase;

import java.util.ArrayList;
import java.util.List;

// Design a data structure to deliver messages from CEO to all the Employees
// Expose a method to return the time it takes to deliver the message to all the employees
public class MessageDeliverySystem {

	public MessageDeliverySystem() {
	}
	
	// returns the time it takes to deliver the message to all the reports 
	public double deliverMessage(Employee source) {
		if (source.reports.isEmpty()) {
			return 0;
		}
		
		double duration = 0;
		for (Employee report : source.reports) {
			duration += report.possibility * (report.duration + deliverMessage(report));
		}
		
		return duration;
	}
	
	// Node of the K-ary tree
	public static class Employee {
		List<Employee> reports;
		double possibility;
		int duration; // the time it takes to deliver message from the manager to the employee
		
		public Employee(int duration, double possibility) {
			this.reports = new ArrayList<>();
			this.duration = duration;
			this.possibility = possibility;
		}
	}
	
	public static void main(String[] args) {
		Employee ceo = new Employee(0, 1);
		Employee e1 = new Employee(10, 0.8);
		e1.reports.add(new Employee(4, 0.7));
		e1.reports.add(new Employee(6, 0.3));
		ceo.reports.add(new Employee(5, 0.2));
		ceo.reports.add(e1);
		MessageDeliverySystem mds = new MessageDeliverySystem();
		
		System.out.println(mds.deliverMessage(ceo));
	}
}


