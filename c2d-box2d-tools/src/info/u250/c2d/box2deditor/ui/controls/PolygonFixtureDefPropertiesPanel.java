package info.u250.c2d.box2deditor.ui.controls;

import info.u250.c2d.box2deditor.adapter.PolygonFixtureDefModel;
import info.u250.c2d.box2deditor.gdx.support.Geometry;
import info.u250.c2d.box2deditor.ui.Binder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ScrollPaneConstants;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
/**
 * This is not good for eat 
 * {@link PolygonFixtureDefModel#polygon} is for editor plus
 */
public class PolygonFixtureDefPropertiesPanel extends FixtureDefPropertiesPanel implements ActionListener{
	private List<Vector2Panel> pointPanels = new ArrayList<Vector2Panel>();
	public PolygonFixtureDefPropertiesPanel() {
		GridBagLayout gridBagLayout = (GridBagLayout) getPanel().getLayout();
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0};
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.insets = new Insets(10, 10, 5, 10);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 11;
		gbc_separator.gridwidth = 2;
		getPanel().add(separator, gbc_separator);
		
		JLabel lblVertices = new JLabel("vertices:");
		GridBagConstraints gbc_lblVertices = new GridBagConstraints();
		gbc_lblVertices.insets = new Insets(0, 0, 5, 5);
		gbc_lblVertices.gridx = 0;
		gbc_lblVertices.gridy = 12;
		getPanel().add(lblVertices, gbc_lblVertices);
		
		JLabel lblPolygonShape = new JLabel("Polygon Shape:");
		lblPolygonShape.setForeground(Color.BLUE);
		GridBagConstraints gbc_lblPolygonShape = new GridBagConstraints();
		gbc_lblPolygonShape.anchor = GridBagConstraints.WEST;
		gbc_lblPolygonShape.insets = new Insets(0, 0, 5, 5);
		gbc_lblPolygonShape.gridx = 1;
		gbc_lblPolygonShape.gridy = 12;
		getPanel().add(lblPolygonShape, gbc_lblPolygonShape);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(100,150));
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 13;
		gbc_scrollPane.gridwidth = 2;
		getPanel().add(scrollPane, gbc_scrollPane);
		
		polygonPanel = new JPanel();
		GroupLayout layout = new GroupLayout(this.polygonPanel);
		this.polygonPanel.setLayout(layout);
		// set all the flags
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(false);
		scrollPane.setViewportView(polygonPanel);
		
	}
	
	private static final long serialVersionUID = 1357591566550629249L;
	private JPanel polygonPanel;

	@Override
	public Class<PolygonFixtureDefModel> getType() {
		return PolygonFixtureDefModel.class;
	}
	Pool<Vector2Panel> vector2PanelPool = new Pool<Vector2Panel>(){
		@Override
		protected Vector2Panel newObject() {
			Vector2Panel panel = new Vector2Panel();
			panel.addActionListener(PolygonFixtureDefPropertiesPanel.this);
			return panel;
		}
	};
	
	public void doBinder() {
		super.doBinder();
		PolygonFixtureDefModel object = PolygonFixtureDefModel.class.cast(this.object);
		if(pointPanels.size()==object.polygon.size()){
			//when the vector points's size is equals of the polygon point size . we just update the bind infomation
			//so we can save a lot of space
			for(int i=0;i<object.polygon.size();i++){
				Vector2Panel panel = pointPanels.get(i);
				Vector2 p = object.polygon.get(i);
				bind(p, "x", panel.spinnerX);
				bind(p, "y", panel.spinnerY);
			}
		}else{
			//Add , remove , Or bind new model should reach here 
			for(Vector2Panel panel: pointPanels){
				vector2PanelPool.free(panel);
			}
			pointPanels.clear();
			for (Vector2 p:object.polygon) {
				Vector2Panel panel = vector2PanelPool.obtain();
				this.pointPanels.add(panel);
				bind(p, "x", panel.spinnerX);
				bind(p, "y", panel.spinnerY);
			}
			createLayout();
		}
	};
	
	public JPanel getPolygonPanel() {
		return polygonPanel;
	}
	/**
	 * Creates the layout for the panel.
	 */
	private void createLayout() {
		// remove all the components
		this.polygonPanel.removeAll();
		
		// recreate the layout
		GroupLayout layout = (GroupLayout)this.polygonPanel.getLayout();
		
		
		int size = this.pointPanels.size();
		
		// create the horizontal layout
		ParallelGroup hGroup = layout.createParallelGroup();
		for (int i = 0; i < size; i++) {
			Vector2Panel panel = this.pointPanels.get(i);
			panel.lblNumber.setText("No."+(i+1));
			hGroup.addComponent(panel);
			if (i < 3) {
				panel.btnRemove.setEnabled(false);
			} else {
				panel.btnRemove.setEnabled(true);
			}
		}
		// create the vertical layout
		SequentialGroup vGroup = layout.createSequentialGroup();
		for (int i = 0; i < size; i++) {
			Vector2Panel panel = this.pointPanels.get(i);
			vGroup.addComponent(panel);
		}
		layout.setHorizontalGroup(hGroup);
		layout.setVerticalGroup(vGroup);
	}
	
	public void actionPerformed(ActionEvent event) {
		PolygonFixtureDefModel model = PolygonFixtureDefModel.class.cast(this.object);
		// find the point panel issuing the event
		int index = this.pointPanels.indexOf(event.getSource());
		// check if its found
		if (index >= 0) {
			// check the type of event
			if ("add".equals(event.getActionCommand())) {
				// redo the layout
				if(model.polygon.size()>index+1){
					Vector2 indexPlus = model.polygon.get(index+1);
					model.polygon.add(index+1, new Vector2(model.polygon.get(index)).add(indexPlus.cpy().sub(model.polygon.get(index)).div(2)));
				}else{
					model.polygon.add(index+1, new Vector2(model.polygon.get(index)).add(20, 20));
				}
				bind(model);
			} else if ("remove".equals(event.getActionCommand())) {
				// redo the layout
				model.polygon.remove(index);
				bind(model);
			} 
		}
	}
	@Override
	public Binder getBinder(Object obj, String name, JComponent widget) {
		return new Binder(obj,name,widget) {
			@Override
			protected void postSolve() {
				if(null == object)return ;
				Geometry.splitPolygon(PolygonFixtureDefModel.class.cast(object));
			}
		};
	}
}
