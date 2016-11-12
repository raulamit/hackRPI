package com.example.uremote.handler;

import java.awt.*;

/**
 * Created by raul on 12/11/16.
 */
public class URemoteHandler {
    Robot robot = new Robot();

    public URemoteHandler() throws AWTException {
    }

    public String process(String kev) {
        robot.delay(2000);
        this.type(kev);
        return "success";
    }

    private void type(String s)
    {
        byte[] bytes = s.getBytes();
        for (byte b : bytes)
        {
            int code = b;
            // keycode only handles [A-Z] (which is ASCII decimal [65-90])
            if (code > 96 && code < 123) code = code - 32;
            robot.delay(40);
            robot.keyPress(code);
            robot.keyRelease(code);
        }
    }
}

