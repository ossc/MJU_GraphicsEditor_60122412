package frames;
import java.awt.BorderLayout;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import constants.GConstants;
import constants.GConstants.EMainFrame;

// MainFrame specialize JFrame
public class GMainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	// component
	private GMenuBar menuBar;
	private GToolBar toolBar;
	private GDrawingPanel drawingPanel;
	// 
	public GMainFrame() {
		// attribute initialization
		this.setTitle(GConstants.MAINFRAME_TITLE);
		this.setLocation(EMainFrame.X.getValue(),EMainFrame.Y.getValue());
		this.setSize(EMainFrame.W.getValue(), EMainFrame.H.getValue());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout());
		// component creation & registration
		this.menuBar = new GMenuBar();
		this.setJMenuBar(menuBar);
		this.toolBar = new GToolBar();
		this.add(toolBar, BorderLayout.NORTH);
		this.drawingPanel = new GDrawingPanel();
		this.add(drawingPanel, BorderLayout.CENTER);
	}
	
	public void initialize(){
		// set associations among components
		toolBar.setDrawingPanel(drawingPanel);
		// component initialization
		menuBar.initialize();	
		toolBar.initialize();	
		drawingPanel.initialize();
		
		
	}
}
