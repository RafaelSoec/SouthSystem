package com.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.exception.ResponseException;
import com.projeto.model.entities.AbstractEntity;

public abstract class AbstractService<T extends AbstractEntity> {

	@Autowired
	private JpaRepository<T, Long> repo;

	public T buscarPorId(Long id) {
		if (id == null) {
			throw new ResponseException("Falta ao tentar recuperar por id: Id nulo.");
		}

		Optional<T> entity = this.repo.findById(id);

		if (!entity.isPresent()) {
			return null;
		}

		return entity.get();
	}

	public List<T> buscarTodos() {
		return this.repo.findAll();
	}

	public T salvar(T entity) {
		if (entity == null) {
			throw new ResponseException("Falha ao tentar salvar: Associado nula.");
		}

		if (entity.getId() == null) {
			return this.repo.save(entity);
		} else {
			throw new ResponseException("Id já cadastrado.");
		}
	}

	public T atualizar(Long id, T entity) {
		T entityRec = this.buscarPorId(id);

		if (entityRec == null) {
			throw new ResponseException("Falta ao tentar atualizar: Id não encontrada.");
		}

		entityRec = entity;
		entityRec.setId(id);
		return this.salvar(entityRec);
	}

	public void excluir(Long id) {
		T entityRec = this.buscarPorId(id);
		this.repo.delete(entityRec);
	}
}
