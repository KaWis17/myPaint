import javafx.event.EventHandler;
import javafx.scene.input.ScrollEvent;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;


/**
 * Class that is responsible for zooming and rotating a Figures object
 * @see Figures#doStuff(Shape)
 */
public class FigureScroll implements EventHandler<ScrollEvent>  {
    /** shape Figure of specific instanceof */
    Shape shape;
    /** inputShape Figure passed to function */
    Shape inputShape;

    /**
     * Passing Shape value to different methods by global variable
     * @param inputShape passing an object that is going to be moved
     */
    FigureScroll(Shape inputShape){ this.inputShape = inputShape;}

    /**
     * Method that determine whether cursor is on a figure
     * @param x X coordinate of a cursor
     * @param y Y coordinate of a cursor
     * @return true if cursor is inside the figure
     * @see FigureScroll#doScale(ScrollEvent)
     * @see FigureScroll#doRotate(ScrollEvent)
     */
    public boolean isHit(double x, double y){ return shape.getBoundsInLocal().contains(x, y);}

    /**
     * Method that expands or compresses object on scroll
     * @param e this event is scrolling
     */
    private void doScale(ScrollEvent e){
        double x = e.getX();
        double y = e.getY();

        if(isHit(x, y)){
            double zoomFactor = 1.03;
            if(e.getDeltaY()<0){ zoomFactor = 0.97;}
            shape.setScaleX(shape.getScaleX() * zoomFactor);
            shape.setScaleY(shape.getScaleX() * zoomFactor);
        }
    }
    /**
     * Method that rotates an object
     * @param e this event is scrolling while holding shift
     */
    private void doRotate(ScrollEvent e){
        double x = e.getX();
        double y = e.getY();
        Rotate rotate;

        if(isHit(x, y)){
            if(e.getDeltaX()<0){rotate = new Rotate(-5,x,y);}
            else{rotate = new Rotate(5, x, y);}
            shape.getTransforms().add(rotate);
        }
    }

    /**
     * Overriding handle method for FigureScroll class. Scrolling with shift rotates a figure, scrolling without shift resizes it.
     * @param scrollEvent scrollEvent
     * @see FigureScroll
     * @see FigureScroll#doRotate(ScrollEvent)
     * @see FigureScroll#doScale(ScrollEvent)
     */
    @Override
    public void handle(ScrollEvent scrollEvent) {
        if(inputShape instanceof Figures.myRectangle){shape = (Figures.myRectangle) scrollEvent.getSource();}
        if(inputShape instanceof Figures.myCircle){shape = (Figures.myCircle) scrollEvent.getSource();}
        if(inputShape instanceof Figures.myTriangle){shape = (Figures.myTriangle) scrollEvent.getSource();}

        if(scrollEvent.getEventType()==ScrollEvent.SCROLL){
            if(scrollEvent.isShiftDown()){
                if(!(shape instanceof Figures.myCircle)){ doRotate(scrollEvent);}
            }
            else{
                doScale(scrollEvent);
            }
        }
    }
}
