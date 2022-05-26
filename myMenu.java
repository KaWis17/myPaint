import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/**
 * Menu Class extending HBox (later to be changed to MenuBar)
 */
public class myMenu extends HBox {
    /** Enum from which a figure is chosen */
    public enum Figury {
        /** Circle figure */
        KOLO,
        /** Rectangle figure */
        PROSTOKAT,
        /** Triangle figure */
        TROJKAT}
    /** Chosen figure */
    public static Figury wybrana = Figury.KOLO;

    /**
     * Constructor to create menu with ChoiceBox to choose figure from, also info window
     */
    myMenu(){
        this.setMinHeight(100);
        this.setBackground(Background.fill(Color.DARKGRAY));

        Label myLabel = new Label(wybrana.name());
        myLabel.setMinSize(100, 50);

        Button button = new Button("INFO");
        button.setOnAction(e ->{
            Text text = new Text("""
                            Author: Krzysztof Wiśniewski
                             LeftMouse -> Draw
                             RightMouse -> Drag
                             Scroll -> Resize
                             RightMouse + Shift -> Change Color
                             Scroll + Shift -> Rotate"""
            );
            text.setFont(new Font("Arial", 20));
            Stage stage = new Stage();
            TextFlow textFlow = new TextFlow(text);
            Scene scene = new Scene(textFlow);
            stage.setScene(scene);
            stage.show();
        });

        ChoiceBox<Figury> myChoiceBox = new ChoiceBox<>();
        myChoiceBox.getItems().addAll(Figury.KOLO, Figury.PROSTOKAT, Figury.TROJKAT);
        myChoiceBox.getSelectionModel().selectFirst();
        myChoiceBox.setOnAction(e -> {
            wybrana = myChoiceBox.getValue();
            myLabel.setText(wybrana.name());
        });

        setPadding(new Insets(15, 12, 15, 12));
        setSpacing(50);
        setAlignment(Pos.CENTER);

        getChildren().addAll(myChoiceBox, myLabel, button);
    }

}
