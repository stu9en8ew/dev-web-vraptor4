package br.com.caelum.vraptor.interceptor;

import javax.inject.Inject;

import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.annotation.Public;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.controller.LoginController;
import br.com.caelum.vraptor.controller.UsuarioLogado;

@Intercepts
public class AutorizadorInterceptor {
	
	private final UsuarioLogado usuarioLogado;
	private final Result result;
	private final ControllerMethod controllerMethod;
	
	
	@Inject
	public AutorizadorInterceptor(UsuarioLogado usuarioLogado, Result result, ControllerMethod controllerMethod){
		this.usuarioLogado = usuarioLogado;
		this.result = result;
		this.controllerMethod = controllerMethod;
	}
	
	public AutorizadorInterceptor() {
		this(null, null, null);
	}
	
	
	@Accepts
	public boolean accepts(){
		return !controllerMethod.containsAnnotation(Public.class);
	}
	
	@AroundCall
	public void intercepta(SimpleInterceptorStack stack){
		
		//antes de executar meu código
		
		if (usuarioLogado.getUsuario() == null) {
			result.redirectTo(LoginController.class).formulario();
			return;
		}
		
		stack.next();
		
		// depois de executar meu código 
	}

	
}
