package com.freepig.cenozoic.code.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.freepig.cenozoic.code.util.XmlReaderAll.Node;

@Component
public class QueryFactory {

	private final Map<String, String> HQL = new ConcurrentHashMap<String, String>();
	private final Map<String, String> SQL = new ConcurrentHashMap<String, String>();

	private String files;

	private volatile boolean isScan = false;

	public void put(String name, String XQL, boolean isHQL) {
		if (null == name || null == XQL)
			return;
		if (isHQL)
			HQL.put(name, XQL);
		else
			SQL.put(name, XQL);
	}

	public String getXQL(String name, boolean isHQL) {
		if (!isScan)
			scan();
		if (null == name)
			return null;
		if (isHQL)
			return HQL.get(name);
		return SQL.get(SQL);
	}

	private void scan() {
		Set<String> o = null;
		try {
			o = Scan.doScan(files);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		isScan = true;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public void x() throws ParserConfigurationException, SAXException, IOException {
		Object resultObject = null;
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		xmlHandler handler = new xmlHandler();
		String x = null;
		ByteArrayInputStream in = new ByteArrayInputStream(x.getBytes());
		InputSource is = new InputSource(in);
		is.setEncoding("utf-8");
		parser.parse(is, handler);

		String packageName;
		String name;
		String type;
	}

	class xmlHandler extends DefaultHandler {

		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
			super.startElement(uri, localName, qName, attributes);
		}

		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			super.endElement(uri, localName, qName);
		}

	}

}
