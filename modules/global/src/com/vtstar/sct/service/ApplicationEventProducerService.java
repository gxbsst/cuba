package com.vtstar.sct.service;

/**
 * Application event publishing mechanism (implemented through Spring)
 */
public interface ApplicationEventProducerService {
    String NAME = "sct_ApplicationEventProducerService";

    void produceApplicationEvent(Object event);

}