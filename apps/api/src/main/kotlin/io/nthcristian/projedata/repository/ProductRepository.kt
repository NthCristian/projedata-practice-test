package io.nthcristian.projedata.repository

import io.nthcristian.projedata.model.ProductModel
import io.nthcristian.projedata.repository.custom.CodeRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : CodeRepository<ProductModel, Long>