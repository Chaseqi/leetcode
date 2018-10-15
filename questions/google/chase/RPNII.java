package questions.leetcode.questions.google.chase;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

// Reversed Polish Notation, but the operator is in front of the numbers
public class RPNII {
	public static int solve(List<String> tokens) {
		Stack<String> stack = new Stack<>();
		
		for (String token : tokens) {
			if (isOperator(token)) {
				stack.push(token);
				continue;
			}
			
			int result = Integer.valueOf(token);
			
			while (!stack.isEmpty() && !isOperator(stack.peek())) {
				result = calculate(Integer.valueOf(stack.pop()), result, stack.pop());
			}
			
			stack.push(result + "");
		}
		
		return Integer.valueOf(stack.pop());
	}
	
	private static int calculate(int num1, int num2, String operator) {
		if (operator.equals("+")) {
			return num1 + num2;
		}
		
		if (operator.equals("-")) {
			return num1 - num2;
		}
		
		if (operator.equals("*")) {
			return num1 * num2;
		}
		
		return num1 / num2;
	}
	
	private static boolean isOperator(String token) {
		return token.equals("+") || token.equals("*") || token.equals("-") || token.equals("/");
	}
	
	public static void main(String[] args) {
		List<String> tokens = Arrays.asList("/", "10", "+", "2", "3");
		System.out.println(solve(tokens));
		
		tokens = Arrays.asList("*", "6", "+", "2", "3");
		System.out.println(solve(tokens));
		
		tokens = Arrays.asList("*", "+", "2", "2", "3");
		System.out.println(solve(tokens));
	}
}
