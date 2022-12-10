package ru.gur.archintercessor.interaction.productdictionary;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.interaction.productdictionary.response.ProductInfo;

@Primary
@Component
@ConditionalOnProperty(prefix = "interaction", name = "productdictionary.stubEnabled", matchIfMissing = false)
public class ProductDictionaryClientStub implements ProductDictionaryClient {

    @Override
    public ProductInfo getProductInfo(final String productId) {
        System.out.println("STUB method " + Thread.currentThread().getStackTrace()[0].getMethodName() + " of class" +
                this.getClass().getSimpleName() + " called");

        return ProductInfo.builder()
                .productName("Супер VIP \"" + RandomStringUtils.randomAlphanumeric(5) + "\"")
                .rate(0.1)
                .active(productId.equals("111"))
//                .active(new Random().nextBoolean())
                .build();
    }
}
