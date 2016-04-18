package Controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.freepig.cenozoic.code.dbmapper.entity.User;
import com.freepig.cenozoic.code.dbmapper.service.UserService;

import util.RandomImage;
import util.ResponseCharset;

@Scope("prototype")
@Controller
@RequestMapping({ "/*", "/", "/**/**" })
public class BaseController extends AbstractController {

	@Autowired
	private UserService userService;

	@RequestMapping("{path}")
	public ModelAndView basePath(HttpServletRequest request, HttpServletResponse response, @PathVariable("path") String path) throws IOException {
		return new ModelAndView(path, getParams(request));
	}

	@RequestMapping("login")
	@ResponseBody
	public Object login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User u = null;
		try {
			u = userService.login(getParams(request));
			if (null != u)
				return 1;
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return 0;
	}

	// 验证码
	@RequestMapping(value = "verificationcode")
	public void ramdonImage(HttpServletRequest request, HttpServletResponse response) {
		// @RequestParam("validation") boolean validation,
		// @RequestParam("verificationCode") String verificationCode
		System.out.println("verificationcode");
		String o = request.getParameter("validation");
		boolean validation = (null != o && o.equalsIgnoreCase("true")) ? true : false;
		String verificationCode = request.getParameter("verificationCode");
		if (validation) {
			RandomImage.randomImage(response, request, 100, 30, 18);
			return;
		}
		// 验证
		response = ResponseCharset.responseChangeEncodeUTF8(response);
		try {
			String result = "fail";
			if (null != verificationCode && verificationCode.toUpperCase().equals(request.getSession().getAttribute("verificationCode"))) {
				result = "success";
			}
			response.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
