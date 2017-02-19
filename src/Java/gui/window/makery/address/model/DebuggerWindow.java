package gui.window.makery.address.model;

import Other.R_machine;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 * Created by Admin on 18.02.2017.
 */
public class DebuggerWindow extends Application implements Runnable{
	static TextField output, outputNode, outputCondition, outputStatement;
	static TextArea outputMemories;
	static Thread rmThread;
	static R_machine r_machine;

	public static void setRmThread(Thread rmThread, R_machine r_machine) {
		DebuggerWindow.r_machine=r_machine;
		DebuggerWindow.rmThread = rmThread;
	}

	@Override
	public synchronized void start(Stage primaryStage) throws Exception {
//		output = new TextField();
		outputNode = new TextField();
		outputNode.setEditable(false);
		outputCondition = new TextField();
		outputCondition.setEditable(false);
		outputStatement = new TextField();
		outputStatement.setEditable(false);
		outputMemories = new TextArea();
		outputMemories.setMinHeight(50);
		outputMemories.setEditable(false);

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(5));
		grid.setHgap(5);
		grid.setVgap(5);
		Button ver = new Button("Далее");
		ver.setOnAction(event -> {
			if(r_machine.endNumber==null){
				outputNode.setText("null");
			}else {
				outputNode.setText(r_machine.endNumber);
			}
			if(r_machine.currentCondition==null){
				outputCondition.setText("null");
			}else {
				outputCondition.setText(r_machine.currentCondition.toString());
			}
			if(r_machine.currenntStatement==null){
				outputStatement.setText("null");
			}else {
				outputStatement.setText(r_machine.currenntStatement.toString());
			}
			outputMemories.setText(r_machine.stringMemories());

			rmThread.interrupt();

			//			try {
//				Thread.sleep(10000);
//			} catch (Exception e) {
//				System.out.println("error");
//			}
		});

		grid.addRow(0, new Label("Вершина:"), outputNode, ver);
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
//        machineStart(primaryStage);


	}

	@Override
	public void run() {
		this.launch(DebuggerWindow.class);
	}
}
