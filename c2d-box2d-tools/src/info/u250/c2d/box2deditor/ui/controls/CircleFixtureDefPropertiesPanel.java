package info.u250.c2d.box2deditor.ui.controls;

import info.u250.c2d.box2d.model.fixture.b2CircleFixtureDefModel;
import info.u250.c2d.box2deditor.ui.Binder;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSeparator;

public class CircleFixtureDefPropertiesPanel extends FixtureDefPropertiesPanel {
	public CircleFixtureDefPropertiesPanel() {
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.gridwidth = 2;
		gbc_separator.insets = new Insets(10, 10, 5, 10);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 11;
		getPanel().add(separator, gbc_separator);
		
		JLabel lblCircleShape = new JLabel("Circle Shape");
		lblCircleShape.setForeground(Color.BLUE);
		GridBagConstraints gbc_lblCircleShape = new GridBagConstraints();
		gbc_lblCircleShape.insets = new Insets(0, 0, 5, 0);
		gbc_lblCircleShape.anchor = GridBagConstraints.WEST;
		gbc_lblCircleShape.gridx = 1;
		gbc_lblCircleShape.gridy = 12;
		getPanel().add(lblCircleShape, gbc_lblCircleShape);
		
		JLabel lblRadius = new JLabel("radius:");
		GridBagConstraints gbc_lblRadius = new GridBagConstraints();
		gbc_lblRadius.insets = new Insets(0, 0, 5, 5);
		gbc_lblRadius.gridx = 0;
		gbc_lblRadius.gridy = 13;
		getPanel().add(lblRadius, gbc_lblRadius);
		
		spinnerRadius = new JSpinner();
		spinnerRadius.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerRadius = new GridBagConstraints();
		gbc_spinnerRadius.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerRadius.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerRadius.gridx = 1;
		gbc_spinnerRadius.gridy = 13;
		getPanel().add(spinnerRadius, gbc_spinnerRadius);
		
	}
	
	private static final long serialVersionUID = 1357591566550629249L;
	private JSpinner spinnerRadius;


	@Override
	public Class<b2CircleFixtureDefModel> getType() {
		return b2CircleFixtureDefModel.class;
	}
	@Override
	public Binder getBinder(Object obj, String name, JComponent widget) {
		return new Binder(obj,name,widget) {
			@Override
			protected void postSolve() {
				
			}
		};
	}

	@Override
	public void doBinder() {
		super.doBinder();
		bind(object, "radius", spinnerRadius);
	}
}
