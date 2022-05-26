import javafx.beans.property.ObjectProperty;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import java.util.ArrayList;

/**
 * Class to draw at
 */
public class myPane extends Pane {

    /** arrayList of rectangles that are drawn */
    ArrayList<Figures.myRectangle> rectangles = new ArrayList<>();
    /** arrayList of circles that are drawn */
    ArrayList<Figures.myCircle> circles = new ArrayList<>();
    /** arrayList of triangles that are drawn */
    ArrayList<Figures.myTriangle> triangles = new ArrayList<>();

    /**
     * Contractor that gives a minimum size of pane and add EventHandler's
     * @see myPane#create(MouseEvent)
     * @see myPane#expand(MouseEvent) 
     */
    myPane(){
        setPrefSize(800,500);
        setMinHeight(500);
        setMinWidth(800);

        addEventHandler(MouseEvent.MOUSE_PRESSED, this::create);
        addEventHandler(MouseEvent.MOUSE_DRAGGED, this::expand);
    }

    /**
     * Method that creates a specific figure and adds in to the ArrayList
     * @param event this event is mouse pressed
     */
    private void create(MouseEvent event){
        if(event.getButton() == MouseButton.PRIMARY){
            switch (myMenu.wybrana) {
                case PROSTOKAT -> {
                    Figures.myRectangle rectangle = new Figures().new myRectangle(event.getX(), event.getY());
                    rectangles.add(rectangle);
                    getChildren().add(rectangle);
                }
                case KOLO -> {
                    Figures.myCircle circle = new Figures().new myCircle(event.getX(), event.getY());
                    circles.add(circle); circles.add(circle);
                    getChildren().add(circle);
                }
                case TROJKAT -> {
                    Figures.myTriangle triangle = new Figures().new myTriangle(event.getX(), event.getY());
                    triangles.add(triangle);
                    getChildren().add(triangle);
                }
            }
        }
    }

    /**
     * Method that expands created figure to specific size
     * @param event this event is mouse dragged
     */
    private void expand(MouseEvent event){
        if(event.getButton() == MouseButton.PRIMARY){
            switch (myMenu.wybrana) {
                case PROSTOKAT -> {
                    Figures.myRectangle rectangle = rectangles.get(rectangles.size()-1);
                    rectangle.rectangleExpanding(Math.abs(event.getX() - rectangle.getX()), Math.abs(event.getY() - rectangle.getY()));
                }
                case KOLO -> {
                    Figures.myCircle circle = circles.get(circles.size()-1);
                    circle.setRadius(Math.sqrt(Math.abs(Math.pow((event.getX() - circle.getCenterX()), 2) + Math.pow((event.getY() - circle.getCenterY()), 2))));
                }
                case TROJKAT -> {
                    Figures.myTriangle triangle = triangles.get(triangles.size()-1);
                    ObjectProperty<Point2D> point;
                    point = triangle.position;
                    getChildren().remove(triangle);

                    triangles.set(triangles.size()-1, new Figures().new myTriangle(point.get().getX(), point.get().getY()));
                    triangle = triangles.get(triangles.size()-1);
                    getChildren().add(triangle);
                    triangle.getPoints().addAll(
                            point.get().getX(), point.get().getY(),
                            point.get().getX(), event.getY(),
                            event.getX(), event.getY());
                }
            }
        }
    }
}
