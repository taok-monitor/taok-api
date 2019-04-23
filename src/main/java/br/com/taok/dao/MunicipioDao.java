package br.com.taok.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.taok.model.Municipio;

public class MunicipioDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public List<Municipio> obtemTodosOsMunicipios(){

		
		return manager.createQuery("from Municipio",Municipio.class)
				.getResultList();
	}
	
	public Municipio obterMunicipioPorId(Integer id){
		
		return manager.createQuery("from Municipio m where m.id = :id ",Municipio.class)
				.setParameter("id", id)
				.getSingleResult();
	}
}
