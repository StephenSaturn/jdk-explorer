package com.microsaturn.explorers.algorithm;

/**
 * 
 * @author Saturn
 * 补充一下计算机基础知识:原码、反码、补码
 * 1.机器数:一个数在计算机中的二进制表示形式,叫做这个数的机器数.机器数是带符号的, 在计算机用一个数的最高位存放符号,正数为0,负数为1
 * 	 比如:十进制数+3, 计算机字长为8位, 转换成二进制就是(0000 0011), 而-3就是(1000 0011), 那么, 这里的 (0000 0011) 和 (1000 0011) 就是机器数
 * 
 * 2.真值:将带符号位的机器数对应的真正数值称为机器数的真值.
 *  比如:(0000 0001)的真值  = (+000 0001) = +1, (1000 0001)的真值  = (–000 0001) = –1
 * 
 * 3.原码:原码就是符号位加上真值的绝对值,即用第一位表示符号,其余位表示值.比如如果是8位二进制:
 *  +3的原码  = (0000 0101), -3的原码 = (1000 0101)
 * 
 * 4.反码:正数的反码是其原码,而负数的反码是在其原码的基础上,符号位不变,其余各个位取反.
 *  +5的反码 = (0000 1010), -5的反码 = (1111 0101)
 * 
 * 5.补码:正数的补码就是其原码,而负数的补码是在其原码的基础上,符号位不变, 其余各位取反, 最后加1(即在反码的基础上+1)
 *  +6的补码 = (0000 0110), -6的补码 = (1111 1010)
 */
public class BitOperate {

	public static void main(String[] args) {
		System.out.println(Integer.toBinaryString(15));
		System.out.println("***************** left shift test start *****************");
		System.out.println(leftShift(5, 5));
		System.out.println(leftShift(-20, 2));
		System.out.println(rightShift(5, 2));
		System.out.println("***************** end shift test start *****************");

		System.out.println("***************** right shift start *****************");
		System.out.println(rightShift(100, 3));
		System.out.println(rightShift(-5, 3));

		System.out.println("***************** right shift end ***************** ");

		System.out.println("Unsigned Right shift:" + unsignedRightShift(-5, 3));
	}
	
	/**
	 * left shift:左移(<<)
	 * 左移count位后, 低位扑0(左为高位, 右为低位)
	 * 丢高位, 低位补0
	 * 相当于 (i * 2^count)
	 * @param i 原始数据
	 * @param count 左移多少位
	 * @return
	 */
	public static int leftShift(int i, int count) {
		return i << count;
	}
	
	/**
	 * right shift:右移(>>)
	 * 丢低位补高位
	 * @param i
	 * @param count
	 */
	public static int rightShift(int i, int count) {
		return i >> count;
	}
	
	// unsigned right shift:无符号右移(>>>)
	public static int unsignedRightShift(int i, int count) {
		return i >>> count;
	}
	
	/**
	 * 位与(&):两个位 位与 都为1才为1,否则为0
	 * @param a
	 * @param b
	 * @return
	 */
	public static int bitAND(int a, int b) {
		return a & b;
	}
	
	/**
	 * 位或(|):两个位 位或  只要一个为1, 结果为1,否则为0
	 * @param a
	 * @param b
	 * @return
	 */
	public static int bitOR(int a, int b) {
		return a | b;
	}
	
	/**
	 * 位异或(^):两个结果相反(如 1^0, 0^1), 结果为1, 否则为0 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int bitXOR(int a, int b) {
		return a ^ b;
	}
	
	// 位非(~)
	public static int bitNOT(int a) {
		return ~a;
	}
	
}
