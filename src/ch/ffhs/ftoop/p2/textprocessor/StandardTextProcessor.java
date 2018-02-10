package ch.ffhs.ftoop.p2.textprocessor;

public class StandardTextProcessor implements TextProcessor {
	public void process(String text) {
		System.out.println(text.toUpperCase());
	}
}