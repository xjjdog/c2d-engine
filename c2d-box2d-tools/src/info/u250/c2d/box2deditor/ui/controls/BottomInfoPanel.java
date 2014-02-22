package info.u250.c2d.box2deditor.ui.controls;

import info.u250.c2d.engine.Engine;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BottomInfoPanel extends JPanel {

	private static final long serialVersionUID = -8394720785345293957L;
	private JTextField txtX;
	private JTextField txtY;
	private JLabel lblZoom;
	private JTextField txtZoom;

	
	public BottomInfoPanel() {
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		
		JLabel lblX = new JLabel("X:");
		add(lblX);
		
		txtX = new JTextField();
		add(txtX);
		txtX.setColumns(10);
		
		JLabel lblY = new JLabel("Y:");
		add(lblY);
		
		txtY = new JTextField();
		add(txtY);
		txtY.setColumns(10);
		
		lblZoom = new JLabel("Zoom:");
		add(lblZoom);
		
		txtZoom = new JTextField();
		add(txtZoom);
		txtZoom.setColumns(10);

	}
	public void update(){
		txtX.setText((Engine.getDefaultCamera().position.x - Engine.getDefaultCamera().viewportWidth/2)+"");
		txtY.setText((Engine.getDefaultCamera().position.y - Engine.getDefaultCamera().viewportHeight/2)+"");
		txtZoom.setText(Engine.getDefaultCamera().zoom+"");
	}

}
