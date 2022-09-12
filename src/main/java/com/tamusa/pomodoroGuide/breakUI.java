package com.tamusa.pomodoroGuide;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import jetbrains.buildServer.messages.serviceMessages.Message;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class breakUI extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        breakMenu.main();
    }
}

