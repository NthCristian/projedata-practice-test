package io.nthcristian.projedata.dto

import jakarta.validation.constraints.Positive
import java.math.BigDecimal

data class ProductCompositionDTO(
    val id: Long,
    val rawMaterialId: Long,
    val rawMaterialCode: String,
    val rawMaterialName: String,
    val requiredQuantity: BigDecimal
)

data class AddMaterialToCompositionDTO(
    @Positive
    val rawMaterialId: Long,

    @Positive
    val requiredQuantity: BigDecimal
)

data class UpdateCompositionDTO(
    val rawMaterialId: Long,

    @Positive
    val requiredQuantity: BigDecimal
)

data class RemoveMaterialFromCompositionDTO(
    val rawMaterialId: Long
)
