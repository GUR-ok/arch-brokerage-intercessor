package ru.gur.archintercessor.Interection;

import org.springframework.stereotype.Component;
import ru.gur.archintercessor.Interection.request.CreateOrderRequest;

import java.util.UUID;

@Component
public interface CrudShotRestClient {

    UUID createProduct(UUID customerId,
                       CreateOrderRequest request,
                       String key);
}
