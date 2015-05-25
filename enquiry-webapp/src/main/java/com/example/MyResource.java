package com.example;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
@Produces(MediaType.TEXT_HTML)

public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getIt() {
            String myEnquiry = "";
     
            Enquiry enquiry = new Enquiry();
            
            try {
				myEnquiry = enquiry.createEnquiry();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

       
        return myEnquiry;
    }
}
