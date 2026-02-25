package io.nthcristian.projedata.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.PositiveOrZero
import jakarta.validation.constraints.Size
import org.hibernate.annotations.ColumnDefault
import java.math.BigDecimal


@Entity
@Table(name = "raw_materials")
class RawMaterialModel {
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
    @Column(name = "stock_quantity", nullable = false, precision = 12, scale = 4)
    var stockQuantity: BigDecimal? = null

    @Size(max = 20)
    @ColumnDefault("'UN'")
    @Column(name = "unit_of_measure", length = 20)
    var unitOfMeasure: String? = null
}