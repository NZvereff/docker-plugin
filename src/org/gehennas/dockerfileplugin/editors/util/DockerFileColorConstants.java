package org.gehennas.dockerfileplugin.editors.util;

import org.eclipse.swt.graphics.RGB;

public interface DockerFileColorConstants {
	RGB INSTRUCTION = new RGB(128, 0, 0);
	RGB COMMENT = new RGB(128, 128, 128);
	RGB ENV_VAR = new RGB(0, 128, 0);
	RGB DEFAULT = new RGB(0, 0, 0); //Not used
	RGB ARGS = new RGB(0, 0, 128);
	RGB STRING = new RGB (128,128,0);
}
