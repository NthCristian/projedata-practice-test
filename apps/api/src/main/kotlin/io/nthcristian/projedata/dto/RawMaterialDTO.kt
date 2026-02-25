package io.nthcristian.projedata.dto

import io.nthcristian.projedata.model.RawMaterialModel
import jakarta.validation.constraints.PositiveOrZero
import jakarta.validation.constraints.Size
import java.math.BigDecimal

data class RawMaterialCreateDTO(
    @Size(max = 50) val code: String,
    @Size(max = 150) val name: String,
    @PositiveOrZero val stockQuantity: BigDecimal,
    @Size(max = 20) val unitOfMeasure: String?
) : ApplyChanges<RawMaterialModel> {
    override fun apply(entity: RawMaterialModel) {
        entity.code = code
        entity.name = name
        entity.stockQuantity = stockQuantity

        unitOfMeasure?.let { entity.unitOfMeasure = it }
    }
}

data class RawMaterialUpdateDTO(
    @Size(max = 50) val code: String?,
    @Size(max = 150) val name: String?,
    @PositiveOrZero val stockQuantity: BigDecimal?,
    @Size(max = 20) val unitOfMeasure: String?
) : ApplyChanges<RawMaterialModel> {
    override fun apply(entity: RawMaterialModel) {
        code?.let { entity.code = it }
        name?.let { entity.name = it }
        stockQuantity?.let { entity.stockQuantity = it }
        unitOfMeasure?.let { entity.unitOfMeasure = it }
    }
}