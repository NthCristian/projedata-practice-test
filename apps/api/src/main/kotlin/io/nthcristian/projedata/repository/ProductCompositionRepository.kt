package io.nthcristian.projedata.repository

import io.nthcristian.projedata.model.ProductCompositionModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProductCompositionRepository : JpaRepository<ProductCompositionModel, Long> {
    fun findByProductId(productId: Long): List<ProductCompositionModel>
    fun findByProductIdAndRawMaterialId(productId: Long, rawMaterialId: Long): Optional<ProductCompositionModel>
    fun existsByProductIdAndRawMaterialId(productId: Long, rawMaterialId: Long): Boolean
}