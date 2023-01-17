package de.imedia24.shop.service

import de.imedia24.shop.db.repository.ProductRepository
import de.imedia24.shop.domain.product.ProductResponse
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository) {

    fun findProductBySku(sku: String): ProductResponse? {
        productRepository.findBySku(sku)? : throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun findProductsBySkus(skus: String): ProductResponse? {
        productRepository.findProductsBySkus(skus)? : throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun addProduct(product: ProductDTO): ProductResponse? {
        productRepository.save(product)? : throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun updateProduct(product: ProductDTO): ProductResponse? {
        productRepository.save(product)? : throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}
