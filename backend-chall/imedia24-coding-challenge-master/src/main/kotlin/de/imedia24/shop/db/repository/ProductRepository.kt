package de.imedia24.shop.db.repository

import de.imedia24.shop.db.entity.ProductEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : CrudRepository<ProductEntity, String> {

    fun findBySku(sku: String): ProductEntity?*
    
    @Query("select * from products where sku IN (:skus)")
    fun findProductsBySkus(@Params('skus') skus: String): ProductEntity?

    
}