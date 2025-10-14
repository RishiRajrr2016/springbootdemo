package com.rishiraj.SpringBootDemo.contactnotesapp.cxf;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;

@WebService
public interface ContactNotesWebService {
    @WebMethod
    String ping();
}
