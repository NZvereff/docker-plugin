package org.gehennas.dockerfileplugin.editors;

import org.eclipse.jface.text.rules.IWordDetector;

public class InstructionDetector implements IWordDetector{ //Detects Dockerfile instructions

	@Override
	public boolean isWordStart(char c) {
		return Character.isUpperCase(c);
	}

	@Override
	public boolean isWordPart(char c) {
		return Character.isUpperCase(c);
	}
	
	

}
