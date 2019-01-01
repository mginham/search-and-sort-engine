package codes;

import java.io.IOException;
import java.util.Scanner;

public class Search_and_Sort_Engine {

	public static int SIZE = 100;
	public static int[] manta = new int[SIZE];
	
	
	public static void menu() {
		System.out.println("\n==================================");
		System.out.println(" 0.	Exit");
		System.out.println(" 1.	Populate: sequential");
		System.out.println(" 2.	Populate: random");
		System.out.println(" 3.	Check if sorted");
		System.out.println(" 4.	Display");
		System.out.println(" 5.	Shuffle");
		System.out.println(" 6.	Search: sequential");
		System.out.println(" 7.	Search: binary");
		System.out.println(" 8.	Sort: random");
		System.out.println(" 9.	Sort: bubble");
		System.out.println("10.	Sort: selection");
		System.out.println("11.	Sort: radix");
		System.out.println("12.	Sort: merge // does not work");
		System.out.println("13.	Sort: quick");
		System.out.println("14.	Method processing time");
		System.out.println("==================================\n");
	} // end menu
	
	public static void runMethod(int mainMenuChoice) {
			if(mainMenuChoice == 1) { // populate sequentially
				populateSequentially();
			}
			else if(mainMenuChoice == 2) { // populate randomly
				populateRandomly();
			}
			else if(mainMenuChoice == 3) { // check if sorted sequentially
				boolean sorted = true;
				
				sorted = checkSorted();
				
				if(!sorted)
					System.out.println("The array is not sorted");
				else
					System.out.println("The array is sorted");
			}
			else if(mainMenuChoice == 4) { // display
				displayArray(manta);
			}
			else if(mainMenuChoice == 5) { // shuffle
				shuffleArray();
			}
			else if(mainMenuChoice == 6) { // search sequential (only presents the first instance)
				int target = 0;
				int targetIndex = 0;
				
				System.out.print("Target: "); // user inputs a target value
					target = errorTrap();
				
				targetIndex = searchSequentially(target);
				
				if(targetIndex == -1)
					System.out.println("Target is not in the array");
				else
					System.out.println("Target found at index: " + targetIndex);
			}
			else if (mainMenuChoice == 7) { // search binary (must be sorted to work)
				int target = 0, targetIndex = 0;
				boolean sorted = false;
				
				sorted = checkSorted();
				
				if(!sorted)
					System.out.println("Please sort the array first");
				else {
					System.out.println("Target: ");
						target = errorTrap();
						
					targetIndex = searchBinary(target);
					
					System.out.println("Target found at index: " + targetIndex);
				}
			}
			else if (mainMenuChoice == 8) { // sort random
				boolean sorted = true;
				int counter = 0;
				
				do {
					sorted = checkSorted();
					
					if(!sorted)
						sortRandom();
					
					counter++;
				} while(!sorted && counter < 10000);
				
				if(counter == 10000)
					System.out.println("Could not be sorted in less than 10000 tries");
			}
			else if (mainMenuChoice == 9) { // sort bubble
				sortBubble();
			}
			else if (mainMenuChoice == 10) { // sort selection
				sortSelection();
			}
			else if (mainMenuChoice == 11) { // sort radix
				sortRadix();
			}
			else if (mainMenuChoice == 12) { // sort merge
				sortMerge();
			}
			else if (mainMenuChoice == 13) { // sort quick
				int begIndex = 0, endIndex = 0;
				
				endIndex = (manta.length-1);
				
				sortQuick(begIndex, endIndex);
			}
			else if (mainMenuChoice == 14) { // method processing time
				int sortMethod = 0;
				long processingTime = 0;
				
				System.out.print("Choose a sorting method: ");
				
				sortMethod = errorTrapRange(13,8); // user inputs a sorting method
				
				processingTime = processingTime(sortMethod);
				
				System.out.println("Processing time (in ms): " + processingTime);
			}
	} // end runMethod
	
	public static void populateSequentially(){ // mainMenuChoice == 1
		for(int i = 0; i < SIZE; i++) {
			manta[i] = i+1;
		}
	} // end populateSequentially
	
	public static void populateRandomly() { // mainMenuChoice == 2
		int min = 0, max = 100;
		
		for(int i = 0; i < SIZE; i ++) {
			manta[i] = (int)(Math.random()*(max-min))+1;
		}
	} // end populateRandomly
	
	public static boolean checkSorted() { // mainMenuChoice == 3
		boolean sorted = true;
		
		for(int i = 0; i < SIZE-1; i ++) {
			if(manta[i] > manta[i+1]) {
				sorted = false;	
				break;
			}
		}
		
		return sorted;
	} // end checkSorted
	
	public static void displayArray(int[] temp) {
		for(int i = 0; i < SIZE; i++) {
			if(temp[i] > 99) // hundreds
				System.out.print(" " + temp[i]);
			else if(manta[i] > 9) // tens
				System.out.print("  " + temp[i]);
			else // ones
				System.out.print("   " + temp[i]);
			
			if((i+1)%10 == 0) // if the the modulo is 0, println (only print 10 numbers into each line)
				System.out.println();
		}
	} // end displayArray
	
	public static void shuffleArray() { // mainMenuChoice == 5
		for(int i = 0; i < 2*SIZE; i++) { // 2*SIZE to make sure it's extra shuffled
			int randomIndexA = (int)(Math.random()*SIZE);
			int randomIndexB = (int)(Math.random()*SIZE);
			int temp = manta[randomIndexA];
			
			manta[randomIndexA] = manta[randomIndexB];
			manta[randomIndexB] = temp;
		}
	} // end shuffleArray
	
	public static int searchSequentially(int target) { // mainMenuChoice == 6 (only returns the first instance)
		int targetIndex = 0;
		
		for(int i = 0; i < SIZE; i++) {
			if(manta[i] == target) { // target found
				targetIndex = i;
				break;
			}
			else // target not found
				targetIndex = -1;
		}
		
		return targetIndex;
	} // end searchArray
	
	public static int searchBinary(int target) { // mainMenuChoice == 7
		int targetIndex = 0, temp = 0, min = 0, max = SIZE-1;
		boolean found = false;
		
		do {
			temp = (max + min)/2;
			
			if(manta[temp] == target) { // found target at temp
				targetIndex = temp;
				found = true;
			}
			else if(manta[max] == target) { // found target at max
				targetIndex = max;
				found = true;
			}
			else if(manta[min] == target) { // found target at min
				targetIndex = min;
				found = true;
			}
			else if(manta[temp] < target) { // target is in upper half of the array
				min = temp+1;
				found = false;
			}
			else if (manta[temp] > target) { // target is in lower half of the array
				max = temp-1;
				found = false;
			}
			
			if(max == min) { // target not in the array
				targetIndex = -1;
				found = true; // target is not really found, but all other options have been eliminated
			}
		} while(!found);
		
		return targetIndex;
	} // end searchBinary
	
	public static void sortRandom() { // mainMenuChoice == 8
		int randomNum1 = 0;
		int randomNum2 = 0;
		int dummy = 0;
		
		for(int x = 0; x < SIZE*2; x++) {
			randomNum1 = (int)(Math.random()*SIZE);
			randomNum2 = (int)(Math.random()*SIZE);
			
			dummy = manta[randomNum1];
			manta[randomNum1] = manta[randomNum2];
			manta[randomNum2] = dummy;
		}
	} // end sortRandom
	
	public static void sortBubble() { // mainMenuChoice == 9
		int dummy = 0;
		
		for(int x = 0; x < SIZE-1; x++) {
			for(int y = x+1; y < SIZE; y++) {
				if(manta[x] > manta[y]) {
					dummy = manta[x];
					manta[x] = manta[y];
					manta[y] = dummy;
				}
			}
		}
	} // end sortBubble
	
	public static void sortSelection() { // mainMenuChoice == 10
		int marker = 0, dummy = 0;
		
		for(int x = 0; x < SIZE-1; x++) {
			marker = x+1;
			
			for(int y = x+1; y < SIZE; y++) { // find the next lowest value
				if(manta[y] < manta[marker])
					marker = y;
			}
			
			if(manta[x] > manta[marker]) { // switch manta[x] and manta[y]
				dummy = manta[x];
				manta[x] = manta[marker];
				manta[marker] = dummy;
			}
		}
	} // end sortSelection
	
	public static void sortRadix() { // mainMenuChoice == 1112
		int[] tempArray = new int[SIZE];
		
		for(int i = 1; i < 4; i++) {
			int counter = 0;
			
			for(int x = 0; x < SIZE; x++) { // copy manta to temp
				tempArray[x] = manta[x];
			}
			
			for(int value = 0; value < 10; value++) {
				for(int index = 0; index < SIZE; index++) {
					if(digitAtIndex(manta[index],i) == value) {
						tempArray[counter] = manta[index];
						
						counter++;
					}
				}
			}
			
			for(int x = 0; x < SIZE; x++) { // copy temp to manta
				manta[x] = tempArray[x];
			}
		}		
	} // end sortRadix
	
	public static void sortMerge() { // mainMenuChoice == 12
		/// TODO:
	} // end sortMerge
	
	public static void sortQuick(int begIndex, int endIndex) { // mainMenuChoice == 13
		int pivot = begIndex;
		int lSlider = begIndex;
		int rSlider = endIndex;
		
		while(lSlider != rSlider) {
			while(lSlider < pivot && manta[lSlider] <= manta[pivot]) { // find a value lower than pivot
				lSlider++;
			}
			while(rSlider > pivot && manta[rSlider] >= manta[pivot]) { // find a value higher than pivot
				rSlider--;
			}
			
			if(lSlider != rSlider) { // swap values
				int dummy = 0;
				
				dummy = manta[lSlider];
				manta[lSlider] = manta[rSlider];
				manta[rSlider] = dummy;
			}
			
			if(lSlider == pivot) { // swap pivot marker
				pivot = rSlider;
			}
			else if(rSlider == pivot) { // swap pivot marker
				pivot = lSlider;
			}
		}
		
		if((pivot-begIndex) > 1) { // sort left-hand side
			sortQuick(begIndex, (pivot-1));
		}
		if((endIndex - pivot) > 1) { // sort right-hand side
			sortQuick((pivot+1), endIndex);
		}
	} // end sortQuick
	
	public static long processingTime(int sortMethod) { // mainMenuChoice == 14
		long iTime = 0, fTime = 0, pTime = 0;
		
		iTime = checkTime();
		
		runMethod(sortMethod);
		
		fTime = checkTime();
		
		pTime = (fTime - iTime);
		
		return pTime;
	} // end processingTime
	
	public static long checkTime() {
		//long time = System.currentTimeMillis();
		long time = java.lang.System.currentTimeMillis();
		
		return time;
	} // end checkTime
	
	public static int digitAtIndex(int number, int index) { // isolate digit at given index (1 = ones, 2 = tens, 3 = hundreds)
		int digit = 0;
		
		digit = number / (int)(Math.pow(10, index-1));
		
		digit = digit%10;
		
		return digit;
	} // end digitIndex
	
	@SuppressWarnings("resource")
	public static int errorTrap() {
		Scanner input = new Scanner(System.in);
		boolean possible = true;
		int number = 0;
		
		do {
			possible = true;
			
			try {
				number = input.nextInt();
			}
			catch(Exception e) {
				input.next();
				possible = false;
				System.out.println("Invalid input. Please try again");
			}
			
		} while(!possible);
		
		return number;
	} // end errorTrap
	
	@SuppressWarnings("resource")
	public static int errorTrapRange(int max, int min) {
		Scanner input = new Scanner(System.in);
		boolean possible = true;
		int number = 0;
		
		do {
			possible = true;
			
			try {
				number = input.nextInt();
			}
			catch(Exception e) {
				input.next();
				possible = false;
			}
			
			if(!possible || number < min || number > max)
				System.out.println("Invalid input. Please try again");
			
		} while(!possible || number < min || number > max);
		
		return number;
	} // end errorTrapRange

//***********************************************************************************//
	
	public static void main(String[] args) throws InterruptedException, IOException {
		int mainMenuChoice = 0;
		
		System.out.println("Search and Sort Engine:");
		
		do {
			menu(); // display menu
			
			mainMenuChoice = errorTrapRange(14,0); // input menu choice
			System.out.println();
			
			runMethod(mainMenuChoice);
		} while(mainMenuChoice != 0); // exit
	} // end main

}