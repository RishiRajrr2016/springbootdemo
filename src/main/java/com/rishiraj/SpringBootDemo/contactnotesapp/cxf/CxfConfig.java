package com.rishiraj.SpringBootDemo.contactnotesapp.cxf;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import jakarta.xml.ws.Endpoint;

@Configuration
public class CxfConfig {
    @Bean
    public Endpoint contactNotesEndpoint(Bus bus, ContactNotesWebServiceImpl contactNotesWebServiceImpl) {
        EndpointImpl endpoint = new EndpointImpl(bus, contactNotesWebServiceImpl);
        endpoint.publish("/ContactNotesService");
        return endpoint;
    }
}
