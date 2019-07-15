package br.com.taok.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.taok.model.City;

public class CityDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public List<City> obtemTodosOsMunicipios(){

		
		return manager.createQuery("from Municipio",City.class)
				.getResultList();
	}
	
	public City obterMunicipioPorId(Integer id){
		
		return manager.createQuery("from Municipio m where m.id = :id ",City.class)
				.setParameter("id", id)
				.getSingleResult();
	}
}
