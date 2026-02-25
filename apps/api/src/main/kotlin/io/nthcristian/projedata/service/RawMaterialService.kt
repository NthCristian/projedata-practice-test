package io.nthcristian.projedata.service

import io.nthcristian.projedata.dto.RawMaterialCreateDTO
import io.nthcristian.projedata.dto.RawMaterialUpdateDTO
import io.nthcristian.projedata.model.RawMaterialModel
import io.nthcristian.projedata.repository.RawMaterialRepository
import org.springframework.stereotype.Service

@Service
class RawMaterialService(override val repo: RawMaterialRepository) :
    ServiceBase<RawMaterialModel, Long, RawMaterialRepository>(repo) {
    fun create(data: RawMaterialCreateDTO) = super.create(data, ::RawMaterialModel)
    fun update(id: Long, changes: RawMaterialUpdateDTO): RawMaterialModel = super.update(id, changes)
}