/**
 *	SortMethods - Sorts an array of Integers in ascending order.
 *
 *	@author 	Kevin Zhou
 *	@since		December 4th, 2017
 */
public class SortMethods {
	
	private final int ARRAY_SIZE = 100;
	private int mergeCompares = 0;
	/**
	 *	Bubble Sort algorithm - in ascending order
	 *	@param arr		array of Integer objects to sort
	 */
	public void bubbleSort(Integer [] arr) {
		int compares = 0;
		//loop through last, which starts at the last index and decrements by 1
		for (int last = arr.length - 1;  last > 0; last--)
			// loop through the shortened array, up until last
			for (int index =  0; index < last; index++){
				compares++;
				// if the element at index is greater than at index + 1
				if(arr[index].compareTo(arr[index+1]) > 0) 
					// swap the elements
					swap(arr, index, index+1);
			}
		System.out.println("Number of elements = " + arr.length + 
					"		Number of comparisons = " + compares);
	}
	
	/**
	 *	Swaps two Integer objects in array arr
	 *	@param arr		array of Integer objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	private void swap(Integer[] arr, int x, int y) {
		Integer temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}
	
	/**
	 *	Selection Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void selectionSort(Integer [] arr) {
		int compares = 0;
		// declare an index to keep track of the largest element
		int index = 0;
		// loop through last, which starts at the last index and decerements by 1
		for (int last = arr.length - 1; last > 0; last--) {
			for (int a = 0; a < last; a++) {
				compares++;
				// if the element at index is less than at a, set index to a
				if(arr[index].compareTo(arr[a]) < 0)
					index = a;
			}
			// swap the last element with the max element
			swap(arr, index, last);
		}
		System.out.println("Number of elements = " + arr.length + 
					"		Number of comparisons = " + compares);
	}
	
	/**
	 *	Insertion Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void insertionSort(Integer [] arr) {
		int compares = 0;
		// loop through the array starting with the second element
		for (int a = 1; a < arr.length; a++) {
			int b = a - 1;
			// store the value at index a
			int temp = arr[a];
			// use a nested loop to loop from a - 1 to 0
			// find when the element at a is less than the one at b
			while (b >= 0 && arr[b].compareTo(temp) > 0) {
				// keep shifting over the entire array by 1 element
				arr[b+1] = arr[b];
				b--;
				compares++;
			}
			// when the while loop is exited, b + 1 is the location where
			// the stored value at index a should be inserted, insert the value
			arr[b+1] = temp;
		}
		System.out.println("Number of elements = " + arr.length + 
					"		Number of comparisons = " + compares);			
	}
	
	/**
	 *	Merge Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void mergeSort(Integer [] arr) {
        	split(0, arr.length - 1, arr);
		System.out.println("Number of elements = " + arr.length + 
					"		Number of comparisons = " + mergeCompares);
    	}

	/**
	 *	This recursive method continually splits the part of the array that needs to be sorted.
	 * 	It finds the middle index of the array and splits the array in two at that index
	 *	@param low, high		first and last index of the array inside of arr
	 * 	@param arr			the original array of values
	 */
	public void split(int low, int high, Integer[] arr) {
		// if the high index is equal to or greater than low, then the array is already sorted
        	if (low < high) {
            		// find the middle index
            		int mid = low + (high - low) / 2;
            		// split the left side of the array
            		split(low, mid, arr);
            		// split the right side of the array
            		split(mid + 1, high, arr);
            		// merge the arrays
            		merge(low, mid, high, arr);
		}
	}

	/**
	 *	This recursive method continually merges the split array parts that needs to be sorted.
	 * 	It uses a temporary array to hold the values of the left and right side split arrays,
	 * 	uses two separate indexes to compare the values, and adds the smaller value to the master arr.
	 * 	If the index of a split array 'falls off', the rest of the values in the left array are added 
	 * 	to the master arr since the right array will already be at the correct position.	
	 *	@param low, high		first and last index of the array inside of arr
	 * 	@param mid			middle index where the left array ends, right array starts at mid+1
	 * 	@param arr			the original array of values
	 */
	private void merge(int low, int mid, int high, Integer[] arr) {
		// this array is used to merge the split arrays
		int[] temp = new int[arr.length];
        	// copy the unsorted values into the temporary array
        	for (int x = low; x <= high; x++) {
            		temp[x] = arr[x];
        	}

        	int a = low;
        	int b = mid + 1;
        	int c = low;
        	// compare the values starting from low and mid+1 since these represent 
		// the starts of the two arrays that need to be merged
		// keep adding values while the indexes of the two arrays that need to be
		// merged have not 'fallen off' their corresponding arrays
        	while (a <= mid && b <= high) {
			// compare the values of the two arrays and add the smaller one to arr
			// increment the corresponding index and the compare counter
            		if (temp[a] <= temp[b]) {
				mergeCompares++;
                		arr[c] = temp[a];
                		a++;
            		} 
			else {
				mergeCompares++;
                		arr[c] = temp[b];
                		b++;
            		}
			// always increment the index of arr after a value is added
            		c++;
        	}
       		// copy the rest of the left side of the array into the target array
       		while (a <= mid) {
         		arr[c] = temp[a];
         		c++;
         		a++;
        	}
        	// the method sorts from the left, thus any leftover elements from the right 
		// side array are already at the correct position

    	}

	/**
	 *	Print an array of Integers to the screen
	 *	@param arr		the array of Integers
	 */
	public void printArray(Integer[] arr) {
		if (arr.length == 0) System.out.print("(");
		else System.out.printf("( %4d", arr[0]);
		for (int a = 1; a < arr.length; a++) {
			if (a % 10 == 0) System.out.printf(",\n  %4d", arr[a]);
			else System.out.printf(", %4d", arr[a]);
		}
		System.out.println(" )");
	}
	
	public static void main(String[] args) {
		SortMethods se = new SortMethods();
		se.run();
	}
	
	public void run() {
		Integer[] arr = new Integer[ARRAY_SIZE];
		// Fill arr with random numbers
		for (int a = 0; a < ARRAY_SIZE; a++)
			arr[a] = (int)(Math.random() * ARRAY_SIZE * 2) + 1;
		System.out.println("\nBubble Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		bubbleSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();

		for (int a = 0; a < ARRAY_SIZE; a++)
			arr[a] = (int)(Math.random() * ARRAY_SIZE * 2) + 1;
		System.out.println("\nSelection Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		selectionSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
		
		for (int a = 0; a < ARRAY_SIZE; a++)
			arr[a] = (int)(Math.random() * ARRAY_SIZE * 2) + 1;
		System.out.println("\nInsertion Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		insertionSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();

		
		for (int a = 0; a < ARRAY_SIZE; a++)
			arr[a] = (int)(Math.random() * ARRAY_SIZE * 2) + 1;
		System.out.println("\nMerge Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		mergeSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);	
		System.out.println();

	}
}
