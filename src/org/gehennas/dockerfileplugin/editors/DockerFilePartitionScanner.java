package org.gehennas.dockerfileplugin.editors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;

public class DockerFilePartitionScanner extends RuleBasedPartitionScanner{
	
	public final static String DF_COMMENT = "__df_comment"; //Tagging comment partitions
	
	public DockerFilePartitionScanner() {
		
		IToken comment = new Token(DF_COMMENT);
		
		List<IPredicateRule> ruleList = new ArrayList<IPredicateRule>(); //Just in case we'll need to add more partitions in the future
		
		ruleList.add(new EndOfLineRule("#",comment));			
				
		IPredicateRule[] rules = new IPredicateRule[ruleList.size()];
		ruleList.toArray(rules);
		
		setPredicateRules(rules);
		
	}

}
