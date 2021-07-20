package br.com.keyworks.util;

import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutUtil {

	public static void logout() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.setAttribute("login", null);
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

		try {
			session.invalidate();
			request.logout();
			FacesContext.getCurrentInstance().getExternalContext().redirect("../public/login");
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}
