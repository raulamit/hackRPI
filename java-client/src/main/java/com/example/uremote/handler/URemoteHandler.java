package com.example.uremote.handler;

import com.example.uremote.api.Event;
import com.example.uremote.api.KeyboardEvent;
import com.example.uremote.api.MouseButtonEvent;
import com.example.uremote.api.MouseMoveEvent;


import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by raul on 12/11/16.
 */
public class URemoteHandler {
    Robot robot = new Robot();
    int[] mouseButtonMap = {InputEvent.BUTTON1_MASK, InputEvent.BUTTON2_MASK,InputEvent.BUTTON3_MASK};

    public void process(Event event) {
        if (event instanceof MouseMoveEvent){
            MouseMoveEvent mouseMoveEvent = (MouseMoveEvent)event;
            robot.mouseMove(mouseMoveEvent.getMouseX(), mouseMoveEvent.getMouseY());
        }
        else if (event instanceof KeyboardEvent){
            KeyboardEvent kev = (KeyboardEvent) event;
            for (String key : kev.getKeyDown()) {
                this.pressKey(key);
            }
            for (String key : kev.getKeyUp()) {
                this.releaseKey(key);
            }
        } else if (event instanceof MouseButtonEvent){
            MouseButtonEvent mbe = (MouseButtonEvent) event;
            for (int mouseEvnt: mbe.getButtonDown()){
                robot.mousePress(mouseButtonMap[mouseEvnt]);
            }
            for (int mouseEvnt: mbe.getButtonUp()){
                robot.mouseRelease(mouseButtonMap[mouseEvnt]);
            }

        }

//        robot.mouseMove(event.getMouseX(), event.getMouseY());
    }

    public void example() {
        robot.setAutoDelay(40);
        robot.setAutoWaitForIdle(true);

        robot.delay(4000);
        robot.mouseMove(40, 130);
        robot.delay(500);
        System.out.println("x"+40 +"\ny"+ 130);
        leftClick();
        robot.delay(500);
        leftClick();

        robot.delay(500);
        type("Hello, world");
        System.out.println("Hello, world");

        robot.mouseMove(40, 160);
        robot.delay(500);

        leftClick();
        robot.delay(500);
        leftClick();

        robot.delay(500);
        type("This is a test of the Java Robot class");
        System.out.println("This is a test of the Java Robot class");
        robot.delay(50);
        type(KeyEvent.VK_DOWN);

        robot.delay(250);
        type("Four score and seven years ago, our fathers ...");

        robot.delay(1000);
        System.exit(0);
    }

    public URemoteHandler() throws AWTException
    {}

    private void leftClick()
    {
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delay(200);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(200);
    }

    private void type(int i)
    {
        robot.delay(40);
        robot.keyPress(i);
        robot.keyRelease(i);
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

    private void pressKey(String s)
    {
        byte[] bytes = s.getBytes();
        for (byte b : bytes)
        {
            int code = b;
            // keycode only handles [A-Z] (which is ASCII decimal [65-90])
            if (code > 96 && code < 123) code = code - 32;
            robot.delay(40);
            robot.keyPress(code);
        }
    }

    private void releaseKey(String s)
    {
        byte[] bytes = s.getBytes();
        for (byte b : bytes)
        {
            int code = b;
            // keycode only handles [A-Z] (which is ASCII decimal [65-90])
            if (code > 96 && code < 123) code = code - 32;
            robot.delay(40);
            robot.keyRelease(code);
        }
    }

}