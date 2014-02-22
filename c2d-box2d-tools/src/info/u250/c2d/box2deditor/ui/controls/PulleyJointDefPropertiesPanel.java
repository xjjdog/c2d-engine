package info.u250.c2d.box2deditor.ui.controls;

import info.u250.c2d.box2deditor.adapter.PulleyJointDefModel;
import info.u250.c2d.box2deditor.gdx.support.Geometry;
import info.u250.c2d.box2deditor.ui.Binder;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class PulleyJointDefPropertiesPanel extends JointDefPropertiesPanel{
	public PulleyJointDefPropertiesPanel() {
		
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
		
		chckbxSetToZero = new JCheckBox("Set To Zero");
		GridBagConstraints gbc_chckbxSetToZero = new GridBagConstraints();
		gbc_chckbxSetToZero.anchor = GridBagConstraints.WEST;
		gbc_chckbxSetToZero.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxSetToZero.gridx = 1;
		gbc_chckbxSetToZero.gridy = 8;
		getPanel().add(chckbxSetToZero, gbc_chckbxSetToZero);
		
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
		
		chckbxSetToZero_1 = new JCheckBox("Set To Zero");
		GridBagConstraints gbc_chckbxSetToZero_1 = new GridBagConstraints();
		gbc_chckbxSetToZero_1.anchor = GridBagConstraints.WEST;
		gbc_chckbxSetToZero_1.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxSetToZero_1.gridx = 1;
		gbc_chckbxSetToZero_1.gridy = 11;
		getPanel().add(chckbxSetToZero_1, gbc_chckbxSetToZero_1);
		
		lblGroundanchorax = new JLabel("groundAnchorAx:");
		GridBagConstraints gbc_lblGroundanchorax = new GridBagConstraints();
		gbc_lblGroundanchorax.insets = new Insets(0, 0, 5, 5);
		gbc_lblGroundanchorax.gridx = 0;
		gbc_lblGroundanchorax.gridy = 12;
		getPanel().add(lblGroundanchorax, gbc_lblGroundanchorax);
		
		spinnerGroundAnchorAx = new JSpinner();
		spinnerGroundAnchorAx.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerGroundAnchorAx = new GridBagConstraints();
		gbc_spinnerGroundAnchorAx.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerGroundAnchorAx.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerGroundAnchorAx.gridx = 1;
		gbc_spinnerGroundAnchorAx.gridy = 12;
		getPanel().add(spinnerGroundAnchorAx, gbc_spinnerGroundAnchorAx);
		
		chckbxAlignBodya = new JCheckBox("Align AnchorA");
		GridBagConstraints gbc_chckbxAlignBodya = new GridBagConstraints();
		gbc_chckbxAlignBodya.anchor = GridBagConstraints.WEST;
		gbc_chckbxAlignBodya.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxAlignBodya.gridx = 1;
		gbc_chckbxAlignBodya.gridy = 13;
		getPanel().add(chckbxAlignBodya, gbc_chckbxAlignBodya);
		
		lblGroundanchoray = new JLabel("groundAnchorAy:");
		GridBagConstraints gbc_lblGroundanchoray = new GridBagConstraints();
		gbc_lblGroundanchoray.insets = new Insets(0, 0, 5, 5);
		gbc_lblGroundanchoray.gridx = 0;
		gbc_lblGroundanchoray.gridy = 14;
		getPanel().add(lblGroundanchoray, gbc_lblGroundanchoray);
		
		spinnerGroundAnchorAy = new JSpinner();
		spinnerGroundAnchorAy.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerGroundAnchorAy = new GridBagConstraints();
		gbc_spinnerGroundAnchorAy.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerGroundAnchorAy.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerGroundAnchorAy.gridx = 1;
		gbc_spinnerGroundAnchorAy.gridy = 14;
		getPanel().add(spinnerGroundAnchorAy, gbc_spinnerGroundAnchorAy);
		
		lblGroundanchorbx = new JLabel("groundAnchorBx:");
		GridBagConstraints gbc_lblGroundanchorbx = new GridBagConstraints();
		gbc_lblGroundanchorbx.insets = new Insets(0, 0, 5, 5);
		gbc_lblGroundanchorbx.gridx = 0;
		gbc_lblGroundanchorbx.gridy = 15;
		getPanel().add(lblGroundanchorbx, gbc_lblGroundanchorbx);
		
		spinnerGroundAnchorBx = new JSpinner();
		spinnerGroundAnchorBx.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerGroundAnchorBx = new GridBagConstraints();
		gbc_spinnerGroundAnchorBx.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerGroundAnchorBx.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerGroundAnchorBx.gridx = 1;
		gbc_spinnerGroundAnchorBx.gridy = 15;
		getPanel().add(spinnerGroundAnchorBx, gbc_spinnerGroundAnchorBx);
		
		chckbxAlignBodyb = new JCheckBox("Align AnchorB");
		GridBagConstraints gbc_chckbxAlignBodyb = new GridBagConstraints();
		gbc_chckbxAlignBodyb.anchor = GridBagConstraints.WEST;
		gbc_chckbxAlignBodyb.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxAlignBodyb.gridx = 1;
		gbc_chckbxAlignBodyb.gridy = 16;
		getPanel().add(chckbxAlignBodyb, gbc_chckbxAlignBodyb);
		
		lblGroundanchorby = new JLabel("groundAnchorBy:");
		GridBagConstraints gbc_lblGroundanchorby = new GridBagConstraints();
		gbc_lblGroundanchorby.insets = new Insets(0, 0, 5, 5);
		gbc_lblGroundanchorby.gridx = 0;
		gbc_lblGroundanchorby.gridy = 17;
		getPanel().add(lblGroundanchorby, gbc_lblGroundanchorby);
		
		spinnerGroundAnchorBy = new JSpinner();
		spinnerGroundAnchorBy.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerGroundAnchorBy = new GridBagConstraints();
		gbc_spinnerGroundAnchorBy.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerGroundAnchorBy.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerGroundAnchorBy.gridx = 1;
		gbc_spinnerGroundAnchorBy.gridy = 17;
		getPanel().add(spinnerGroundAnchorBy, gbc_spinnerGroundAnchorBy);
		
		lblLengtha = new JLabel("lengthA:");
		GridBagConstraints gbc_lblLengtha = new GridBagConstraints();
		gbc_lblLengtha.insets = new Insets(0, 0, 5, 5);
		gbc_lblLengtha.gridx = 0;
		gbc_lblLengtha.gridy = 18;
		getPanel().add(lblLengtha, gbc_lblLengtha);
		
		spinnerLengthA = new JSpinner();
		spinnerLengthA.setEnabled(false);
		spinnerLengthA.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerLengthA = new GridBagConstraints();
		gbc_spinnerLengthA.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerLengthA.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerLengthA.gridx = 1;
		gbc_spinnerLengthA.gridy = 18;
		getPanel().add(spinnerLengthA, gbc_spinnerLengthA);
		
		lblLengthb = new JLabel("lengthB:");
		GridBagConstraints gbc_lblLengthb = new GridBagConstraints();
		gbc_lblLengthb.insets = new Insets(0, 0, 5, 5);
		gbc_lblLengthb.gridx = 0;
		gbc_lblLengthb.gridy = 19;
		getPanel().add(lblLengthb, gbc_lblLengthb);
		
		spinnerLengthB = new JSpinner();
		spinnerLengthB.setEnabled(false);
		spinnerLengthB.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerLengthB = new GridBagConstraints();
		gbc_spinnerLengthB.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerLengthB.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerLengthB.gridx = 1;
		gbc_spinnerLengthB.gridy = 19;
		getPanel().add(spinnerLengthB, gbc_spinnerLengthB);
		
		lblRatio = new JLabel("ratio:");
		GridBagConstraints gbc_lblRatio = new GridBagConstraints();
		gbc_lblRatio.insets = new Insets(0, 0, 5, 5);
		gbc_lblRatio.gridx = 0;
		gbc_lblRatio.gridy = 20;
		getPanel().add(lblRatio, gbc_lblRatio);
		
		spinnerRatio = new JSpinner();
		spinnerRatio.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerRatio = new GridBagConstraints();
		gbc_spinnerRatio.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerRatio.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerRatio.gridx = 1;
		gbc_spinnerRatio.gridy = 20;
		getPanel().add(spinnerRatio, gbc_spinnerRatio);
	}
	private static final long serialVersionUID = -2033825670615410634L;
	private JSpinner spinnerLocalAnchorAX;
	private JSpinner spinnerLocalAnchorBy;
	private JSpinner spinnerLocalAnchorBx;
	private JSpinner spinnerLocalAnchorAY;
	private JLabel lblGroundanchorax;
	private JSpinner spinnerGroundAnchorAx;
	private JLabel lblGroundanchoray;
	private JSpinner spinnerGroundAnchorAy;
	private JLabel lblGroundanchorbx;
	private JSpinner spinnerGroundAnchorBx;
	private JLabel lblGroundanchorby;
	private JSpinner spinnerGroundAnchorBy;
	private JLabel lblLengtha;
	private JSpinner spinnerLengthA;
	private JLabel lblLengthb;
	private JSpinner spinnerLengthB;
	private JLabel lblRatio;
	private JSpinner spinnerRatio;
	private JCheckBox chckbxSetToZero;
	private JCheckBox chckbxSetToZero_1;
	private JCheckBox chckbxAlignBodya;
	private JCheckBox chckbxAlignBodyb;

	@Override
	public Class<PulleyJointDefModel> getType() {
		return PulleyJointDefModel.class;
	}

	@Override
	public void doBinder() {
		super.doBinder();
		PulleyJointDefModel model = PulleyJointDefModel.class.cast(object);
		bind(model.localAnchorA,"x",spinnerLocalAnchorAX);
		bind(model.localAnchorA,"y",spinnerLocalAnchorAY);
		bind(model.localAnchorB,"x",spinnerLocalAnchorBx);
		bind(model.localAnchorB,"y",spinnerLocalAnchorBy);
		bind(model.groundAnchorA,"x",spinnerGroundAnchorAx);
		bind(model.groundAnchorA,"y",spinnerGroundAnchorAy);
		bind(model.groundAnchorB,"x",spinnerGroundAnchorBx);
		bind(model.groundAnchorB,"y",spinnerGroundAnchorBy);
		bind(model,"ratio",spinnerRatio);
		bind(model,"setBodyAZero",chckbxSetToZero);
		bind(model,"setBodyBZero",chckbxSetToZero_1);
		bind(model,"groundAAlignAnchorA",chckbxAlignBodya);
		bind(model,"groundBAlignAnchorB",chckbxAlignBodyb);
	}
	@Override
	public Binder getBinder(Object obj, String name, final JComponent widget) {
		return new Binder(obj,name,widget) {
			@Override
			protected void postSolve() {
				PulleyJointDefModel model = PulleyJointDefModel.class.cast(object);
				Geometry.ajustJoint(model);
				
				spinnerLengthA.setValue(model.lengthA);
				spinnerLengthB.setValue(model.lengthB);
				spinnerLocalAnchorAX.setValue(model.localAnchorA.x);
				spinnerLocalAnchorAY.setValue(model.localAnchorA.y);
				spinnerLocalAnchorBx.setValue(model.localAnchorB.x);
				spinnerLocalAnchorBy.setValue(model.localAnchorB.y);
				spinnerGroundAnchorAx.setValue(model.groundAnchorA.x);
				spinnerGroundAnchorBx.setValue(model.groundAnchorB.x);
			}
		};
	}
}
