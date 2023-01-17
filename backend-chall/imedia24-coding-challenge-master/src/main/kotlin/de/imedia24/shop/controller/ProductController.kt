package de.imedia24.shop.controller

import de.imedia24.shop.domain.product.ProductResponse
import de.imedia24.shop.service.ProductService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import javax.websocket.server.PathParam

@RestController
class ProductController(private val productService: ProductService) {

    private val logger = LoggerFactory.getLogger(ProductController::class.java)!!

    @GetMapping("/products/{sku}", produces = ["application/json;charset=utf-8"])
    fun findProductsBySku(
        @PathVariable("sku") sku: String
    ): ResponseEntity<ProductResponse> {
        logger.info("Request for product $sku")

        val product = productService.findProductBySku(sku)
        return if(product == null) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(product)
        }
    }

    @GetMapping("/products?skus={skus}", produces = ["application/json;charset=utf-8"])
    fun findProductsDetailsBySkus(
        @RequestParam("skus") skus: String
    ): ResponseEntity<ProductResponse> {
        logger.info("Request for products details $skus")
        val products = productService.findProductsBySkus(skus)
        return if(products == null) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(product)
        }
    }

    @PostMapping("/product", produces = ["application/json;charset=utf-8"])
    fun addProduct(
        @RequestBody("product") product: ProductDTO
    ): ResponseEntity<ProductResponse> {
        logger.info("Request for add product $product")
        val products = productService.addProduct(product)
        return if(products == null) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(product)
        }
    }

    @PutMapping("/product/{sku}", produces = ["application/json;charset=utf-8"])
    fun updateProduct(
        @PathVariable("sku") sku: String,
        @RequestBody("product") product: ProductDTO, 
    ): ResponseEntity<ProductResponse> {
        logger.info("Request for add product $product")
        var updatedProduct = productService.findProductsBySku(sku)
        updatedProduct.name = product.name
        updatedProduct.description = product.description
        updatedProduct.price = product.price
        val products = productService.update(updatedProduct)
        return if(products == null) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(product)
        }
    }
}
