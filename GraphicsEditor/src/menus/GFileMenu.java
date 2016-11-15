package menus;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GConstants;
import constants.GConstants.EFileMenuItem;

public class GFileMenu extends JMenu {
	private static final long serialVersionUID = 1L;

	public GFileMenu() {
		super(GConstants.FILEMENU_TITLE);		
		// object creation 
		for(EFileMenuItem eMenuItem: EFileMenuItem.values()){
			JMenuItem menuItem = new JMenuItem(eMenuItem.getText());
			this.add(menuItem);
			if(eMenuItem.getText().equals(EFileMenuItem.close.getText()) 
					|| eMenuItem.getText().equals(EFileMenuItem.saveAs.getText())){
				this.addSeparator();
			}
		}

	}
}
