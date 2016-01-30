package org.gehennas.dockerfileplugin.editors.util;

/**
 * Storage for instruction keywords
 * 
 * @author Nikita Zverev
 * @version 1.0.1
 */
public enum Instruction {
	FROM,
	MAINTANER,
	RUN,
	CMD,
	LABEL,
	EXPOSE,
	ADD,
	ENV,
	COPY,
	ENTRYPOINT,
	VOLUME,
	USER,
	WORKDIR,
	ARG,
	ONBUILD,
	STOPSIGNAL	
}
