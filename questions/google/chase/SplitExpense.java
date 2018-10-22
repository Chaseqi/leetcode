package questions.leetcode.questions.google.chase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//有p个人(对应ID 1, 2, 3, …, p)，m个events，每个event对应{ID, price}，表示这个event由某人付款。
//e.g: p = 3 m = 4 events={{1, 10}, {2, 10}, {3, 5}, {1, 20}}. 表示第一个人付款$10, 第二个人付款$15， 第三个人付款$5。
//最终需要p个人平摊这些events的花费，以{ID1, ID2, price}的形式，输出一种平摊方式。
//e.g: 以上面这个例子，我们输出{{2, 1, 5}, {3, 1, 10}},表示第二个人付给第一个人$5，第三个人付给第一个人$10。
public class SplitExpense {
	
	/**
	 * .
	 * @param p the number of user
	 * @param events the events
	 * @return a way to split the expense
	 */
	public static List<Transaction> solve(int p, List<Event> events) {
		int totalExpense = 0;
		int[] balances = new int[p + 1];
		
		for (Event event : events) {
			totalExpense += event.expense;
		}
		
		Arrays.fill(balances, totalExpense / p);
		for (Event event : events) {
			balances[event.userId] -= event.expense;
		}
		
		List<Transaction> result = new ArrayList<>();
		dfs(balances, 1, result);
		return result;
	}
	
	private static void dfs(int[] balances, int index, List<Transaction> transactions) {
		while (index < balances.length && balances[index] == 0) {
			index++;
		}
		
		for (int i = index + 1; i < balances.length; i++) {
			if (balances[i] == 0 || balances[index] * balances[i] > 0) {
				continue;
			}
			
			balances[i] += balances[index];
			if (balances[index] > 0) {
				transactions.add(new Transaction(index, i, balances[index]));
			} else {
				transactions.add(new Transaction(i, index, -balances[index]));
			}
			
			dfs(balances, index + 1, transactions);
			break;
		}
	}
	
	private static class Transaction {
		int source;
		int destination;
		int amount;
		
		public Transaction(int source, int destination, int amount) {
			this.source = source;
			this.destination = destination;
			this.amount = amount;
		}
	}
	
	private static class Event {
		int userId;
		int expense;
		
		public Event(int userId, int expense) {
			this.userId = userId;
			this.expense = expense;
		}
	}
	
	public static void main(String[] args) {
		List<Event> events = new ArrayList<>();
		events.add(new Event(1, 10));
		events.add(new Event(2, 10));
		events.add(new Event(3, 5));
		events.add(new Event(1, 20));
		
		List<Transaction> result = solve(3, events);
		for (Transaction transaction : result) {
			System.out.println(transaction.source + " " + transaction.destination + " " + transaction.amount);
		}
	}
}
