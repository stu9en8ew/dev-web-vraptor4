package br.com.caelum.vraptor.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.annotation.Log;
import br.com.caelum.vraptor.dao.ProdutoDao;
import br.com.caelum.vraptor.model.Produto;
import br.com.caelum.vraptor.simplemail.Mailer;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;

@Controller
public class ProdutoController {

	private final Result result;
	private final ProdutoDao dao;
	private final Validator validator;
	private final Mailer mailer;

	@Inject
	public ProdutoController(Result result, ProdutoDao dao, Validator validator, Mailer mailer){
		this.result = result;
		this.dao = dao;
		this.validator = validator;
		this.mailer = mailer;
	}
	
	public ProdutoController(){
		this(null, null, null, null);
	}

	
	
	@Get("/")
	public void inicio(){
	}
	
	@Get
	public void sobre(){
	}
	
	@Get
	public void lista(){
		//ProdutoDao dao = new ProdutoDao(JPAUtil.criaEntityManager());
		//List<Produto> lista = new ArrayList<Produto>();
		//lista = dao.lista();
		result.include("produtoList", dao.lista());
	}
	
	
	@Get
	public void listaXML(){
		//ProdutoDao dao = new ProdutoDao(JPAUtil.criaEntityManager());
		//List<Produto> lista = new ArrayList<Produto>();
		//lista = dao.lista();
		result.use(Results.xml()).from(dao.lista()).serialize();
	}
	
	@Get
	public void listaJSON(){
		//ProdutoDao dao = new ProdutoDao(JPAUtil.criaEntityManager());
		//List<Produto> lista = new ArrayList<Produto>();
		//lista = dao.lista();
		result.use(Results.json()).from(dao.lista()).serialize();
	}

	
	@Get @Log
	public void formulario(){
	}
	
	
	@Post
	public void adiciona(@Valid Produto produto){
		//validator.check(produto.getQuantidade()>0, new SimpleMessage("erro", "Não pode cadastrar um produto com quantidade negativa."));
		//validator.check(produto.getQuantidade()>0, new I18nMessage("erro", "produto.quantidade.negativa"));
		validator.onErrorUsePageOf(this).formulario();
		
		dao.adiciona(produto);
		result.include("mensagem", "Produto adicionado com sucesso!");
		result.redirectTo(ProdutoController.class).lista();
	}
	
	
	@Path("/produto/remove")
	public void remove(Produto produto){
		dao.remove(produto);
		result.include("mensagem", "Produto removido com sucesso!");
		result.redirectTo(ProdutoController.class).lista();
	}
	
	
	public void enviaPedidoDeNovosItens(Produto produto) throws EmailException{
		Email email = new SimpleEmail();
		email.setSubject("[vraptor-produtos] Precisamos de mais estoque");
		email.addTo("abc@yahoo.com.br");
		email.setMsg("Precisamos de mais itens do produto" + produto.getNome());
		mailer.send(email);
		result.redirectTo(this).lista();
	}
	
	
	
	
}
