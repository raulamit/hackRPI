package com.example.uremote.api;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by raul on 12/11/16.
 */
@JsonPropertyOrder({
        "user",
        "event"
})
public class UserEvent {
    @JsonIgnore
    private ObjectMapper objectMapper = new ObjectMapper();
    @JsonProperty("user")
    private String user;
    @JsonProperty("event")
    private Event event;

    /**
     *
     * @return
     * The user
     */
    @JsonProperty("user")
    public String getUser() {
        return user;
    }

    /**
     *
     * @param user
     * The user
     */
    @JsonProperty("user")
    public void setUser(String user) {
        this.user = user;
    }


    /**
     *
     * @return
     * The event
     */
    @JsonProperty("event")
    public Event getEvent() {
        return event;
    }

    /**
     *
     * @param event
     * The event
     */
    @JsonProperty("event")
    public void setEvent(Event event) {
        this.event = event;
    }
}
