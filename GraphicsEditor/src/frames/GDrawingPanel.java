package frames;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import constants.GConstants;
import constants.GConstants.EAnchors;
import shapes.GShape;


public class GDrawingPanel extends JPanel {
	// attributes
	private static final long serialVersionUID = 1L;
	// object states
	private static enum EState {idleTP, idleNP, drawingTP, drawingNP}; 
	private EState eState;
	// components
	private Vector<GShape> shapeVector;
	// associative attributes
	private GShape selectedShape;
	public void setSelectedShape( GShape selectedShape){
		this.selectedShape = selectedShape;
		switch (this.selectedShape.geteDrawingType()){
			case TP: eState = EState.idleTP; break;
			case NP: eState = EState.idleNP; break;
		}
	}
	// working objects;
	private GShape currentShape;
	
	
	public GDrawingPanel() {
		MousEventHandler mousEventHandler = new MousEventHandler();
		this.addMouseListener(mousEventHandler);
		this.addMouseMotionListener(mousEventHandler);
		this.shapeVector = new Vector<GShape>();
		setOpaque(false);
	}
	public void initialize() {
	}	
	
	public void paint(Graphics g) {
		for(GShape shape: this.shapeVector){
			shape.draw((Graphics2D)g);
		}
	}
	private void resetSelected(){
		for(GShape shape: this.shapeVector){
			shape.setSelected(false);
		}
		this.repaint();
	}
	private void initDrawing(int x, int y){
		this.resetSelected();
		this.currentShape = this.selectedShape.clone();
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());
		this.currentShape.initDrawing(x,y,g2D);
	}
	private void keepDrawing(int x, int y){
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());
		this.currentShape.keepDrawing(x,y,g2D);
	}
	private void continueDrawing(int x, int y){
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());
		this.currentShape.continueDrawing(x,y,g2D);
	}
	private void finishDrawing(int x, int y){
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());
		this.currentShape.finishDrawing(x,y,g2D);
		this.shapeVector.add(this.currentShape);
		this.currentShape.setSelected(true);
		this.repaint();
	}
	private void changeCursor(int x, int y){
		for(GShape shape: this.shapeVector){
			EAnchors eAnchors = shape.contains(x, y);
			if(eAnchors != null) {
				switch (eAnchors) {
				case NN:
					this.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
					return;
				case NW:
					this.setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
					return;
				case NE:
					this.setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));
					return;
				case SS:
					this.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
					return;
				case SE:
					this.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
					return;
				case SW:
					this.setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));
					return;
				case EE:
					this.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
					return;
				case WW:
					this.setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
					return;
				case RR:
					this.setCursor(new Cursor(Cursor.HAND_CURSOR));
					return;
				case MM:
					this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
					return;
				default:
					break;
				}
			}
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		
	}

	class MousEventHandler implements MouseMotionListener, MouseInputListener{
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() == 1){
				mouse1Clicked(e);
			} else if (e.getClickCount() == 2){
				mouse2Clicked(e);
			}
		}
		private void mouse1Clicked(MouseEvent e){
			if(eState == EState.idleNP){
				initDrawing(e.getX(), e.getY());
				eState = EState.drawingNP;
			} else if(eState == EState.drawingNP){
				continueDrawing(e.getX(),e.getY());
			}
		}
		private void mouse2Clicked(MouseEvent e){
			if(eState == EState.drawingNP){
				finishDrawing(e.getX(), e.getY());
				eState = EState.idleNP;
			}
		}
		@Override
		public void mousePressed(MouseEvent e) {
			if(eState == EState.idleTP){
				initDrawing(e.getX(), e.getY());
				eState = EState.drawingTP;
			}
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			if(eState == EState.drawingTP){
				finishDrawing(e.getX(), e.getY());
				eState = EState.idleTP;
			}
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			if(eState == EState.drawingTP){
				keepDrawing(e.getX(), e.getY());
			}
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			if(eState == EState.drawingNP){
				keepDrawing(e.getX(), e.getY());
			} else if(eState == EState.idleNP || eState == EState.idleTP){
				changeCursor(e.getX(), e.getY());
			}
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
		
	}


}
