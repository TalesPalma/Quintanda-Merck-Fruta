package com.talespalma.quitandamerkfrutas.database

import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productDao: ProductDao
) {

    suspend fun getAllProducts(): List<Product>  = productDao.getAllProducts()

    suspend fun insertProduct(product: Product) = productDao.insertProduct(product)

    suspend fun deleteProduct(product: Product) = productDao.deleteProduct(product)


}