package ru.gur.archintercessor.kafka;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "event"
)
@JsonSubTypes({
//        @JsonSubTypes.Type(value = OrderApproveEventData.class, name = "ORDER_APPROVE"),
//        @JsonSubTypes.Type(value = OrderCancelEventData.class, name = "ORDER_CANCEL"),
//        @JsonSubTypes.Type(value = DeliveryCancelEventData.class, name = "DELIVERY_CANCEL"),
//        @JsonSubTypes.Type(value = ProductReserveCancelEventData.class, name = "PRODUCT_RESERVE_CANCEL")
})
public interface KafkaEvent extends EventSource {
}