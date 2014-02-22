package info.u250.c2d.tests;


import info.u250.c2d.engine.Engine;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl.LwjglFiles;
import com.badlogic.gdx.backends.lwjgl.LwjglPreferences;
import com.badlogic.gdx.files.FileHandle;

public class C2dDesktop {
	
	static class TestList extends JPanel {
		private static final long serialVersionUID = -6629737055788922919L;

		public TestList () {
			setLayout(new BorderLayout());

			
			final JList list = new JList(C2dTests.getNames());
			final JButton button = new JButton("Run Test");
			JScrollPane pane = new JScrollPane(list);

			DefaultListSelectionModel m = new DefaultListSelectionModel();
			m.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			m.setLeadAnchorNotificationEnabled(false);
			list.setSelectionModel(m);

			list.addMouseListener(new MouseAdapter() {
				public void mouseClicked (MouseEvent event) {
					if (event.getClickCount() == 2) button.doClick();
				}
			});

			final Preferences prefs = new LwjglPreferences(new FileHandle(new LwjglFiles().getExternalStoragePath() + ".prefs/c2d-tests"));
			list.setSelectedValue(prefs.getString("last", null), true);
			
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed (ActionEvent e) {
					String testName = (String)list.getSelectedValue();
					Engine test = C2dTests.newTest(testName);
					LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
					config.fullscreen = false;
					config.width =(int) Engine.getWidth();
					config.height = (int) Engine.getHeight();
					config.useGL20 = Engine.useGL20();
					config.title = testName;
					config.vSyncEnabled = true;

					prefs.putString("last", testName);
					prefs.flush();
					
					new LwjglApplication(test,  config);
				}
			});

			add(pane, BorderLayout.CENTER);
			add(button, BorderLayout.SOUTH);
		}
	}

	
	public static void main (String[] argv) {
		JFrame frame = new JFrame("C2d - Jogl Test Launcher");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new TestList());
		frame.pack();
		frame.setSize(400, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
