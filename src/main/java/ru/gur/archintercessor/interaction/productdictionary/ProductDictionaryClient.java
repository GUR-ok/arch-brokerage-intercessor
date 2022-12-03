package ru.gur.archintercessor.interaction.productdictionary;

import ru.gur.archintercessor.interaction.productdictionary.response.ProductInfo;

public interface ProductDictionaryClient {

    ProductInfo getProductInfo(String productId);
}
