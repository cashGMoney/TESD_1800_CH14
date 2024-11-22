import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import java.util.Random;

public class ClockPane extends Pane {
    private int hour;
    private int minute;
    private int second;

    // Add Boolean properties for hand visibility
    private boolean hourHandVisible = true;
    private boolean minuteHandVisible = true;
    private boolean secondHandVisible = false;

    /** Construct a default clock with the current time*/
    public ClockPane() {
        setCurrentTime();
    }

    /** Construct a clock with specified hour, minute, and second */
    public ClockPane(int hour,int minute,int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    /** Return hour */
    public int getHour() {
        return hour;
    }

    /** Set a new hour */
    public void setHour(int hour) {
        this.hour = hour;
        paintClock();
    }

    /** Return minute */
    public int getMinute() {
        return minute;
    }

    /** Set a new minute */
    public void setMinute(int minute) {
        this.minute = minute;
        paintClock();
    }

    /** Return second */
    public int getSecond() {
        return second;
    }

    /** Set a new second */
    public void setSecond(int second) {
        this.second = second;
        paintClock();
    }

    /** Get hour hand visibility */
    public boolean isHourHandVisible() {
        return hourHandVisible;
    }

    /** Set hour hand visibility */
    public void setHourHandVisible(boolean visible) {
        this.hourHandVisible = visible;
        paintClock();
    }

    /** Get minute hand visibility */
    public boolean isMinuteHandVisible() {
        return minuteHandVisible;
    }

    /** Set minute hand visibility */
    public void setMinuteHandVisible(boolean visible) {
        this.minuteHandVisible = visible;
        paintClock();
    }

    /** Get second hand visibility */
    public boolean isSecondHandVisible() {
        return secondHandVisible;
    }

    /** Set second hand visibility */
    public void setSecondHandVisible(boolean visible) {
        this.secondHandVisible = visible;
        paintClock();
    }

    /* Set the current time for the clock */
    public void setCurrentTime() {
        // Construct a calendar for the current date and time
        Calendar calendar = new GregorianCalendar();

        // Set current hour, minute and second
        this.hour = calendar.get(Calendar.HOUR_OF_DAY);
        this.minute = calendar.get(Calendar.MINUTE);
        this.second = calendar.get(Calendar.SECOND);

        paintClock(); // Repaint the clock
    }

    /** Paint the clock */
    private void paintClock() {
        // Initialize clock parameters
        double clockRadius =
        Math.min(getWidth(), getHeight()) * 0.8 * 0.5;
        double centerX = getWidth() /2;
        double centerY = getHeight() /2;

        // Draw circle
        Circle circle = new Circle(centerX, centerY, clockRadius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        Text t1 = new Text(centerX - 5, centerY - clockRadius + 12, "12");
        Text t2 = new Text(centerX - clockRadius + 3, centerY + 5, "9");
        Text t3 = new Text(centerX + clockRadius - 10, centerY + 3, "3");
        Text t4 = new Text(centerX - 3, centerY + clockRadius - 3, "6");

        // Draw second hand if visible
        Line sLine = null;
        if (secondHandVisible) {
            double sLength = clockRadius * 0.8;
            double secondX = centerX + sLength *
            Math.sin(second * (2 * Math.PI / 60));
            double secondY = centerY - sLength *
            Math.cos(second * (2 * Math.PI / 60));
            sLine = new Line(centerX, centerY, secondX, secondY);
            sLine.setStroke(Color.RED);
        }

        // Draw minute hand if visible
        Line mLine = null;

        // The minute values are randomly generated.
        // The minute is either 0 or 30.
        Random random = new Random();
        double randomValue = random.nextDouble();
        if (minuteHandVisible) {
            double mLength = clockRadius * 0.65;
            double xMinute = centerX + mLength * Math.sin((randomValue < 0.5 ? 0 : 30) * (2 * Math.PI / 60));
            double minuteY = centerY - mLength * Math.cos((randomValue < 0.5 ? 0 : 30) * (2 * Math.PI / 60));
            mLine = new Line(centerX, centerY, xMinute, minuteY);
            mLine.setStroke(Color.BLUE);
        }

        // Draw hour hand if visible
        Line hLine = null;
        
        // The hour values are randomly generated.
        // The hour is between 0 and 11
        if (hourHandVisible) {
            double hLength = clockRadius *  0.5;
            double hourX = centerX + hLength * Math.sin((randomValue * 11) * (2 * Math.PI / 12));
            double hourY = centerY - hLength * Math.cos((randomValue * 11) * (2 * Math.PI / 12));
            hLine = new Line(centerX, centerY, hourX, hourY);
            hLine.setStroke(Color.GREEN);
        }

        getChildren().clear();
        getChildren().addAll(circle, t1, t2, t3, t4);

        // If line is visible add to scene
        if (sLine != null) getChildren().add(sLine);
        if (mLine != null) getChildren().add(mLine);
        if (hLine != null) getChildren().add(hLine);
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        paintClock();
    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height);
        paintClock();
    }
}

/* -- Backup --

        // Draw minute hand if visible
        Line mLine = null;

        // The minute values are randomly generated.
        // The minute is either 0 or 30.
        if (minuteHandVisible) {
            double mLength = clockRadius * 0.65;
            double xMinute = centerX + mLength *
            Math.sin(minute * (2 * Math.PI / 60));
            double minuteY = centerY - mLength *
            Math.cos(minute * (2 * Math.PI / 60));
            mLine = new Line(centerX, centerY, xMinute, minuteY);
            mLine.setStroke(Color.BLUE);
        }

        // Draw hour hand if visible
        Line hLine = null;
        
        // The hour values are randomly generated.
        // The hour is between 0 and 11
        if (hourHandVisible) {
            double hLength = clockRadius *  0.5;
            double hourX = centerX + hLength *
            Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
            double hourY = centerY - hLength *
            Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
            hLine = new Line(centerX, centerY, hourX, hourY);
            hLine.setStroke(Color.GREEN);
        }

 */