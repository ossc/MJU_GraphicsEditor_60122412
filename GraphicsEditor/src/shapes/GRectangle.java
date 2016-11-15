package shapes;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import constants.GConstants.EDrawingType;

public class GRectangle extends GShape{
	private Rectangle rectangle;
	
	public GRectangle(){
		super(EDrawingType.TP, new Rectangle(0, 0, 0, 0));
		this.rectangle =(Rectangle)this.getShape();
	}
	@Override
	public void initDrawing(int x, int y, Graphics2D g2D) {
		this.rectangle.setLocation(x, y);
		this.draw(g2D);
	}
	@Override
	public void keepDrawing(int x, int y, Graphics2D g2D) {
		// erase shape
		this.draw(g2D);
		this.rectangle.setSize(new Dimension(x - this.rectangle.x, y - this.rectangle.y));
		// redraw shape
		this.draw(g2D);
	}
}
