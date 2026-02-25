package io.nthcristian.projedata.service

import io.nthcristian.projedata.dto.*
import io.nthcristian.projedata.model.ProductCompositionModel
import io.nthcristian.projedata.model.ProductModel
import io.nthcristian.projedata.repository.ProductCompositionRepository
import io.nthcristian.projedata.repository.ProductRepository
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class ProductService(
    override val repo: ProductRepository,
    val compositionRepo: ProductCompositionRepository,
    val rawMaterialService: RawMaterialService
) : ServiceBase<ProductModel, Long, ProductRepository>(repo) {
    fun create(data: ProductCreateDTO) = super.create(data, ::ProductModel)
    fun update(id: Long, changes: ProductUpdateDTO) = super.update(id, changes)

    fun listComposition(productId: Long): List<ProductCompositionDTO> {
        // Verify product exists
        getById(productId)

        return compositionRepo.findByProductId(productId)
            .map { composition ->
                val rawMaterial = composition.rawMaterial
                    ?: throw EntityNotFoundException("Raw material not found")

                ProductCompositionDTO(
                    id = composition.id!!,
                    rawMaterialId = rawMaterial.id!!,
                    rawMaterialCode = rawMaterial.code!!,
                    rawMaterialName = rawMaterial.name!!,
                    requiredQuantity = composition.requiredQuantity!!
                )
            }
    }

    @Transactional
    fun addMaterialToComposition(productId: Long, data: AddMaterialToCompositionDTO): ProductCompositionModel {
        val product = getById(productId)

        val rawMaterial = rawMaterialService.getById(data.rawMaterialId)

        if (compositionRepo.existsByProductIdAndRawMaterialId(productId, data.rawMaterialId)) {
            throw IllegalStateException("Dependency already exists for this product and raw material")
        }

        val composition = ProductCompositionModel().apply {
            this.product = product
            this.rawMaterial = rawMaterial
            this.requiredQuantity = data.requiredQuantity
        }

        return compositionRepo.save(composition)
    }

    @Transactional
    fun updateComposition(productId: Long, data: UpdateCompositionDTO): ProductCompositionModel {
        // Verify product exists
        getById(productId)

        val composition = compositionRepo.findByProductIdAndRawMaterialId(
            productId,
            data.rawMaterialId
        ).orElseThrow {
            EntityNotFoundException(
                "Dependency not found: Product $productId, Raw Material ${data.rawMaterialId}"
            )
        }

        // Verify material exists
        rawMaterialService.getById(data.rawMaterialId)

        composition.requiredQuantity = data.requiredQuantity

        return compositionRepo.save(composition)
    }

    @Transactional
    fun removeMaterialFromComposition(productId: Long, data: RemoveMaterialFromCompositionDTO) {
        // Verify product exists
        getById(productId)

        val composition = compositionRepo.findByProductIdAndRawMaterialId(
            productId,
            data.rawMaterialId
        ).orElseThrow {
            EntityNotFoundException(
                "Dependency not found: Product $productId, Raw Material ${data.rawMaterialId}"
            )
        }

        compositionRepo.delete(composition)
    }
}