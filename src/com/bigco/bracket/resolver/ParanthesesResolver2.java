package com.bigco.bracket.resolver;

import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

import com.bigco.bracket.resolver.constants.BracketResolverConstants;

/**
 * @author divviswa
 *
 */

public class ParanthesesResolver2 {
	
	/**
	 * @param args
	 * 
	 * Method to run through the program
	 */
	public static void main(String[] args) {
		ParanthesesResolver2 resolver = new ParanthesesResolver2();
		Scanner scanner = new Scanner(System.in);
		int mode;
		try {
			while(true) {
				System.out.println("Press \n 1 for Simple Mode \n 2 for Complexmode \n 0 To quit");
				mode = scanner.nextInt();
				
				if(mode == 0)
					break;
				
				scanner.nextLine();//To consume the new line character entered with the program mode 
				
				System.out.println("Enter the bracket description");
				String s = scanner.nextLine();
				
				if (mode == 1) {
					System.out.println(resolver.isValidSequence(s) ? BracketResolverConstants.VALID_SEQUENCE 
							: BracketResolverConstants.IN_VALID_SEQUENCE);
				} else if (mode == 2) {
					String fixedSequence = resolver.fixTheDescription(s);
					if(!areThereEmptyContainers(fixedSequence)) {
						resolver.printIfFixed(s, fixedSequence);				
					} else {
						System.out.println(s + " is unfixable");
					}
				}
				
			}
		} finally {
			scanner.close();
		}
	}
	
	/**
	 * @param str
	 * @return
	 * 
	 * Method that will fix any mismatch in open and closing parantheses
	 */
	public String fixTheDescription(String str) {
		if(str == null) {
			return "";
		}
		//To fix the preceding closing parantheses, the original string has been toggled and reversed 
		//and fixed again for opening parantheses and toggled reversed 
		return fixExcessiveCloseBrackets(fixExcessiveOpenBrackets(str));
	}
	
	/**
	 * @param original
	 * @param modified
	 * 
	 * Prints the modified version of the description
	 */
	private void printIfFixed(String original, String modified) {
		if(!original.equals(modified)) {
			System.out.println("Fixed version of " + original + "\t"+modified);
		} else {
			System.out.println(original + " is a valid sequence");
		}
	}
	
	/**
	 * @param str
	 * @return
	 * 
	 * Method to fix preceding opening parantheses
	 */
	private static String fixExcessiveOpenBrackets(String str) {
		Stack<Character> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i< str.length();i++) {
			if(BracketResolverConstants.OPEN_BRACKET_MAP.containsKey(str.charAt(i))) {
				stack.push(BracketResolverConstants.OPEN_BRACKET_MAP.get(str.charAt(i)));
			} else if(BracketResolverConstants.CLOSE_BRACKET_MAP.containsKey(str.charAt(i))) {
				try {
					char stackTop = stack.pop();
					while(stackTop != str.charAt(i)) {
						sb.append(stackTop);
						stackTop = stack.pop();
					}
					sb.append(stackTop);
				} catch(EmptyStackException e) {
					sb.append(str.charAt(i));
				}
				continue;
			}
			sb.append(str.charAt(i));
		}
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		return sb.toString();
	}
	
	
	private static String fixExcessiveCloseBrackets(String str) {
		Stack<Character> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		for(int i = str.length()-1; i>0;i--) {
			if(BracketResolverConstants.CLOSE_BRACKET_MAP.containsKey(str.charAt(i))) {
				stack.push(BracketResolverConstants.CLOSE_BRACKET_MAP.get(str.charAt(i)));
			} else if(BracketResolverConstants.OPEN_BRACKET_MAP.containsKey(str.charAt(i))) {
				try {
					char stackTop = stack.pop();
					while(stackTop != str.charAt(i)) {
						sb.append(stackTop);
						stackTop = stack.pop();
					}
					sb.append(stackTop);
				} catch(EmptyStackException e) {
					sb.append(str.charAt(i));
				}
				continue;
			}
			sb.append(str.charAt(i));
		}
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		return sb.reverse().toString();
	}
	
	private static String reverseDesc(String str) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i< str.length();i++) {
			if(BracketResolverConstants.OPEN_BRACKET_MAP.containsKey(str.charAt(i))) {
				sb.append(BracketResolverConstants.OPEN_BRACKET_MAP.get(str.charAt(i)));
			} else if(BracketResolverConstants.CLOSE_BRACKET_MAP.containsKey(str.charAt(i))) {
				sb.append(BracketResolverConstants.CLOSE_BRACKET_MAP.get(str.charAt(i)));
			} else {
				sb.append(str.charAt(i));
			}
		}
		return sb.reverse().toString();
	}
	
	/**
	 * @param sequence
	 * @return
	 * 
	 * Checks if the sequence is valid
	 */
	public boolean isValidSequence(String sequence) {
		if (sequence != null) {
			Stack<Character> charStack = new Stack<>();
			for (int i = 0; i < sequence.length(); i++) {
				char c = sequence.charAt(i);
				if (BracketResolverConstants.OPEN_BRACKET_MAP.containsKey(c) 
						|| BracketResolverConstants.CLOSE_BRACKET_MAP.containsKey(c)) {
					if (!BracketResolverConstants.CLOSE_BRACKET_MAP.containsKey(c)) {
						charStack.push(c);
					} else {
						try {
							if (charStack.pop() != BracketResolverConstants.CLOSE_BRACKET_MAP.get(c)) {
								return false;
							}
						} catch (EmptyStackException ece) {
							return false;
						}
					}
				}
			}
			return (!areThereEmptyContainers(sequence) && charStack.isEmpty());
		} else {
			return false;
		}
	}
	
	private static boolean areThereEmptyContainers(String desc) {
    	for(int i = 0;i<desc.length()-1;i++) {
    		if(BracketResolverConstants.OPEN_BRACKET_MAP.containsKey(desc.charAt(i))) {
    			if(desc.charAt(i+1) == BracketResolverConstants.OPEN_BRACKET_MAP.get(desc.charAt(i))) {
    				return true;
    			}
    		}
    	}
    	return false;
    }

}
