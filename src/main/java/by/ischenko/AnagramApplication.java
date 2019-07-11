package by.ischenko;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnagramApplication {
	public static void main(String[] args) throws IOException, URISyntaxException {
		List<AnagramString> firstNameList = new AnagramApplication().getNamesFromFile("first-name.txt").stream()
				.map(AnagramString::of)
				.collect(Collectors.toList());

		List<AnagramTuple<String, String>> anagrams = new ArrayList<>();
		for (int x = 0; x < firstNameList.size(); x++) {
			AnagramString wordX = firstNameList.get(x);
			for (int y = 0; y < firstNameList.size(); y++) {
				AnagramString wordY = firstNameList.get(y);
				if (x!=y && wordX.isAnagram(wordY)) {
					AnagramTuple<String, String> anagramPare = new AnagramTuple<>(wordX.toString(), wordY.toString());
					if (!anagrams.contains(anagramPare))
						anagrams.add(anagramPare);
				}
			}
		}
		if (anagrams.size()>0) {
			System.out.println("Anagrams were found:");
			anagrams.forEach(t-> System.out.println(t.x + " | " + t.y));
		}
	}

	private List<String> getNamesFromFile(String fileName) throws URISyntaxException, IOException {
		try (Stream<String> fullNamesStream = Files.lines(Paths.get(getClass().getClassLoader().getResource(fileName).toURI()))) {
			return fullNamesStream.collect(Collectors.toList());
		}
	}
}
