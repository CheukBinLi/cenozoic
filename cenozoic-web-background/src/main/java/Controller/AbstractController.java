package Controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractController {
	protected final Map<String, Object> getParams(HttpServletRequest request) {
		Enumeration<String> en = request.getParameterNames();
		Map<String, Object> map = new HashMap<String, Object>();
		String name;
		while (en.hasMoreElements()) {
			name = en.nextElement();
			map.put(name, request.getParameter(name));
		}
		return map;
	}
}
