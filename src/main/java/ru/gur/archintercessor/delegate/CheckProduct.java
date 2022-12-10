package ru.gur.archintercessor.delegate;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.interaction.productdictionary.ProductDictionaryClient;
import ru.gur.archintercessor.interaction.productdictionary.response.ProductInfo;
import ru.gur.archintercessor.process.VariableKey;

@Component
@RequiredArgsConstructor
public class CheckProduct extends AbstractRetryableDelegate {

    private final ProductDictionaryClient productDictionaryClient;

    @Override
    protected void doExecute(final DelegateExecution delegateExecution) {
        final String productId = (String) delegateExecution.getVariable(VariableKey.PRODUCT_ID.name());

        final ProductInfo productInfo = productDictionaryClient.getProductInfo(productId);
        final boolean isActive = productInfo.getActive();
        final String productName = productInfo.getProductName();

        delegateExecution.setVariable(VariableKey.PRODUCT_NAME.name(), productName);
        delegateExecution.setVariable(VariableKey.PRODUCT_CHECK_RESULT.name(), isActive);
    }
}
