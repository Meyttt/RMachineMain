package gui.window.makery.address.model;

import Other.R_machine;
import Other.StopType;
import Other.WorkExchange;
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
//TODO: дописать вывод ленты

/**
 * Created by Admin on 18.02.2017.
 */
public class DebuggerWindow extends Application implements Runnable{
	static TextField output, outputNode, outputCondition, outputStatement;
	static TextArea outputMemories;
	static Button nextCond,nextStat,nextNode,toEnd;

	static R_machine r_machine;
	static WorkExchange workExchange;

	public DebuggerWindow( R_machine r_machine,WorkExchange workExchange) {
		DebuggerWindow.r_machine=r_machine;
		DebuggerWindow.workExchange = workExchange;
	}

	public DebuggerWindow(R_machine r_machine) {
		DebuggerWindow.r_machine=r_machine;
	}

	public DebuggerWindow() {
	}

	@Override
	public synchronized void start(Stage primaryStage) throws Exception {
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

		nextCond = new Button("Следующее условие");
		nextStat = new Button("Следующее выражение");
		nextNode = new Button("Следующая вершина");
		toEnd = new Button("В конец программы");
		Button exit = new Button("Выход");

		nextCond.setOnAction(event -> {
			try {
				r_machine=workExchange.sendWork(StopType.CONDITION);
				if(r_machine.endOfAlgorythm()){
					disableButtons();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			setFields();
		});

		nextStat.setOnAction(event -> {
			try {
				r_machine=workExchange.sendWork(StopType.STATEMENT);
				if(r_machine.endOfAlgorythm()){
					disableButtons();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			setFields();

		});

		nextNode.setOnAction(event -> {
			try {
				this.r_machine=workExchange.sendWork(StopType.NODE);
				if(r_machine.endOfAlgorythm()){
					disableButtons();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			setFields();
		});

		toEnd.setOnAction(event -> {
			try {
				r_machine=workExchange.sendWork(StopType.END);
				if(r_machine.endOfAlgorythm()){
					disableButtons();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			setFields();

		});

		exit.setOnAction(event -> {
			System.exit(0);
		});
		grid.addRow(0, new Label("Вершина:"), outputNode);
		grid.addRow(1, new Label("Условие:"), outputCondition);
		grid.addRow(2, new Label("Выражение:"), outputStatement);
		grid.addRow(3, new Label("Памяти:"), outputMemories);
		grid.addColumn(2, nextCond,nextStat, nextNode, toEnd,exit);
		GridPane.setHgrow(outputStatement, Priority.ALWAYS);
		GridPane.setHgrow(outputCondition, Priority.ALWAYS);
		GridPane.setHgrow(outputNode, Priority.ALWAYS);
		GridPane.setHgrow(outputMemories, Priority.ALWAYS);
		Scene sceneFirst = new Scene(grid);
		primaryStage.setTitle("Обмен");
		primaryStage.setScene(sceneFirst);
		primaryStage.show();
	}
	private static void disableButtons(){
		nextCond.setDisable(true);
		nextStat.setDisable(true);
		nextNode.setDisable(true);
		toEnd.setDisable(true);
	}

	public void setFields(){
		String currentNumber = r_machine.getCurrentNumber();
		if(currentNumber==null){
			outputNode.setText("null");
		}else {
			outputNode.setText(currentNumber);
		}
		if(r_machine.getCurrentCondition()==null){
			outputCondition.setText("null");
		}else {
			outputCondition.setText(r_machine.getCurrentCondition().toString());
		}
		if(r_machine.getCurrenntStatement()==null){
			outputStatement.setText("null");
		}else {
			outputStatement.setText(r_machine.getCurrenntStatement().toString());
		}
		outputMemories.setText(r_machine.stringMemories());
	}

	@Override
	public void run() {
		launch(DebuggerWindow.class);
	}

}
