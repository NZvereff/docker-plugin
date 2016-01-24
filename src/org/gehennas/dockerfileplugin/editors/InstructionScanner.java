package org.gehennas.dockerfileplugin.editors;

import java.util.ArrayList;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;
import org.gehennas.dockerfileplugin.editors.util.DockerFileColorConstants;
import org.gehennas.dockerfileplugin.editors.util.DockerFileWhitespaceDetector;

public class InstructionScanner extends RuleBasedScanner {
	public InstructionScanner(ColorManager manager) {
		IToken instruction = new Token (new TextAttribute(manager.getColor(DockerFileColorConstants.INSTRUCTION))); //Instruction keywords
		IToken string = new Token (new TextAttribute(manager.getColor(DockerFileColorConstants.STRING))); //Strings
		IToken env_var = new Token (new TextAttribute(manager.getColor(DockerFileColorConstants.ENV_VAR))); //Environment variables
		
		ArrayList<IRule> ruleList = new ArrayList<IRule>();
		
		//Rules for instruction keywords.
		WordRule instRule = new WordRule(new InstructionDetector()); 
		for (Instruction i: Instruction.values()) {
			instRule.addWord(i.name(), instruction);
		}
		
		ruleList.add(instRule);
		ruleList.add(new SingleLineRule("\"", "\"", string, '\\'));
		ruleList.add(new SingleLineRule("'", "'", string, '\\'));
		ruleList.add(new SingleLineRule("${", "}", env_var));
		ruleList.add(new SingleLineRule("$", " ", env_var));
		ruleList.add(new WhitespaceRule(new DockerFileWhitespaceDetector()));
		
		IRule[] rules = new IRule[ruleList.size()]; 
		ruleList.toArray(rules);
		setRules(rules);
	}
}