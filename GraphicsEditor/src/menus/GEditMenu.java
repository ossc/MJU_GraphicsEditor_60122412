package menus;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GConstants;
import constants.GConstants.EEditMenuItem;

public class GEditMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	
	public GEditMenu() {
		super(GConstants.EDITMENU_TITLE);
		for(EEditMenuItem eMenuItem: EEditMenuItem.values()){
			JMenuItem menuItem = new JMenuItem(eMenuItem.getText());
			this.add(menuItem);
			if(eMenuItem.getText().equals(EEditMenuItem.delete.getText()) 
					|| eMenuItem.getText().equals(EEditMenuItem.undo.getText())){
				this.addSeparator();
			}
		}	
	}
}
