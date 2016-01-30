package org.gehennas.dockerfileplugin.editors;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DefaultIndentLineAutoEditStrategy;
import org.eclipse.jface.text.DocumentCommand;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextUtilities;

/**
 * Indent strategy used exclusively for multiline instructions (RUN for example)
 * 
 * @author Nikita Zverev
 * @version 1.0.1
 */
public class DockerFileTabulationStrategy extends DefaultIndentLineAutoEditStrategy {
	
	/* Inherited from DefaultIndentLineAutoEditStrategy. Checks if the line ends with backslash */
	public void customizeDocumentCommand(IDocument document, DocumentCommand command) {
		if (command.length == 0 && command.text != null && TextUtilities.endsWith(document.getLegalLineDelimiters(), command.text) != -1) {
			TabAfterBackSlash(document, command);
		}
	}
	
	/* Adds additional tabulation to command */
	private void TabAfterBackSlash(IDocument document, DocumentCommand command) {
		if (command.offset == -1 || document.getLength() == 0)
			return;
		try {
			int p= ((command.offset == document.getLength()) ? (command.offset - 1) : command.offset);
			StringBuffer buf= new StringBuffer(command.text);
			if ((command.offset < document.getLength()) && (document.getChar(p-1) == '\\')) {
				buf.append('\t');
			}
			command.text = buf.toString();
		} catch (BadLocationException x) { //TODO: Do something with this exception
			x.printStackTrace();
		}		
	}
}
