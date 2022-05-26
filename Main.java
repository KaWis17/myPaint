import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * MAIN CLASS EXTENDING APPLICATION
 * @author Krzysztof Wiśniewski
 * @version 1.0
 */
public class Main extends Application {

    /**
     * Method that launches program
     * @param args main function arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Method that adds menu and pane to draw at
     * @param stage stage
     * @see myMenu
     * @see myPane
     */
    @Override
    public void start(Stage stage) {
        stage = new Stage();
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, Color.RED);

        myMenu top = new myMenu();

        StackPane center = new StackPane();
        center.setBackground(Background.fill(Color.LIGHTGRAY));
        myPane pane = new myPane();
        center.getChildren().add(pane);

        root.setTop(top);
        root.setCenter(center);

        stage.setScene(scene);
        stage.setTitle("myPaint");
        stage.show();
    }
}