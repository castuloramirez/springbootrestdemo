package at.drei.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.drei.rest.dao.RegexDAO;
import at.drei.rest.model.EvalResult;
import at.drei.rest.model.Regex;

@RestController
@RequestMapping(path = "/regex")
public class RegexController {

	@Autowired
	private RegexDAO regexDAO;

	@PostMapping(path = "/", consumes = "application/json", produces = "application/json")
	public EvalResult evaluateExpression(@RequestBody Regex regex) throws Exception {
		return regexDAO.evaluateExpression(regex);
	}
}