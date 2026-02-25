package io.nthcristian.projedata.controller

import io.nthcristian.projedata.dto.RawMaterialCreateDTO
import io.nthcristian.projedata.dto.RawMaterialUpdateDTO
import io.nthcristian.projedata.service.RawMaterialService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/raw-materials")
class RawMaterialController(private val service: RawMaterialService) {
    @GetMapping
    fun list() = ResponseEntity.ok(service.getAll())

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = ResponseEntity.ok(service.getById(id))

    @PostMapping
    fun create(@RequestBody @Valid data: RawMaterialCreateDTO) =
        ResponseEntity.status(HttpStatus.CREATED).body(service.create(data))

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long, @RequestBody @Valid data: RawMaterialUpdateDTO
    ) = ResponseEntity.ok(service.update(id, data))

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        service.delete(id)
        return ResponseEntity.noContent().build()
    }
}