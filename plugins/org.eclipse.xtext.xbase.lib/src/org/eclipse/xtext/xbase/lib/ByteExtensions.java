/*******************************************************************************
 * Copyright (c) 2012 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xbase.lib;

/**
 * This is an extension library for {@link Byte} numbers.
 * 
 * @author Jan Koehnlein - Code generator
 */
public class ByteExtensions {
	// BEGIN generated code
	/**
	 * The unary <code>minus</code> operator. This is the equivalent to the Java's <code>-</code> function.
	 * 
	 * @param a  a byte.
	 * @return   <code>-a</code>
	 */
	public static int operator_minus(byte a) {
		return -a;
	}
	
	/**
	 * The binary <code>plus</code> operator. This is the equivalent to the Java <code>+</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a double.
	 * @return   <code>a+b</code>
	 */
	public static double operator_plus(byte a, double b) {
		return a + b;
	}
	
	/**
	 * The binary <code>minus</code> operator. This is the equivalent to the Java <code>-</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a double.
	 * @return   <code>a-b</code>
	 */
	public static double operator_minus(byte a, double b) {
		return a - b;
	}
	
	/**
	 * The binary <code>multiply</code> operator. This is the equivalent to the Java <code>*</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a double.
	 * @return   <code>a*b</code>
	 */
	public static double operator_multiply(byte a, double b) {
		return a * b;
	}
	
	/**
	 * The binary <code>divide</code> operator. This is the equivalent to the Java <code>/</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a double.
	 * @return   <code>a/b</code>
	 */
	public static double operator_divide(byte a, double b) {
		return a / b;
	}
	
	/**
	 * The binary <code>modulo</code> operator. This is the equivalent to the Java <code>%</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a double.
	 * @return   <code>a%b</code>
	 */
	public static double operator_modulo(byte a, double b) {
		return a % b;
	}
	
	/**
	 * The binary <code>lessThan</code> operator. This is the equivalent to the Java <code>&lt;</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a double.
	 * @return   <code>a&lt;b</code>
	 */
	public static boolean operator_lessThan(byte a, double b) {
		return a < b;
	}
	
	/**
	 * The binary <code>lessEqualsThan</code> operator. This is the equivalent to the Java <code>&lt;=</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a double.
	 * @return   <code>a&lt;=b</code>
	 */
	public static boolean operator_lessEqualsThan(byte a, double b) {
		return a <= b;
	}
	
	/**
	 * The binary <code>greaterThan</code> operator. This is the equivalent to the Java <code>&gt;</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a double.
	 * @return   <code>a&gt;b</code>
	 */
	public static boolean operator_greaterThan(byte a, double b) {
		return a > b;
	}
	
	/**
	 * The binary <code>greaterEqualsThan</code> operator. This is the equivalent to the Java <code>&gt;=</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a double.
	 * @return   <code>a&gt;=b</code>
	 */
	public static boolean operator_greaterEqualsThan(byte a, double b) {
		return a >= b;
	}
	
	/**
	 * The binary <code>equals</code> operator. This is the equivalent to the Java <code>==</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a double.
	 * @return   <code>a==b</code>
	 */
	public static boolean operator_equals(byte a, double b) {
		return a == b;
	}
	
	/**
	 * The binary <code>notEquals</code> operator. This is the equivalent to the Java <code>!=</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a double.
	 * @return   <code>a!=b</code>
	 */
	public static boolean operator_notEquals(byte a, double b) {
		return a != b;
	}
	
	/**
	 * The binary <code>power</code> operator. This is the equivalent to the Java's <code>Math.pow()</code> function.
	 * 
	 * @param a  a byte.
	 * @param b  a double.
	 * @return   <code>Math.pow(a, b)</code>
	 */
	public static double operator_power(byte a, double b) {
		return Math.pow(a, b);
	}
	
	/**
	 * The binary <code>plus</code> operator. This is the equivalent to the Java <code>+</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a float.
	 * @return   <code>a+b</code>
	 */
	public static float operator_plus(byte a, float b) {
		return a + b;
	}
	
	/**
	 * The binary <code>minus</code> operator. This is the equivalent to the Java <code>-</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a float.
	 * @return   <code>a-b</code>
	 */
	public static float operator_minus(byte a, float b) {
		return a - b;
	}
	
	/**
	 * The binary <code>multiply</code> operator. This is the equivalent to the Java <code>*</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a float.
	 * @return   <code>a*b</code>
	 */
	public static float operator_multiply(byte a, float b) {
		return a * b;
	}
	
	/**
	 * The binary <code>divide</code> operator. This is the equivalent to the Java <code>/</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a float.
	 * @return   <code>a/b</code>
	 */
	public static float operator_divide(byte a, float b) {
		return a / b;
	}
	
	/**
	 * The binary <code>modulo</code> operator. This is the equivalent to the Java <code>%</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a float.
	 * @return   <code>a%b</code>
	 */
	public static float operator_modulo(byte a, float b) {
		return a % b;
	}
	
	/**
	 * The binary <code>lessThan</code> operator. This is the equivalent to the Java <code>&lt;</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a float.
	 * @return   <code>a&lt;b</code>
	 */
	public static boolean operator_lessThan(byte a, float b) {
		return a < b;
	}
	
	/**
	 * The binary <code>lessEqualsThan</code> operator. This is the equivalent to the Java <code>&lt;=</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a float.
	 * @return   <code>a&lt;=b</code>
	 */
	public static boolean operator_lessEqualsThan(byte a, float b) {
		return a <= b;
	}
	
	/**
	 * The binary <code>greaterThan</code> operator. This is the equivalent to the Java <code>&gt;</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a float.
	 * @return   <code>a&gt;b</code>
	 */
	public static boolean operator_greaterThan(byte a, float b) {
		return a > b;
	}
	
	/**
	 * The binary <code>greaterEqualsThan</code> operator. This is the equivalent to the Java <code>&gt;=</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a float.
	 * @return   <code>a&gt;=b</code>
	 */
	public static boolean operator_greaterEqualsThan(byte a, float b) {
		return a >= b;
	}
	
	/**
	 * The binary <code>equals</code> operator. This is the equivalent to the Java <code>==</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a float.
	 * @return   <code>a==b</code>
	 */
	public static boolean operator_equals(byte a, float b) {
		return a == b;
	}
	
	/**
	 * The binary <code>notEquals</code> operator. This is the equivalent to the Java <code>!=</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a float.
	 * @return   <code>a!=b</code>
	 */
	public static boolean operator_notEquals(byte a, float b) {
		return a != b;
	}
	
	/**
	 * The binary <code>power</code> operator. This is the equivalent to the Java's <code>Math.pow()</code> function.
	 * 
	 * @param a  a byte.
	 * @param b  a float.
	 * @return   <code>Math.pow(a, b)</code>
	 */
	public static double operator_power(byte a, float b) {
		return Math.pow(a, b);
	}
	
	/**
	 * The binary <code>plus</code> operator. This is the equivalent to the Java <code>+</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a long.
	 * @return   <code>a+b</code>
	 */
	public static long operator_plus(byte a, long b) {
		return a + b;
	}
	
	/**
	 * The binary <code>minus</code> operator. This is the equivalent to the Java <code>-</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a long.
	 * @return   <code>a-b</code>
	 */
	public static long operator_minus(byte a, long b) {
		return a - b;
	}
	
	/**
	 * The binary <code>multiply</code> operator. This is the equivalent to the Java <code>*</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a long.
	 * @return   <code>a*b</code>
	 */
	public static long operator_multiply(byte a, long b) {
		return a * b;
	}
	
	/**
	 * The binary <code>divide</code> operator. This is the equivalent to the Java <code>/</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a long.
	 * @return   <code>a/b</code>
	 */
	public static long operator_divide(byte a, long b) {
		return a / b;
	}
	
	/**
	 * The binary <code>modulo</code> operator. This is the equivalent to the Java <code>%</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a long.
	 * @return   <code>a%b</code>
	 */
	public static long operator_modulo(byte a, long b) {
		return a % b;
	}
	
	/**
	 * The binary <code>lessThan</code> operator. This is the equivalent to the Java <code>&lt;</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a long.
	 * @return   <code>a&lt;b</code>
	 */
	public static boolean operator_lessThan(byte a, long b) {
		return a < b;
	}
	
	/**
	 * The binary <code>lessEqualsThan</code> operator. This is the equivalent to the Java <code>&lt;=</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a long.
	 * @return   <code>a&lt;=b</code>
	 */
	public static boolean operator_lessEqualsThan(byte a, long b) {
		return a <= b;
	}
	
	/**
	 * The binary <code>greaterThan</code> operator. This is the equivalent to the Java <code>&gt;</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a long.
	 * @return   <code>a&gt;b</code>
	 */
	public static boolean operator_greaterThan(byte a, long b) {
		return a > b;
	}
	
	/**
	 * The binary <code>greaterEqualsThan</code> operator. This is the equivalent to the Java <code>&gt;=</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a long.
	 * @return   <code>a&gt;=b</code>
	 */
	public static boolean operator_greaterEqualsThan(byte a, long b) {
		return a >= b;
	}
	
	/**
	 * The binary <code>equals</code> operator. This is the equivalent to the Java <code>==</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a long.
	 * @return   <code>a==b</code>
	 */
	public static boolean operator_equals(byte a, long b) {
		return a == b;
	}
	
	/**
	 * The binary <code>notEquals</code> operator. This is the equivalent to the Java <code>!=</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a long.
	 * @return   <code>a!=b</code>
	 */
	public static boolean operator_notEquals(byte a, long b) {
		return a != b;
	}
	
	/**
	 * The binary <code>power</code> operator. This is the equivalent to the Java's <code>Math.pow()</code> function.
	 * 
	 * @param a  a byte.
	 * @param b  a long.
	 * @return   <code>Math.pow(a, b)</code>
	 */
	public static double operator_power(byte a, long b) {
		return Math.pow(a, b);
	}
	
	/**
	 * The binary <code>plus</code> operator. This is the equivalent to the Java <code>+</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  an integer.
	 * @return   <code>a+b</code>
	 */
	public static int operator_plus(byte a, int b) {
		return a + b;
	}
	
	/**
	 * The binary <code>minus</code> operator. This is the equivalent to the Java <code>-</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  an integer.
	 * @return   <code>a-b</code>
	 */
	public static int operator_minus(byte a, int b) {
		return a - b;
	}
	
	/**
	 * The binary <code>multiply</code> operator. This is the equivalent to the Java <code>*</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  an integer.
	 * @return   <code>a*b</code>
	 */
	public static int operator_multiply(byte a, int b) {
		return a * b;
	}
	
	/**
	 * The binary <code>divide</code> operator. This is the equivalent to the Java <code>/</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  an integer.
	 * @return   <code>a/b</code>
	 */
	public static int operator_divide(byte a, int b) {
		return a / b;
	}
	
	/**
	 * The binary <code>modulo</code> operator. This is the equivalent to the Java <code>%</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  an integer.
	 * @return   <code>a%b</code>
	 */
	public static int operator_modulo(byte a, int b) {
		return a % b;
	}
	
	/**
	 * The binary <code>lessThan</code> operator. This is the equivalent to the Java <code>&lt;</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  an integer.
	 * @return   <code>a&lt;b</code>
	 */
	public static boolean operator_lessThan(byte a, int b) {
		return a < b;
	}
	
	/**
	 * The binary <code>lessEqualsThan</code> operator. This is the equivalent to the Java <code>&lt;=</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  an integer.
	 * @return   <code>a&lt;=b</code>
	 */
	public static boolean operator_lessEqualsThan(byte a, int b) {
		return a <= b;
	}
	
	/**
	 * The binary <code>greaterThan</code> operator. This is the equivalent to the Java <code>&gt;</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  an integer.
	 * @return   <code>a&gt;b</code>
	 */
	public static boolean operator_greaterThan(byte a, int b) {
		return a > b;
	}
	
	/**
	 * The binary <code>greaterEqualsThan</code> operator. This is the equivalent to the Java <code>&gt;=</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  an integer.
	 * @return   <code>a&gt;=b</code>
	 */
	public static boolean operator_greaterEqualsThan(byte a, int b) {
		return a >= b;
	}
	
	/**
	 * The binary <code>equals</code> operator. This is the equivalent to the Java <code>==</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  an integer.
	 * @return   <code>a==b</code>
	 */
	public static boolean operator_equals(byte a, int b) {
		return a == b;
	}
	
	/**
	 * The binary <code>notEquals</code> operator. This is the equivalent to the Java <code>!=</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  an integer.
	 * @return   <code>a!=b</code>
	 */
	public static boolean operator_notEquals(byte a, int b) {
		return a != b;
	}
	
	/**
	 * The binary <code>power</code> operator. This is the equivalent to the Java's <code>Math.pow()</code> function.
	 * 
	 * @param a  a byte.
	 * @param b  an integer.
	 * @return   <code>Math.pow(a, b)</code>
	 */
	public static double operator_power(byte a, int b) {
		return Math.pow(a, b);
	}
	
	/**
	 * The binary <code>plus</code> operator. This is the equivalent to the Java <code>+</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a character.
	 * @return   <code>a+b</code>
	 */
	public static int operator_plus(byte a, char b) {
		return a + b;
	}
	
	/**
	 * The binary <code>minus</code> operator. This is the equivalent to the Java <code>-</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a character.
	 * @return   <code>a-b</code>
	 */
	public static int operator_minus(byte a, char b) {
		return a - b;
	}
	
	/**
	 * The binary <code>multiply</code> operator. This is the equivalent to the Java <code>*</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a character.
	 * @return   <code>a*b</code>
	 */
	public static int operator_multiply(byte a, char b) {
		return a * b;
	}
	
	/**
	 * The binary <code>divide</code> operator. This is the equivalent to the Java <code>/</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a character.
	 * @return   <code>a/b</code>
	 */
	public static int operator_divide(byte a, char b) {
		return a / b;
	}
	
	/**
	 * The binary <code>modulo</code> operator. This is the equivalent to the Java <code>%</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a character.
	 * @return   <code>a%b</code>
	 */
	public static int operator_modulo(byte a, char b) {
		return a % b;
	}
	
	/**
	 * The binary <code>lessThan</code> operator. This is the equivalent to the Java <code>&lt;</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a character.
	 * @return   <code>a&lt;b</code>
	 */
	public static boolean operator_lessThan(byte a, char b) {
		return a < b;
	}
	
	/**
	 * The binary <code>lessEqualsThan</code> operator. This is the equivalent to the Java <code>&lt;=</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a character.
	 * @return   <code>a&lt;=b</code>
	 */
	public static boolean operator_lessEqualsThan(byte a, char b) {
		return a <= b;
	}
	
	/**
	 * The binary <code>greaterThan</code> operator. This is the equivalent to the Java <code>&gt;</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a character.
	 * @return   <code>a&gt;b</code>
	 */
	public static boolean operator_greaterThan(byte a, char b) {
		return a > b;
	}
	
	/**
	 * The binary <code>greaterEqualsThan</code> operator. This is the equivalent to the Java <code>&gt;=</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a character.
	 * @return   <code>a&gt;=b</code>
	 */
	public static boolean operator_greaterEqualsThan(byte a, char b) {
		return a >= b;
	}
	
	/**
	 * The binary <code>equals</code> operator. This is the equivalent to the Java <code>==</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a character.
	 * @return   <code>a==b</code>
	 */
	public static boolean operator_equals(byte a, char b) {
		return a == b;
	}
	
	/**
	 * The binary <code>notEquals</code> operator. This is the equivalent to the Java <code>!=</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a character.
	 * @return   <code>a!=b</code>
	 */
	public static boolean operator_notEquals(byte a, char b) {
		return a != b;
	}
	
	/**
	 * The binary <code>power</code> operator. This is the equivalent to the Java's <code>Math.pow()</code> function.
	 * 
	 * @param a  a byte.
	 * @param b  a character.
	 * @return   <code>Math.pow(a, b)</code>
	 */
	public static double operator_power(byte a, char b) {
		return Math.pow(a, b);
	}
	
	/**
	 * The binary <code>plus</code> operator. This is the equivalent to the Java <code>+</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a short.
	 * @return   <code>a+b</code>
	 */
	public static int operator_plus(byte a, short b) {
		return a + b;
	}
	
	/**
	 * The binary <code>minus</code> operator. This is the equivalent to the Java <code>-</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a short.
	 * @return   <code>a-b</code>
	 */
	public static int operator_minus(byte a, short b) {
		return a - b;
	}
	
	/**
	 * The binary <code>multiply</code> operator. This is the equivalent to the Java <code>*</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a short.
	 * @return   <code>a*b</code>
	 */
	public static int operator_multiply(byte a, short b) {
		return a * b;
	}
	
	/**
	 * The binary <code>divide</code> operator. This is the equivalent to the Java <code>/</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a short.
	 * @return   <code>a/b</code>
	 */
	public static int operator_divide(byte a, short b) {
		return a / b;
	}
	
	/**
	 * The binary <code>modulo</code> operator. This is the equivalent to the Java <code>%</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a short.
	 * @return   <code>a%b</code>
	 */
	public static int operator_modulo(byte a, short b) {
		return a % b;
	}
	
	/**
	 * The binary <code>lessThan</code> operator. This is the equivalent to the Java <code>&lt;</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a short.
	 * @return   <code>a&lt;b</code>
	 */
	public static boolean operator_lessThan(byte a, short b) {
		return a < b;
	}
	
	/**
	 * The binary <code>lessEqualsThan</code> operator. This is the equivalent to the Java <code>&lt;=</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a short.
	 * @return   <code>a&lt;=b</code>
	 */
	public static boolean operator_lessEqualsThan(byte a, short b) {
		return a <= b;
	}
	
	/**
	 * The binary <code>greaterThan</code> operator. This is the equivalent to the Java <code>&gt;</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a short.
	 * @return   <code>a&gt;b</code>
	 */
	public static boolean operator_greaterThan(byte a, short b) {
		return a > b;
	}
	
	/**
	 * The binary <code>greaterEqualsThan</code> operator. This is the equivalent to the Java <code>&gt;=</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a short.
	 * @return   <code>a&gt;=b</code>
	 */
	public static boolean operator_greaterEqualsThan(byte a, short b) {
		return a >= b;
	}
	
	/**
	 * The binary <code>equals</code> operator. This is the equivalent to the Java <code>==</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a short.
	 * @return   <code>a==b</code>
	 */
	public static boolean operator_equals(byte a, short b) {
		return a == b;
	}
	
	/**
	 * The binary <code>notEquals</code> operator. This is the equivalent to the Java <code>!=</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a short.
	 * @return   <code>a!=b</code>
	 */
	public static boolean operator_notEquals(byte a, short b) {
		return a != b;
	}
	
	/**
	 * The binary <code>power</code> operator. This is the equivalent to the Java's <code>Math.pow()</code> function.
	 * 
	 * @param a  a byte.
	 * @param b  a short.
	 * @return   <code>Math.pow(a, b)</code>
	 */
	public static double operator_power(byte a, short b) {
		return Math.pow(a, b);
	}
	
	/**
	 * The binary <code>plus</code> operator. This is the equivalent to the Java <code>+</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a byte.
	 * @return   <code>a+b</code>
	 */
	public static int operator_plus(byte a, byte b) {
		return a + b;
	}
	
	/**
	 * The binary <code>minus</code> operator. This is the equivalent to the Java <code>-</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a byte.
	 * @return   <code>a-b</code>
	 */
	public static int operator_minus(byte a, byte b) {
		return a - b;
	}
	
	/**
	 * The binary <code>multiply</code> operator. This is the equivalent to the Java <code>*</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a byte.
	 * @return   <code>a*b</code>
	 */
	public static int operator_multiply(byte a, byte b) {
		return a * b;
	}
	
	/**
	 * The binary <code>divide</code> operator. This is the equivalent to the Java <code>/</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a byte.
	 * @return   <code>a/b</code>
	 */
	public static int operator_divide(byte a, byte b) {
		return a / b;
	}
	
	/**
	 * The binary <code>modulo</code> operator. This is the equivalent to the Java <code>%</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a byte.
	 * @return   <code>a%b</code>
	 */
	public static int operator_modulo(byte a, byte b) {
		return a % b;
	}
	
	/**
	 * The binary <code>lessThan</code> operator. This is the equivalent to the Java <code>&lt;</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a byte.
	 * @return   <code>a&lt;b</code>
	 */
	public static boolean operator_lessThan(byte a, byte b) {
		return a < b;
	}
	
	/**
	 * The binary <code>lessEqualsThan</code> operator. This is the equivalent to the Java <code>&lt;=</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a byte.
	 * @return   <code>a&lt;=b</code>
	 */
	public static boolean operator_lessEqualsThan(byte a, byte b) {
		return a <= b;
	}
	
	/**
	 * The binary <code>greaterThan</code> operator. This is the equivalent to the Java <code>&gt;</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a byte.
	 * @return   <code>a&gt;b</code>
	 */
	public static boolean operator_greaterThan(byte a, byte b) {
		return a > b;
	}
	
	/**
	 * The binary <code>greaterEqualsThan</code> operator. This is the equivalent to the Java <code>&gt;=</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a byte.
	 * @return   <code>a&gt;=b</code>
	 */
	public static boolean operator_greaterEqualsThan(byte a, byte b) {
		return a >= b;
	}
	
	/**
	 * The binary <code>equals</code> operator. This is the equivalent to the Java <code>==</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a byte.
	 * @return   <code>a==b</code>
	 */
	public static boolean operator_equals(byte a, byte b) {
		return a == b;
	}
	
	/**
	 * The binary <code>notEquals</code> operator. This is the equivalent to the Java <code>!=</code> operator.
	 * 
	 * @param a  a byte.
	 * @param b  a byte.
	 * @return   <code>a!=b</code>
	 */
	public static boolean operator_notEquals(byte a, byte b) {
		return a != b;
	}
	
	/**
	 * The binary <code>power</code> operator. This is the equivalent to the Java's <code>Math.pow()</code> function.
	 * 
	 * @param a  a byte.
	 * @param b  a byte.
	 * @return   <code>Math.pow(a, b)</code>
	 */
	public static double operator_power(byte a, byte b) {
		return Math.pow(a, b);
	}
	
	// END generated code



}
