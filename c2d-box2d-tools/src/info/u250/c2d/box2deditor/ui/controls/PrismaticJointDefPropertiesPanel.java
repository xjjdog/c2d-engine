package info.u250.c2d.box2deditor.ui.controls;

import info.u250.c2d.box2deditor.adapter.PrismaticJointDefModel;
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

public class PrismaticJointDefPropertiesPanel extends JointDefPropertiesPanel{
	public PrismaticJointDefPropertiesPanel() {
		
		lblLocalaxisax = new JLabel("localAxisAx:");
		GridBagConstraints gbc_lblLocalaxisax = new GridBagConstraints();
		gbc_lblLocalaxisax.insets = new Insets(0, 0, 5, 5);
		gbc_lblLocalaxisax.gridx = 0;
		gbc_lblLocalaxisax.gridy = 6;
		getPanel().add(lblLocalaxisax, gbc_lblLocalaxisax);
		
		spinnerLocalAxisAx = new JSpinner();
		spinnerLocalAxisAx.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerLocalAxisAx = new GridBagConstraints();
		gbc_spinnerLocalAxisAx.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerLocalAxisAx.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerLocalAxisAx.gridx = 1;
		gbc_spinnerLocalAxisAx.gridy = 6;
		getPanel().add(spinnerLocalAxisAx, gbc_spinnerLocalAxisAx);
		
		lblLocalAxisAy = new JLabel("localAxisAy:");
		GridBagConstraints gbc_lblLocalAxisAy = new GridBagConstraints();
		gbc_lblLocalAxisAy.insets = new Insets(0, 0, 5, 5);
		gbc_lblLocalAxisAy.gridx = 0;
		gbc_lblLocalAxisAy.gridy = 7;
		getPanel().add(lblLocalAxisAy, gbc_lblLocalAxisAy);
		
		spinnerLocalAxisAy = new JSpinner();
		spinnerLocalAxisAy.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerLocalAxisA = new GridBagConstraints();
		gbc_spinnerLocalAxisA.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerLocalAxisA.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerLocalAxisA.gridx = 1;
		gbc_spinnerLocalAxisA.gridy = 7;
		getPanel().add(spinnerLocalAxisAy, gbc_spinnerLocalAxisA);
		
		chckbxUseBodyabCenter = new JCheckBox("Use BodyA/B Center Line");
		GridBagConstraints gbc_chckbxUseBodyabCenter = new GridBagConstraints();
		gbc_chckbxUseBodyabCenter.anchor = GridBagConstraints.WEST;
		gbc_chckbxUseBodyabCenter.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxUseBodyabCenter.gridx = 1;
		gbc_chckbxUseBodyabCenter.gridy = 8;
		getPanel().add(chckbxUseBodyabCenter, gbc_chckbxUseBodyabCenter);
		
		JLabel lblLocalanchorax = new JLabel("localAnchorA-x:");
		GridBagConstraints gbc_lblLocalanchorax = new GridBagConstraints();
		gbc_lblLocalanchorax.insets = new Insets(0, 0, 5, 5);
		gbc_lblLocalanchorax.gridx = 0;
		gbc_lblLocalanchorax.gridy = 9;
		getPanel().add(lblLocalanchorax, gbc_lblLocalanchorax);
		
		spinnerLocalAnchorAX = new JSpinner();
		spinnerLocalAnchorAX.setEnabled(false);
		spinnerLocalAnchorAX.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerLocalAnchorAX = new GridBagConstraints();
		gbc_spinnerLocalAnchorAX.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerLocalAnchorAX.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerLocalAnchorAX.gridx = 1;
		gbc_spinnerLocalAnchorAX.gridy = 9;
		getPanel().add(spinnerLocalAnchorAX, gbc_spinnerLocalAnchorAX);
		
		JLabel lblLocalanchoray = new JLabel("localAnchorA-y:");
		GridBagConstraints gbc_lblLocalanchoray = new GridBagConstraints();
		gbc_lblLocalanchoray.insets = new Insets(0, 0, 5, 5);
		gbc_lblLocalanchoray.gridx = 0;
		gbc_lblLocalanchoray.gridy = 10;
		getPanel().add(lblLocalanchoray, gbc_lblLocalanchoray);
		
		spinnerLocalAnchorAY = new JSpinner();
		spinnerLocalAnchorAY.setEnabled(false);
		spinnerLocalAnchorAY.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerLocalAnchorAY = new GridBagConstraints();
		gbc_spinnerLocalAnchorAY.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerLocalAnchorAY.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerLocalAnchorAY.gridx = 1;
		gbc_spinnerLocalAnchorAY.gridy = 10;
		getPanel().add(spinnerLocalAnchorAY, gbc_spinnerLocalAnchorAY);
		
		chckbxUseBodyaCenter = new JCheckBox("Use BodyA Center");
		GridBagConstraints gbc_chckbxUseBodyaCenter = new GridBagConstraints();
		gbc_chckbxUseBodyaCenter.anchor = GridBagConstraints.WEST;
		gbc_chckbxUseBodyaCenter.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxUseBodyaCenter.gridx = 1;
		gbc_chckbxUseBodyaCenter.gridy = 11;
		getPanel().add(chckbxUseBodyaCenter, gbc_chckbxUseBodyaCenter);
		
		JLabel lblLocalanchorbx = new JLabel("localAnchorB-x:");
		GridBagConstraints gbc_lblLocalanchorbx = new GridBagConstraints();
		gbc_lblLocalanchorbx.insets = new Insets(0, 0, 5, 5);
		gbc_lblLocalanchorbx.gridx = 0;
		gbc_lblLocalanchorbx.gridy = 12;
		getPanel().add(lblLocalanchorbx, gbc_lblLocalanchorbx);
		
		spinnerLocalAnchorBx = new JSpinner();
		spinnerLocalAnchorBx.setEnabled(false);
		spinnerLocalAnchorBx.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerLocalAnchorBx = new GridBagConstraints();
		gbc_spinnerLocalAnchorBx.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerLocalAnchorBx.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerLocalAnchorBx.gridx = 1;
		gbc_spinnerLocalAnchorBx.gridy = 12;
		getPanel().add(spinnerLocalAnchorBx, gbc_spinnerLocalAnchorBx);
		
		JLabel lblLocalanchorby = new JLabel("localAnchorB-y:");
		GridBagConstraints gbc_lblLocalanchorby = new GridBagConstraints();
		gbc_lblLocalanchorby.insets = new Insets(0, 0, 5, 5);
		gbc_lblLocalanchorby.gridx = 0;
		gbc_lblLocalanchorby.gridy = 13;
		getPanel().add(lblLocalanchorby, gbc_lblLocalanchorby);
		
		spinnerLocalAnchorBy = new JSpinner();
		spinnerLocalAnchorBy.setEnabled(false);
		spinnerLocalAnchorBy.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerLocalAnchorBy = new GridBagConstraints();
		gbc_spinnerLocalAnchorBy.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerLocalAnchorBy.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerLocalAnchorBy.gridx = 1;
		gbc_spinnerLocalAnchorBy.gridy = 13;
		getPanel().add(spinnerLocalAnchorBy, gbc_spinnerLocalAnchorBy);
		
		chckbxUseBodybCenter = new JCheckBox("Use BodyB Center");
		GridBagConstraints gbc_chckbxUseBodybCenter = new GridBagConstraints();
		gbc_chckbxUseBodybCenter.anchor = GridBagConstraints.WEST;
		gbc_chckbxUseBodybCenter.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxUseBodybCenter.gridx = 1;
		gbc_chckbxUseBodybCenter.gridy = 14;
		getPanel().add(chckbxUseBodybCenter, gbc_chckbxUseBodybCenter);
		
		lblReferenceangle = new JLabel("referenceAngle:");
		GridBagConstraints gbc_lblReferenceangle = new GridBagConstraints();
		gbc_lblReferenceangle.insets = new Insets(0, 0, 5, 5);
		gbc_lblReferenceangle.gridx = 0;
		gbc_lblReferenceangle.gridy = 15;
		getPanel().add(lblReferenceangle, gbc_lblReferenceangle);
		
		spinnerReferenceAngle = new JSpinner();
		spinnerReferenceAngle.setEnabled(false);
		spinnerReferenceAngle.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerReferenceAngle = new GridBagConstraints();
		gbc_spinnerReferenceAngle.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerReferenceAngle.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerReferenceAngle.gridx = 1;
		gbc_spinnerReferenceAngle.gridy = 15;
		getPanel().add(spinnerReferenceAngle, gbc_spinnerReferenceAngle);
		
		lblAnchorx = new JLabel("anchorX:");
		GridBagConstraints gbc_lblAnchorx = new GridBagConstraints();
		gbc_lblAnchorx.insets = new Insets(0, 0, 5, 5);
		gbc_lblAnchorx.gridx = 0;
		gbc_lblAnchorx.gridy = 16;
		getPanel().add(lblAnchorx, gbc_lblAnchorx);
		
		spinnerAnchorx = new JSpinner();
		spinnerAnchorx.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerAnchorx = new GridBagConstraints();
		gbc_spinnerAnchorx.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerAnchorx.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerAnchorx.gridx = 1;
		gbc_spinnerAnchorx.gridy = 16;
		getPanel().add(spinnerAnchorx, gbc_spinnerAnchorx);
		
		lblAnchory = new JLabel("anchorY:");
		GridBagConstraints gbc_lblAnchory = new GridBagConstraints();
		gbc_lblAnchory.insets = new Insets(0, 0, 5, 5);
		gbc_lblAnchory.gridx = 0;
		gbc_lblAnchory.gridy = 17;
		getPanel().add(lblAnchory, gbc_lblAnchory);
		
		spinnerAnchory = new JSpinner();
		spinnerAnchory.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerAnchory = new GridBagConstraints();
		gbc_spinnerAnchory.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerAnchory.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerAnchory.gridx = 1;
		gbc_spinnerAnchory.gridy = 17;
		getPanel().add(spinnerAnchory, gbc_spinnerAnchory);
		
		lblEnablelimit = new JLabel("enableLimit:");
		GridBagConstraints gbc_lblEnablelimit = new GridBagConstraints();
		gbc_lblEnablelimit.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnablelimit.gridx = 0;
		gbc_lblEnablelimit.gridy = 18;
		getPanel().add(lblEnablelimit, gbc_lblEnablelimit);
		
		chckbxEnablelimit = new JCheckBox("enableLimit");
		GridBagConstraints gbc_chckbxEnablelimit = new GridBagConstraints();
		gbc_chckbxEnablelimit.anchor = GridBagConstraints.WEST;
		gbc_chckbxEnablelimit.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxEnablelimit.gridx = 1;
		gbc_chckbxEnablelimit.gridy = 18;
		getPanel().add(chckbxEnablelimit, gbc_chckbxEnablelimit);
		
		lblLowerTranslation = new JLabel("lowerTranslation:");
		GridBagConstraints gbc_lblLowerTranslation = new GridBagConstraints();
		gbc_lblLowerTranslation.insets = new Insets(0, 0, 5, 5);
		gbc_lblLowerTranslation.gridx = 0;
		gbc_lblLowerTranslation.gridy = 19;
		getPanel().add(lblLowerTranslation, gbc_lblLowerTranslation);
		
		spinnerLowerTranslation = new JSpinner();
		spinnerLowerTranslation.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerLowerTranslation = new GridBagConstraints();
		gbc_spinnerLowerTranslation.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerLowerTranslation.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerLowerTranslation.gridx = 1;
		gbc_spinnerLowerTranslation.gridy = 19;
		getPanel().add(spinnerLowerTranslation, gbc_spinnerLowerTranslation);
		
		lblUpperTranslation = new JLabel("upperTranslation:");
		GridBagConstraints gbc_lblUpperTranslation = new GridBagConstraints();
		gbc_lblUpperTranslation.insets = new Insets(0, 0, 5, 5);
		gbc_lblUpperTranslation.gridx = 0;
		gbc_lblUpperTranslation.gridy = 20;
		getPanel().add(lblUpperTranslation, gbc_lblUpperTranslation);
		
		spinnerUpperTranslation = new JSpinner();
		spinnerUpperTranslation.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerUpperTranslation = new GridBagConstraints();
		gbc_spinnerUpperTranslation.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerUpperTranslation.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerUpperTranslation.gridx = 1;
		gbc_spinnerUpperTranslation.gridy = 20;
		getPanel().add(spinnerUpperTranslation, gbc_spinnerUpperTranslation);
		
		lblEnablemotor = new JLabel("enableMotor:");
		GridBagConstraints gbc_lblEnablemotor = new GridBagConstraints();
		gbc_lblEnablemotor.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnablemotor.gridx = 0;
		gbc_lblEnablemotor.gridy = 21;
		getPanel().add(lblEnablemotor, gbc_lblEnablemotor);
		
		chckbxEnablemotor = new JCheckBox("enableMotor");
		GridBagConstraints gbc_chckbxEnablemotor = new GridBagConstraints();
		gbc_chckbxEnablemotor.anchor = GridBagConstraints.WEST;
		gbc_chckbxEnablemotor.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxEnablemotor.gridx = 1;
		gbc_chckbxEnablemotor.gridy = 21;
		getPanel().add(chckbxEnablemotor, gbc_chckbxEnablemotor);
		
		lblMotorspeed = new JLabel("motorSpeed:");
		GridBagConstraints gbc_lblMotorspeed = new GridBagConstraints();
		gbc_lblMotorspeed.insets = new Insets(0, 0, 5, 5);
		gbc_lblMotorspeed.gridx = 0;
		gbc_lblMotorspeed.gridy = 22;
		getPanel().add(lblMotorspeed, gbc_lblMotorspeed);
		
		spinnerMotorSpeed = new JSpinner();
		spinnerMotorSpeed.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerMotorSpeed = new GridBagConstraints();
		gbc_spinnerMotorSpeed.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerMotorSpeed.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerMotorSpeed.gridx = 1;
		gbc_spinnerMotorSpeed.gridy = 22;
		getPanel().add(spinnerMotorSpeed, gbc_spinnerMotorSpeed);
		
		lblMaxMotorForce = new JLabel("maxMotorForce:");
		GridBagConstraints gbc_lblMaxMotorForce = new GridBagConstraints();
		gbc_lblMaxMotorForce.insets = new Insets(0, 0, 5, 5);
		gbc_lblMaxMotorForce.gridx = 0;
		gbc_lblMaxMotorForce.gridy = 23;
		getPanel().add(lblMaxMotorForce, gbc_lblMaxMotorForce);
		
		spinnerMaxMotorForce = new JSpinner();
		spinnerMaxMotorForce.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerMaxMotorForce = new GridBagConstraints();
		gbc_spinnerMaxMotorForce.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerMaxMotorForce.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerMaxMotorForce.gridx = 1;
		gbc_spinnerMaxMotorForce.gridy = 23;
		getPanel().add(spinnerMaxMotorForce, gbc_spinnerMaxMotorForce);
		
		chckbxUseBodyaCenter.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(chckbxUseBodyaCenter.isSelected()){
					PrismaticJointDefModel.class.cast(object).useBodyBCenter = false;
					chckbxUseBodybCenter.setSelected(false);
				}
			}
		});
		chckbxUseBodybCenter.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(chckbxUseBodybCenter.isSelected()){
					PrismaticJointDefModel.class.cast(object).useBodyACenter = false;
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
	private JLabel lblLowerTranslation;
	private JSpinner spinnerLowerTranslation;
	private JLabel lblUpperTranslation;
	private JSpinner spinnerUpperTranslation;
	private JLabel lblEnablemotor;
	private JCheckBox chckbxEnablemotor;
	private JLabel lblMotorspeed;
	private JSpinner spinnerMotorSpeed;
	private JLabel lblMaxMotorForce;
	private JSpinner spinnerMaxMotorForce;
	private JLabel lblLocalaxisax;
	private JSpinner spinnerLocalAxisAx;
	private JLabel lblLocalAxisAy;
	private JSpinner spinnerLocalAxisAy;
	private JCheckBox chckbxUseBodyaCenter;
	private JCheckBox chckbxUseBodybCenter;
	private JCheckBox chckbxUseBodyabCenter;

	@Override
	public Class<PrismaticJointDefModel> getType() {
		return PrismaticJointDefModel.class;
	}

	
	@Override
	public void doBinder() {
		super.doBinder();
		PrismaticJointDefModel model = PrismaticJointDefModel.class.cast(object);
		bind(model.anchor,"x",spinnerAnchorx);
		bind(model.anchor,"y",spinnerAnchory);
		bind(model,"maxMotorForce",spinnerMaxMotorForce);
		bind(model,"motorSpeed",spinnerMotorSpeed);
		bind(model,"enableMotor",chckbxEnablemotor);
		bind(model,"upperTranslation",spinnerUpperTranslation);
		bind(model,"lowerTranslation",spinnerLowerTranslation);
		bind(model,"enableLimit",chckbxEnablelimit);
		bind(model,"useBodyACenter",chckbxUseBodyaCenter);
		bind(model,"useBodyBCenter",chckbxUseBodybCenter);
		bind(model,"useABCenterLine",chckbxUseBodyabCenter);
		bind(model.localAxisA,"x",spinnerLocalAxisAx);
		bind(model.localAxisA,"y",spinnerLocalAxisAy);
	}
	@Override
	public Binder getBinder(Object obj, String name, final JComponent widget) {
		return new Binder(obj,name,widget) {
			@Override
			protected void postSolve() {
				PrismaticJointDefModel model = PrismaticJointDefModel.class.cast(object);
				
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
				
				
				if(widget != spinnerLocalAxisAx)spinnerLocalAxisAx.setValue(model.localAxisA.x);
				if(widget != spinnerLocalAxisAy)spinnerLocalAxisAy.setValue(model.localAxisA.y);
				
			}
		};
	}
}
