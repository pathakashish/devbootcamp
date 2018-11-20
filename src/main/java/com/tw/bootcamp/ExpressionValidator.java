package com.tw.bootcamp;

import java.util.Stack;

public class ExpressionValidator {

    public boolean validate(String expr) {
        if(null == expr) return false;
        Stack<Character> stack = new Stack<>();
        for(Character ch : expr.toCharArray()) {
            if('(' != ch && ')' != ch) {
                continue;
            }
            if('(' == ch) {
                stack.push(ch);
            } else {
                if(stack.isEmpty() || stack.peek() == ')') {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }
        return stack.isEmpty();
    }
}
