package ru.gur.archintercessor.Interection;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.gur.archintercessor.Interection.request.CreateOrderRequest;

import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class CrudShopRestClientImpl implements CrudShotRestClient{

    private final RestTemplate exchangeRateRestTemplate;

    @Override
    public UUID createProduct(final UUID customerId,
                              final CreateOrderRequest request,
                              final String key) {
        try {
            log.info("Делаем вызов на создание продукта из сервиса CrudShop ");

            return exchangeRateRestTemplate.getForObject("/my-app/orders", UUID.class);
        } catch (Exception e) {
            log.error("Ошибка создание продукта из сервиса CrudShop: " + e.getMessage());
            return null;
        }
    }
}
