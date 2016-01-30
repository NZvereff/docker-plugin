package org.gehennas.dockerfileplugin.editors.util;

import org.eclipse.jface.text.rules.IWhitespaceDetector;

/**
 * Whitespace detector for the java-default whitespace characters
 * 
 * @author Nikita Zverev
 * @version 1.0.1
 */
public class DockerFileWhitespaceDetector implements IWhitespaceDetector {

	//Returns true if character is a whitespace
	public boolean isWhitespace(char c) {
		return Character.isWhitespace(c);
	}
}
