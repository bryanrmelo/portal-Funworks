package br.com.keyworks.business;

import java.security.Principal;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class LoginBean {

	@Inject
	private Principal principal;

	public Principal getPrincipal() {
		return principal;
	}

}
