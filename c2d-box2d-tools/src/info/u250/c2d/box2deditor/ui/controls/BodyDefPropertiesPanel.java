package info.u250.c2d.box2deditor.ui.controls;

import info.u250.c2d.box2d.model.b2BodyDefModel;
import info.u250.c2d.box2d.model.b2FixtureDefModel;
import info.u250.c2d.box2d.model.b2JointDefModel;
import info.u250.c2d.box2d.model.fixture.b2CircleFixtureDefModel;
import info.u250.c2d.box2d.model.fixture.b2RectangleFixtureDefModel;
import info.u250.c2d.box2deditor.adapter.PolygonFixtureDefModel;
import info.u250.c2d.box2deditor.adapter.SceneModelAdapter;
import info.u250.c2d.box2deditor.gdx.PhysicalWorld;
import info.u250.c2d.box2deditor.gdx.support.BuildWorld;
import info.u250.c2d.box2deditor.gdx.support.Geometry;
import info.u250.c2d.box2deditor.ui.Binder;
import info.u250.c2d.box2deditor.ui.util.DefCellRenderer;
import info.u250.c2d.box2deditor.ui.util.DefListModel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import com.badlogic.gdx.math.Vector2;

public class BodyDefPropertiesPanel extends AbstractBindablePropertiesPanel {
	private static final long serialVersionUID = -2860320186841083760L;
	private JSpinner spinnerAngularVelocity;
	private JSpinner spinnerAngularDamping;
	private JTextField textName;
	private JCheckBox chckbxAwake;
	private JSpinner spinnerAngle;
	private JSpinner spinnerPositionX;
	private JCheckBox chckbxBullet;
	private JSpinner spinnerLinearDamping;
	private JCheckBox chckbxActive;
	private JSpinner spinnerGravityScale;
	private JSpinner spinnerPositionY;
	private JCheckBox chckbxFixdrotation;
	private JCheckBox chckbxAllowsleep;
	private JSpinner spinnerLinearVelocityY;
	private JSpinner spinnerLinearVelocityX;
	private JComboBox comboBox;

	/**
	 * @wbp.nonvisual location=333,527
	 */
	private final JList fixtureList;

	/**
	 * Create the panel.
	 */
	public BodyDefPropertiesPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		JPanel mainPanel = new JPanel();
		scrollPane.setViewportView(mainPanel);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{0, 0};
		gbl_mainPanel.rowHeights = new int[]{0, 15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_mainPanel.columnWeights = new double[]{ 0, 1.0};
		gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);
		
		JLabel lblMark = new JLabel("Mark:");
		GridBagConstraints gbc_lblMark = new GridBagConstraints();
		gbc_lblMark.anchor = GridBagConstraints.EAST;
		gbc_lblMark.insets = new Insets(0, 0, 5, 5);
		gbc_lblMark.gridx = 0;
		gbc_lblMark.gridy = 0;
		mainPanel.add(lblMark, gbc_lblMark);
		
		txtMark = new JTextField();
		GridBagConstraints gbc_txtMark = new GridBagConstraints();
		gbc_txtMark.insets = new Insets(0, 0, 5, 0);
		gbc_txtMark.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMark.gridx = 1;
		gbc_txtMark.gridy = 0;
		mainPanel.add(txtMark, gbc_txtMark);
		txtMark.setColumns(10);
		
		
		
		JLabel lblName = new JLabel("Name:");
		lblName.setIcon(new ImageIcon(BodyDefPropertiesPanel.class.getResource("/info/u250/c2d/box2deditor/ui/res/info.png")));
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 1;
		mainPanel.add(lblName, gbc_lblName);
		
		textName = new JTextField();
		textName.setColumns(10);
		GridBagConstraints gbc_textName = new GridBagConstraints();
		gbc_textName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textName.insets = new Insets(0, 0, 5, 0);
		gbc_textName.gridx = 1;
		gbc_textName.gridy = 1;
		mainPanel.add(textName, gbc_textName);
		
		JLabel lblIsstatic = new JLabel("bodyType:");
		GridBagConstraints gbc_lblIsstatic = new GridBagConstraints();
		gbc_lblIsstatic.anchor = GridBagConstraints.EAST;
		gbc_lblIsstatic.insets = new Insets(0, 0, 5, 5);
		gbc_lblIsstatic.gridx = 0;
		gbc_lblIsstatic.gridy = 2;
		mainPanel.add(lblIsstatic, gbc_lblIsstatic);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"StaticBody", "KinematicBody", "DynamicBody"}));
		comboBox.setSelectedIndex(2);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 2;
		mainPanel.add(comboBox, gbc_comboBox);
		
		JLabel lblPositionx = new JLabel("positionX:");
		GridBagConstraints gbc_lblPositionx = new GridBagConstraints();
		gbc_lblPositionx.insets = new Insets(0, 0, 5, 5);
		gbc_lblPositionx.gridx = 0;
		gbc_lblPositionx.gridy = 3;
		mainPanel.add(lblPositionx, gbc_lblPositionx);
		
		spinnerPositionX = new JSpinner();
		spinnerPositionX.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerPostionX = new GridBagConstraints();
		gbc_spinnerPostionX.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerPostionX.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerPostionX.gridx = 1;
		gbc_spinnerPostionX.gridy = 3;
		mainPanel.add(spinnerPositionX, gbc_spinnerPostionX);
		
		JLabel lblPositiony = new JLabel("positionY:");
		GridBagConstraints gbc_lblPositiony = new GridBagConstraints();
		gbc_lblPositiony.insets = new Insets(0, 0, 5, 5);
		gbc_lblPositiony.gridx = 0;
		gbc_lblPositiony.gridy = 4;
		mainPanel.add(lblPositiony, gbc_lblPositiony);
		
		spinnerPositionY = new JSpinner();
		spinnerPositionY.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerPositionY = new GridBagConstraints();
		gbc_spinnerPositionY.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerPositionY.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerPositionY.gridx = 1;
		gbc_spinnerPositionY.gridy = 4;
		mainPanel.add(spinnerPositionY, gbc_spinnerPositionY);
		
		JLabel lblAngle = new JLabel("Degrees:");
		GridBagConstraints gbc_lblAngle = new GridBagConstraints();
		gbc_lblAngle.insets = new Insets(0, 0, 5, 5);
		gbc_lblAngle.gridx = 0;
		gbc_lblAngle.gridy = 5;
		mainPanel.add(lblAngle, gbc_lblAngle);
		
		spinnerAngle = new JSpinner();
		spinnerAngle.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerAngle = new GridBagConstraints();
		gbc_spinnerAngle.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerAngle.anchor = GridBagConstraints.WEST;
		gbc_spinnerAngle.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerAngle.gridx = 1;
		gbc_spinnerAngle.gridy = 5;
		mainPanel.add(spinnerAngle, gbc_spinnerAngle);
		
		JLabel lblLinearvelocityx = new JLabel("linearVelocityX:");
		GridBagConstraints gbc_lblLinearvelocityx = new GridBagConstraints();
		gbc_lblLinearvelocityx.insets = new Insets(0, 0, 5, 5);
		gbc_lblLinearvelocityx.gridx = 0;
		gbc_lblLinearvelocityx.gridy = 6;
		mainPanel.add(lblLinearvelocityx, gbc_lblLinearvelocityx);
		
		spinnerLinearVelocityX = new JSpinner();
		spinnerLinearVelocityX.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		
		
		GridBagConstraints gbc_spinnerLinearVelocityX = new GridBagConstraints();
		gbc_spinnerLinearVelocityX.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerLinearVelocityX.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerLinearVelocityX.gridx = 1;
		gbc_spinnerLinearVelocityX.gridy = 6;
		mainPanel.add(spinnerLinearVelocityX, gbc_spinnerLinearVelocityX);
		
		JLabel lblLinearvelocityy = new JLabel("linearVelocityY:");
		GridBagConstraints gbc_lblLinearvelocityy = new GridBagConstraints();
		gbc_lblLinearvelocityy.insets = new Insets(0, 0, 5, 5);
		gbc_lblLinearvelocityy.gridx = 0;
		gbc_lblLinearvelocityy.gridy = 7;
		mainPanel.add(lblLinearvelocityy, gbc_lblLinearvelocityy);
		
		spinnerLinearVelocityY = new JSpinner();
		spinnerLinearVelocityY.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerLinearVelocityY = new GridBagConstraints();
		gbc_spinnerLinearVelocityY.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerLinearVelocityY.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerLinearVelocityY.gridx = 1;
		gbc_spinnerLinearVelocityY.gridy = 7;
		mainPanel.add(spinnerLinearVelocityY, gbc_spinnerLinearVelocityY);
		
		JLabel lblAngularvelocity = new JLabel("AngularVelocity:");
		GridBagConstraints gbc_lblAngularvelocity = new GridBagConstraints();
		gbc_lblAngularvelocity.insets = new Insets(0, 0, 5, 5);
		gbc_lblAngularvelocity.gridx = 0;
		gbc_lblAngularvelocity.gridy = 8;
		mainPanel.add(lblAngularvelocity, gbc_lblAngularvelocity);
		
		spinnerAngularVelocity = new JSpinner();
		spinnerAngularVelocity.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerAngularVelocity = new GridBagConstraints();
		gbc_spinnerAngularVelocity.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerAngularVelocity.anchor = GridBagConstraints.WEST;
		gbc_spinnerAngularVelocity.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerAngularVelocity.gridx = 1;
		gbc_spinnerAngularVelocity.gridy = 8;
		mainPanel.add(spinnerAngularVelocity, gbc_spinnerAngularVelocity);
		
		JLabel lblLineardamping = new JLabel("LinearDamping:");
		GridBagConstraints gbc_lblLineardamping = new GridBagConstraints();
		gbc_lblLineardamping.insets = new Insets(0, 0, 5, 5);
		gbc_lblLineardamping.gridx = 0;
		gbc_lblLineardamping.gridy = 9;
		mainPanel.add(lblLineardamping, gbc_lblLineardamping);
		
		spinnerLinearDamping = new JSpinner();
		spinnerLinearDamping.setModel(new SpinnerNumberModel(new Float(0), new Float(0), new Float(1), new Float(0.1)));
		GridBagConstraints gbc_spinnerLinearDamping = new GridBagConstraints();
		gbc_spinnerLinearDamping.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerLinearDamping.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerLinearDamping.gridx = 1;
		gbc_spinnerLinearDamping.gridy = 9;
		mainPanel.add(spinnerLinearDamping, gbc_spinnerLinearDamping);
		
		JLabel lblAngulardamping = new JLabel("AngularDamping:");
		GridBagConstraints gbc_lblAngulardamping = new GridBagConstraints();
		gbc_lblAngulardamping.insets = new Insets(0, 0, 5, 5);
		gbc_lblAngulardamping.gridx = 0;
		gbc_lblAngulardamping.gridy = 10;
		mainPanel.add(lblAngulardamping, gbc_lblAngulardamping);
		
		spinnerAngularDamping = new JSpinner();
		spinnerAngularDamping.setModel(new SpinnerNumberModel(new Float(0), new Float(0), new Float(1), new Float(0.1)));
		GridBagConstraints gbc_spinnerAngularDamping = new GridBagConstraints();
		gbc_spinnerAngularDamping.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerAngularDamping.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerAngularDamping.gridx = 1;
		gbc_spinnerAngularDamping.gridy = 10;
		mainPanel.add(spinnerAngularDamping, gbc_spinnerAngularDamping);
		
		JLabel lblAllowsleep = new JLabel("AllowSleep:");
		GridBagConstraints gbc_lblAllowsleep = new GridBagConstraints();
		gbc_lblAllowsleep.insets = new Insets(0, 0, 5, 5);
		gbc_lblAllowsleep.gridx = 0;
		gbc_lblAllowsleep.gridy = 11;
		mainPanel.add(lblAllowsleep, gbc_lblAllowsleep);
		
		chckbxAllowsleep = new JCheckBox("AllowSleep");
		chckbxAllowsleep.setSelected(true);
		GridBagConstraints gbc_chckbxAllowsleep = new GridBagConstraints();
		gbc_chckbxAllowsleep.anchor = GridBagConstraints.WEST;
		gbc_chckbxAllowsleep.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxAllowsleep.gridx = 1;
		gbc_chckbxAllowsleep.gridy = 11;
		mainPanel.add(chckbxAllowsleep, gbc_chckbxAllowsleep);
		
		JLabel lblAwake = new JLabel("Awake:");
		GridBagConstraints gbc_lblAwake = new GridBagConstraints();
		gbc_lblAwake.insets = new Insets(0, 0, 5, 5);
		gbc_lblAwake.gridx = 0;
		gbc_lblAwake.gridy = 12;
		mainPanel.add(lblAwake, gbc_lblAwake);
		
		chckbxAwake = new JCheckBox("Awake");
		chckbxAwake.setSelected(true);
		GridBagConstraints gbc_chckbxAwake = new GridBagConstraints();
		gbc_chckbxAwake.anchor = GridBagConstraints.WEST;
		gbc_chckbxAwake.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxAwake.gridx = 1;
		gbc_chckbxAwake.gridy = 12;
		mainPanel.add(chckbxAwake, gbc_chckbxAwake);
		
		JLabel lblFixedrotation = new JLabel("FixedRotation:");
		GridBagConstraints gbc_lblFixedrotation = new GridBagConstraints();
		gbc_lblFixedrotation.insets = new Insets(0, 0, 5, 5);
		gbc_lblFixedrotation.gridx = 0;
		gbc_lblFixedrotation.gridy = 13;
		mainPanel.add(lblFixedrotation, gbc_lblFixedrotation);
		
		chckbxFixdrotation = new JCheckBox("FixdRotation");
		GridBagConstraints gbc_chckbxFixdrotation = new GridBagConstraints();
		gbc_chckbxFixdrotation.anchor = GridBagConstraints.WEST;
		gbc_chckbxFixdrotation.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxFixdrotation.gridx = 1;
		gbc_chckbxFixdrotation.gridy = 13;
		mainPanel.add(chckbxFixdrotation, gbc_chckbxFixdrotation);
		
		JLabel lblBullet = new JLabel("Bullet:");
		GridBagConstraints gbc_lblBullet = new GridBagConstraints();
		gbc_lblBullet.insets = new Insets(0, 0, 5, 5);
		gbc_lblBullet.gridx = 0;
		gbc_lblBullet.gridy = 14;
		mainPanel.add(lblBullet, gbc_lblBullet);
		
		chckbxBullet = new JCheckBox("Bullet");
		GridBagConstraints gbc_chckbxBullet = new GridBagConstraints();
		gbc_chckbxBullet.anchor = GridBagConstraints.WEST;
		gbc_chckbxBullet.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxBullet.gridx = 1;
		gbc_chckbxBullet.gridy = 14;
		mainPanel.add(chckbxBullet, gbc_chckbxBullet);
		
		JLabel lblActive = new JLabel("Active:");
		GridBagConstraints gbc_lblActive = new GridBagConstraints();
		gbc_lblActive.insets = new Insets(0, 0, 5, 5);
		gbc_lblActive.gridx = 0;
		gbc_lblActive.gridy = 15;
		mainPanel.add(lblActive, gbc_lblActive);
		
		chckbxActive = new JCheckBox("Active");
		chckbxActive.setSelected(true);
		GridBagConstraints gbc_chckbxActive = new GridBagConstraints();
		gbc_chckbxActive.anchor = GridBagConstraints.WEST;
		gbc_chckbxActive.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxActive.gridx = 1;
		gbc_chckbxActive.gridy = 15;
		mainPanel.add(chckbxActive, gbc_chckbxActive);
		
		JLabel lblNewLabel = new JLabel("GravityScale:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 16;
		mainPanel.add(lblNewLabel, gbc_lblNewLabel);
		
		spinnerGravityScale = new JSpinner();
		spinnerGravityScale.setModel(new SpinnerNumberModel(new Float(1), new Float(0), new Float(10), new Float(1)));
		GridBagConstraints gbc_spinnerGravityScale = new GridBagConstraints();
		gbc_spinnerGravityScale.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerGravityScale.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerGravityScale.gridx = 1;
		gbc_spinnerGravityScale.gridy = 16;
		mainPanel.add(spinnerGravityScale, gbc_spinnerGravityScale);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setPreferredSize(new Dimension(100,200));
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 17;
		gbc_scrollPane_1.gridwidth = 2;
		mainPanel.add(scrollPane_1, gbc_scrollPane_1);
		
		fixtureList = new JList();
		scrollPane_1.setViewportView(fixtureList);
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(fixtureList, popupMenu);
		
		defListModel = new DefListModel();
		fixtureList.setModel(defListModel);
		fixtureList.setCellRenderer(new DefCellRenderer());
	}
	DefListModel defListModel;
	
	

	@Override
	public Class<b2BodyDefModel> getType() {
		return b2BodyDefModel.class;
	}
	private void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if(SwingUtilities.isRightMouseButton(e) ){
					fixtureList.setSelectedIndex(fixtureList.locationToIndex(e.getPoint()));
				}
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isRightMouseButton(e) ){
					fixtureList.setSelectedIndex(fixtureList.locationToIndex(e.getPoint()));
				}
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.removeAll();
				SceneModelAdapter data = PhysicalWorld.MODEL;
				
				JMenu mnAdd = new JMenu("Add Fixture");
				mnAdd.setFont(new Font(mnAdd.getFont().getName(),Font.PLAIN,24));
				for(final b2FixtureDefModel m2:data.fixtureDefModels){
					JMenuItem item = new JMenuItem(m2.name, new ImageIcon(this.getClass().getResource("/info/u250/c2d/box2deditor/ui/res/"+m2.getClass().getSimpleName()+".png")));
					if(defListModel.contains(m2)){
						item.setEnabled(false);
					}else{
						item.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								b2BodyDefModel model = b2BodyDefModel.class.cast(object);
								model.fixtures.add(m2);
								doBinder();
								rebuildBody();
							}
						});
					}
					mnAdd.add(item);
				}
				mnAdd.setIcon(new ImageIcon(BodyDefPropertiesPanel.class.getResource("/info/u250/c2d/box2deditor/ui/res/add-icon.png")));
				popup.add(mnAdd);
				
				JMenuItem mntmDeleteFixture = new JMenuItem("Delete Fixture");
				mntmDeleteFixture.setFont(new Font(mnAdd.getFont().getName(),Font.PLAIN,24));
				mntmDeleteFixture.setIcon(new ImageIcon(BodyDefPropertiesPanel.class.getResource("/info/u250/c2d/box2deditor/ui/res/remove-icon.png")));
				if(fixtureList.getSelectedIndex()==-1){
					mntmDeleteFixture.setEnabled(false);
				}else{
					mntmDeleteFixture.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							b2BodyDefModel model = b2BodyDefModel.class.cast(object);
							model.fixtures.remove(fixtureList.getSelectedValue());
							doBinder();
							rebuildBody();
						}
					});
				}
				popup.add(mntmDeleteFixture);
				
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	Vector2 lower = new Vector2();
	Vector2 upper = new Vector2();
	private JTextField txtMark;
	@Override
	public Binder getBinder(Object obj, String name, JComponent widget) {
		return new Binder(obj,name,widget) {
			@Override
			protected void postSolve() {
				if(null == object)return ;
				b2BodyDefModel b2Body = b2BodyDefModel.class.cast(object);
				//the sprite offset~ and the width , height
				lower.set(0,0);
				upper.set(0, 0);
				for(b2FixtureDefModel def:b2Body.fixtures){
					if(def instanceof b2CircleFixtureDefModel){
						float r = ((b2CircleFixtureDefModel) def).radius;
						lower.x = Math.min(lower.x, -r);
						lower.y = Math.min(lower.y, -r);
						upper.x = Math.max(upper.x,  r);
						upper.y = Math.max(upper.y,  r);
					}else if(def instanceof b2RectangleFixtureDefModel){
						b2RectangleFixtureDefModel o = (b2RectangleFixtureDefModel)def;
						float w = o.width;
						float h = o.height;
						lower.x = Math.min(lower.x,-w/2);
						lower.y = Math.min(lower.y,-h/2);
						upper.x = Math.max(upper.x, w/2);
						upper.y = Math.max(upper.y, h/2);
					}else if(def instanceof PolygonFixtureDefModel){
						PolygonFixtureDefModel o = (PolygonFixtureDefModel)def;
						for(Vector2 v:o.polygon){
							lower.x = Math.min(lower.x, v.x);
							lower.y = Math.min(lower.y, v.y);
							upper.x = Math.max(upper.x, v.x);
							upper.y = Math.max(upper.y, v.y);
						}
					}
				}
				//now set the width , height and the center 
				b2Body.drawableWidth  = Math.abs(upper.x-lower.x);
				b2Body.drawableHeight = Math.abs(upper.y-lower.y);
				b2Body.drawableOffsetX= -lower.x;
				b2Body.drawableOffsetY= -lower.y;
				
				rebuildBody();
			}
		};
	}

	void rebuildBody(){
		b2BodyDefModel b2Body = b2BodyDefModel.class.cast(object);
		if(null!=b2Body.body){
			PhysicalWorld.WORLD.destroyBody(b2Body.body);
			b2Body.body = null;
		}
		BuildWorld.buildBody(b2Body);
		
		//Ajust the relation joints
		for(b2JointDefModel joint:PhysicalWorld.MODEL.jointDefModels){
			if(joint.bodyA==object || joint.bodyB==object){
				Geometry.ajustJoint(joint);
			}
		}
	}
	@Override
	public void doBinder() {
		b2BodyDefModel object = b2BodyDefModel.class.cast(this.object);
		
		bind(object,"name",textName);
		bind(object,"type",comboBox);
		bind(object.position,"x",spinnerPositionX);
		bind(object.position,"y",spinnerPositionY);
		bind(object.linearVelocity,"x",spinnerLinearVelocityX);
		bind(object.linearVelocity,"y",spinnerLinearVelocityY);
		bind(object,"degrees",spinnerAngle);
		bind(object,"angularVelocity",spinnerAngularVelocity);
		bind(object,"linearDamping",spinnerLinearDamping);
		bind(object,"angularDamping",spinnerAngularDamping);
		bind(object,"allowSleep",chckbxAllowsleep);
		bind(object,"awake",chckbxAwake);
		bind(object,"fixedRotation",chckbxFixdrotation);
		bind(object,"bullet",chckbxBullet);
		bind(object,"active",chckbxActive);
		bind(object,"gravityScale",spinnerGravityScale);
		bind(object,"mark",txtMark);
		
		defListModel.clear();
		for(b2FixtureDefModel f:object.fixtures){
			defListModel.addElement(f);
		}
	}
}
