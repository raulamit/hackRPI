package com.example.uremote.resources;

import com.example.uremote.api.Event;
import com.example.uremote.api.MouseMoveEvent;
import com.example.uremote.api.UserEvent;
import com.example.uremote.handler.URemoteHandler;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.awt.*;

/**
 * Created by raul on 12/11/16.
 */
@Path("/event")
public class EventResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventResource.class);

    @POST
    public String receive(UserEvent userEvent ){

        LOGGER.info("Received a saying: {}", userEvent);
        try {
            URemoteHandler uRemoteHandler = new URemoteHandler();
            uRemoteHandler.process(userEvent.getEvent());
        } catch (AWTException e) {
            LOGGER.error(e.getMessage());
            return "failure while processing your request";
        } catch (Exception e){
            LOGGER.error(e.getMessage());
            return "failure typcasting the event";
        }

        return "Success";
    }

    @GET
    @Path("kev")
    public String receiveKev() {
        String kev ="a";
        LOGGER.info("key event is" + kev);
        try {
            URemoteHandler uRemoteHandler = new URemoteHandler();
//            uRemoteHandler.process(kev);
        }catch (AWTException e){
            LOGGER.error(e.getMessage());
            return "failure while processing your request";
        }
        return "success";
    }
}
