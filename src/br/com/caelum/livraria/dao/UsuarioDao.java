package br.com.caelum.livraria.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.caelum.livraria.modelo.Usuario;

public class UsuarioDao {

	public Boolean existe(Usuario usuario) {
		Boolean existe = false;
		EntityManager em = new JPAUtil().getEntityManager();
		TypedQuery<Usuario> query = em.createQuery("select u from Usuario u where u.login=:pLogin and u.senha=:pSenha",
				Usuario.class);
		query.setParameter("pLogin", usuario.getLogin());
		query.setParameter("pSenha", usuario.getSenha());
		try {
			Usuario result = query.getSingleResult();
			if (result != null) {
				existe = true;
			}
		} catch (NoResultException e) {
			existe = false;
		}
		System.out.println("Existe>>>>" + existe);
		em.close();

		return existe;
	}
}
