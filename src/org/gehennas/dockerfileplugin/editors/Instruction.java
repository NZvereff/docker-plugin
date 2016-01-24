package org.gehennas.dockerfileplugin.editors;

public enum Instruction { //List of all available instructions
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