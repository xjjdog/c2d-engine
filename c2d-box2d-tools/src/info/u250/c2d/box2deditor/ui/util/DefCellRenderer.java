package info.u250.c2d.box2deditor.ui.util;

import info.u250.c2d.box2d.model.b2Model;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;

public class DefCellRenderer extends JLabel implements ListCellRenderer {
	private static final long serialVersionUID = -4431896294266156816L;
	private static final Color HIGHLIGHT_COLOR = new Color(120, 181, 223);

	public DefCellRenderer() {
		setOpaque(true);
		setIconTextGap(5);		
	}

	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		this.setIcon(new ImageIcon(this.getClass().getResource("/info/u250/c2d/box2deditor/ui/res/"+value.getClass().getSimpleName()+".png")));
		this.setText("<html><font color='blue'><b>"+b2Model.class.cast(value).name+
				"</b></font><font color='gray'>("
				+value.getClass().getSimpleName().replaceAll("b2", "").replaceAll("DefModel", "").replaceAll("Fixture", "").replaceAll("Joint", "")
				+")</font></html>");

		if (isSelected) {
			setBackground(HIGHLIGHT_COLOR);
			setForeground(Color.white);
		} else {
			setBackground(Color.white);
			setForeground(Color.black);
		}
		
		Border paddingBorder = BorderFactory.createEmptyBorder(5,10,5,10);
		Border border = BorderFactory.createLineBorder(Color.BLUE);
		this.setBorder(BorderFactory.createCompoundBorder(border,paddingBorder));

		return this;
	}

}
