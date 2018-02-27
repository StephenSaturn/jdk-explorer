package com.microsaturn.explorers.algorithm;

import java.util.Random;

/**
 * 经典排序算法
 * http://wuchong.me/blog/2014/02/09/algorithm-sort-summary/
 * @author Saturn
 *
 */
public class BasicSort {

	public static void main(String[] args) {
		Random random = new Random();
		int[] arr = new int[10];
		for(int i = 0; i < arr.length; i++) {
			arr[i] = random.nextInt(100);
		}
		print("before sort:");
		print(arr);
		print("\n");
		
		//bubbleSort(arr);
		selectionSort(arr);
		print("after sort:");
		print(arr);
	}
	
	static void print(Object o) {
		System.out.print(o);
	}
	
	static void print(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			print(arr[i] + " ");
		}
	}
	
	/**
	 * 冒泡排序:两两比较，每次循环，最大值就"浮"到最后的位置上
	 * @param arr
	 */
	static void bubbleSort(int[] arr) {
		for(int i = 0; i < arr.length - 1; i++) {
			for(int j = 0; j < arr.length - 1; j ++) {
				if(arr[j] > arr[j + 1]) {
					int t = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = t;
				}
			}
		}
	}
	
	/**
	 * 选择排序:在未排序序列中找到最小（大）元素，存放到排序序列的起始位置,再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾
	 * @param arr
	 */
	static void selectionSort(int[] arr) {
		for(int i = 0; i < arr.length - 1; i++) {
			int min = i;
			for(int j = i + 1; j < arr.length; j++) {
				if(arr[min] > arr[j]) {
					min = j;
				}
			}
			if(min != i) {
				int t = arr[i];
				arr[i] = arr[min];
				arr[min] = t;
			}
		}
	}
	
	// 插入排序 InsertionSort
	static void insertionSort() {}
	
	// 希尔排序 ShellSort
	static void shellSort() {}
	
	// 归并排序 MergeSort
	static void mergeSort() {}
	
	// 快速排序 QuickSort
	static void quickSort() {}
	
	// 堆排序 HeapSort
	static void heapSort() {}
}
