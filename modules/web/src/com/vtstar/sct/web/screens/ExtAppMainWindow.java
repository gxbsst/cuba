package com.vtstar.sct.web.screens;

import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.web.app.mainwindow.AppMainWindow;

public class ExtAppMainWindow extends AppMainWindow {

    @Override
    public void ready() {
        super.ready();
        String defaultScreen = "sct$VtApps.browse";
        openWindow(defaultScreen, WindowManager.OpenType.NEW_TAB);
    }
}