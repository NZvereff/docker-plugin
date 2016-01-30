package org.gehennas.dockerfileplugin.editors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;
import org.gehennas.dockerfileplugin.editors.util.ColorManager;
import org.gehennas.dockerfileplugin.editors.util.DockerFileColorConstants;
import org.gehennas.dockerfileplugin.editors.util.DockerFileWhitespaceDetector;
import org.gehennas.dockerfileplugin.editors.util.Instruction;
import org.gehennas.dockerfileplugin.editors.util.InstructionDetector;

/**
 * Rule-based scanner for syntax highlighting in instructions. Finds instruction keywords, environment variables and strings.
 * 
 * @author Gehennas
 * @version 1.0.1
 */
public class InstructionScanner extends RuleBasedScanner {
	
	/**
	 * Creates a new scanner, and initializes string, keyword and environment variable rules
	 * 
	 * @param manager color manager used for highlighting
	 */
	public InstructionScanner(ColorManager manager) {
		IToken instruction = new Token (new TextAttribute(manager.getColor(DockerFileColorConstants.INSTRUCTION))); //Instruction keywords
		IToken string = new Token (new TextAttribute(manager.getColor(DockerFileColorConstants.STRING))); //Strings
		IToken env_var = new Token (new TextAttribute(manager.getColor(DockerFileColorConstants.ENV_VAR))); //Environment variables
		IToken not_the_keyword = new Token (new TextAttribute(manager.getColor(DockerFileColorConstants.ARGS))); //default color if not a keyword
		List<IRule> ruleList = new ArrayList<IRule>();
		IRule[] rules;
			
		ruleList.add(new SingleLineRule("\"", "\"", string, '\\'));
		ruleList.add(new SingleLineRule("'", "'", string, '\\'));
		ruleList.add(new SingleLineRule("${", "}", env_var));
		ruleList.add(new SingleLineRule("$", " ", env_var));
		ruleList.add(new WhitespaceRule(new DockerFileWhitespaceDetector()));
		WordRule instRule = new WordRule(new InstructionDetector(), not_the_keyword); 
		for (Instruction i: Instruction.values()) {
			instRule.addWord(i.name(), instruction);
		}
		ruleList.add(instRule);
		rules = new IRule[ruleList.size()]; 
		ruleList.toArray(rules);
		setRules(rules);
	}
}
