package info.u250.c2d.box2deditor;

import info.u250.c2d.box2deditor.gdx.PhysicalWorld;
import info.u250.c2d.box2deditor.gdx.scenes.MainScene;
import info.u250.c2d.box2deditor.gdx.support.BuildWorld;
import info.u250.c2d.box2deditor.io.IO;
import info.u250.c2d.box2deditor.ui.EditorAdapter;
import info.u250.c2d.box2deditor.ui.controls.AbstractBindablePropertiesPanel;
import info.u250.c2d.box2deditor.ui.controls.BodyDefPropertiesPanel;
import info.u250.c2d.box2deditor.ui.controls.BottomInfoPanel;
import info.u250.c2d.box2deditor.ui.controls.Box2dFunctionPanel;
import info.u250.c2d.box2deditor.ui.controls.CircleFixtureDefPropertiesPanel;
import info.u250.c2d.box2deditor.ui.controls.DistanceJointDefPropertiesPanel;
import info.u250.c2d.box2deditor.ui.controls.FrictionJointDefPropertiesPanel;
import info.u250.c2d.box2deditor.ui.controls.PolygonFixtureDefPropertiesPanel;
import info.u250.c2d.box2deditor.ui.controls.PrismaticJointDefPropertiesPanel;
import info.u250.c2d.box2deditor.ui.controls.PulleyJointDefPropertiesPanel;
import info.u250.c2d.box2deditor.ui.controls.RectangelFixtureDefPropertiesPanel;
import info.u250.c2d.box2deditor.ui.controls.RevoluteJointDefPropertiesPanel;
import info.u250.c2d.box2deditor.ui.controls.RopeJointDefPropertiesPanel;
import info.u250.c2d.box2deditor.ui.controls.WeldJointDefPropertiesPanel;
import info.u250.c2d.box2deditor.ui.controls.WheelJointDefPropertiesPanel;
import info.u250.c2d.box2deditor.ui.util.DisabledPanel;
import info.u250.c2d.engine.Engine;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;

import com.badlogic.gdx.Gdx;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
	private void fillRightPart(Component comp){
		mainContentSplitPane.setRightComponent(comp);
		mainContentSplitPane.getLeftComponent().setMinimumSize(new Dimension(0,0));
		mainContentSplitPane.getLeftComponent().setMaximumSize(new Dimension(0,0));
		mainContentSplitPane.getRightComponent().setMinimumSize(new Dimension(250,0));
		frmCdboxdSceneEditor.validate();
		canvasPanel.getComponent(0).requestFocus();//focus the main canvas
	}

	private Map<Class<?>, DisabledPanel> propertiesPanels = new HashMap<Class<?>, DisabledPanel>();
	public final static void bind(Object model){
		bindUI(model);
		bindGdx(model);
	}
	public final static void bindUI(Object model){
		try{
			DisabledPanel comp = INSTANCE.propertiesPanels.get(model.getClass());
			AbstractBindablePropertiesPanel compInner = AbstractBindablePropertiesPanel.class.cast(comp.getContentContainer());
			compInner.bind(compInner.getType().cast(model));
			INSTANCE.objectListPanel.getDefList().setSelectedValue(model, true);
			if(comp!=INSTANCE.mainContentSplitPane.getRightComponent()){
				INSTANCE.fillRightPart(comp);
			}
		}catch(Exception ex){
			Gdx.app.error("C2d-Box2d", "Can not find properties panel for :" + model.getClass().getSimpleName());
		}
	}
	public final static void bindGdx(Object model){
		MainScene.INSTANCE.bind(model);
	}
	public final static void updateCameraInfo(){
		INSTANCE.bottomInfoPanel.update();
	}
	public final static void setupModel(){
		INSTANCE.objectListPanel.setupModel();
	}
	public final static void addModel(Object model){
		INSTANCE.objectListPanel.addModel(model);
	}
	public final static boolean isDebug(){
		return INSTANCE.chckbxDebug.isSelected();
	}
	private void setupProperties(){
		CircleFixtureDefPropertiesPanel circleFixtureDefPropertiesPanel = new CircleFixtureDefPropertiesPanel();
		RectangelFixtureDefPropertiesPanel rectangelFixtureDefPropertiesPanel = new RectangelFixtureDefPropertiesPanel();
		PolygonFixtureDefPropertiesPanel colygonFixtureDefPropertiesPanel = new PolygonFixtureDefPropertiesPanel();
		BodyDefPropertiesPanel bodyDefPropertiesPanel = new BodyDefPropertiesPanel();
		DistanceJointDefPropertiesPanel distanceJointDefPropertiesPanel = new DistanceJointDefPropertiesPanel();
		RevoluteJointDefPropertiesPanel revoluteJointDefPropertiesPanel = new RevoluteJointDefPropertiesPanel();
		PrismaticJointDefPropertiesPanel prismaticJointDefPropertiesPanel = new PrismaticJointDefPropertiesPanel();
		WeldJointDefPropertiesPanel weldJointDefPropertiesPanel = new WeldJointDefPropertiesPanel();
		FrictionJointDefPropertiesPanel frictionJointDefPropertiesPanel = new FrictionJointDefPropertiesPanel();
		RopeJointDefPropertiesPanel ropeJointDefPropertiesPanel = new RopeJointDefPropertiesPanel();
		WheelJointDefPropertiesPanel wheelJointDefPropertiesPanel = new WheelJointDefPropertiesPanel();
		PulleyJointDefPropertiesPanel pulleyJointDefPropertiesPanel = new PulleyJointDefPropertiesPanel();
		propertiesPanels.put(circleFixtureDefPropertiesPanel.getType(), new DisabledPanel(circleFixtureDefPropertiesPanel));
		propertiesPanels.put(rectangelFixtureDefPropertiesPanel.getType(), new DisabledPanel(rectangelFixtureDefPropertiesPanel));
		propertiesPanels.put(colygonFixtureDefPropertiesPanel.getType(), new DisabledPanel(colygonFixtureDefPropertiesPanel));
		propertiesPanels.put(bodyDefPropertiesPanel.getType(), new DisabledPanel(bodyDefPropertiesPanel));
		propertiesPanels.put(distanceJointDefPropertiesPanel.getType(), new DisabledPanel(distanceJointDefPropertiesPanel));
		propertiesPanels.put(revoluteJointDefPropertiesPanel.getType(), new DisabledPanel(revoluteJointDefPropertiesPanel));
		propertiesPanels.put(prismaticJointDefPropertiesPanel.getType(), new DisabledPanel(prismaticJointDefPropertiesPanel));
		propertiesPanels.put(weldJointDefPropertiesPanel.getType(), new DisabledPanel(weldJointDefPropertiesPanel));
		propertiesPanels.put(frictionJointDefPropertiesPanel.getType(), new DisabledPanel(frictionJointDefPropertiesPanel));
		propertiesPanels.put(ropeJointDefPropertiesPanel.getType(), new DisabledPanel(ropeJointDefPropertiesPanel));
		propertiesPanels.put(wheelJointDefPropertiesPanel.getType(), new DisabledPanel(wheelJointDefPropertiesPanel));
		propertiesPanels.put(pulleyJointDefPropertiesPanel.getType(), new DisabledPanel(pulleyJointDefPropertiesPanel));
	}
	private static Main INSTANCE = null;
	private JFrame frmCdboxdSceneEditor;
	private JSplitPane mainContentSplitPane;
	private JPanel mainPanel;
	private JPanel canvasPanel;
	private BottomInfoPanel bottomInfoPanel;
	private Box2dFunctionPanel objectListPanel;
	private DisabledPanel disabledObjectListPanel ;
	private JCheckBox chckbxDebug;

	/**
	 * Launch the application.
	 * @throws Exception 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws Exception {
		try{
			javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		}catch(Exception ex){
			
		}
		JPopupMenu.setDefaultLightWeightPopupEnabled(false);
		ToolTipManager.sharedInstance().setLightWeightPopupEnabled(false);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmCdboxdSceneEditor.setVisible(true);
					Canvas canvas = new Canvas();
					EditorAdapter.setupCanvas(canvas);
					Main.INSTANCE.canvasPanel.add(canvas);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
		setupProperties();
		INSTANCE = this;
	}

	void windowsClosing(){
		int n=JOptionPane.showConfirmDialog(frmCdboxdSceneEditor, 
				"Are you sure to close ? You current work will be lost! Make sure you have saved it",
				"Close",
				JOptionPane.YES_NO_OPTION );
		if(0 == n){
			System.exit(0);
		}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCdboxdSceneEditor = new JFrame();
		frmCdboxdSceneEditor.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				windowsClosing();
			}
		});
		frmCdboxdSceneEditor.setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/info/u250/c2d/box2deditor/ui/res/logo.png")));
		frmCdboxdSceneEditor.setTitle("C2d / Box2d Scene Editor - lycying@gmail.com");
		frmCdboxdSceneEditor.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmCdboxdSceneEditor.setPreferredSize(new Dimension(600,500));
		frmCdboxdSceneEditor.setExtendedState(Frame.MAXIMIZED_BOTH);
		
		JPanel objectListPanelOutter = new JPanel();
		disabledObjectListPanel = new DisabledPanel(objectListPanelOutter);
		frmCdboxdSceneEditor.getContentPane().add(disabledObjectListPanel, BorderLayout.WEST);
		objectListPanelOutter.setLayout(new BorderLayout(0, 0));
		
		objectListPanel = new Box2dFunctionPanel();
		objectListPanelOutter.add(objectListPanel, BorderLayout.CENTER);
		
		mainContentSplitPane = new JSplitPane();
		mainContentSplitPane.setContinuousLayout(true);
		mainContentSplitPane.setResizeWeight(0.9);
		frmCdboxdSceneEditor.getContentPane().add(mainContentSplitPane, BorderLayout.CENTER);
		
		mainPanel = new JPanel();
		mainContentSplitPane.setLeftComponent(mainPanel);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		mainPanel.add(toolBar, BorderLayout.NORTH);
		
		btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStop.setEnabled(true);
				mnExamples.setEnabled(false);
				mnFile.setEnabled(false);
				disabledObjectListPanel.setEnabled(false);
				mainContentSplitPane.getRightComponent().setEnabled(false);
				MainScene.INSTANCE.simulation();
			}
		});
		
		JButton btnZoom = new JButton("");
		btnZoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Engine.getDefaultCamera().zoom = 1;
			}
		});
		btnZoom.setIcon(new ImageIcon(Main.class.getResource("/info/u250/c2d/box2deditor/ui/res/zoom.png")));
		toolBar.add(btnZoom);
		btnStart.setIcon(new ImageIcon(Main.class.getResource("/info/u250/c2d/box2deditor/ui/res/start.png")));
		toolBar.add(btnStart);
		
		btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStop.setEnabled(false);
				disabledObjectListPanel.setEnabled(true);
				mnExamples.setEnabled(true);
				mnFile.setEnabled(true);
				mainContentSplitPane.getRightComponent().setEnabled(true);
				MainScene.INSTANCE.stopSimulation();
			}
		});
		btnStop.setEnabled(false);
		btnStop.setIcon(new ImageIcon(Main.class.getResource("/info/u250/c2d/box2deditor/ui/res/stop.png")));
		toolBar.add(btnStop);
		
		chckbxDebug = new JCheckBox("Show Debug");
		chckbxDebug.setSelected(true);
		toolBar.add(chckbxDebug);
		
		
		canvasPanel = new JPanel();
		canvasPanel.setLayout(new BorderLayout(0, 0));
		mainPanel.add(canvasPanel, BorderLayout.CENTER);
		
		bottomInfoPanel = new BottomInfoPanel();
		canvasPanel.add(bottomInfoPanel, BorderLayout.SOUTH);
		
		JLabel lblNewLabel = new JLabel("By<lycying@gmail.com>");
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		mainContentSplitPane.setRightComponent(lblNewLabel);
		
		
		JMenuBar menuBar = new JMenuBar();
		frmCdboxdSceneEditor.setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n=JOptionPane.showConfirmDialog(frmCdboxdSceneEditor, 
						"Make A new Scene? You current work will be lost! Make sure you have saved it",
						"New",
						JOptionPane.YES_NO_OPTION );
				if(0 == n){
					IO.INSTANCE.reset();
					setModelToUI();
				}
			}
		});
		mnFile.add(mntmNew);
		
		JMenuItem mntmOpen = new JMenuItem("Open File...");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jf = new JFileChooser();
				int result = jf.showOpenDialog(frmCdboxdSceneEditor);
				if (result == JFileChooser.APPROVE_OPTION) {
					File file = jf.getSelectedFile();
					IO.INSTANCE.read(file);
					setModelToUI();
				}
			}
		});
		mnFile.add(mntmOpen);
		
		JMenuItem mntmSaveAs = new JMenuItem("Save As...");
		mntmSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jf = new JFileChooser();
				int result = jf.showSaveDialog(frmCdboxdSceneEditor);
				if (result == JFileChooser.APPROVE_OPTION) {
					File file = jf.getSelectedFile();
					IO.INSTANCE.save(file);
				}
			}
		});
		mnFile.add(mntmSaveAs);
		
		JMenuItem mntmClose = new JMenuItem("Exit");
		mntmClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				windowsClosing();
			}
		});
		mnFile.add(mntmClose);
		
		JMenu mnExport = new JMenu("Export");
		menuBar.add(mnExport);
		
		JMenuItem mntmExportToXml = new JMenuItem("Export to XML...");
		mntmExportToXml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jf = new JFileChooser();
				int result = jf.showSaveDialog(frmCdboxdSceneEditor);
				if (result == JFileChooser.APPROVE_OPTION) {
					File file = jf.getSelectedFile();
					IO.INSTANCE.exportXML(file);
				}
			}
		});
		mnExport.add(mntmExportToXml);
		
		mnExamples = new JMenu("Examples");
		mnExamples.setIcon(new ImageIcon(Main.class.getResource("/info/u250/c2d/box2deditor/ui/res/examples.gif")));
		menuBar.add(mnExamples);
		
		try{
			BufferedReader dr = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream("/examples/examples.def")));
			String line =  dr.readLine();
			while(line!= null){ 
				if(!"".equals(line)){
					if("-".equals(line)){
						mnExamples.add(new JSeparator());
					}else{
						JMenuItem mntmComplexWorld = new JMenuItem(line);
						mntmComplexWorld.addActionListener(exampleActionListener);
						mnExamples.add(mntmComplexWorld);
					}
				}
				line = dr.readLine();
			} 
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}

	final ActionListener exampleActionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			IO.INSTANCE.read(Main.class.getResourceAsStream("/examples/"+(((JMenuItem)e.getSource()).getText()).replaceAll(" ", "")+".an"));
			setModelToUI();
		}
	};
	private JButton btnStop;
	private JButton btnStart;
	private JMenu mnExamples;
	private JMenu mnFile;
	void setModelToUI(){
		MainScene.INSTANCE.callUI.setupModel();
		try{
			bind(PhysicalWorld.MODEL.bodyDefModels.get(0));
		}catch(Exception e){
			//Ignore
		}
		BuildWorld.buildBodys();
	}

}
