import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MyMapDecoder implements MapDecoder {
	
	public static void main(String args[]) {
		MyMapDecoder decoder = new MyMapDecoder();
		Map<String, String> inputMap = new HashMap<>();
		System.out.println("Enter the string value for decoding (Sample Input: one=1&two=2): ");

		Scanner scanner = new Scanner(System.in);
		if (scanner.hasNext()) {
			inputMap = decoder.decode(scanner.next());
			if (inputMap != null) {
				inputMap.forEach((key, value) -> {
					System.out.print(key + " : ");
					System.out.println(value);
				});
			} else {
				System.out.println("Map is::" + inputMap);
			}
		}
		scanner.close();
	}

	@Override
	public Map<String, String> decode(String s) {
		Map<String, String> decodeMap = null;
		if (s != null) {
			decodeMap = new HashMap<>();
			if (!s.isEmpty()) {
				if (isLegalFormat(s)) {
					decodeMap = splitString(s);
				} else {
					throw new IllegalArgumentException(s);
				}
			}
		}
		return decodeMap;
	}

	/**
	 * This method checks for illegal format of the string
	 * 
	 * @param s
	 * @return
	 */
	private boolean isLegalFormat(String s) {
		if (s.contains("=") && !s.contains("&&") && !s.contains("=="))
			return true;
		return false;
	}

	/**
	 * This method decodes a string to a Map
	 * 
	 * @param s
	 * @return
	 */
	private Map<String, String> splitString(String s) {
		Map<String, String> tempMap = new HashMap<>();
		String equal = "";
		String[] str = null;
		String key = "";
		String value = "";
		for (String start : s.split("&")) {
			equal = start;
			str = equal.split("=", 2);
			key = str[0];
			value = str[1];
			tempMap.put(key, value);
		}
		return tempMap;
	}
}
