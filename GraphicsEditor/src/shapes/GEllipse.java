package shapes;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import constants.GConstants.EDrawingType;

public class GEllipse extends GShape {
	private Ellipse2D ellipse;
	public GEllipse(){
		super(EDrawingType.TP, new Ellipse2D.Double(0, 0, 0, 0));
		this.ellipse = (Ellipse2D)getShape();
	}
	@Override
	public void initDrawing(int x, int y, Graphics2D g2D) {
		this.ellipse.setFrameFromDiagonal(x, y, x, y);
		this.draw(g2D);
	}

	@Override
	public void keepDrawing(int x, int y, Graphics2D g2D) {
		this.draw(g2D);
		this.ellipse.setFrameFromDiagonal(this.ellipse.getX(), this.ellipse.getY(), x, y);;
		this.draw(g2D);
	}

}
