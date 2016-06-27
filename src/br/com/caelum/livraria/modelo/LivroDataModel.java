package br.com.caelum.livraria.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.caelum.livraria.dao.DAO;

public class LivroDataModel extends LazyDataModel<Livro> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2036840763021827679L;

	public LivroDataModel() {
		System.out.println(new DAO<Livro>(Livro.class).quantidadeDeElementos());
		super.setRowCount(new DAO<Livro>(Livro.class).quantidadeDeElementos());
	}

	/*
	 * @Override public List<Livro> load(int inicio, int quantidade, S
	 * sentidoOrdenacao, Map<String, Object> filtros) { String titulo = (String)
	 * filtros.get("titulo"); return new
	 * DAO<Livro>(Livro.class).listaTodosPaginada(inicio, quantidade, "titulo",
	 * titulo); }
	 */
	@Override
	public List<Livro> load(int inicio, int quantidade, String sortField, SortOrder sortOrder,
			Map<String, Object> filtros) {
		List<Parametro> parametros = new ArrayList<Parametro>();

		String titulo = (String) filtros.get("titulo");
		String genero = (String) filtros.get("genero");
		
		if(titulo!=null){
			parametros.add(new Parametro("titulo", titulo));
		}
		
		if(genero!=null){
			parametros.add(new Parametro("genero", genero));
		}
		return new DAO<Livro>(Livro.class).listaTodosPaginada(inicio, quantidade, parametros);
	}
}
