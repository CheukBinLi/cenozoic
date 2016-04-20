package com.freepig.cenozoic.code.dbmapper.entity;

import java.io.Serializable;

public abstract class AbstractEntity implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
