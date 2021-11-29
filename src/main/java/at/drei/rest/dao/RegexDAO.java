package at.drei.rest.dao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Repository;

import at.drei.rest.model.EvalResult;
import at.drei.rest.model.Regex;

@Repository
public class RegexDAO {

	/**
	 * @param regex
	 * @return
	 */
	public EvalResult evaluateExpression(Regex pRegex) {

		final String regex = pRegex.getPattern();
		final String string = pRegex.getText();
		final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
		final Matcher matcher = pattern.matcher(string);

		EvalResult evalResult = new EvalResult();

		String out = "";
		int cont = 0;

		while (matcher.find()) {
			System.out.println("Full match: " + matcher.group(0));
			if (cont == 0) {
				out = matcher.group(0);
			} else {
				out += ";" + matcher.group(0);
			}

			cont++;
		}
		evalResult.setId(cont);
		evalResult.setResult(out);

		return evalResult;
	}

}
