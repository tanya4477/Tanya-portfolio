import java.util.LinkedHashSet;
import java.util.Set;


/** Program to return only unique elements from Array of integers */

public class Unique {

	public static void main(String[] args) {
		Integer[] array = { 6, 3, 8, 8, 9, 13, 6, 3, 7 };
		System.out.println("The unique elements are::");
		uniqueInt(array);
	}

	public static void uniqueInt(Integer[] array) {

		Set<Integer> uniqueSet = new LinkedHashSet<>();
		for (Integer element : array) {
			if (!uniqueSet.add(element)) {
				uniqueSet.remove(element);
			}
		}
		uniqueSet.stream().forEach(System.out::println);
	}
}
