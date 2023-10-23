package me.n1ar4.jar.analyzer.cfg.theme.shape;

import me.n1ar4.jar.analyzer.cfg.canvas.Box;
import me.n1ar4.jar.analyzer.cfg.canvas.Canvas;
import me.n1ar4.jar.analyzer.cfg.canvas.Drawable;

public class Rectangle implements Drawable {
    public final int width;
    public final int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @SuppressWarnings({"UnnecessaryLocalVariable", "Duplicates"})
    @Override
    public void draw(Canvas canvas, int startRow, int startCol) {
        int left = startCol;
        int right = left + width + 1;
        int top = startRow;
        int bottom = top + height + 1;


        canvas.moveTo(top, left);
        canvas.drawPixel(Box.DOWN_AND_RIGHT);
        canvas.moveTo(top, right);
        canvas.drawPixel(Box.DOWN_AND_LEFT);
        canvas.moveTo(bottom, left);
        canvas.drawPixel(Box.UP_AND_RIGHT);
        canvas.moveTo(bottom, right);
        canvas.drawPixel(Box.UP_AND_LEFT);


        canvas.moveTo(top, left + 1);
        canvas.drawHorizontalLine(width);
        canvas.moveTo(bottom, left + 1);
        canvas.drawHorizontalLine(width);
        canvas.moveTo(top + 1, left);
        canvas.drawVerticalLine(height);
        canvas.moveTo(top + 1, right);
        canvas.drawVerticalLine(height);
    }
}
