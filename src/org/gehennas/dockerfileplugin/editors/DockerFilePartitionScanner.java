package org.gehennas.dockerfileplugin.editors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;

/**
 * Rule-based Dockerfile partition scanner that currently supports the only comment partition
 * 
 * @author Nikita Zverev
 * @version 1.0.1
 */
public class DockerFilePartitionScanner extends RuleBasedPartitionScanner{
	/* 
	 * The standard implementation below instead of one-element array.
	 * Just because we may want to search for additional partitions in the future
	 */
	
	public final static String DF_COMMENT = "__df_comment"; //Tag for comment partitions
	
	public DockerFilePartitionScanner() {
		IToken comment = new Token(DF_COMMENT);
		List<IPredicateRule> ruleList = new ArrayList<IPredicateRule>();
		ruleList.add(new EndOfLineRule("#",comment));			
		IPredicateRule[] rules = new IPredicateRule[ruleList.size()];
		ruleList.toArray(rules);
		setPredicateRules(rules);		
	}
}
