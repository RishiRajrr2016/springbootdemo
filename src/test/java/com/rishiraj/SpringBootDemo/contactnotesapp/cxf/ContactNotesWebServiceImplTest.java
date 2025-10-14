package com.rishiraj.SpringBootDemo.contactnotesapp.cxf;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ContactNotesWebServiceImplTest {
    @Mock
    private MeterRegistry meterRegistry;
    @Mock
    private Counter requestCounter;
    @Mock
    private Counter response2xxCounter;
    @Mock
    private Counter response4xxCounter;
    @Mock
    private Counter response5xxCounter;
    @Mock
    private Timer responseTimer;

    private ContactNotesWebServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(meterRegistry.counter("cxf.requests.count")).thenReturn(requestCounter);
        when(meterRegistry.counter("cxf.responses.2xx.count")).thenReturn(response2xxCounter);
        when(meterRegistry.counter("cxf.responses.4xx.count")).thenReturn(response4xxCounter);
        when(meterRegistry.counter("cxf.responses.5xx.count")).thenReturn(response5xxCounter);
        when(meterRegistry.timer("cxf.responses.time")).thenReturn(responseTimer);
        service = new ContactNotesWebServiceImpl(meterRegistry);
    }

    @Test
    void testPingMetricsRecorded() {
        when(responseTimer.record(any(java.util.function.Supplier.class))).thenAnswer(invocation -> {
            java.util.function.Supplier<?> supplier = invocation.getArgument(0);
            return supplier.get();
        });
        String result = service.ping();
        assertEquals("CXF Service is running", result);
        verify(requestCounter, times(1)).increment();
        verify(response2xxCounter, times(1)).increment();
        verify(response4xxCounter, never()).increment();
        verify(response5xxCounter, never()).increment();
        verify(responseTimer, times(1)).record(any(java.util.function.Supplier.class));
    }
}
