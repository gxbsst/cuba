package com.vtstar.sct.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(ApplicationEventProducerService.NAME)
public class ApplicationEventProducerServiceBean implements ApplicationEventProducerService {

    @Inject
    ApplicationEventPublisher publisher;

    @Override
    public void produceApplicationEvent(Object event) {
        publisher.publishEvent(event);
    }

}