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

public class DockerFileConfiguration extends SourceViewerConfiguration {
	private DockerFileDoubleClickStrategy doubleClickStrategy;
	private InstructionScanner instructionScanner;
	private ColorManager colorManager;

	public DockerFileConfiguration(ColorManager colorManager) {
		this.colorManager = colorManager;
	}
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] {
			IDocument.DEFAULT_CONTENT_TYPE,
			DockerFilePartitionScanner.DF_COMMENT };
	}
	public ITextDoubleClickStrategy getDoubleClickStrategy( //Using default DoubleClickStrategy here
		ISourceViewer sourceViewer,
		String contentType) {
		if (doubleClickStrategy == null)
			doubleClickStrategy = new DockerFileDoubleClickStrategy();
		return doubleClickStrategy;
	}

	protected InstructionScanner getInstructionScanner() {
		if (instructionScanner == null) {
			instructionScanner = new InstructionScanner(colorManager);
			instructionScanner.setDefaultReturnToken(new Token (new TextAttribute(colorManager.getColor(DockerFileColorConstants.ARGS))));
		}
		return instructionScanner;
	}
	
	public IAutoEditStrategy[] getAutoEditStrategies(ISourceViewer sourceViewer, String contentType) {
		IAutoEditStrategy strategy= (IDocument.DEFAULT_CONTENT_TYPE.equals(contentType) ? new DockerFileTabulationStrategy() : new DefaultIndentLineAutoEditStrategy());
		return new IAutoEditStrategy[] { strategy };
	}
	
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();
		
		//reconciling default content
		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(getInstructionScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);
		
		//reconciling comments
		NonRuleBasedDamagerRepairer ndr = new NonRuleBasedDamagerRepairer(new TextAttribute(colorManager.getColor(DockerFileColorConstants.COMMENT)));
		reconciler.setDamager(ndr, DockerFilePartitionScanner.DF_COMMENT);
		reconciler.setRepairer(ndr, DockerFilePartitionScanner.DF_COMMENT);

		return reconciler;
	}

}