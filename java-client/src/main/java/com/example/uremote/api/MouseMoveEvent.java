package com.example.uremote.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by raul on 12/11/16.
 */
public class MouseMoveEvent implements  Event{
    @JsonProperty("mouseX")
    private Integer mouseX;
    @JsonProperty("mouseY")
    private Integer mouseY;
    /**
     *
     * @return
     * The mouseX
     */
    @JsonProperty("mouseX")
    public Integer getMouseX() {
        return mouseX;
    }

    /**
     *
     * @param mouseX
     * The mouseX
     */
    @JsonProperty("mouseX")
    public void setMouseX(Integer mouseX) {
        this.mouseX = mouseX;
    }

    /**
     *
     * @return
     * The mouseY
     */
    @JsonProperty("mouseY")
    public Integer getMouseY() {
        return mouseY;
    }

    /**
     *
     * @param mouseY
     * The mouseY
     */
    @JsonProperty("mouseY")
    public void setMouseY(Integer mouseY) {
        this.mouseY = mouseY;
    }

}
