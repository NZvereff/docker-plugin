package org.gehennas.dockerfileplugin.editors;

import org.eclipse.jface.text.DefaultIndentLineAutoEditStrategy;
import org.eclipse.jface.text.IAutoEditStrategy;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.gehennas.dockerfileplugin.editors.util.ColorManager;
import org.gehennas.dockerfileplugin.editors.util.DockerFileColorConstants;

/**
 * The default SourceViewerConfiguration implementation for Dockerfile.
 * Adds a custom double-click behavior, tabulation and syntax highlighting
 * 
 * @author Nikita Zverev
 * @version 1.0.1
 */

public class DockerFileConfiguration extends SourceViewerConfiguration {
	
	private DockerFileDoubleClickStrategy doubleClickStrategy;
	private InstructionScanner instructionScanner; //The Rule-based scanner used for document partitioning
	private ColorManager colorManager; //The default color manager for this configuration

	/**
	 * Creates a new {@link DockerFileConfiguration} and initializes its color manager used for syntax highlighting
	 * @param colorManager the default color manager that will be used for syntax highlighting
	 */
	public DockerFileConfiguration(ColorManager colorManager) {
		this.colorManager = colorManager;
	}
	
	/* Inherited from SourceViewerConfiguration. Returns content types used in document partitioning */
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] {
			IDocument.DEFAULT_CONTENT_TYPE,
			DockerFilePartitionScanner.DF_COMMENT };
	}
	
	/* Inherited from SourceViewerConfiguration. Returns Double-click strategy used in the editor */
	public ITextDoubleClickStrategy getDoubleClickStrategy(
		ISourceViewer sourceViewer,
		String contentType) {
		if (doubleClickStrategy == null)
			doubleClickStrategy = new DockerFileDoubleClickStrategy();
		return doubleClickStrategy;
	}

	/* Inherited from SourceViewerConfiguration. Returns tabulation strategy depending on content type */
	public IAutoEditStrategy[] getAutoEditStrategies(ISourceViewer sourceViewer, String contentType) {
		IAutoEditStrategy strategy= (IDocument.DEFAULT_CONTENT_TYPE.equals(contentType)
				? new DockerFileTabulationStrategy()
				: new DefaultIndentLineAutoEditStrategy()); //Using default tabulation strategy when editing comments
		return new IAutoEditStrategy[] { strategy };
	}
	
	/* Inherited from SourceViewerConfiguration. Used for setting up syntax highlighting */
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();
		
		/* Reconciling default content by setting damager and repairer*/
		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(getInstructionScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);
		
		/* Reconciling comments using NonRuleBasedDamagerRepairer*/
		NonRuleBasedDamagerRepairer ndr = new NonRuleBasedDamagerRepairer(
				new TextAttribute(colorManager.getColor(DockerFileColorConstants.COMMENT)));
		reconciler.setDamager(ndr, DockerFilePartitionScanner.DF_COMMENT);
		reconciler.setRepairer(ndr, DockerFilePartitionScanner.DF_COMMENT);

		return reconciler;
	}
	
	/*Private method used for instructionScanner initialization */	
	private InstructionScanner getInstructionScanner() {
		if (instructionScanner == null) {
			instructionScanner = new InstructionScanner(colorManager);
			instructionScanner.setDefaultReturnToken(new Token (
					new TextAttribute(colorManager.getColor(DockerFileColorConstants.ARGS))));
		}
		return instructionScanner;
	}

}