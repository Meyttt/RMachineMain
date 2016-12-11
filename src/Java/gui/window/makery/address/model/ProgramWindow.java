package gui.window.makery.address.model;/**
 * Created by Anton on 05.12.2016.
 */

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ProgramWindow extends Application {

    private Stage primaryStage;
    private AnchorPane rootLayout;
    private TextArea textArea;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
//        this.textArea.appendText("Hello world");
        this.primaryStage.setTitle("R Машина");

        initRootLayout();

//        showPersonOverview();
    }

    /**
     * Инициализирует корневой макет.
     */
    public void initRootLayout() {
        try {
            // Загружаем корневой макет из fxml файла.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ProgramWindow.class.getResource("ProgramWindow.fxml"));
            rootLayout = (AnchorPane) loader.load();

            // Отображаем сцену, содержащую корневой макет.
            Scene scene = new Scene(rootLayout);
            ObservableList<Node> node = rootLayout.getChildren();
            System.out.println(rootLayout.getChildren());
            System.out.println(node.indexOf(textArea));
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Показывает в корневом макете сведения об адресатах.
     */
//    public void showPersonOverview() {
//        try {
//            // Загружаем сведения об адресатах.
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(ProgramWindow.class.getResource("view/PersonOverview.fxml"));
//            AnchorPane personOverview = (AnchorPane) loader.load();
//
//            // Помещаем сведения об адресатах в центр корневого макета.
//            rootLayout.setCenter(personOverview);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * Возвращает главную сцену.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}