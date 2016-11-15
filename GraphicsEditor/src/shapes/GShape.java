package shapes;

import java.awt.Graphics2D;
import java.awt.Shape;

import constants.GConstants;
import constants.GConstants.EAnchors;
import constants.GConstants.EDrawingType;


abstract public class GShape {
	// attributes
	private EDrawingType eDrawingType;
	private boolean selected; 
	// components
	private Shape shape;
	private Anchors anchors;
	// getters & setters
	public EDrawingType geteDrawingType() { return eDrawingType; }
	public void seteDrawingType(EDrawingType eDrawingType) { this.eDrawingType = eDrawingType; }
	public boolean isSelected() { return selected; }
	public void setSelected(boolean selected) { this.selected = selected; }
	
	public Shape getShape() { return shape; }
	public Anchors getAnchors() { return anchors; }
	public void setAnchors(Anchors anchors) { this.anchors = anchors; }
	// constructors
	public GShape(EDrawingType eDrawingType, Shape shape){
		this.eDrawingType = eDrawingType;
		this.selected = false;
		this.shape = shape;
		this.anchors = new Anchors();
	}
	public GShape clone(){
		try { return this.getClass().newInstance();	} 
		catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	// methods
	public void draw(Graphics2D g2D) {
		g2D.draw(this.shape);
		if(this.selected){
			this.anchors.draw(g2D, this.shape.getBounds());
		}
	}
	public EAnchors contains(int x, int y) {
		if(this.selected) {
			EAnchors eAnchor = this.anchors.contains(x,y);
			if(eAnchor != null)
				return eAnchor;
		}
		if(this.shape.contains(x, y))
			return GConstants.EAnchors.MM;
		return null;
	}
	abstract public void initDrawing(int x, int y, Graphics2D g2D);
	abstract public void keepDrawing(int x, int y, Graphics2D g2D);
	public void continueDrawing(int x, int y, Graphics2D g2D){}
	public void finishDrawing(int x, int y, Graphics2D g2D){}
}
