package by.ischenko;


import java.util.Arrays;

public class AnagramString {
	private final char[] inputWord;
	private final char[] sortedCharArray;
	private final int charSum;

	private AnagramString(String string) {
		string = normilizeString(string);

		this.inputWord = string.toCharArray();
		this.sortedCharArray = Arrays.copyOf(inputWord, inputWord.length);
		Arrays.sort(sortedCharArray);

		int charSum = 0;
		for (char c : sortedCharArray) {
			charSum += c;
		}
		this.charSum = charSum;
	}

	private String normilizeString(String string) {
		return string.replaceAll("[^a-zA-Z]", "").trim().toLowerCase();
	}

	public static AnagramString of(String string) {
		if (string == null || string.equals("")) {
			throw new IllegalArgumentException("Input string is unavailable");
		}
		return new AnagramString(string);
	}

	public boolean isAnagram(AnagramString anagramString) {
				// first check like hashCode checking (for better performance)
		return (charSum == anagramString.charSum)
				// input word mustn't be equal to this one...
				&& !Arrays.equals(this.inputWord, anagramString.inputWord)
				// ...but sorted arrays must be
				&& Arrays.equals(this.sortedCharArray, anagramString.sortedCharArray);
	}

	public boolean isAnagram(String string) {
		return this.isAnagram(AnagramString.of(string));
	}

	@Override
	public String toString() {
		return String.valueOf(inputWord);
	}
}
