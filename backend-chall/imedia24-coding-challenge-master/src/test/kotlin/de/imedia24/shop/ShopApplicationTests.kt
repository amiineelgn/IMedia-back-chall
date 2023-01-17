package de.imedia24.shop

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ShopApplicationTests {

	@Test
	fun contextLoads() {
	}

	@Test
	fun 'get list of product details by list of SKUs'(skus){
		val entity = product.getForEntity<String>('/products?skus={skus}')
		assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
		assertThat(entity.body).contains(message.sku)
	}

	@Test
	fun 'update a product'(sku){
		val entity = product.getForEntity<String>('/product/{sku}')
		assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
		assertThat(entity.body).contains(message.sku)
		assertThat(entity.body.name).isEqualTo(message.name)
		assertThat(entity.body.description).isEqualTo(message.description)
		assertThat(entity.body.price).isEqualTo(message.price)
	}

}
