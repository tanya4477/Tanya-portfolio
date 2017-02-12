package com.tanya;

public class ArrayIndex {
	public static void main(String args[]) {
//		int[] array = {4,9,3,7,8} ;
//		int[] subArray = {3,7};
//		int[] array = {1,3,5} ;
//		int[] subArray = {1};
//		int[] array = {7,8,9} ;
//		int[] subArray = {8,9,10};
		int[] array = {4,9,3,7,8,3,7,1} ;
		int[] subArray = {3,7};
		
		int index = 0;
		int finalIndex = -1; //Default case
		for(int start : array){
			if(start == subArray[0]){
				boolean isCompleteMatch = false;
				int startIndex = index;
				int remainingArrayLength = array.length - startIndex;
				if (subArray.length > remainingArrayLength) {
					// Do nothing;
				} else {
					for (int subArrayElement : subArray) {
						if (subArrayElement == array[startIndex++]) {
							isCompleteMatch = true;
						} else {
							isCompleteMatch = false;
							break;
						}
					}
					if (isCompleteMatch) {
						finalIndex = index;
					}
				}
				
			}
			index++;
		}
		System.out.println(finalIndex);
		
	}
}
