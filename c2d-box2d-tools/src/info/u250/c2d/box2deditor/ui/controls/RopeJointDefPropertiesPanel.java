package info.u250.c2d.box2deditor.ui.controls;

import info.u250.c2d.box2deditor.adapter.RopeJointDefModel;
import info.u250.c2d.box2deditor.gdx.support.Geometry;
import info.u250.c2d.box2deditor.ui.Binder;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class RopeJointDefPropertiesPanel extends JointDefPropertiesPanel{
	public RopeJointDefPropertiesPanel() {
		
		JLabel lblLocalanchorax = new JLabel("localAnchorA-x:");
		GridBagConstraints gbc_lblLocalanchorax = new GridBagConstraints();
		gbc_lblLocalanchorax.insets = new Insets(0, 0, 5, 5);
		gbc_lblLocalanchorax.gridx = 0;
		gbc_lblLocalanchorax.gridy = 6;
		getPanel().add(lblLocalanchorax, gbc_lblLocalanchorax);
		
		spinnerLocalAnchorAX = new JSpinner();
		spinnerLocalAnchorAX.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerLocalAnchorAX = new GridBagConstraints();
		gbc_spinnerLocalAnchorAX.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerLocalAnchorAX.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerLocalAnchorAX.gridx = 1;
		gbc_spinnerLocalAnchorAX.gridy = 6;
		getPanel().add(spinnerLocalAnchorAX, gbc_spinnerLocalAnchorAX);
		
		JLabel lblLocalanchoray = new JLabel("localAnchorA-y:");
		GridBagConstraints gbc_lblLocalanchoray = new GridBagConstraints();
		gbc_lblLocalanchoray.insets = new Insets(0, 0, 5, 5);
		gbc_lblLocalanchoray.gridx = 0;
		gbc_lblLocalanchoray.gridy = 7;
		getPanel().add(lblLocalanchoray, gbc_lblLocalanchoray);
		
		spinnerLocalAnchorAY = new JSpinner();
		spinnerLocalAnchorAY.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerLocalAnchorAY = new GridBagConstraints();
		gbc_spinnerLocalAnchorAY.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerLocalAnchorAY.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerLocalAnchorAY.gridx = 1;
		gbc_spinnerLocalAnchorAY.gridy = 7;
		getPanel().add(spinnerLocalAnchorAY, gbc_spinnerLocalAnchorAY);
		
		chckbxUseBodyaCenter = new JCheckBox("Set To Zero");
		GridBagConstraints gbc_chckbxUseBodyaCenter = new GridBagConstraints();
		gbc_chckbxUseBodyaCenter.anchor = GridBagConstraints.WEST;
		gbc_chckbxUseBodyaCenter.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxUseBodyaCenter.gridx = 1;
		gbc_chckbxUseBodyaCenter.gridy = 8;
		getPanel().add(chckbxUseBodyaCenter, gbc_chckbxUseBodyaCenter);
		
		JLabel lblLocalanchorbx = new JLabel("localAnchorB-x:");
		GridBagConstraints gbc_lblLocalanchorbx = new GridBagConstraints();
		gbc_lblLocalanchorbx.insets = new Insets(0, 0, 5, 5);
		gbc_lblLocalanchorbx.gridx = 0;
		gbc_lblLocalanchorbx.gridy = 9;
		getPanel().add(lblLocalanchorbx, gbc_lblLocalanchorbx);
		
		spinnerLocalAnchorBx = new JSpinner();
		spinnerLocalAnchorBx.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerLocalAnchorBx = new GridBagConstraints();
		gbc_spinnerLocalAnchorBx.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerLocalAnchorBx.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerLocalAnchorBx.gridx = 1;
		gbc_spinnerLocalAnchorBx.gridy = 9;
		getPanel().add(spinnerLocalAnchorBx, gbc_spinnerLocalAnchorBx);
		
		JLabel lblLocalanchorby = new JLabel("localAnchorB-y:");
		GridBagConstraints gbc_lblLocalanchorby = new GridBagConstraints();
		gbc_lblLocalanchorby.insets = new Insets(0, 0, 5, 5);
		gbc_lblLocalanchorby.gridx = 0;
		gbc_lblLocalanchorby.gridy = 10;
		getPanel().add(lblLocalanchorby, gbc_lblLocalanchorby);
		
		spinnerLocalAnchorBy = new JSpinner();
		spinnerLocalAnchorBy.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerLocalAnchorBy = new GridBagConstraints();
		gbc_spinnerLocalAnchorBy.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerLocalAnchorBy.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerLocalAnchorBy.gridx = 1;
		gbc_spinnerLocalAnchorBy.gridy = 10;
		getPanel().add(spinnerLocalAnchorBy, gbc_spinnerLocalAnchorBy);
		
		chckbxUseBodybCenter = new JCheckBox("Set To Zero");
		GridBagConstraints gbc_chckbxUseBodybCenter = new GridBagConstraints();
		gbc_chckbxUseBodybCenter.anchor = GridBagConstraints.WEST;
		gbc_chckbxUseBodybCenter.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxUseBodybCenter.gridx = 1;
		gbc_chckbxUseBodybCenter.gridy = 11;
		getPanel().add(chckbxUseBodybCenter, gbc_chckbxUseBodybCenter);
		
		JLabel lblLength = new JLabel("maxLength:");
		GridBagConstraints gbc_lblLength = new GridBagConstraints();
		gbc_lblLength.insets = new Insets(0, 0, 5, 5);
		gbc_lblLength.gridx = 0;
		gbc_lblLength.gridy = 12;
		getPanel().add(lblLength, gbc_lblLength);
		
		spinnerLength = new JSpinner();
		spinnerLength.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerLength = new GridBagConstraints();
		gbc_spinnerLength.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerLength.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerLength.gridx = 1;
		gbc_spinnerLength.gridy = 12;
		getPanel().add(spinnerLength, gbc_spinnerLength);
		
		chckbxAutoCalculate = new JCheckBox("Auto Calculate");
		GridBagConstraints gbc_chckbxAutoCalculate = new GridBagConstraints();
		gbc_chckbxAutoCalculate.anchor = GridBagConstraints.WEST;
		gbc_chckbxAutoCalculate.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxAutoCalculate.gridx = 1;
		gbc_chckbxAutoCalculate.gridy = 13;
		getPanel().add(chckbxAutoCalculate, gbc_chckbxAutoCalculate);
	}
	private static final long serialVersionUID = -2033825670615410634L;
	private JSpinner spinnerLocalAnchorAX;
	private JSpinner spinnerLength;
	private JSpinner spinnerLocalAnchorBy;
	private JSpinner spinnerLocalAnchorBx;
	private JSpinner spinnerLocalAnchorAY;
	private JCheckBox chckbxUseBodyaCenter;
	private JCheckBox chckbxUseBodybCenter;
	private JCheckBox chckbxAutoCalculate;

	@Override
	public Class<RopeJointDefModel> getType() {
		return RopeJointDefModel.class;
	}

	@Override
	public void doBinder() {
		super.doBinder();
		RopeJointDefModel model = RopeJointDefModel.class.cast(object);
		bind(model.localAnchorA,"x",spinnerLocalAnchorAX);
		bind(model.localAnchorA,"y",spinnerLocalAnchorAY);
		bind(model.localAnchorB,"x",spinnerLocalAnchorBx);
		bind(model.localAnchorB,"y",spinnerLocalAnchorBy);
		bind(model,"maxLength",spinnerLength);
		bind(model,"setBodyAZero",chckbxUseBodyaCenter);
		bind(model,"setBodyBZero",chckbxUseBodybCenter);
		bind(model,"autoCalculateLength",chckbxAutoCalculate);
	}
	@Override
	public Binder getBinder(Object obj, String name, JComponent widget) {
		return new Binder(obj,name,widget) {
			@Override
			protected void postSolve() {
				RopeJointDefModel model = RopeJointDefModel.class.cast(object);
				Geometry.ajustJoint(model);
				
				//set the UI values
				spinnerLength.setValue(model.maxLength);
				spinnerLocalAnchorAX.setValue(model.localAnchorA.x);
				spinnerLocalAnchorAY.setValue(model.localAnchorA.y);
				spinnerLocalAnchorBx.setValue(model.localAnchorB.x);
				spinnerLocalAnchorBy.setValue(model.localAnchorB.y);
			}
		};
	}
}
