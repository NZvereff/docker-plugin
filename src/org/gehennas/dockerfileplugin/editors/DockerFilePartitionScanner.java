package org.gehennas.dockerfileplugin.editors;

import java.util.ArrayList;

import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;

public class DockerFilePartitionScanner extends RuleBasedPartitionScanner{
	
	public final static String DF_COMMENT = "__df_comment";
	
	public DockerFilePartitionScanner() {
		
		IToken comment = new Token(DF_COMMENT);
		
		ArrayList<IPredicateRule> ruleList = new ArrayList<IPredicateRule>();
		
		ruleList.add(new EndOfLineRule("#",comment));			
				
		IPredicateRule[] rules = new IPredicateRule[ruleList.size()];
		ruleList.toArray(rules);
		
		setPredicateRules(rules);
		
	}

}
