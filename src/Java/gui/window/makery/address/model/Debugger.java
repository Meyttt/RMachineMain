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
public class Debugger extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(5));
        grid.setHgap(5);
        grid.setVgap(5);
        TextField output = new TextField();
        TextField outputNode = new TextField();
        TextField outputCondition = new TextField();
        TextField outputStatement = new TextField();
        TextField outputMemories = new TextField();
        Button ver = new Button("Далее");
        grid.addRow(0, new Label("Вершина:"), outputNode,ver);
        grid.addRow(1, new Label("Условие:"), outputCondition);
        grid.addRow(2, new Label("Выражение:"), outputStatement);
        grid.addRow(3, new Label("Памяти:"), outputMemories);
        GridPane.setHgrow(outputStatement, Priority.ALWAYS);
        GridPane.setHgrow(outputCondition, Priority.ALWAYS);
        GridPane.setHgrow(outputNode, Priority.ALWAYS);
        GridPane.setHgrow(outputMemories, Priority.ALWAYS);
        Scene sceneFirst = new Scene(grid);
        primaryStage.setTitle("Обмен");
        primaryStage.setScene(sceneFirst);
        primaryStage.show();
//        this.wait();
        machineStart(primaryStage);




    }
    public void machineStart(Stage stage) throws Exception{
//        this.wait();
        AlgorithmReaderNew algorithmReader = new AlgorithmReaderNew("templateStrorageTest.xml");
        algorithmReader.readMemories();
        algorithmReader.readAlgorithm();
        Tape tape = new Tape("perfectapple#");
        Storage storage = new Storage(algorithmReader.arms,algorithmReader.memoryHashMap,algorithmReader.alphabetHashMap);
        AllStorage allStorage = new AllStorage(storage,tape);
        R_machine r_machine = new R_machine(allStorage);
        r_machine.setDaemon(false);
        r_machine.start();
        while(r_machine.isAlive()){
            if (r_machine.getState()== Thread.State.WAITING){
                System.out.println("Memories: ");
                r_machine.printMemories();
//                System.out.println("Wake up, R-Machine!");
                System.out.println("Press ENTER to continue...");
                Scanner in = new Scanner(System.in);
                String line = in.nextLine();
                r_machine.interrupt();
                Thread.sleep(100);
            }
        }
    }


}
