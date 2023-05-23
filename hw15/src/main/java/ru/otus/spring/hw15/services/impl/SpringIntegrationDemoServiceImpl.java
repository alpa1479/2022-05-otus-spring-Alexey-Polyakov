package ru.otus.spring.hw15.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw15.messaging.gateways.ProductOrderGateway;
import ru.otus.spring.hw15.services.ProductCatalogService;
import ru.otus.spring.hw15.services.SpringIntegrationDemoService;
import ru.otus.spring.hw15.utils.ProductOrderGenerator;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpringIntegrationDemoServiceImpl implements SpringIntegrationDemoService {

    private final ProductOrderGateway productOrderGateway;
    private final ProductCatalogService productCatalogService;

    @Override
    public void start() {
        while (productCatalogService.isNotEmpty()) {
            ProductOrderGenerator.generate().forEach(productOrderGateway::process);
        }
        log.info(">>>> Catalog is empty. No more orders will be processed");
    }
}
