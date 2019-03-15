package rest;

import entities.Heater;
import entities.Home;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class SampleWebService {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello() {
        return "Hello, how are you?";
    }



    @GET
    @Path("/home")
    @Produces(MediaType.APPLICATION_JSON)
    public Home getHome() {
        Home h = new Home();
        h.setName("toto");
        Heater h1 = new Heater();
        h1.setPower("500w");
        Heater h2 = new Heater();
        h2.setPower("600w");
        h.addDevice(h1);
        h.addDevice(h2);
        return h;
    }

}
