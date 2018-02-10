package ch.ffhs.ftoop.p2.i18ndemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class InternationalizationDemo {
	public static void main(String[] args) throws IOException {
		Locale locale = Locale.getDefault();

		locale = Locale.ENGLISH.US;

		// Bundle wird nur im Classpath gesucht!
		ResourceBundle bundle = PropertyResourceBundle.getBundle("p2.strings",
				locale);
		BufferedReader consoleReader = new BufferedReader(
				new InputStreamReader(System.in));
		String question, answer, name;
		while (true) {
			question = bundle.getString("enterLocale");
			System.out.println(question);
			answer = consoleReader.readLine();
			locale = new Locale(answer.substring(0, 2), answer.substring(3));
			bundle = PropertyResourceBundle.getBundle("p2.strings", locale);

			question = bundle.getString("enterName");
			System.out.println(question);
			name = consoleReader.readLine();
			answer = bundle.getString("greeting");
			System.out.println(MessageFormat.format(answer, name));
		}
	}
}