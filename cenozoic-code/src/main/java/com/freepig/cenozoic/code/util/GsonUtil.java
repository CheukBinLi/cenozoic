package com.freepig.cenozoic.code.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {

	public static void main(String[] args) {
		new GsonUtil().test();
	}

	final class DefaultExclusionStrategy implements ExclusionStrategy {

		private Set<String> widthOutFiled;

		public boolean shouldSkipField(FieldAttributes f) {
			return widthOutFiled.contains(f.getName());
		}

		public boolean shouldSkipClass(Class<?> clazz) {
			return false;
		}

		public DefaultExclusionStrategy(String... fields) {
			this.widthOutFiled = new HashSet<String>(Arrays.asList(fields));
		}
	}

	void test() {
		test t = new test(1, "2", true);

		Gson g = new GsonBuilder().setExclusionStrategies(new DefaultExclusionStrategy("a")).create();
		System.err.println(g.toJson(t));
	}

	class test {
		private int a;
		private String b;
		private boolean c;

		public test(int a, String b, boolean c) {
			super();
			this.a = a;
			this.b = b;
			this.c = c;
		}

	}

}
