package org.gehennas.dockerfileplugin.editors;

import org.eclipse.ui.editors.text.TextEditor;
import org.gehennas.dockerfileplugin.editors.util.ColorManager;

/**
 * The main class for DockerFileEditor
 * 
 * @author Nikita Zverev
 * @version 1.0.1
 */
public class DockerFileEditor extends TextEditor {

	private ColorManager colorManager;
	
	/**
	 * Standard editor constructor. Initializes configuration and document provider
	 */
	public DockerFileEditor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new DockerFileConfiguration(colorManager));
		setDocumentProvider(new ExternalDockerFileDocumentProvider());
	}
	
	/* Inherited from TextEditor. Disposes plugin modules */
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}

}
