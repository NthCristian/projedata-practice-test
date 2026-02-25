package io.nthcristian.projedata.repository.custom

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean
import java.util.*

@NoRepositoryBean
interface CodeRepository<M : Any, ID : Any> : JpaRepository<M, ID> {
    fun findByCode(code: String): Optional<M>
}