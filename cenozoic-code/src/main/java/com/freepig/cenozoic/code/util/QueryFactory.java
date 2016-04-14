package com.freepig.cenozoic.code.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import freemarker.cache.StringTemplateLoader;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateNotFoundException;

@Component
public class QueryFactory implements QueryType {

	protected Logger log = Logger.getLogger(this.getClass().getName());

	private final Map<String, Template> FORMAT_XQL = new ConcurrentHashMap<String, Template>();
	private final Map<String, String> UNFORMAT_XQL = new ConcurrentHashMap<String, String>();
	private final Configuration freemarkerConfiguration = new Configuration(Configuration.VERSION_2_3_0);
	private StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();

	private String files;

	public QueryFactory() {
		super();
		freemarkerConfiguration.setTemplateLoader(stringTemplateLoader);
	}

	private volatile boolean isScan = false;

	public synchronized void put(String name, String XQL, boolean isFormat) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException {
		if (null == name || null == XQL)
			return;
		if (isFormat) {
			stringTemplateLoader.putTemplate(name, XQL);
			FORMAT_XQL.put(name, freemarkerConfiguration.getTemplate(name));
		} else
			UNFORMAT_XQL.put(name, XQL);

	}

	public String getXQL(String name, boolean isFormat, Map<String, Object> params) {
		if (!isScan)
			scan();
		if (isFormat)
			return UNFORMAT_XQL.get(name);
		Template tp = FORMAT_XQL.get(name);
		if (null == tp)
			return null;
		StringWriter sw = new StringWriter();
		try {
			tp.process(params, sw);
		} catch (Exception e) {
			DefaultLogFactory.newInstance().error(getClass(), String.format("getXQL :%s", name), e);
		}
		return sw.toString();
	}

	private void scan() {
		Set<String> o = null;
		try {
			o = Scan.doScan(files);
			xmlExplain(o);
		} catch (Exception e) {
			DefaultLogFactory.newInstance().error(getClass(), "装载Query文件失败", e);
		}
		isScan = true;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public void xmlExplain(Set<String> urls) throws ParserConfigurationException, SAXException, IOException {
		Iterator<String> it = urls.iterator();
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		xmlHandler handler = new xmlHandler();
		XMLReader xmlReader = parser.getXMLReader();
		xmlReader.setEntityResolver(new EntityResolver() {
			public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
				return new InputSource(this.getClass().getClassLoader().getResourceAsStream("dtd/Query.dtd"));
			}
		});
		while (it.hasNext()) {
			String str = it.next();
			InputSource is = new InputSource(Thread.currentThread().getContextClassLoader().getResourceAsStream(str));
			is.setEncoding("utf-8");
			xmlReader.setContentHandler(handler);
			xmlReader.parse(is);
		}
	}

	class xmlHandler extends DefaultHandler {
		//		private boolean isHQL = false;
		private boolean format = false;
		private String packageName = null;
		private String name = null;

		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
			if (qName.equals(QUERY_LIST)) {
				packageName = attributes.getValue(PACKAGE);
			} else if (qName.equals(QUERY)) {
				//				isHQL = attributes.getValue(TYPE).equals("HQL");
				name = attributes.getValue(NAME);
				format = Boolean.valueOf(attributes.getValue(FREEMARK_FORMAT));
			}
			super.startElement(uri, localName, qName, attributes);
		}

		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			if (length > 0) {
				try {
					put(String.format("%s.%s", packageName, name).toLowerCase(), new String(ch, start, length), format);
				} catch (Exception e) {
					DefaultLogFactory.newInstance().error(getClass(), "xml读取失败", e);
				}
			}
			super.characters(ch, start, length);
		}
	}

}
