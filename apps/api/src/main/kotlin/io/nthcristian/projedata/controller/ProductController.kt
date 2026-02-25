package io.nthcristian.projedata.controller

import io.nthcristian.projedata.dto.*
import io.nthcristian.projedata.service.ProductService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/products")
class ProductController(private val service: ProductService) {
    @GetMapping
    fun list() = ResponseEntity.ok(service.getAll())

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = ResponseEntity.ok(service.getById(id))

    @PostMapping
    fun create(@RequestBody @Valid data: ProductCreateDTO) =
        ResponseEntity.status(HttpStatus.CREATED).body(service.create(data))

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody @Valid data: ProductUpdateDTO) =
        ResponseEntity.ok(service.update(id, data))

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        service.delete(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/{id}/composition")
    fun listComposition(@PathVariable id: Long) = ResponseEntity.ok(service.listComposition(id))

    @PostMapping("/{id}/composition")
    fun addMaterialToComposition(
        @PathVariable id: Long, @RequestBody @Valid data: AddMaterialToCompositionDTO
    ) = ResponseEntity.status(HttpStatus.CREATED).body(service.addMaterialToComposition(id, data))

    @PutMapping("/{id}/composition")
    fun updateComposition(
        @PathVariable id: Long, @RequestBody @Valid data: UpdateCompositionDTO
    ) = ResponseEntity.ok(service.updateComposition(id, data))

    @DeleteMapping("/{id}/composition")
    fun removeMaterialFromComposition(
        @PathVariable id: Long, @RequestBody @Valid data: RemoveMaterialFromCompositionDTO
    ): ResponseEntity<Void> {
        service.removeMaterialFromComposition(id, data)
        return ResponseEntity.noContent().build()
    }
}