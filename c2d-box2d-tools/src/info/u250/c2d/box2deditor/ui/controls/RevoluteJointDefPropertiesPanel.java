package info.u250.c2d.box2deditor.ui.controls;

import info.u250.c2d.box2deditor.adapter.RevoluteJointDefModel;
import info.u250.c2d.box2deditor.gdx.support.Geometry;
import info.u250.c2d.box2deditor.ui.Binder;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class RevoluteJointDefPropertiesPanel extends JointDefPropertiesPanel{
	public RevoluteJointDefPropertiesPanel() {
		
		JLabel lblLocalanchorax = new JLabel("localAnchorA-x:");
		GridBagConstraints gbc_lblLocalanchorax = new GridBagConstraints();
		gbc_lblLocalanchorax.insets = new Insets(0, 0, 5, 5);
		gbc_lblLocalanchorax.gridx = 0;
		gbc_lblLocalanchorax.gridy = 6;
		getPanel().add(lblLocalanchorax, gbc_lblLocalanchorax);
		
		spinnerLocalAnchorAX = new JSpinner();
		spinnerLocalAnchorAX.setEnabled(false);
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
		spinnerLocalAnchorAY.setEnabled(false);
		spinnerLocalAnchorAY.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerLocalAnchorAY = new GridBagConstraints();
		gbc_spinnerLocalAnchorAY.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerLocalAnchorAY.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerLocalAnchorAY.gridx = 1;
		gbc_spinnerLocalAnchorAY.gridy = 7;
		getPanel().add(spinnerLocalAnchorAY, gbc_spinnerLocalAnchorAY);
		
		chckbxUseBodyaCenter = new JCheckBox("Use BodyA Center");
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
		spinnerLocalAnchorBx.setEnabled(false);
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
		spinnerLocalAnchorBy.setEnabled(false);
		spinnerLocalAnchorBy.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerLocalAnchorBy = new GridBagConstraints();
		gbc_spinnerLocalAnchorBy.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerLocalAnchorBy.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerLocalAnchorBy.gridx = 1;
		gbc_spinnerLocalAnchorBy.gridy = 10;
		getPanel().add(spinnerLocalAnchorBy, gbc_spinnerLocalAnchorBy);
		
		chckbxUseBodybCenter = new JCheckBox("Use BodyB Center");
		GridBagConstraints gbc_chckbxUseBodybCenter = new GridBagConstraints();
		gbc_chckbxUseBodybCenter.anchor = GridBagConstraints.WEST;
		gbc_chckbxUseBodybCenter.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxUseBodybCenter.gridx = 1;
		gbc_chckbxUseBodybCenter.gridy = 11;
		getPanel().add(chckbxUseBodybCenter, gbc_chckbxUseBodybCenter);
		
		lblReferenceangle = new JLabel("referenceAngle:");
		GridBagConstraints gbc_lblReferenceangle = new GridBagConstraints();
		gbc_lblReferenceangle.insets = new Insets(0, 0, 5, 5);
		gbc_lblReferenceangle.gridx = 0;
		gbc_lblReferenceangle.gridy = 12;
		getPanel().add(lblReferenceangle, gbc_lblReferenceangle);
		
		spinnerReferenceAngle = new JSpinner();
		spinnerReferenceAngle.setEnabled(false);
		spinnerReferenceAngle.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerReferenceAngle = new GridBagConstraints();
		gbc_spinnerReferenceAngle.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerReferenceAngle.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerReferenceAngle.gridx = 1;
		gbc_spinnerReferenceAngle.gridy = 12;
		getPanel().add(spinnerReferenceAngle, gbc_spinnerReferenceAngle);
		
		lblAnchorx = new JLabel("anchorX:");
		GridBagConstraints gbc_lblAnchorx = new GridBagConstraints();
		gbc_lblAnchorx.insets = new Insets(0, 0, 5, 5);
		gbc_lblAnchorx.gridx = 0;
		gbc_lblAnchorx.gridy = 13;
		getPanel().add(lblAnchorx, gbc_lblAnchorx);
		
		spinnerAnchorx = new JSpinner();
		spinnerAnchorx.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerAnchorx = new GridBagConstraints();
		gbc_spinnerAnchorx.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerAnchorx.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerAnchorx.gridx = 1;
		gbc_spinnerAnchorx.gridy = 13;
		getPanel().add(spinnerAnchorx, gbc_spinnerAnchorx);
		
		lblAnchory = new JLabel("anchorY:");
		GridBagConstraints gbc_lblAnchory = new GridBagConstraints();
		gbc_lblAnchory.insets = new Insets(0, 0, 5, 5);
		gbc_lblAnchory.gridx = 0;
		gbc_lblAnchory.gridy = 14;
		getPanel().add(lblAnchory, gbc_lblAnchory);
		
		spinnerAnchory = new JSpinner();
		spinnerAnchory.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerAnchory = new GridBagConstraints();
		gbc_spinnerAnchory.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerAnchory.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerAnchory.gridx = 1;
		gbc_spinnerAnchory.gridy = 14;
		getPanel().add(spinnerAnchory, gbc_spinnerAnchory);
		
		lblEnablelimit = new JLabel("enableLimit:");
		GridBagConstraints gbc_lblEnablelimit = new GridBagConstraints();
		gbc_lblEnablelimit.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnablelimit.gridx = 0;
		gbc_lblEnablelimit.gridy = 15;
		getPanel().add(lblEnablelimit, gbc_lblEnablelimit);
		
		chckbxEnablelimit = new JCheckBox("enableLimit");
		GridBagConstraints gbc_chckbxEnablelimit = new GridBagConstraints();
		gbc_chckbxEnablelimit.anchor = GridBagConstraints.WEST;
		gbc_chckbxEnablelimit.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxEnablelimit.gridx = 1;
		gbc_chckbxEnablelimit.gridy = 15;
		getPanel().add(chckbxEnablelimit, gbc_chckbxEnablelimit);
		
		lblLowerangle = new JLabel("lowerDegrees:");
		GridBagConstraints gbc_lblLowerangle = new GridBagConstraints();
		gbc_lblLowerangle.insets = new Insets(0, 0, 5, 5);
		gbc_lblLowerangle.gridx = 0;
		gbc_lblLowerangle.gridy = 16;
		getPanel().add(lblLowerangle, gbc_lblLowerangle);
		
		spinnerLowerAngle = new JSpinner();
		spinnerLowerAngle.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerLowerAngle = new GridBagConstraints();
		gbc_spinnerLowerAngle.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerLowerAngle.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerLowerAngle.gridx = 1;
		gbc_spinnerLowerAngle.gridy = 16;
		getPanel().add(spinnerLowerAngle, gbc_spinnerLowerAngle);
		
		lblUpperangle = new JLabel("upperDegrees:");
		GridBagConstraints gbc_lblUpperangle = new GridBagConstraints();
		gbc_lblUpperangle.insets = new Insets(0, 0, 5, 5);
		gbc_lblUpperangle.gridx = 0;
		gbc_lblUpperangle.gridy = 17;
		getPanel().add(lblUpperangle, gbc_lblUpperangle);
		
		spinnerUpperAngle = new JSpinner();
		spinnerUpperAngle.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerUpperAngle = new GridBagConstraints();
		gbc_spinnerUpperAngle.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerUpperAngle.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerUpperAngle.gridx = 1;
		gbc_spinnerUpperAngle.gridy = 17;
		getPanel().add(spinnerUpperAngle, gbc_spinnerUpperAngle);
		
		lblEnablemotor = new JLabel("enableMotor:");
		GridBagConstraints gbc_lblEnablemotor = new GridBagConstraints();
		gbc_lblEnablemotor.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnablemotor.gridx = 0;
		gbc_lblEnablemotor.gridy = 18;
		getPanel().add(lblEnablemotor, gbc_lblEnablemotor);
		
		chckbxEnablemotor = new JCheckBox("enableMotor");
		GridBagConstraints gbc_chckbxEnablemotor = new GridBagConstraints();
		gbc_chckbxEnablemotor.anchor = GridBagConstraints.WEST;
		gbc_chckbxEnablemotor.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxEnablemotor.gridx = 1;
		gbc_chckbxEnablemotor.gridy = 18;
		getPanel().add(chckbxEnablemotor, gbc_chckbxEnablemotor);
		
		lblMotorspeed = new JLabel("motorSpeed:");
		GridBagConstraints gbc_lblMotorspeed = new GridBagConstraints();
		gbc_lblMotorspeed.insets = new Insets(0, 0, 5, 5);
		gbc_lblMotorspeed.gridx = 0;
		gbc_lblMotorspeed.gridy = 19;
		getPanel().add(lblMotorspeed, gbc_lblMotorspeed);
		
		spinnerMotorSpeed = new JSpinner();
		spinnerMotorSpeed.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerMotorSpeed = new GridBagConstraints();
		gbc_spinnerMotorSpeed.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerMotorSpeed.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerMotorSpeed.gridx = 1;
		gbc_spinnerMotorSpeed.gridy = 19;
		getPanel().add(spinnerMotorSpeed, gbc_spinnerMotorSpeed);
		
		lblMaxmotortorque = new JLabel("maxMotorTorque:");
		GridBagConstraints gbc_lblMaxmotortorque = new GridBagConstraints();
		gbc_lblMaxmotortorque.insets = new Insets(0, 0, 5, 5);
		gbc_lblMaxmotortorque.gridx = 0;
		gbc_lblMaxmotortorque.gridy = 20;
		getPanel().add(lblMaxmotortorque, gbc_lblMaxmotortorque);
		
		spinnerMaxMotorTorque = new JSpinner();
		spinnerMaxMotorTorque.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerMaxMotorTorque = new GridBagConstraints();
		gbc_spinnerMaxMotorTorque.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerMaxMotorTorque.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerMaxMotorTorque.gridx = 1;
		gbc_spinnerMaxMotorTorque.gridy = 20;
		getPanel().add(spinnerMaxMotorTorque, gbc_spinnerMaxMotorTorque);
		
		
		chckbxUseBodyaCenter.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(chckbxUseBodyaCenter.isSelected()){
					RevoluteJointDefModel.class.cast(object).useBodyBCenter = false;
					chckbxUseBodybCenter.setSelected(false);
				}
			}
		});
		chckbxUseBodybCenter.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(chckbxUseBodybCenter.isSelected()){
					RevoluteJointDefModel.class.cast(object).useBodyACenter = false;
					chckbxUseBodyaCenter.setSelected(false);
				}
			}
		});
	}
	private static final long serialVersionUID = -2033825670615410634L;
	private JSpinner spinnerLocalAnchorAX;
	private JSpinner spinnerLocalAnchorBy;
	private JSpinner spinnerLocalAnchorBx;
	private JSpinner spinnerLocalAnchorAY;
	private JLabel lblAnchorx;
	private JSpinner spinnerAnchorx;
	private JLabel lblAnchory;
	private JSpinner spinnerAnchory;
	private JLabel lblReferenceangle;
	private JSpinner spinnerReferenceAngle;
	private JLabel lblEnablelimit;
	private JCheckBox chckbxEnablelimit;
	private JLabel lblLowerangle;
	private JSpinner spinnerLowerAngle;
	private JLabel lblUpperangle;
	private JSpinner spinnerUpperAngle;
	private JLabel lblEnablemotor;
	private JCheckBox chckbxEnablemotor;
	private JLabel lblMotorspeed;
	private JSpinner spinnerMotorSpeed;
	private JLabel lblMaxmotortorque;
	private JSpinner spinnerMaxMotorTorque;
	private JCheckBox chckbxUseBodyaCenter;
	private JCheckBox chckbxUseBodybCenter;

	@Override
	public Class<RevoluteJointDefModel> getType() {
		return RevoluteJointDefModel.class;
	}

	@Override
	public void doBinder() {
		super.doBinder();
		RevoluteJointDefModel model = RevoluteJointDefModel.class.cast(object);
		bind(model.anchor,"x",spinnerAnchorx);
		bind(model.anchor,"y",spinnerAnchory);
		bind(model,"maxMotorTorque",spinnerMaxMotorTorque);
		bind(model,"motorSpeed",spinnerMotorSpeed);
		bind(model,"enableMotor",chckbxEnablemotor);
		bind(model,"upperDegrees",spinnerUpperAngle);
		bind(model,"lowerDegrees",spinnerLowerAngle);
		bind(model,"enableLimit",chckbxEnablelimit);
		bind(model,"useBodyACenter",chckbxUseBodyaCenter);
		bind(model,"useBodyBCenter",chckbxUseBodybCenter);
	}
	@Override
	public Binder getBinder(Object obj, String name, final JComponent widget) {
		return new Binder(obj,name,widget) {
			@Override
			protected void postSolve() {
				RevoluteJointDefModel model = RevoluteJointDefModel.class.cast(object);
				
				Geometry.ajustJoint(model);
				
				if(chckbxUseBodyaCenter.isSelected()){
					spinnerAnchorx.setValue(model.bodyA.position.x);
					spinnerAnchory.setValue(model.bodyA.position.y);
				}else{
					if(chckbxUseBodybCenter.isSelected()){
						spinnerAnchorx.setValue(model.bodyB.position.x);
						spinnerAnchory.setValue(model.bodyB.position.y);
					}
				}
				spinnerLocalAnchorAX.setValue(model.localAnchorA.x);
				spinnerLocalAnchorAY.setValue(model.localAnchorA.y);
				spinnerLocalAnchorBx.setValue(model.localAnchorB.x);
				spinnerLocalAnchorBy.setValue(model.localAnchorB.y);
				spinnerReferenceAngle.setValue(model.referenceDegrees);
			}
		};
	}
}
