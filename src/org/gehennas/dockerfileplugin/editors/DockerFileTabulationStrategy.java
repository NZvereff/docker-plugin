package org.gehennas.dockerfileplugin.editors;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DefaultIndentLineAutoEditStrategy;
import org.eclipse.jface.text.DocumentCommand;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextUtilities;

public class DockerFileTabulationStrategy extends DefaultIndentLineAutoEditStrategy {
		
	public void customizeDocumentCommand(IDocument document, DocumentCommand command) {
		if (command.length == 0 && command.text != null && TextUtilities.endsWith(document.getLegalLineDelimiters(), command.text) != -1) {
			TabAfterBackSlash(document, command);
		}
	}
	

	private void TabAfterBackSlash(IDocument document, DocumentCommand command) { //Ads indent if multiline instruction was used
		
		if (command.offset == -1 || document.getLength() == 0)
			return;
		
		try {
			int p= ((command.offset == document.getLength()) ? (command.offset - 1) : command.offset);

			StringBuffer buf= new StringBuffer(command.text);
			if ((command.offset < document.getLength()) && (document.getChar(p-1) == '\\')) { //Adding tabulation if line ends with backslash
				buf.append('\t');
			}
			command.text = buf.toString();
		} catch (BadLocationException x) {
			x.printStackTrace();
		}
		
	}

}
