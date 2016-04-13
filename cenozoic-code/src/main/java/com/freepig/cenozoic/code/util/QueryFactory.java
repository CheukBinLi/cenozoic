package com.freepig.cenozoic.code.util;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;

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
		System.out.println(files);
		Object o = null;
		try {
			o = Scan.doScan(files);
			System.out.println(o);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
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
}
