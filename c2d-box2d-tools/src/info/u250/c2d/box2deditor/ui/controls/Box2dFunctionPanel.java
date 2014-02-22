package info.u250.c2d.box2deditor.ui.controls;

import info.u250.c2d.box2d.model.b2BodyDefModel;
import info.u250.c2d.box2d.model.b2FixtureDefModel;
import info.u250.c2d.box2d.model.b2JointDefModel;
import info.u250.c2d.box2d.model.fixture.b2CircleFixtureDefModel;
import info.u250.c2d.box2d.model.fixture.b2RectangleFixtureDefModel;
import info.u250.c2d.box2deditor.Main;
import info.u250.c2d.box2deditor.adapter.PolygonFixtureDefModel;
import info.u250.c2d.box2deditor.adapter.SceneModelAdapter;
import info.u250.c2d.box2deditor.gdx.PhysicalWorld;
import info.u250.c2d.box2deditor.ui.util.DefCellRenderer;
import info.u250.c2d.box2deditor.ui.util.DefListModel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
public class Box2dFunctionPanel extends JPanel {

	private static final long serialVersionUID = 1368673847228258801L;
	private JList defList;
	
	private DefListModel defListModel;
	private JMenuItem mntmDelete;
	
	/**
	 * Create the panel.
	 */
	public Box2dFunctionPanel() {
		
		setLayout(new BorderLayout(0, 0));
		
		
		
		JScrollPane defScrollPanel = new JScrollPane();
		add(defScrollPanel, BorderLayout.CENTER);
		defList = new JList();
		defList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(2==e.getClickCount()){
					Main.bind(defList.getSelectedValue());
				}
			}
		});
		defList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		defList.setCellRenderer(new DefCellRenderer());
		defScrollPanel.setViewportView(defList);
		
		

		defListModel = new DefListModel();
		defList.setModel(defListModel);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(defList, popupMenu);
		

		mntmDelete = new JMenuItem("Delete");
		mntmDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SceneModelAdapter model = PhysicalWorld.MODEL;
				Object object = defList.getSelectedValue();
				if(object instanceof b2JointDefModel){
					model.removeJoint(b2JointDefModel.class.cast(object));
					setupModel();
				}else if(object instanceof b2BodyDefModel){
					model.removeBody(b2BodyDefModel.class.cast(object));
					setupModel();
				}else if(object instanceof b2FixtureDefModel){
					model.removeFixture(b2FixtureDefModel.class.cast(object));
					setupModel();
				}
			}
		});
		
		JMenuItem mntmAddBoxFixture = new JMenuItem("Add Box Fixture");
		mntmAddBoxFixture.setFont(new Font(mntmAddBoxFixture.getFont().getName(),Font.PLAIN,24));
		popupMenu.add(mntmAddBoxFixture);
		mntmAddBoxFixture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b2RectangleFixtureDefModel model = new b2RectangleFixtureDefModel();
				PhysicalWorld.MODEL.addFixture(model);
				addModel(model);
			}
		});
		mntmAddBoxFixture.setIcon(new ImageIcon(Box2dFunctionPanel.class.getResource("/info/u250/c2d/box2deditor/ui/res/b2RectangleFixtureDefModel.png")));
		
		JMenuItem mntmAddCircleFixture = new JMenuItem("Add Circle Fixture");
		mntmAddCircleFixture.setFont(new Font(mntmAddCircleFixture.getFont().getName(),Font.PLAIN,24));
		popupMenu.add(mntmAddCircleFixture);
		mntmAddCircleFixture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b2CircleFixtureDefModel model = new b2CircleFixtureDefModel();
				PhysicalWorld.MODEL.addFixture(model);
				addModel(model);
			}
		});
		mntmAddCircleFixture.setIcon(new ImageIcon(Box2dFunctionPanel.class.getResource("/info/u250/c2d/box2deditor/ui/res/b2CircleFixtureDefModel.png")));
		
		JMenuItem mntmAddPolygonFixture = new JMenuItem("Add Polygon Fixture");
		mntmAddPolygonFixture.setFont(new Font(mntmAddPolygonFixture.getFont().getName(),Font.PLAIN,24));
		popupMenu.add(mntmAddPolygonFixture);
		mntmAddPolygonFixture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PolygonFixtureDefModel model = new PolygonFixtureDefModel();
				PhysicalWorld.MODEL.addFixture(model);
				addModel(model);
			}
		});
		mntmAddPolygonFixture.setIcon(new ImageIcon(Box2dFunctionPanel.class.getResource("/info/u250/c2d/box2deditor/ui/res/PolygonFixtureDefModel.png")));
		
		JSeparator separator = new JSeparator();
		popupMenu.add(separator);
		
		JMenuItem mntmAddBody = new JMenuItem("Add Body");
		mntmAddBody.setFont(new Font(mntmAddBody.getFont().getName(),Font.PLAIN,24));
		popupMenu.add(mntmAddBody);
		mntmAddBody.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b2BodyDefModel model = new b2BodyDefModel();
				PhysicalWorld.MODEL.addBody(model);
				addModel(model);
			}
		});
		mntmAddBody.setIcon(new ImageIcon(Box2dFunctionPanel.class.getResource("/info/u250/c2d/box2deditor/ui/res/b2BodyDefModel.png")));
		
		JSeparator separator_1 = new JSeparator();
		popupMenu.add(separator_1);
		mntmDelete.setFont(new Font(mntmDelete.getFont().getName(),Font.PLAIN,24));
		mntmDelete.setIcon(new ImageIcon(Box2dFunctionPanel.class.getResource("/info/u250/c2d/box2deditor/ui/res/remove-icon.png")));
		popupMenu.add(mntmDelete);
	}
	public void addModel(Object model){
		Main.bind(model);
		setupModel();
		defList.setSelectedValue(model, true);
	}
	public void setupModel(){
		defListModel.clear();
		for(b2FixtureDefModel b2:PhysicalWorld.MODEL.fixtureDefModels){
			defListModel.addElement(b2);
		}
		for(b2BodyDefModel b2:PhysicalWorld.MODEL.bodyDefModels){
			defListModel.addElement(b2);
		}
		for(b2JointDefModel b2:PhysicalWorld.MODEL.jointDefModels){
			defListModel.addElement(b2);
		}
	}

	public JList getDefList() {
		return defList;
	}
	
	private  void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if(SwingUtilities.isRightMouseButton(e) ){
					defList.setSelectedIndex(defList.locationToIndex(e.getPoint()));
				}
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isRightMouseButton(e) ){
					defList.setSelectedIndex(defList.locationToIndex(e.getPoint()));
				}
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				if(defList.getSelectedIndex()==-1){
					mntmDelete.setEnabled(false);
				}else{
					mntmDelete.setEnabled(true);
				}
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
}
