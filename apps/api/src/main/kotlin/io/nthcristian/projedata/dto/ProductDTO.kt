package io.nthcristian.projedata.dto

import io.nthcristian.projedata.model.ProductModel
import jakarta.validation.constraints.PositiveOrZero
import jakarta.validation.constraints.Size
import java.math.BigDecimal

data class ProductUpdateDTO(
    @Size(max = 50) val code: String?,
    @Size(max = 150) val name: String?,
    @PositiveOrZero val value: BigDecimal?
) : ApplyChanges<ProductModel> {
    override fun apply(entity: ProductModel) {
        code?.let { entity.code = it }
        name?.let { entity.name = it }
        value?.let { entity.value = it }
    }
}

data class ProductCreateDTO(
    @Size(max = 50) val code: String,
    @Size(max = 150) val name: String,
    @PositiveOrZero val value: BigDecimal
) : ApplyChanges<ProductModel> {
    override fun apply(entity: ProductModel) {
        entity.code = code
        entity.name = name
        entity.value = value
    }
}