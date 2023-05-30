package utils;

import java.awt.Paint;
import java.util.Date;

import org.jfree.chart.renderer.category.GanttRenderer;
import org.jfree.data.gantt.Task;

public class ColoredTask extends Task {
    private Paint color;

    public ColoredTask(String description, Date start, Date end, Paint color) {
        super(description, start, end);
        this.color = color;
    }

    public Paint getColor() {
        return color;
    }
}