package com.example.uremote.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raul on 12/11/16.
 */
public class MouseButtonEvent implements  Event {
    @JsonProperty("buttonDown")
    private List<Integer> buttonDown = new ArrayList<Integer>();
    @JsonProperty("buttonUp")
    private List<Integer> buttonUp = new ArrayList<Integer>();
/**
     *
     * @return
     * The buttonDown
     */
    @JsonProperty("buttonDown")
    public List<Integer> getButtonDown() {
        return buttonDown;
    }

    /**
     *
     * @param buttonDown
     * The buttonDown
     */
    @JsonProperty("buttonDown")
    public void setButtonDown(List<Integer> buttonDown) {
        this.buttonDown = buttonDown;
    }

    /**
     *
     * @return
     * The buttonUp
     */
    @JsonProperty("buttonUp")
    public List<Integer> getButtonUp() {
        return buttonUp;
    }

    /**
     *
     * @param buttonUp
     * The buttonUp
     */
    @JsonProperty("buttonUp")
    public void setButtonUp(List<Integer> buttonUp) {
        this.buttonUp = buttonUp;
    }

}
