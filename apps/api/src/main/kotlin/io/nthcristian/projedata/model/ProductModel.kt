package io.nthcristian.projedata.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.PositiveOrZero
import jakarta.validation.constraints.Size
import java.math.BigDecimal


@Entity
@Table(name = "products")
class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @Size(max = 50)
    @NotNull
    @Column(name = "code", nullable = false, length = 50)
    var code: String? = null

    @Size(max = 150)
    @NotNull
    @Column(name = "name", nullable = false, length = 150)
    var name: String? = null

    @NotNull
    @PositiveOrZero
    @Column(name = "value", nullable = false, precision = 12, scale = 2)
    var value: BigDecimal? = null
}
