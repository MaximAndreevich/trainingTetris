package com.keyboard.lwjglmodule;

import com.field.ShiftDirection;
import com.keyboard.KeyboardHandleModule;

public class LwjglKeyboardHandleModule implements KeyboardHandleModule {
    @Override
    public void update() {

    }

    @Override
    public boolean wasEscPressed() {
        return false;
    }

    @Override
    public ShiftDirection getShiftDirection() {
        return null;
    }

    @Override
    public boolean wasRotateRequested() {
        return false;
    }

    @Override
    public boolean wasBoostRequested() {
        return false;
    }
}
