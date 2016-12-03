/*package br.com.caelum.vraptor.interceptor;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;

@Intercepts(after=AutorizadorInterceptor.class)
public class ControleDeTransacaoInterceptor {

	private EntityManager em;
	
	@Inject
	public ControleDeTransacaoInterceptor(EntityManager em) {
		this.em = em;
	}
	
	public ControleDeTransacaoInterceptor(){
		this(null);
	}
	
	@AroundCall
	public void intercepta(SimpleInterceptorStack stack){
		
		em.getTransaction().begin();
		stack.next();
		em.getTransaction().commit();
	}
	
	
}
*/