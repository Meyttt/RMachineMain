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

	static R_machine r_machine;
	static WorkExchange workExchange;

//	public static void setR_machine(R_machine r_machine) {
//		DebuggerWindow.r_machine=r_machine;
//	}

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
		Button start = new Button("Начать работу");
		Button nextCond = new Button("Следующее условие");
		Button nextStat = new Button("Следующее выражение");
		Button nextNode = new Button("Следующая вершина");
		Button toEnd = new Button("В конец программы");
//		nextCond.setDisable(true);
//		nextStat.setDisable(true);
//		nextNode.setDisable(true);
//		toEnd.setDisable(true);
		nextCond.setOnAction(event -> {
//			try {
//				r_machine=workExchange.sendWork(StopType.CONDITION);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
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
		});

		nextStat.setOnAction(event -> {
			try {
				r_machine=workExchange.sendWork(StopType.STATEMENT);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
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

		});

		nextNode.setOnAction(event -> {
			try {
				this.r_machine=workExchange.sendWork(StopType.NODE);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			String currentNumber = r_machine.getCurrentNumber();
			System.out.println("String CN in debug is "+currentNumber);
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
		});

		toEnd.setOnAction(event -> {
			try {
				r_machine=workExchange.sendWork(StopType.END);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
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

		});

		start.setOnAction(event -> {
			nextCond.setDisable(false);
			nextStat.setDisable(false);
			nextNode.setDisable(false);
			toEnd.setDisable(false);
			System.out.println(r_machine.getState());
			while(!(r_machine.getState()== Thread.State.WAITING)&&!(r_machine.getState()== Thread.State.TERMINATED)){
				System.out.println(r_machine.getState());
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
//			r_machine.interrupt();
			while((!(r_machine.getState()== Thread.State.WAITING))&&!(r_machine.getState()== Thread.State.TERMINATED)){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(r_machine.currentNumber.getValue() ==null){
				outputNode.setText("null");
			}else {
				outputNode.setText(r_machine.currentNumber.getValue());
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
			start.setDisable(true);



			//			try {
//				Thread.sleep(10000);
//			} catch (Exception e) {
//				System.out.println("error");
//			}
		});

		grid.addRow(0, new Label("Вершина:"), outputNode);
		grid.addRow(1, new Label("Условие:"), outputCondition);
		grid.addRow(2, new Label("Выражение:"), outputStatement);
		grid.addRow(3, new Label("Памяти:"), outputMemories);
		grid.addColumn(2,start, nextCond,nextStat, nextNode, toEnd);
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
		launch(DebuggerWindow.class);
	}
}
