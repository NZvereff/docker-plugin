package org.gehennas.dockerfileplugin.editors;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.ui.editors.text.TextFileDocumentProvider;

/**
 * Document provider for Dockerfile which can be used for both internal and external files
 * 
 * @author Nikita Zverev
 * @version 1.0.1
 */
public class ExternalDockerFileDocumentProvider extends TextFileDocumentProvider { 
	
	protected FileInfo createFileInfo(Object element) throws CoreException {
		FileInfo info = super.createFileInfo(element);
    	if(info==null){
    		info = createEmptyFileInfo();
    	}
    	IDocument document = info.fTextFileBuffer.getDocument();
    	if (document != null) {
    		IDocumentPartitioner partitioner =	new FastPartitioner(
    				new DockerFilePartitionScanner(), new String[] {DockerFilePartitionScanner.DF_COMMENT});
    		partitioner.connect(document);
    		document.setDocumentPartitioner(partitioner);
    	}
    	return info;
	}
}
