package com.tamusa.pomodoroGuide;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.options.ex.Settings;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.tamusa.pomodoroGuide.Menu;

import javax.swing.*;
import java.awt.*;

public class startSession extends AnAction {
    /*
           public startSession(){
               //Changes titles
               //super("Session Menu");
           }
     */

    public void actionPerformed(AnActionEvent e) {
        /*
        Project project = e.getProject();
        Menu popup = new Menu(project);
        popup.show();
         */
        Menu.main();

    }

}