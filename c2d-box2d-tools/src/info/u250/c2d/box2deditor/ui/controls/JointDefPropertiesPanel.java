package info.u250.c2d.box2deditor.ui.controls;
import info.u250.c2d.box2d.model.b2JointDefModel;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;


public abstract class JointDefPropertiesPanel extends AbstractBindablePropertiesPanel {

	private static final long serialVersionUID = -2822788517963609245L;
	private JTextField txtName;
	private JPanel panel;
	private JCheckBox chckbxCollideconnected;
	private JTextField txtBodya;
	private JTextField txtBodyb;
	private JTextField txtMark;

	
	public JointDefPropertiesPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		panel = new JPanel();
		scrollPane.setViewportView(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{
				0,
				0,
				0,
				0, 
				0, 
				0, 
				0, 
				0,
				0,
				0,
				0, 
				0, 
				0, 
				0,
				0, 
				0,
				0, 
				0, 
				0, 
				0,
				0, 
				0, 
				0, 
				0, 
				0, 
				0, 
				0, 
				0, 
				0, 
				0,
				0, 
				0,
				0,
				0, 
				0, 
				0, 
				0, 
				0, 
				0, 
				0};
		gbl_panel.columnWeights = new double[]{ 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{
				0,
				0,
				0,
				0, 
				0, 
				0, 
				0, 
				0, 
				0,
				0,
				0, 
				0, 
				0, 
				0,
				0, 
				0,
				0, 
				0, 
				0, 
				0,
				0, 
				0, 
				0, 
				0, 
				0, 
				0, 
				0, 
				0, 
				0, 
				0,
				0, 
				0,
				0,
				0, 
				0, 
				0, 
				0, 
				0, 
				0,
				Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblMark = new JLabel("Mark:");
		GridBagConstraints gbc_lblMark = new GridBagConstraints();
		gbc_lblMark.anchor = GridBagConstraints.EAST;
		gbc_lblMark.insets = new Insets(0, 0, 5, 5);
		gbc_lblMark.gridx = 0;
		gbc_lblMark.gridy = 0;
		panel.add(lblMark, gbc_lblMark);
		
		txtMark = new JTextField();
		GridBagConstraints gbc_txtMark = new GridBagConstraints();
		gbc_txtMark.insets = new Insets(0, 0, 5, 0);
		gbc_txtMark.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMark.gridx = 1;
		gbc_txtMark.gridy = 0;
		panel.add(txtMark, gbc_txtMark);
		txtMark.setColumns(10);
		
		JLabel lblName = new JLabel("name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 1;
		panel.add(lblName, gbc_lblName);
		
		txtName = new JTextField();
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.insets = new Insets(0, 0, 5, 0);
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.gridx = 1;
		gbc_txtName.gridy = 1;
		panel.add(txtName, gbc_txtName);
		txtName.setColumns(10);
		
		JLabel lblBodya = new JLabel("bodyA:");
		lblBodya.setIcon(new ImageIcon(JointDefPropertiesPanel.class.getResource("/info/u250/c2d/box2deditor/ui/res/b2BodyDefModel.png")));
		GridBagConstraints gbc_lblBodya = new GridBagConstraints();
		gbc_lblBodya.anchor = GridBagConstraints.EAST;
		gbc_lblBodya.insets = new Insets(0, 0, 5, 5);
		gbc_lblBodya.gridx = 0;
		gbc_lblBodya.gridy = 2;
		panel.add(lblBodya, gbc_lblBodya);
		
		txtBodya = new JTextField();
		txtBodya.setEnabled(false);
		txtBodya.setEditable(false);
		txtBodya.setText("bodyA");
		GridBagConstraints gbc_txtBodya = new GridBagConstraints();
		gbc_txtBodya.insets = new Insets(0, 0, 5, 0);
		gbc_txtBodya.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBodya.gridx = 1;
		gbc_txtBodya.gridy = 2;
		panel.add(txtBodya, gbc_txtBodya);
		txtBodya.setColumns(10);
		
		JLabel lblBodyb = new JLabel("bodyB:");
		lblBodyb.setIcon(new ImageIcon(JointDefPropertiesPanel.class.getResource("/info/u250/c2d/box2deditor/ui/res/b2BodyDefModel.png")));
		GridBagConstraints gbc_lblBodyb = new GridBagConstraints();
		gbc_lblBodyb.anchor = GridBagConstraints.EAST;
		gbc_lblBodyb.insets = new Insets(0, 0, 5, 5);
		gbc_lblBodyb.gridx = 0;
		gbc_lblBodyb.gridy = 3;
		panel.add(lblBodyb, gbc_lblBodyb);
		
		txtBodyb = new JTextField();
		txtBodyb.setEditable(false);
		txtBodyb.setEnabled(false);
		txtBodyb.setText("bodyB");
		GridBagConstraints gbc_txtBodyb = new GridBagConstraints();
		gbc_txtBodyb.insets = new Insets(0, 0, 5, 0);
		gbc_txtBodyb.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBodyb.gridx = 1;
		gbc_txtBodyb.gridy = 3;
		panel.add(txtBodyb, gbc_txtBodyb);
		txtBodyb.setColumns(10);
		
		JLabel lblCollideconnected = new JLabel("collideConnected:");
		GridBagConstraints gbc_lblCollideconnected = new GridBagConstraints();
		gbc_lblCollideconnected.insets = new Insets(0, 0, 5, 5);
		gbc_lblCollideconnected.gridx = 0;
		gbc_lblCollideconnected.gridy = 4;
		panel.add(lblCollideconnected, gbc_lblCollideconnected);
		
		chckbxCollideconnected = new JCheckBox("collideConnected");
		GridBagConstraints gbc_chckbxCollideconnected = new GridBagConstraints();
		gbc_chckbxCollideconnected.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxCollideconnected.anchor = GridBagConstraints.WEST;
		gbc_chckbxCollideconnected.gridx = 1;
		gbc_chckbxCollideconnected.gridy = 4;
		panel.add(chckbxCollideconnected, gbc_chckbxCollideconnected);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.insets = new Insets(10, 10, 10, 10);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 5;
		gbc_separator.gridwidth  = 2;
		panel.add(separator, gbc_separator);

	}

	@Override
	public void doBinder() {
		b2JointDefModel model = b2JointDefModel.class.cast(object);
		bind(object,"name",txtName);
		bind(object,"collideConnected",chckbxCollideconnected);
		bind(model.bodyA,"name",txtBodya);
		bind(model.bodyB,"name",txtBodyb);
		bind(object,"mark",txtMark);
	}
	public JPanel getPanel() {
		return panel;
	}
}
