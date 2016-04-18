package com.freepig.cenozoic.code;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.cache.StringTemplateLoader;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class freemarktest {

	private static final String ax = "FROM User u WHERE 1=1 <#if phone??> and u.phone=:phone </#if> and u.password=:password";

	public static void main(String[] args) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, TemplateException, IOException {

		Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
		StringTemplateLoader loader = new StringTemplateLoader();
		loader.putTemplate("A1", "${name}你好吗？");
		loader.putTemplate("A2", "你好，${name}今天约吗？");
		loader.putTemplate("A3", "不约不约，${name}你不能这么坏。");

		loader.putTemplate("AX", ax);

		cfg.setTemplateLoader(loader);

		StringWriter sw = new StringWriter();

		Map<String, String> name = new HashMap<String, String>();
		name.put("name", "小小明明");
//		name.put("phone", "112");
		cfg.getTemplate("A1").process(name, sw);
		System.out.println(sw.toString());

		cfg.getTemplate("AX").process(name, sw);
		System.out.println(sw.toString());
	}

}
