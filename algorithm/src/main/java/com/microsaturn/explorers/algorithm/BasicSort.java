package com.microsaturn.explorers.algorithm;

import java.util.Random;

/**
 * 经典排序算法
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
		
		long start = System.nanoTime();
//		bubbleSort(arr);
//		selectionSort(arr);
//		insertionSort(arr);
//		shellSort(arr);
		arr = mergeSort(arr);
		long end = System.nanoTime();
		System.out.println("Time::" + (end -start));
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
	static void insertionSort(int[] arr) {
		for(int i = 1; i < arr.length; i++) {
			int t = arr[i];
			int index = i;
			for(int j = i - 1; j >= 0; j--) {
				if(arr[j] > t) {
					arr[j + 1] = arr[j];
					index = j;
				}
			}
			arr[index] = t;
		}
	}
	
	/**
	 * 希尔排序 ShellSort:Wiki上面的代码
	 * @param arr
	 */
	static void shellSort(int[] arr) {
		int gap = 1, i, j, len = arr.length;
		int temp;
		while (gap < len / 3)
			gap = gap * 3 + 1; // <O(n^(3/2)) by Knuth,1973>: 1, 4, 13, 40, 121, ...
		for (; gap > 0; gap /= 3)
			for (i = gap; i < len; i++) {
				temp = arr[i];
				for (j = i - gap; j >= 0 && arr[j] > temp; j -= gap)
					arr[j + gap] = arr[j];
				arr[j + gap] = temp;
			}
	}
	
	static void shellSort(int[] arr, int n) {
		int len = arr.length;
		for(int gap = len / n; gap > 0; gap /= n) 
			for(int i = gap; i < len; i++) {
				for(int j = i; j >= gap && arr[j] < arr[j - gap]; j -= gap) {
					int t = arr[j];
					arr[j] = arr[j - gap];
                    arr[j - gap] = t;
				}
			}
	}
	
	// 归并排序 MergeSort
	static int[] mergeSort(int[] arr) {
		if(arr.length < 2) 
			return arr;
		int len = arr.length;
		int middle = len / 2;
		int[] left = slice(arr, 0, middle);
		int[] right = slice(arr, middle, len);
		return merge(mergeSort(left), mergeSort(right));
	}
	
	static int[] merge(int[] left, int[] right) {
		int[] ret = new int[left.length + right.length];
		int t = 0;
		while(left.length > 0 && right.length > 0) {
			if(left[0] < right[0]) {
				ret[t++] = left[0];
				left = pop(left, 0);
			} else {
				ret[t++] = right[0];
				right = pop(right, 0);
			}
		}
		
		while(left.length > 0) {
			ret[t++] = left[0];
			left = pop(left, 0);
		}
		
		while(right.length > 0) {
			ret[t++] = right[0];
			right = pop(right, 0);
		}
		
		return ret;
	}
	
	static int[] pop(int[] arr, int index) {
		int[] ret = new int[arr.length - 1];
		int t = 0;
		for(int i = 0; i < arr.length; i++) {
			if(i != index) {
				ret[t++] = arr[i];
			}
		}
		return ret;
	}
	
	private static int[] slice(int arr[], int start, int end) {
		int[] result = new int[end - start];
		int t = 0;
		for(int i = start; i < end; i++) { 
			result[t++] = arr[i];
		}
		return result;
	}
	
	// 快速排序 QuickSort
	static void quickSort(int[] arr) {
		
	}
	
	// 堆排序 HeapSort
	static void heapSort(int[] arr) {
		
	}
}