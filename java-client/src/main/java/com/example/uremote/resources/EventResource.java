package com.example.uremote.resources;

import com.example.uremote.api.UserEvent;
import com.example.uremote.handler.URemoteHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
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
    public void receive(@Valid UserEvent userEvent) {
        LOGGER.info("Received a saying: {}", userEvent);
    }

    @POST
    @Path("kev")
    public String receiveKev(String kev) {
        LOGGER.info("key event is" + kev);
        try {
            URemoteHandler uRemoteHandler = new URemoteHandler();
            uRemoteHandler.process(kev);
        }catch (AWTException e){
            LOGGER.error(e.getMessage());
            return "failure while processing your request";
        }
        return "success";
    }

}
