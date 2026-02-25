package io.nthcristian.projedata.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.PositiveOrZero
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.math.BigDecimal


@Entity
@Table(name = "product_compositions")
class ProductCompositionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "product_id", nullable = false)
    var product: ProductModel? = null

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "raw_material_id", nullable = false)
    var rawMaterial: RawMaterialModel? = null

    @NotNull
    @PositiveOrZero
    @Column(name = "required_quantity", nullable = false, precision = 12, scale = 4)
    var requiredQuantity: BigDecimal? = null
}