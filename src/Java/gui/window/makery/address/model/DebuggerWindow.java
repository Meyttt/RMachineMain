package gui.window.makery.address.model;

import Other.R_machine;
import Other.StopType;
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
		Button start = new Button("Начать работу");
		Button nextCond = new Button("Следующее условие");
		Button nextStat = new Button("Следующее выражение");
		Button nextNode = new Button("Следующая вершина");
		Button toEnd = new Button("В конец программы");
		nextCond.setDisable(true);
		nextStat.setDisable(true);
		nextNode.setDisable(true);
		toEnd.setDisable(true);
		nextCond.setOnAction(event -> {
			System.out.println(rmThread.getState());
			while(!(rmThread.getState()== Thread.State.WAITING)&&!(rmThread.getState()== Thread.State.TERMINATED)){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			r_machine.setStopType(StopType.CONDITION);
			rmThread.interrupt();
			while((!(rmThread.getState()== Thread.State.WAITING))&&!(rmThread.getState()== Thread.State.TERMINATED)){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(r_machine.getCurrentNumber() ==null){
				outputNode.setText("null");
			}else {
				outputNode.setText(r_machine.getCurrentNumber());
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



			//			try {
//				Thread.sleep(10000);
//			} catch (Exception e) {
//				System.out.println("error");
//			}
		});

		nextStat.setOnAction(event -> {
			while(!(rmThread.getState()== Thread.State.WAITING)&&!(rmThread.getState()== Thread.State.TERMINATED)){
				System.out.println(rmThread.getState());
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			r_machine.setStopType( StopType.STATEMENT);
			rmThread.interrupt();
			while(!(rmThread.getState()== Thread.State.WAITING)&&!(rmThread.getState()== Thread.State.TERMINATED)){
				System.out.println(rmThread.getState());
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(r_machine.currentNumber ==null){
				outputNode.setText("null");
			}else {
				outputNode.setText(r_machine.currentNumber);
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



			//			try {
//				Thread.sleep(10000);
//			} catch (Exception e) {
//				System.out.println("error");
//			}
		});

		nextNode.setOnAction(event -> {
			System.out.println(rmThread.getState());
			while(!(rmThread.getState()== Thread.State.WAITING)&&!(rmThread.getState()== Thread.State.TERMINATED)){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			r_machine.setStopType(StopType.NODE);
			rmThread.interrupt();
			while((!(rmThread.getState()== Thread.State.WAITING))&&!(rmThread.getState()== Thread.State.TERMINATED)){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(r_machine.currentNumber ==null){
				outputNode.setText("null");
			}else {
				outputNode.setText(r_machine.currentNumber);
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



			//			try {
//				Thread.sleep(10000);
//			} catch (Exception e) {
//				System.out.println("error");
//			}
		});

		toEnd.setOnAction(event -> {
			System.out.println(rmThread.getState());
			while(!(rmThread.getState()== Thread.State.WAITING)&&!(rmThread.getState()== Thread.State.TERMINATED)){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			r_machine.setStopType(StopType.END);
			rmThread.interrupt();
			while((!(rmThread.getState()== Thread.State.WAITING))&&!(rmThread.getState()== Thread.State.TERMINATED)){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(r_machine.currentNumber ==null){
				outputNode.setText("null");
			}else {
				outputNode.setText(r_machine.currentNumber);
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



			//			try {
//				Thread.sleep(10000);
//			} catch (Exception e) {
//				System.out.println("error");
//			}
		});

		start.setOnAction(event -> {
			nextCond.setDisable(false);
			nextStat.setDisable(false);
			nextNode.setDisable(false);
			toEnd.setDisable(false);
			System.out.println(rmThread.getState());
			while(!(rmThread.getState()== Thread.State.WAITING)&&!(rmThread.getState()== Thread.State.TERMINATED)){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
//			rmThread.interrupt();
			while((!(rmThread.getState()== Thread.State.WAITING))&&!(rmThread.getState()== Thread.State.TERMINATED)){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(r_machine.currentNumber ==null){
				outputNode.setText("null");
			}else {
				outputNode.setText(r_machine.currentNumber);
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
		this.launch(DebuggerWindow.class);
	}
}
