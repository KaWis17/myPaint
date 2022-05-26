
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * OuterClass for all types of figures
 */
public class Figures extends Shape {
    /**
     * InnerClass for Rectangle
     */
     class myRectangle extends Rectangle {
        /**
         * Creating rectangle of size 0, adding mouse and scroll events to an object
         * @param X X coordinate of rectangle
         * @param Y Y coordinate of rectangle
         */
        myRectangle(double X, double Y){
            super(X, Y, 0, 0);
            doStuff(this);
        }

        /**
         * Method that sets rectangle's size
         * @param width width of rectangle
         * @param height height of rectangle
         */
        void rectangleExpanding(double width, double height){
            setWidth(width);
            setHeight(height);
        }
    }

    /**
     * InnerClass for Circle
     */
    class myCircle extends Circle {
        /**
         * Creating circle of size 0, adding mouse and scroll events to an object
         * @param X X coordinate of circle
         * @param Y Y coordinate of circle
         */
        myCircle(double X, double Y){
            super(X, Y, 0);
            doStuff(this);
        }
    }

    /**
     * InnerClass of Triangle
     */
    class myTriangle extends Polygon {
        /**
         * Position of top-left corner of triangle
         */
        final ObjectProperty<Point2D> position = new SimpleObjectProperty<>();
        /**
         * Creating triangle of size 0, adding mouse and scroll events to an object
         * @param X X coordinate of triangle
         * @param Y Y coordinate of triangle
         */
        myTriangle(double X, double Y){
            position.set(new Point2D(X, Y));
            getPoints().addAll(
                    X, Y,
                    X, Y,
                    X, Y);
            doStuff(this);
        }
    }

    /**
     * Method to add FigureScroll and FigureMouse events
     * @param shape passing shape to add events to it.
     * @see FigureMouse
     * @see FigureScroll
     */
    void doStuff(Shape shape){
        shape.setOnScroll(new FigureScroll(shape));
        shape.setOnMouseClicked(new FigureMouse(shape));
        shape.setOnMouseDragged(new FigureMouse(shape));
        shape.setOnMouseEntered(new FigureMouse(shape));
        shape.setOnMouseEntered(new FigureMouse(shape));
        shape.setOnMouseExited(new FigureMouse(shape));
    }
}
