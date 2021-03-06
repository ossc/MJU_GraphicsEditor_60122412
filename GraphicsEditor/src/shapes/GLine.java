package shapes;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import constants.GConstants.EDrawingType;

public class GLine extends GShape {
	private Line2D line;
	public GLine(){
		super(EDrawingType.TP, new Line2D.Double(0, 0, 0, 0));
		this.line = (Line2D)getShape();
	}
	@Override
	public void initDrawing(int x, int y, Graphics2D g2D) {
		this.line.setLine(x, y, x, y);
		this.draw(g2D);
	}
	@Override
	public void keepDrawing(int x, int y, Graphics2D g2D) {
		BasicStroke stroke = new BasicStroke(2.0f);	// 패널 위에 굵기가 2인 선을 그린다.
		g2D.setStroke(stroke);
		this.draw(g2D);
		this.line.setLine(this.line.getX1(), this.line.getY1(), x, y);
		this.draw(g2D);
	}
}
