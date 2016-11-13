package com.example.uremote.api;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by raul on 12/11/16.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = KeyboardEvent.class, name = "KeyboardEvent"),
        @JsonSubTypes.Type(value = MouseMoveEvent.class, name = "MouseMoveEvent"),
        @JsonSubTypes.Type(value = MouseButtonEvent.class, name = "MouseButtonEvent")}
)
public interface Event {
}
