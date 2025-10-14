package com.rishiraj.SpringBootDemo.contactnotesapp.cxf;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.Counter;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@WebService(endpointInterface = "com.rishiraj.SpringBootDemo.contactnotesapp.cxf.ContactNotesWebService")
@Component
public class ContactNotesWebServiceImpl implements ContactNotesWebService {
    private final MeterRegistry meterRegistry;
    private final Counter requestCounter;
    private final Counter response2xxCounter;
    private final Counter response4xxCounter;
    private final Counter response5xxCounter;
    private final Timer responseTimer;

    @Autowired
    public ContactNotesWebServiceImpl(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        this.requestCounter = meterRegistry.counter("cxf.requests.count");
        this.response2xxCounter = meterRegistry.counter("cxf.responses.2xx.count");
        this.response4xxCounter = meterRegistry.counter("cxf.responses.4xx.count");
        this.response5xxCounter = meterRegistry.counter("cxf.responses.5xx.count");
        this.responseTimer = meterRegistry.timer("cxf.responses.time");
    }

    @Override
    @WebMethod
    public String ping() {
        requestCounter.increment();
        return responseTimer.record(() -> {
            // Simulate response status code logic
            int statusCode = 200; // Replace with actual logic if needed
            if (statusCode >= 200 && statusCode < 300) {
                response2xxCounter.increment();
            } else if (statusCode >= 400 && statusCode < 500) {
                response4xxCounter.increment();
            } else if (statusCode >= 500) {
                response5xxCounter.increment();
            }
            return "CXF Service is running";
        });
    }
}
