package com.freepig.cenozoic.code.util;

import java.util.List;

public class PageEntry<T> {

	private List<T> root;
	private long totalProperty;

	public List<T> getRoot() {
		return root;
	}

	public PageEntry<T> setRoot(List<T> root) {
		this.root = root;
		return this;
	}

	public long getTotalProperty() {
		return totalProperty;
	}

	public PageEntry<T> setTotalProperty(long totalProperty) {
		this.totalProperty = totalProperty;
		return this;
	}

	public PageEntry(List<T> root, long totalProperty) {
		super();
		this.root = root;
		this.totalProperty = totalProperty;
	}
}
