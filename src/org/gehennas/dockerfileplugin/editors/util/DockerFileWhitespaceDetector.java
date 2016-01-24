package org.gehennas.dockerfileplugin.editors.util;

import org.eclipse.jface.text.rules.IWhitespaceDetector;

public class DockerFileWhitespaceDetector implements IWhitespaceDetector { //Standard whitespace detector

	public boolean isWhitespace(char c) {
		return Character.isWhitespace(c);
	}
}
