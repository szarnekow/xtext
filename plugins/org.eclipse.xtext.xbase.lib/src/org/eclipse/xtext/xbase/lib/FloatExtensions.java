/*******************************************************************************
 * Copyright (c) 2012 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xbase.lib;

/**
 * This is an extension library for {@link Float} numbers.
 * 
 * @author Jan Koehnlein - Code generator
 */
public class FloatExtensions {
	// BEGIN generated code
	/**
	 * The unary <code>minus</code> operator. This is the equivalent to the Java's <code>-</code> function.
	 * 
	 * @param a  a float.
	 * @return   <code>-a</code>
	 */
	public static float operator_minus(float a) {
		return -a;
	}
	
	/**
	 * The binary <code>plus</code> operator. This is the equivalent to the Java <code>+</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a double.
	 * @return   <code>a+b</code>
	 */
	public static double operator_plus(float a, double b) {
		return a + b;
	}
	
	/**
	 * The binary <code>minus</code> operator. This is the equivalent to the Java <code>-</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a double.
	 * @return   <code>a-b</code>
	 */
	public static double operator_minus(float a, double b) {
		return a - b;
	}
	
	/**
	 * The binary <code>multiply</code> operator. This is the equivalent to the Java <code>*</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a double.
	 * @return   <code>a*b</code>
	 */
	public static double operator_multiply(float a, double b) {
		return a * b;
	}
	
	/**
	 * The binary <code>divide</code> operator. This is the equivalent to the Java <code>/</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a double.
	 * @return   <code>a/b</code>
	 */
	public static double operator_divide(float a, double b) {
		return a / b;
	}
	
	/**
	 * The binary <code>modulo</code> operator. This is the equivalent to the Java <code>%</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a double.
	 * @return   <code>a%b</code>
	 */
	public static double operator_modulo(float a, double b) {
		return a % b;
	}
	
	/**
	 * The binary <code>lessThan</code> operator. This is the equivalent to the Java <code>&lt;</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a double.
	 * @return   <code>a&lt;b</code>
	 */
	public static boolean operator_lessThan(float a, double b) {
		return a < b;
	}
	
	/**
	 * The binary <code>lessEqualsThan</code> operator. This is the equivalent to the Java <code>&lt;=</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a double.
	 * @return   <code>a&lt;=b</code>
	 */
	public static boolean operator_lessEqualsThan(float a, double b) {
		return a <= b;
	}
	
	/**
	 * The binary <code>greaterThan</code> operator. This is the equivalent to the Java <code>&gt;</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a double.
	 * @return   <code>a&gt;b</code>
	 */
	public static boolean operator_greaterThan(float a, double b) {
		return a > b;
	}
	
	/**
	 * The binary <code>greaterEqualsThan</code> operator. This is the equivalent to the Java <code>&gt;=</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a double.
	 * @return   <code>a&gt;=b</code>
	 */
	public static boolean operator_greaterEqualsThan(float a, double b) {
		return a >= b;
	}
	
	/**
	 * The binary <code>equals</code> operator. This is the equivalent to the Java <code>==</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a double.
	 * @return   <code>a==b</code>
	 */
	public static boolean operator_equals(float a, double b) {
		return a == b;
	}
	
	/**
	 * The binary <code>notEquals</code> operator. This is the equivalent to the Java <code>!=</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a double.
	 * @return   <code>a!=b</code>
	 */
	public static boolean operator_notEquals(float a, double b) {
		return a != b;
	}
	
	/**
	 * The binary <code>power</code> operator. This is the equivalent to the Java's <code>Math.pow()</code> function.
	 * 
	 * @param a  a float.
	 * @param b  a double.
	 * @return   <code>Math.pow(a, b)</code>
	 */
	public static double operator_power(float a, double b) {
		return Math.pow(a, b);
	}
	
	/**
	 * The binary <code>plus</code> operator. This is the equivalent to the Java <code>+</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a float.
	 * @return   <code>a+b</code>
	 */
	public static float operator_plus(float a, float b) {
		return a + b;
	}
	
	/**
	 * The binary <code>minus</code> operator. This is the equivalent to the Java <code>-</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a float.
	 * @return   <code>a-b</code>
	 */
	public static float operator_minus(float a, float b) {
		return a - b;
	}
	
	/**
	 * The binary <code>multiply</code> operator. This is the equivalent to the Java <code>*</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a float.
	 * @return   <code>a*b</code>
	 */
	public static float operator_multiply(float a, float b) {
		return a * b;
	}
	
	/**
	 * The binary <code>divide</code> operator. This is the equivalent to the Java <code>/</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a float.
	 * @return   <code>a/b</code>
	 */
	public static float operator_divide(float a, float b) {
		return a / b;
	}
	
	/**
	 * The binary <code>modulo</code> operator. This is the equivalent to the Java <code>%</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a float.
	 * @return   <code>a%b</code>
	 */
	public static float operator_modulo(float a, float b) {
		return a % b;
	}
	
	/**
	 * The binary <code>lessThan</code> operator. This is the equivalent to the Java <code>&lt;</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a float.
	 * @return   <code>a&lt;b</code>
	 */
	public static boolean operator_lessThan(float a, float b) {
		return a < b;
	}
	
	/**
	 * The binary <code>lessEqualsThan</code> operator. This is the equivalent to the Java <code>&lt;=</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a float.
	 * @return   <code>a&lt;=b</code>
	 */
	public static boolean operator_lessEqualsThan(float a, float b) {
		return a <= b;
	}
	
	/**
	 * The binary <code>greaterThan</code> operator. This is the equivalent to the Java <code>&gt;</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a float.
	 * @return   <code>a&gt;b</code>
	 */
	public static boolean operator_greaterThan(float a, float b) {
		return a > b;
	}
	
	/**
	 * The binary <code>greaterEqualsThan</code> operator. This is the equivalent to the Java <code>&gt;=</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a float.
	 * @return   <code>a&gt;=b</code>
	 */
	public static boolean operator_greaterEqualsThan(float a, float b) {
		return a >= b;
	}
	
	/**
	 * The binary <code>equals</code> operator. This is the equivalent to the Java <code>==</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a float.
	 * @return   <code>a==b</code>
	 */
	public static boolean operator_equals(float a, float b) {
		return a == b;
	}
	
	/**
	 * The binary <code>notEquals</code> operator. This is the equivalent to the Java <code>!=</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a float.
	 * @return   <code>a!=b</code>
	 */
	public static boolean operator_notEquals(float a, float b) {
		return a != b;
	}
	
	/**
	 * The binary <code>power</code> operator. This is the equivalent to the Java's <code>Math.pow()</code> function.
	 * 
	 * @param a  a float.
	 * @param b  a float.
	 * @return   <code>Math.pow(a, b)</code>
	 */
	public static double operator_power(float a, float b) {
		return Math.pow(a, b);
	}
	
	/**
	 * The binary <code>plus</code> operator. This is the equivalent to the Java <code>+</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a long.
	 * @return   <code>a+b</code>
	 */
	public static float operator_plus(float a, long b) {
		return a + b;
	}
	
	/**
	 * The binary <code>minus</code> operator. This is the equivalent to the Java <code>-</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a long.
	 * @return   <code>a-b</code>
	 */
	public static float operator_minus(float a, long b) {
		return a - b;
	}
	
	/**
	 * The binary <code>multiply</code> operator. This is the equivalent to the Java <code>*</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a long.
	 * @return   <code>a*b</code>
	 */
	public static float operator_multiply(float a, long b) {
		return a * b;
	}
	
	/**
	 * The binary <code>divide</code> operator. This is the equivalent to the Java <code>/</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a long.
	 * @return   <code>a/b</code>
	 */
	public static float operator_divide(float a, long b) {
		return a / b;
	}
	
	/**
	 * The binary <code>modulo</code> operator. This is the equivalent to the Java <code>%</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a long.
	 * @return   <code>a%b</code>
	 */
	public static float operator_modulo(float a, long b) {
		return a % b;
	}
	
	/**
	 * The binary <code>lessThan</code> operator. This is the equivalent to the Java <code>&lt;</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a long.
	 * @return   <code>a&lt;b</code>
	 */
	public static boolean operator_lessThan(float a, long b) {
		return a < b;
	}
	
	/**
	 * The binary <code>lessEqualsThan</code> operator. This is the equivalent to the Java <code>&lt;=</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a long.
	 * @return   <code>a&lt;=b</code>
	 */
	public static boolean operator_lessEqualsThan(float a, long b) {
		return a <= b;
	}
	
	/**
	 * The binary <code>greaterThan</code> operator. This is the equivalent to the Java <code>&gt;</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a long.
	 * @return   <code>a&gt;b</code>
	 */
	public static boolean operator_greaterThan(float a, long b) {
		return a > b;
	}
	
	/**
	 * The binary <code>greaterEqualsThan</code> operator. This is the equivalent to the Java <code>&gt;=</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a long.
	 * @return   <code>a&gt;=b</code>
	 */
	public static boolean operator_greaterEqualsThan(float a, long b) {
		return a >= b;
	}
	
	/**
	 * The binary <code>equals</code> operator. This is the equivalent to the Java <code>==</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a long.
	 * @return   <code>a==b</code>
	 */
	public static boolean operator_equals(float a, long b) {
		return a == b;
	}
	
	/**
	 * The binary <code>notEquals</code> operator. This is the equivalent to the Java <code>!=</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a long.
	 * @return   <code>a!=b</code>
	 */
	public static boolean operator_notEquals(float a, long b) {
		return a != b;
	}
	
	/**
	 * The binary <code>power</code> operator. This is the equivalent to the Java's <code>Math.pow()</code> function.
	 * 
	 * @param a  a float.
	 * @param b  a long.
	 * @return   <code>Math.pow(a, b)</code>
	 */
	public static double operator_power(float a, long b) {
		return Math.pow(a, b);
	}
	
	/**
	 * The binary <code>plus</code> operator. This is the equivalent to the Java <code>+</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  an integer.
	 * @return   <code>a+b</code>
	 */
	public static float operator_plus(float a, int b) {
		return a + b;
	}
	
	/**
	 * The binary <code>minus</code> operator. This is the equivalent to the Java <code>-</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  an integer.
	 * @return   <code>a-b</code>
	 */
	public static float operator_minus(float a, int b) {
		return a - b;
	}
	
	/**
	 * The binary <code>multiply</code> operator. This is the equivalent to the Java <code>*</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  an integer.
	 * @return   <code>a*b</code>
	 */
	public static float operator_multiply(float a, int b) {
		return a * b;
	}
	
	/**
	 * The binary <code>divide</code> operator. This is the equivalent to the Java <code>/</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  an integer.
	 * @return   <code>a/b</code>
	 */
	public static float operator_divide(float a, int b) {
		return a / b;
	}
	
	/**
	 * The binary <code>modulo</code> operator. This is the equivalent to the Java <code>%</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  an integer.
	 * @return   <code>a%b</code>
	 */
	public static float operator_modulo(float a, int b) {
		return a % b;
	}
	
	/**
	 * The binary <code>lessThan</code> operator. This is the equivalent to the Java <code>&lt;</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  an integer.
	 * @return   <code>a&lt;b</code>
	 */
	public static boolean operator_lessThan(float a, int b) {
		return a < b;
	}
	
	/**
	 * The binary <code>lessEqualsThan</code> operator. This is the equivalent to the Java <code>&lt;=</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  an integer.
	 * @return   <code>a&lt;=b</code>
	 */
	public static boolean operator_lessEqualsThan(float a, int b) {
		return a <= b;
	}
	
	/**
	 * The binary <code>greaterThan</code> operator. This is the equivalent to the Java <code>&gt;</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  an integer.
	 * @return   <code>a&gt;b</code>
	 */
	public static boolean operator_greaterThan(float a, int b) {
		return a > b;
	}
	
	/**
	 * The binary <code>greaterEqualsThan</code> operator. This is the equivalent to the Java <code>&gt;=</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  an integer.
	 * @return   <code>a&gt;=b</code>
	 */
	public static boolean operator_greaterEqualsThan(float a, int b) {
		return a >= b;
	}
	
	/**
	 * The binary <code>equals</code> operator. This is the equivalent to the Java <code>==</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  an integer.
	 * @return   <code>a==b</code>
	 */
	public static boolean operator_equals(float a, int b) {
		return a == b;
	}
	
	/**
	 * The binary <code>notEquals</code> operator. This is the equivalent to the Java <code>!=</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  an integer.
	 * @return   <code>a!=b</code>
	 */
	public static boolean operator_notEquals(float a, int b) {
		return a != b;
	}
	
	/**
	 * The binary <code>power</code> operator. This is the equivalent to the Java's <code>Math.pow()</code> function.
	 * 
	 * @param a  a float.
	 * @param b  an integer.
	 * @return   <code>Math.pow(a, b)</code>
	 */
	public static double operator_power(float a, int b) {
		return Math.pow(a, b);
	}
	
	/**
	 * The binary <code>plus</code> operator. This is the equivalent to the Java <code>+</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a character.
	 * @return   <code>a+b</code>
	 */
	public static float operator_plus(float a, char b) {
		return a + b;
	}
	
	/**
	 * The binary <code>minus</code> operator. This is the equivalent to the Java <code>-</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a character.
	 * @return   <code>a-b</code>
	 */
	public static float operator_minus(float a, char b) {
		return a - b;
	}
	
	/**
	 * The binary <code>multiply</code> operator. This is the equivalent to the Java <code>*</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a character.
	 * @return   <code>a*b</code>
	 */
	public static float operator_multiply(float a, char b) {
		return a * b;
	}
	
	/**
	 * The binary <code>divide</code> operator. This is the equivalent to the Java <code>/</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a character.
	 * @return   <code>a/b</code>
	 */
	public static float operator_divide(float a, char b) {
		return a / b;
	}
	
	/**
	 * The binary <code>modulo</code> operator. This is the equivalent to the Java <code>%</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a character.
	 * @return   <code>a%b</code>
	 */
	public static float operator_modulo(float a, char b) {
		return a % b;
	}
	
	/**
	 * The binary <code>lessThan</code> operator. This is the equivalent to the Java <code>&lt;</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a character.
	 * @return   <code>a&lt;b</code>
	 */
	public static boolean operator_lessThan(float a, char b) {
		return a < b;
	}
	
	/**
	 * The binary <code>lessEqualsThan</code> operator. This is the equivalent to the Java <code>&lt;=</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a character.
	 * @return   <code>a&lt;=b</code>
	 */
	public static boolean operator_lessEqualsThan(float a, char b) {
		return a <= b;
	}
	
	/**
	 * The binary <code>greaterThan</code> operator. This is the equivalent to the Java <code>&gt;</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a character.
	 * @return   <code>a&gt;b</code>
	 */
	public static boolean operator_greaterThan(float a, char b) {
		return a > b;
	}
	
	/**
	 * The binary <code>greaterEqualsThan</code> operator. This is the equivalent to the Java <code>&gt;=</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a character.
	 * @return   <code>a&gt;=b</code>
	 */
	public static boolean operator_greaterEqualsThan(float a, char b) {
		return a >= b;
	}
	
	/**
	 * The binary <code>equals</code> operator. This is the equivalent to the Java <code>==</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a character.
	 * @return   <code>a==b</code>
	 */
	public static boolean operator_equals(float a, char b) {
		return a == b;
	}
	
	/**
	 * The binary <code>notEquals</code> operator. This is the equivalent to the Java <code>!=</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a character.
	 * @return   <code>a!=b</code>
	 */
	public static boolean operator_notEquals(float a, char b) {
		return a != b;
	}
	
	/**
	 * The binary <code>power</code> operator. This is the equivalent to the Java's <code>Math.pow()</code> function.
	 * 
	 * @param a  a float.
	 * @param b  a character.
	 * @return   <code>Math.pow(a, b)</code>
	 */
	public static double operator_power(float a, char b) {
		return Math.pow(a, b);
	}
	
	/**
	 * The binary <code>plus</code> operator. This is the equivalent to the Java <code>+</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a short.
	 * @return   <code>a+b</code>
	 */
	public static float operator_plus(float a, short b) {
		return a + b;
	}
	
	/**
	 * The binary <code>minus</code> operator. This is the equivalent to the Java <code>-</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a short.
	 * @return   <code>a-b</code>
	 */
	public static float operator_minus(float a, short b) {
		return a - b;
	}
	
	/**
	 * The binary <code>multiply</code> operator. This is the equivalent to the Java <code>*</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a short.
	 * @return   <code>a*b</code>
	 */
	public static float operator_multiply(float a, short b) {
		return a * b;
	}
	
	/**
	 * The binary <code>divide</code> operator. This is the equivalent to the Java <code>/</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a short.
	 * @return   <code>a/b</code>
	 */
	public static float operator_divide(float a, short b) {
		return a / b;
	}
	
	/**
	 * The binary <code>modulo</code> operator. This is the equivalent to the Java <code>%</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a short.
	 * @return   <code>a%b</code>
	 */
	public static float operator_modulo(float a, short b) {
		return a % b;
	}
	
	/**
	 * The binary <code>lessThan</code> operator. This is the equivalent to the Java <code>&lt;</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a short.
	 * @return   <code>a&lt;b</code>
	 */
	public static boolean operator_lessThan(float a, short b) {
		return a < b;
	}
	
	/**
	 * The binary <code>lessEqualsThan</code> operator. This is the equivalent to the Java <code>&lt;=</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a short.
	 * @return   <code>a&lt;=b</code>
	 */
	public static boolean operator_lessEqualsThan(float a, short b) {
		return a <= b;
	}
	
	/**
	 * The binary <code>greaterThan</code> operator. This is the equivalent to the Java <code>&gt;</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a short.
	 * @return   <code>a&gt;b</code>
	 */
	public static boolean operator_greaterThan(float a, short b) {
		return a > b;
	}
	
	/**
	 * The binary <code>greaterEqualsThan</code> operator. This is the equivalent to the Java <code>&gt;=</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a short.
	 * @return   <code>a&gt;=b</code>
	 */
	public static boolean operator_greaterEqualsThan(float a, short b) {
		return a >= b;
	}
	
	/**
	 * The binary <code>equals</code> operator. This is the equivalent to the Java <code>==</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a short.
	 * @return   <code>a==b</code>
	 */
	public static boolean operator_equals(float a, short b) {
		return a == b;
	}
	
	/**
	 * The binary <code>notEquals</code> operator. This is the equivalent to the Java <code>!=</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a short.
	 * @return   <code>a!=b</code>
	 */
	public static boolean operator_notEquals(float a, short b) {
		return a != b;
	}
	
	/**
	 * The binary <code>power</code> operator. This is the equivalent to the Java's <code>Math.pow()</code> function.
	 * 
	 * @param a  a float.
	 * @param b  a short.
	 * @return   <code>Math.pow(a, b)</code>
	 */
	public static double operator_power(float a, short b) {
		return Math.pow(a, b);
	}
	
	/**
	 * The binary <code>plus</code> operator. This is the equivalent to the Java <code>+</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a byte.
	 * @return   <code>a+b</code>
	 */
	public static float operator_plus(float a, byte b) {
		return a + b;
	}
	
	/**
	 * The binary <code>minus</code> operator. This is the equivalent to the Java <code>-</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a byte.
	 * @return   <code>a-b</code>
	 */
	public static float operator_minus(float a, byte b) {
		return a - b;
	}
	
	/**
	 * The binary <code>multiply</code> operator. This is the equivalent to the Java <code>*</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a byte.
	 * @return   <code>a*b</code>
	 */
	public static float operator_multiply(float a, byte b) {
		return a * b;
	}
	
	/**
	 * The binary <code>divide</code> operator. This is the equivalent to the Java <code>/</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a byte.
	 * @return   <code>a/b</code>
	 */
	public static float operator_divide(float a, byte b) {
		return a / b;
	}
	
	/**
	 * The binary <code>modulo</code> operator. This is the equivalent to the Java <code>%</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a byte.
	 * @return   <code>a%b</code>
	 */
	public static float operator_modulo(float a, byte b) {
		return a % b;
	}
	
	/**
	 * The binary <code>lessThan</code> operator. This is the equivalent to the Java <code>&lt;</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a byte.
	 * @return   <code>a&lt;b</code>
	 */
	public static boolean operator_lessThan(float a, byte b) {
		return a < b;
	}
	
	/**
	 * The binary <code>lessEqualsThan</code> operator. This is the equivalent to the Java <code>&lt;=</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a byte.
	 * @return   <code>a&lt;=b</code>
	 */
	public static boolean operator_lessEqualsThan(float a, byte b) {
		return a <= b;
	}
	
	/**
	 * The binary <code>greaterThan</code> operator. This is the equivalent to the Java <code>&gt;</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a byte.
	 * @return   <code>a&gt;b</code>
	 */
	public static boolean operator_greaterThan(float a, byte b) {
		return a > b;
	}
	
	/**
	 * The binary <code>greaterEqualsThan</code> operator. This is the equivalent to the Java <code>&gt;=</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a byte.
	 * @return   <code>a&gt;=b</code>
	 */
	public static boolean operator_greaterEqualsThan(float a, byte b) {
		return a >= b;
	}
	
	/**
	 * The binary <code>equals</code> operator. This is the equivalent to the Java <code>==</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a byte.
	 * @return   <code>a==b</code>
	 */
	public static boolean operator_equals(float a, byte b) {
		return a == b;
	}
	
	/**
	 * The binary <code>notEquals</code> operator. This is the equivalent to the Java <code>!=</code> operator.
	 * 
	 * @param a  a float.
	 * @param b  a byte.
	 * @return   <code>a!=b</code>
	 */
	public static boolean operator_notEquals(float a, byte b) {
		return a != b;
	}
	
	/**
	 * The binary <code>power</code> operator. This is the equivalent to the Java's <code>Math.pow()</code> function.
	 * 
	 * @param a  a float.
	 * @param b  a byte.
	 * @return   <code>Math.pow(a, b)</code>
	 */
	public static double operator_power(float a, byte b) {
		return Math.pow(a, b);
	}
	
	// END generated code



}
