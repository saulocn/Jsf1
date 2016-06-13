package br.com.caelum.livraria.bean;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;

import br.com.caelum.livraria.dao.UsuarioDao;
import br.com.caelum.livraria.modelo.Usuario;

@ManagedBean
@ViewScoped
public class LoginBean {

	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Map<String, Object> getAtributos() {
		HtmlInputText inputText = new HtmlInputText();
		Map<String, Object> atributos = inputText.getPassThroughAttributes();
		atributos.put("type", "email");
		atributos.put("placeholder", "Entre com seu email");
		return atributos;
	}

	public String efetuarLogin() {
		System.out.println("Efetuar Login! - " + this.usuario.getLogin());
		FacesContext context = FacesContext.getCurrentInstance();
		if (new UsuarioDao().existe(this.usuario)) {
			context.getExternalContext().getSessionMap().put("usuario", usuario);
			return "livro?faces-redirect=true";
		} else {
			//context.addMessage("login:email", new FacesMessage("Login e/ou Senha Inválida!"));
			context.getExternalContext().getFlash().setKeepMessages(true);
			context.addMessage(null, new FacesMessage("Login e/ou Senha Inválida!"));
			return "login?faces-redirect=true";
		}
	}
	
	public String logout(){
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("usuario");
		return "login?faces-redirect=true";
	}
}
