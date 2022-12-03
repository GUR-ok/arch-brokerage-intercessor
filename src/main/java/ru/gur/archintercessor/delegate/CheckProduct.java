package ru.gur.archintercessor.delegate;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.interaction.productdictionary.ProductDictionaryClient;
import ru.gur.archintercessor.process.VariableKey;

@Component
@RequiredArgsConstructor
public class CheckProduct extends AbstractRetryableDelegate {

    private final ProductDictionaryClient productDictionaryClient;

    @Override
    protected void doExecute(final DelegateExecution delegateExecution) {
        final String productId = (String) delegateExecution.getVariable(VariableKey.PRODUCT_ID.name());

        final boolean isActive = productDictionaryClient.getProductInfo(productId).getActive();
        delegateExecution.setVariable(VariableKey.PRODUCT_CHECK_RESULT.name(), isActive);
    }
}
