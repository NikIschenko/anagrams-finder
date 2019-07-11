package by.ischenko;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnagramApplication {
	public static void main(String[] args) throws IOException, URISyntaxException {
		List<AnagramString> firstNameList = new AnagramApplication().getNamesFromFile("first-name.txt").stream()
				.map(AnagramString::of)
				.collect(Collectors.toList());

		Map<String, List<String>> anagramsForWord = new HashMap<>();
		for (int x = 0; x < firstNameList.size(); x++) {
			AnagramString wordX = firstNameList.get(x);
			List<String> anagrams = null;
			for (int y = 0; y < firstNameList.size(); y++) {
				AnagramString wordY = firstNameList.get(y);
				if (x!=y && wordX.isAnagram(wordY)) {
					if (anagrams == null) {
						anagrams = new ArrayList<>();
					}
					anagrams.add(String.valueOf(wordY));
				}
				if (anagrams != null && anagrams.size()>0) {
					anagramsForWord.put(String.valueOf(wordX), anagrams);
				}
			}
		}
		if (anagramsForWord.size()>0) {
			anagramsForWord
					.forEach(AnagramApplication::printAnagram);
		}
	}

	private static void printAnagram(String key, List<String> anagrams) {
		System.out.println("List of anagrams for word: '" + key + "' is next: '" + String.join("," , anagrams) + "'");
	}

	private List<String> getNamesFromFile(String fileName) throws URISyntaxException, IOException {
		try (Stream<String> fullNamesStream = Files.lines(Paths.get(getClass().getClassLoader().getResource(fileName).toURI()))) {
			return fullNamesStream.collect(Collectors.toList());
		}
	}
}
