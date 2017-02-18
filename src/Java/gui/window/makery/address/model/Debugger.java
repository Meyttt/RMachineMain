package gui.window.makery.address.model;

import Other.AllStorage;
import Other.R_machine;
import Other.Storage;
import Other.Tape;
import XmlReader.AlgorithmReaderNew;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.util.Scanner;

/**
 * Created by master on 13.02.2017.
 */
public class Debugger implements Runnable {
    @Override
    public synchronized void run() {
        DebuggerWindow debuggerWindow = new DebuggerWindow();
        try {
            debuggerWindow.launch(DebuggerWindow.class);
        }catch (StackOverflowError e){
            try {
                this.wait();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }
}

