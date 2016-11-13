package com.example.uremote.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raul on 12/11/16.
 */
public class KeyboardEvent implements  Event{
    @JsonProperty("keyDown")
    private List<String> keyDown = new ArrayList<String>();
    @JsonProperty("keyUp")
    private List<String> keyUp = new ArrayList<String>();
    /**
     *
     * @return
     * The keyDown
     */
    @JsonProperty("keyDown")
    public List<String> getKeyDown() {
        return keyDown;
    }

    /**
     *
     * @param keyDown
     * The keyDown
     */
    @JsonProperty("keyDown")
    public void setKeyDown(List<String> keyDown) {
        this.keyDown = keyDown;
    }

    /**
     *
     * @return
     * The keyUp
     */
    @JsonProperty("keyUp")
    public List<String> getKeyUp() {
        return keyUp;
    }

    /**
     *
     * @param keyUp
     * The keyUp
     */
    @JsonProperty("keyUp")
    public void setKeyUp(List<String> keyUp) {
        this.keyUp = keyUp;
    }
}
