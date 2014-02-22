package info.u250.c2d.box2deditor.ui.controls;

import info.u250.c2d.box2d.model.fixture.b2RectangleFixtureDefModel;
import info.u250.c2d.box2deditor.ui.Binder;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class RectangelFixtureDefPropertiesPanel extends FixtureDefPropertiesPanel {
	public RectangelFixtureDefPropertiesPanel() {
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.gridwidth = 2;
		gbc_separator.insets = new Insets(10, 10, 5, 10);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 11;
		getPanel().add(separator, gbc_separator);
		
		JLabel lblCircleShape = new JLabel("Box Shape");
		lblCircleShape.setForeground(Color.BLUE);
		GridBagConstraints gbc_lblCircleShape = new GridBagConstraints();
		gbc_lblCircleShape.insets = new Insets(0, 0, 5, 0);
		gbc_lblCircleShape.anchor = GridBagConstraints.WEST;
		gbc_lblCircleShape.gridx = 1;
		gbc_lblCircleShape.gridy = 12;
		getPanel().add(lblCircleShape, gbc_lblCircleShape);
		
		JLabel lblWidth = new JLabel("width:");
		GridBagConstraints gbc_lblWidth = new GridBagConstraints();
		gbc_lblWidth.insets = new Insets(0, 0, 5, 5);
		gbc_lblWidth.gridx = 0;
		gbc_lblWidth.gridy = 13;
		getPanel().add(lblWidth, gbc_lblWidth);
		
		spinnerWidth = new JSpinner();
		spinnerWidth.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerWidth = new GridBagConstraints();
		gbc_spinnerWidth.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerWidth.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerWidth.gridx = 1;
		gbc_spinnerWidth.gridy = 13;
		getPanel().add(spinnerWidth, gbc_spinnerWidth);
		
		JLabel lblHeight = new JLabel("height:");
		GridBagConstraints gbc_lblHeight = new GridBagConstraints();
		gbc_lblHeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeight.gridx = 0;
		gbc_lblHeight.gridy = 14;
		getPanel().add(lblHeight, gbc_lblHeight);
		
		spinnerHeight = new JSpinner();
		spinnerHeight.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerHeight = new GridBagConstraints();
		gbc_spinnerHeight.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerHeight.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerHeight.gridx = 1;
		gbc_spinnerHeight.gridy = 14;
		getPanel().add(spinnerHeight, gbc_spinnerHeight);
		
	}
	
	private static final long serialVersionUID = 1357591566550629249L;
	private JSpinner spinnerHeight;
	private JSpinner spinnerWidth;

	@Override
	public void doBinder() {
		super.doBinder();
		bind(object, "width", spinnerWidth);
		bind(object, "height",spinnerHeight);
	}

	@Override
	public Class<b2RectangleFixtureDefModel> getType() {
		return b2RectangleFixtureDefModel.class;
	}
	@Override
	public Binder getBinder(Object obj, String name, JComponent widget) {
		return new Binder(obj,name,widget) {
			@Override
			protected void postSolve() {
				
			}
		};
	}
}
