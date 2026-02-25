package io.nthcristian.projedata.repository

import io.nthcristian.projedata.model.RawMaterialModel
import io.nthcristian.projedata.repository.custom.CodeRepository
import org.springframework.stereotype.Repository

@Repository
interface RawMaterialRepository : CodeRepository<RawMaterialModel, Long>