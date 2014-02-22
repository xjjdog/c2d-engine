package info.u250.c2d.box2deditor.ui.controls;

import info.u250.c2d.box2deditor.adapter.DistanceJointDefModel;
import info.u250.c2d.box2deditor.gdx.support.Geometry;
import info.u250.c2d.box2deditor.ui.Binder;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class DistanceJointDefPropertiesPanel extends JointDefPropertiesPanel{
	public DistanceJointDefPropertiesPanel() {
		
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
		
		JLabel lblLength = new JLabel("length:");
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
		
		chckbxAutoCalculateLength = new JCheckBox("auto calculate length");
		GridBagConstraints gbc_chckbxAutoCalculateLength = new GridBagConstraints();
		gbc_chckbxAutoCalculateLength.anchor = GridBagConstraints.WEST;
		gbc_chckbxAutoCalculateLength.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxAutoCalculateLength.gridx = 1;
		gbc_chckbxAutoCalculateLength.gridy = 13;
		getPanel().add(chckbxAutoCalculateLength, gbc_chckbxAutoCalculateLength);
		
		JLabel lblFrequencyhz = new JLabel("frequencyHz:");
		GridBagConstraints gbc_lblFrequencyhz = new GridBagConstraints();
		gbc_lblFrequencyhz.insets = new Insets(0, 0, 5, 5);
		gbc_lblFrequencyhz.gridx = 0;
		gbc_lblFrequencyhz.gridy = 14;
		getPanel().add(lblFrequencyhz, gbc_lblFrequencyhz);
		
		spinnerFrequencyHz = new JSpinner();
		spinnerFrequencyHz.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerFrequencyHz = new GridBagConstraints();
		gbc_spinnerFrequencyHz.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerFrequencyHz.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerFrequencyHz.gridx = 1;
		gbc_spinnerFrequencyHz.gridy = 14;
		getPanel().add(spinnerFrequencyHz, gbc_spinnerFrequencyHz);
		
		JLabel lblDampingratio = new JLabel("dampingRatio:");
		GridBagConstraints gbc_lblDampingratio = new GridBagConstraints();
		gbc_lblDampingratio.insets = new Insets(0, 0, 5, 5);
		gbc_lblDampingratio.gridx = 0;
		gbc_lblDampingratio.gridy = 15;
		getPanel().add(lblDampingratio, gbc_lblDampingratio);
		
		spinnerDampingRatio = new JSpinner();
		spinnerDampingRatio.setModel(new SpinnerNumberModel(new Float(0), new Float(0), new Float(1), new Float(0.1)));
		GridBagConstraints gbc_spinnerDampingRatio = new GridBagConstraints();
		gbc_spinnerDampingRatio.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerDampingRatio.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerDampingRatio.gridx = 1;
		gbc_spinnerDampingRatio.gridy = 15;
		getPanel().add(spinnerDampingRatio, gbc_spinnerDampingRatio);
	}
	private static final long serialVersionUID = -2033825670615410634L;
	private JSpinner spinnerLocalAnchorAX;
	private JSpinner spinnerFrequencyHz;
	private JSpinner spinnerLength;
	private JSpinner spinnerLocalAnchorBy;
	private JSpinner spinnerLocalAnchorBx;
	private JSpinner spinnerLocalAnchorAY;
	private JSpinner spinnerDampingRatio;
	private JCheckBox chckbxAutoCalculateLength;
	private JCheckBox chckbxSetToZero;
	private JCheckBox chckbxSetToZero_1;

	@Override
	public Class<DistanceJointDefModel> getType() {
		return DistanceJointDefModel.class;
	}

	@Override
	public void doBinder() {
		super.doBinder();
		DistanceJointDefModel model = DistanceJointDefModel.class.cast(object);
		bind(model.localAnchorA,"x",spinnerLocalAnchorAX);
		bind(model.localAnchorA,"y",spinnerLocalAnchorAY);
		bind(model.localAnchorB,"x",spinnerLocalAnchorBx);
		bind(model.localAnchorB,"y",spinnerLocalAnchorBy);
		bind(model,"length",spinnerLength);
		bind(model,"frequencyHz",spinnerFrequencyHz);
		bind(model,"dampingRatio",spinnerDampingRatio);
		bind(model,"autoCalculateLength",chckbxAutoCalculateLength);
		bind(model,"setBodyAZero",chckbxSetToZero);
		bind(model,"setBodyBZero",chckbxSetToZero_1);
	}
	@Override
	public Binder getBinder(Object obj, String name, JComponent widget) {
		return new Binder(obj,name,widget) {
			@Override
			protected void postSolve() {
				
				DistanceJointDefModel model = DistanceJointDefModel.class.cast(object);
				Geometry.ajustJoint(model);
				
				//set the UI values
				spinnerLength.setValue(model.length);
				spinnerLocalAnchorAX.setValue(model.localAnchorA.x);
				spinnerLocalAnchorAY.setValue(model.localAnchorA.y);
				spinnerLocalAnchorBx.setValue(model.localAnchorB.x);
				spinnerLocalAnchorBy.setValue(model.localAnchorB.y);
			}
		};
	}
}
