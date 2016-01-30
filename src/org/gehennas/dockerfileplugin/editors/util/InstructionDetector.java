package org.gehennas.dockerfileplugin.editors.util;

import org.eclipse.jface.text.rules.IWordDetector;

/**
 * WordDetector implementation for instruction keywords (Uppercase letters)
 * 
 * @author Nikita Zverev
 * @version 1.0.1
 */
public class InstructionDetector implements IWordDetector{

	@Override
	public boolean isWordStart(char c) {
		return Character.isUpperCase(c);
	}

	@Override
	public boolean isWordPart(char c) {
		return Character.isUpperCase(c);
	}
	
	

}
