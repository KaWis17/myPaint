import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Point2D;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;

/**
 * Class that is responsible for all mouse movements of a Figures object
 * @see Figures#doStuff(Shape)
 */

public class FigureMouse implements EventHandler<MouseEvent> {
    /** shape Figure of specific instanceof */
    Shape shape;
    /** inputShape Figure passed to function */
    Shape inputShape;
    /** X coordinate of mouse clicked */
    private double x;
    /** Y coordinate of mouse clicked */
    private double y;

    /**
     * Passing Shape value to different methods by global variable
     * @param inputShape passing an object that is going to be moved
     */
    FigureMouse(Shape inputShape){ this.inputShape = inputShape;}

    /**
     * Method that determine whether cursor is on a figure
     * @param x X coordinate of a cursor
     * @param y Y coordinate of a cursor
     * @return true if cursor is inside the figure
     * @see FigureMouse#doMove(MouseEvent)
     */
    public boolean isHit(double x, double y){ return shape.getBoundsInLocal().contains(x, y);}

    /**
     * Changing X coordinate of a figure
     * @param x value to change X coordinate by
     */
    private void addX(double x){ shape.setLayoutX(shape.getLayoutX()+x);}

    /**
     * Changing Y coordinate of a figure
     * @param y value to change Y coordinate by
     */
    private void addY(double y){ shape.setLayoutY(shape.getLayoutY()+y);}

    /**
     * method responsible for moving object inside pane
     * @param event this event is mouse dragging while secondary button is pressed
     */
    private void doMove(MouseEvent event) {
        double dx = event.getX() - x;
        double dy = event.getY() - y;

        if (isHit(x, y)) {
            final ObjectProperty<Point2D> mousePosition = new SimpleObjectProperty<>();
            shape.setOnMousePressed(event1 -> mousePosition.set(new Point2D(event1.getSceneX(), event1.getSceneY())));
            shape.setOnMouseDragged(event2 -> {
                if(mousePosition.get() != null){
                    addX(event2.getSceneX() - mousePosition.get().getX());
                    addY(event2.getSceneY() - mousePosition.get().getY());
                }
                mousePosition.set(new Point2D(event2.getSceneX(), event2.getSceneY()));
            });
            addX(dx);
            addY(dy);
        }
        x += dx;
        y += dy;
    }

    /**
     * Method responsible for changing color of an object using ColorPicker
     * @param event this event is mouseClicked while holding shift
     */

    private void changeColor(MouseEvent event) {
        ColorPicker picker = new ColorPicker();
        MenuItem wrapPicker = new MenuItem(null, picker);
        picker.setValue((Color) shape.getFill());
        picker.setOnAction(actionEvent -> shape.setFill(picker.getValue()));
        final ContextMenu menu = new ContextMenu(wrapPicker);
        menu.show(shape, event.getScreenX(), event.getScreenY());
    }

    /**
     * overriding handle method for FigureMouse class
     * @param mouseEvent mouseEvent
     * @see FigureMouse
     */
    @Override
    public void handle(MouseEvent mouseEvent) {
        if(inputShape instanceof Figures.myRectangle){shape = (Figures.myRectangle) mouseEvent.getSource();}
        if(inputShape instanceof Figures.myCircle){shape = (Figures.myCircle) mouseEvent.getSource();}
        if(inputShape instanceof Figures.myTriangle){shape = (Figures.myTriangle) mouseEvent.getSource();}
        shape.setStrokeWidth(5);
        shape.setStrokeType(StrokeType.CENTERED);

        EventType<? extends MouseEvent> evType= mouseEvent.getEventType();
        if (evType == MouseEvent.MOUSE_CLICKED){ x = mouseEvent.getX(); y = mouseEvent.getY();}
        if (evType == MouseEvent.MOUSE_DRAGGED && mouseEvent.getButton() == MouseButton.SECONDARY){ doMove(mouseEvent);}
        if (evType == MouseEvent.MOUSE_CLICKED && mouseEvent.isShiftDown()){ changeColor(mouseEvent);}
        if(evType == MouseEvent.MOUSE_ENTERED){ shape.setStroke(((Color) shape.getFill()).invert());}
        if(evType == MouseEvent.MOUSE_EXITED){ shape.setStroke(null);}
    }
}
