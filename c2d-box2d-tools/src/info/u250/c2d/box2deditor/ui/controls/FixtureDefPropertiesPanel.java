package info.u250.c2d.box2deditor.ui.controls;

import info.u250.c2d.box2d.model.b2FixtureDefModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
public abstract class FixtureDefPropertiesPanel extends AbstractBindablePropertiesPanel {

	private static final long serialVersionUID = -8501957140419675396L;
	private JTextField textName;
	private JSpinner spinnerDensity;
	private JSpinner spinnerFriction;
	private JSpinner spinnerMaskBits;
	private JSpinner spinnerCategoryBits;
	private JSpinner spinnerGroupIndex;
	private JCheckBox chckbxIsSensor;
	private JSpinner spinnerRestitution;
	private JPanel panel;

	/** The list of available categories */
	private static final Category[] CATEGORIES;
	private JScrollPane scrollPaneCategoryBits;
	private JList listCategoryBits;
	private JScrollPane scrollPaneMaskBits;
	private JList listMaskBits;
	private JLabel lblMark;
	private JTextField txtMark;
	/**
	 * Class used to display the categories in a JList.
	 * @author William Bittle
	 * @version 1.0.0
	 * @since 1.0.0
	 */
	private static class Category {
		/** The value of the category */
		public short value;
		
		/** The text shown in the list box */
		public String text;
		
		/**
		 * Full constructor.
		 * @param value the category
		 * @param text the category name
		 */
		public Category(short value, String text) {
			this.value = value;
			this.text = text;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return this.text;
		}
	}
	
	/**
	 * Generates the list of available categories.
	 */
	static {
		// the list of categories
		Category[] categories = new Category[16];
		// add the initial ones
		categories[0] = new Category((short)-1, "ALL");
		short v = 1;
		for (short i = 1; i < 16; i++) {
			categories[i] = new Category(v, "Category"+i);
			v *= 2;
		}
		CATEGORIES = categories;
	}
	/**
	 * Create the panel.
	 */

	public FixtureDefPropertiesPanel() {
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
		
		lblMark = new JLabel("Mark:");
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
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 1;
		panel.add(lblName, gbc_lblName);
		
		textName = new JTextField();
		textName.setColumns(10);
		GridBagConstraints gbc_textName = new GridBagConstraints();
		gbc_textName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textName.insets = new Insets(0, 0, 5, 0);
		gbc_textName.gridx = 1;
		gbc_textName.gridy = 1;
		panel.add(textName, gbc_textName);
		
		JLabel lblFriction = new JLabel("friction:");
		GridBagConstraints gbc_lblFriction = new GridBagConstraints();
		gbc_lblFriction.insets = new Insets(0, 0, 5, 5);
		gbc_lblFriction.gridx = 0;
		gbc_lblFriction.gridy = 2;
		panel.add(lblFriction, gbc_lblFriction);
		
		spinnerFriction = new JSpinner();
		spinnerFriction.setModel(new SpinnerNumberModel(new Float(0.2f), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerFriction = new GridBagConstraints();
		gbc_spinnerFriction.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerFriction.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerFriction.gridx = 1;
		gbc_spinnerFriction.gridy = 2;
		panel.add(spinnerFriction, gbc_spinnerFriction);
		
		JLabel lblRestitution = new JLabel("restitution:");
		GridBagConstraints gbc_lblRestitution = new GridBagConstraints();
		gbc_lblRestitution.insets = new Insets(0, 0, 5, 5);
		gbc_lblRestitution.gridx = 0;
		gbc_lblRestitution.gridy = 3;
		panel.add(lblRestitution, gbc_lblRestitution);
		
		spinnerRestitution = new JSpinner();
		spinnerRestitution.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerRestitution = new GridBagConstraints();
		gbc_spinnerRestitution.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerRestitution.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerRestitution.gridx = 1;
		gbc_spinnerRestitution.gridy = 3;
		panel.add(spinnerRestitution, gbc_spinnerRestitution);
		
		JLabel lblDensity = new JLabel("density:");
		GridBagConstraints gbc_lblDensity = new GridBagConstraints();
		gbc_lblDensity.insets = new Insets(0, 0, 5, 5);
		gbc_lblDensity.gridx = 0;
		gbc_lblDensity.gridy = 4;
		panel.add(lblDensity, gbc_lblDensity);
		
		spinnerDensity = new JSpinner();
		spinnerDensity.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		GridBagConstraints gbc_spinnerDensity = new GridBagConstraints();
		gbc_spinnerDensity.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerDensity.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerDensity.gridx = 1;
		gbc_spinnerDensity.gridy = 4;
		panel.add(spinnerDensity, gbc_spinnerDensity);
		
		JLabel lblIssensor = new JLabel("isSensor:");
		GridBagConstraints gbc_lblIssensor = new GridBagConstraints();
		gbc_lblIssensor.insets = new Insets(0, 0, 5, 5);
		gbc_lblIssensor.gridx = 0;
		gbc_lblIssensor.gridy = 5;
		panel.add(lblIssensor, gbc_lblIssensor);
		
		chckbxIsSensor = new JCheckBox("isSensor");
		GridBagConstraints gbc_chckbxIsSensor = new GridBagConstraints();
		gbc_chckbxIsSensor.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxIsSensor.anchor = GridBagConstraints.WEST;
		gbc_chckbxIsSensor.gridx = 1;
		gbc_chckbxIsSensor.gridy = 5;
		panel.add(chckbxIsSensor, gbc_chckbxIsSensor);
		
		JLabel lblCategorybits = new JLabel("categoryBits:");
		GridBagConstraints gbc_lblCategorybits = new GridBagConstraints();
		gbc_lblCategorybits.insets = new Insets(0, 0, 5, 5);
		gbc_lblCategorybits.gridx = 0;
		gbc_lblCategorybits.gridy = 6;
		panel.add(lblCategorybits, gbc_lblCategorybits);
		
		spinnerCategoryBits = new JSpinner();
		spinnerCategoryBits.setEnabled(false);
		spinnerCategoryBits.setModel(new SpinnerNumberModel(new Short((short) 0), null, null, new Short((short) 1)));
		GridBagConstraints gbc_spinnerCategoryBits = new GridBagConstraints();
		gbc_spinnerCategoryBits.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerCategoryBits.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerCategoryBits.gridx = 1;
		gbc_spinnerCategoryBits.gridy = 6;
		panel.add(spinnerCategoryBits, gbc_spinnerCategoryBits);
		
		scrollPaneCategoryBits = new JScrollPane();
		scrollPaneCategoryBits.setPreferredSize(new Dimension(150, 100));
		GridBagConstraints gbc_scrollPaneCategoryBits = new GridBagConstraints();
		gbc_scrollPaneCategoryBits.fill = GridBagConstraints.HORIZONTAL;
		gbc_scrollPaneCategoryBits.insets = new Insets(0, 10, 5, 0);
		gbc_scrollPaneCategoryBits.gridx = 0;
		gbc_scrollPaneCategoryBits.gridy = 7;
		gbc_scrollPaneCategoryBits.gridwidth = 2;
		panel.add(scrollPaneCategoryBits, gbc_scrollPaneCategoryBits);
		
		listCategoryBits = new JList(CATEGORIES);
		listCategoryBits.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// make sure the user is done adjusting
				if (!e.getValueIsAdjusting()) {
					JList list = (JList)e.getSource();
					int[] selections = list.getSelectedIndices();
					short value = 0;
					for (int i = 0; i < selections.length; i++) {
						value |= CATEGORIES[selections[i]].value;
					}
					spinnerCategoryBits.setValue(value);
					try{
						spinnerCategoryBits.commitEdit();
					}catch(Exception ex){
						
					}
				}
			}
		});
		scrollPaneCategoryBits.setViewportView(listCategoryBits);
		
		JLabel lblMaskbits = new JLabel("maskBits:");
		GridBagConstraints gbc_lblMaskbits = new GridBagConstraints();
		gbc_lblMaskbits.insets = new Insets(0, 0, 5, 5);
		gbc_lblMaskbits.gridx = 0;
		gbc_lblMaskbits.gridy = 8;
		panel.add(lblMaskbits, gbc_lblMaskbits);
		
		spinnerMaskBits = new JSpinner();
		spinnerMaskBits.setEnabled(false);
		spinnerMaskBits.setModel(new SpinnerNumberModel(new Short((short) 0), null, null, new Short((short) 1)));
		GridBagConstraints gbc_spinnerMaskBits = new GridBagConstraints();
		gbc_spinnerMaskBits.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerMaskBits.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerMaskBits.gridx = 1;
		gbc_spinnerMaskBits.gridy = 8;
		panel.add(spinnerMaskBits, gbc_spinnerMaskBits);
		listMaskBits = new JList(CATEGORIES);
		listMaskBits.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// make sure the user is done adjusting
				if (!e.getValueIsAdjusting()) {
					JList list = (JList)e.getSource();
					int[] selections = list.getSelectedIndices();
					short value = 0;
					for (int i = 0; i < selections.length; i++) {
						value |= CATEGORIES[selections[i]].value;
					}
					spinnerMaskBits.setValue(value);
					try{
						spinnerMaskBits.commitEdit();
					}catch(Exception ex){
						
					}
				}
			}
		});
		scrollPaneMaskBits = new JScrollPane(listMaskBits);
		scrollPaneMaskBits.setPreferredSize(new Dimension(150, 100));
		GridBagConstraints gbc_scrollPaneMaskBits = new GridBagConstraints();
		gbc_scrollPaneMaskBits.insets = new Insets(0, 10, 5, 0);
		gbc_scrollPaneMaskBits.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneMaskBits.gridx = 0;
		gbc_scrollPaneMaskBits.gridy = 9;
		gbc_scrollPaneMaskBits.gridwidth = 2;
		panel.add(scrollPaneMaskBits, gbc_scrollPaneMaskBits);
		
		JLabel lblGroupindex = new JLabel("groupIndex:");
		GridBagConstraints gbc_lblGroupindex = new GridBagConstraints();
		gbc_lblGroupindex.insets = new Insets(0, 0, 5, 5);
		gbc_lblGroupindex.gridx = 0;
		gbc_lblGroupindex.gridy = 10;
		panel.add(lblGroupindex, gbc_lblGroupindex);
		
		spinnerGroupIndex = new JSpinner();
		spinnerGroupIndex.setModel(new SpinnerNumberModel(new Short((short) 0), null, null, new Short((short) 1)));
		GridBagConstraints gbc_spinnerGroupIndex = new GridBagConstraints();
		gbc_spinnerGroupIndex.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerGroupIndex.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerGroupIndex.gridx = 1;
		gbc_spinnerGroupIndex.gridy = 10;
		panel.add(spinnerGroupIndex, gbc_spinnerGroupIndex);
		
		
		GridBagConstraints gbc_list_1 = new GridBagConstraints();
		gbc_list_1.insets = new Insets(0, 0, 5, 5);
		gbc_list_1.fill = GridBagConstraints.BOTH;
		gbc_list_1.gridx = 0;
		gbc_list_1.gridy = 10;
		

		this.listCategoryBits.setSelectedIndex(0);
		this.listMaskBits.setSelectedIndex(0);
	}

	@Override
	public void doBinder() {
		b2FixtureDefModel object = b2FixtureDefModel.class.cast(this.object);
		bind(object,"name",textName);
		bind(object,"friction",spinnerFriction);
		bind(object,"restitution",spinnerRestitution);
		bind(object,"isSensor",chckbxIsSensor);
		bind(object,"density",spinnerDensity);
		bind(object,"categoryBits",spinnerCategoryBits);
		bind(object,"maskBits",spinnerMaskBits);
		bind(object,"groupIndex",spinnerGroupIndex);
		bind(object,"mark",txtMark);
		int[] indices = this.getSelectedIndices(object.categoryBits);
		this.listCategoryBits.setSelectedIndices(indices);
		if(object.maskBits==-1){
			this.listMaskBits.setSelectedIndex(0);
		}else{
			indices = this.getSelectedIndices(object.maskBits);
			this.listMaskBits.setSelectedIndices(indices);
		}
	}

	
	public JPanel getPanel() {
		return panel;
	}
	
	/**
	 * Returns an array of indices that should be selected in the JList
	 * given the value of the category or mask.
	 * @param value the category or mask
	 * @return int[] the selected indices
	 */
	private int[] getSelectedIndices(short value) {
		List<Short> indexList = new ArrayList<Short>();
		int t = 1;
		for (short i = 1; i < 16; i++) {
			if ((value & t) == t) {
				indexList.add(i);
			}
			t *= 2;
		}
		int[] indices = new int[indexList.size()];
		for (int i = 0; i < indexList.size(); i++) {
			indices[i] = indexList.get(i);
		}
		return indices;
	}
}
