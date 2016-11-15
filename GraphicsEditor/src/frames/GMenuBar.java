package frames;
import javax.swing.JMenuBar;

import menus.GEditMenu;
import menus.GFileMenu;

public class GMenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;

	GMenuBar() {
		GFileMenu fileMenu = new GFileMenu();
		this.add(fileMenu);
		GEditMenu editMenu = new GEditMenu();
		this.add(editMenu);
	}

	public void initialize() {
		// TODO Auto-generated method stub
		
	}
}
