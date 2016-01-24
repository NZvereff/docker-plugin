package org.gehennas.dockerfileplugin.editors;

import org.eclipse.ui.editors.text.TextEditor;
import org.gehennas.dockerfileplugin.editors.util.ColorManager;

public class DockerFileEditor extends TextEditor {

	private ColorManager colorManager;

	public DockerFileEditor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new DockerFileConfiguration(colorManager));
		setDocumentProvider(new DockerFileDocumentProvider());
	}
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}

}
