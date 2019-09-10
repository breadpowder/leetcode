package source.stack;

import java.util.List;
import java.util.Set;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

//150 Evaluate Reverse Polish Notation

/*

Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Note:

Division between two integers should truncate toward zero.
The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.
Example 1:

Input: ["2", "1", "+", "3", "*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9
Example 2:

Input: ["4", "13", "5", "/", "+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6
Example 3:

Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
Output: 22
Explanation:
  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22
 */

//take away, initial a static set, use arrays.asList
//swithc works for string string as well

public class EvaluateReversePolishNotation {


    Set<String> operator = new HashSet<>(Arrays.asList("+","-","*","/"));

    public int evalRPN(String[] tokens) {

        List<Integer> operands = new LinkedList<Integer>();

        for(String token: tokens){

            if(operator.contains(token)){

                int result = calculate(operands, token);

                operands.add(0, result);

            }

            else{
                operands.add(0, Integer.valueOf(token));
            }

        }

        return operands.remove(0);
    }



    public int calculate(List<Integer> operands, String operator){

        int right = operands.remove(0);

        int left = operands.remove(0);

        switch(operator){

            case "+": return left + right;

            case "-": return left - right;

            case "*": return left * right;

            case "/": return left/right;

        }

        return Integer.MIN_VALUE;
    }
}
