package com.bigco.bracket.resolver.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * @author divviswa
 *
 */
public class BracketResolverConstants {

	public final static Map<Character, Character> CLOSE_BRACKET_MAP = new HashMap<>();
	public final static Map<Character, Character> OPEN_BRACKET_MAP = new HashMap<>();
	static {
		CLOSE_BRACKET_MAP.put(')','(');
		CLOSE_BRACKET_MAP.put('}','{');
		CLOSE_BRACKET_MAP.put(']','[');
		OPEN_BRACKET_MAP.put('{', '}');
		OPEN_BRACKET_MAP.put('[', ']');
		OPEN_BRACKET_MAP.put('(', ')');
	}
	
	public final static String VALID_SEQUENCE = "OKAY";
	public final static String IN_VALID_SEQUENCE = "BAD";
}
