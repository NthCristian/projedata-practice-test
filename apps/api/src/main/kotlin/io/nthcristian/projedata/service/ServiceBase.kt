package io.nthcristian.projedata.service

import io.nthcristian.projedata.dto.ApplyChanges
import io.nthcristian.projedata.repository.custom.CodeRepository
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional

abstract class ServiceBase<M : Any, ID : Any, R : CodeRepository<M, ID>>(open val repo: R) {
    fun getAll() = repo.findAll()
    fun getById(id: ID) = repo.findById(id).orElseThrow { EntityNotFoundException() } as M
    fun getByCode(code: String) = repo.findByCode(code).orElseThrow { EntityNotFoundException() } as M

    @Transactional
    protected open fun create(dto: ApplyChanges<M>, constructor: () -> M): M {
        val entity = constructor()
        dto.apply(entity)
        return repo.save(entity)
    }

    @Transactional
    protected open fun update(id: ID, dto: ApplyChanges<M>): M {
        val entity = getById(id)
        dto.apply(entity)
        return repo.save(entity)
    }

    @Transactional
    open fun delete(id: ID) {
        if (!repo.existsById(id)) throw EntityNotFoundException()

        repo.deleteById(id)
    }
}