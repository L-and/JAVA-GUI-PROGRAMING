package HomeWork;

import java.util.ArrayList;
import java.util.Scanner;

public class Solve1920 {

	public static void sort(int[] arr) {
		quickSort(arr, 0, arr.length-1);
	}
	
	private static void quickSort(int[] arr, int low, int high) {
		if(low >= high) {
			return;
		}
		int pivot = partition(arr, low, high);
		
		
		
		quickSort(arr, low, pivot-1);
		quickSort(arr, pivot+1, high);
	}
	
	private static int partition(int[] arr, int left, int right) {
		int low = left;
		int high = right;
		int pivot = arr[left];
		
		while(low < high) {
			while(arr[high] >= pivot && high > left) {
				high--;
			}
			while(arr[low] <= pivot && low < right) {
				low++;
			}
			
			swap(arr, low, high);
		}
		
		swap(arr, high - 1, left);
		
		return high;
	}
	
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	
	public static int binarySearch(int n, int[] arr, int key) {
		
		int low,high,mid;
		low = 0;
		high = arr.length-1;
		mid = (low+high) / 2;
		while (low <=high) {
			if(key == arr[mid]) {
				return 1;
			} else if(arr[mid] < key) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
			mid = (low+high) / 2;
		}
		
		return 0;
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int n = input.nextInt();
		int result;
		int[] A = new int[n];
		
		for(int i=0; i<A.length; i++) {
			A[i] = input.nextInt();
		}
		
		int m = input.nextInt();
		int[] M = new int[m];
		
		for(int i=0; i<M.length; i++) {
			M[i] = input.nextInt();
		}
		
		sort(A);
		
//		for(int i=0; i<A.length; i++) {
//			result = binarySearch(n, A, M[i]);
//			System.out.println(result);
//		}
		
		for(int i=0; i<A.length; i++)
			System.out.println(A[i]);
	}
	
}
