package info.u250.c2d.box2deditor.ui.controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import java.awt.Color;


public class Vector2Panel extends JPanel implements ActionListener {
	/** The version id */
	private static final long serialVersionUID = 5446710351912720509L;
	
	/** The text field for the x value */
	public JSpinner spinnerX;
	
	/** The text field for the y value */
	public JSpinner spinnerY;
	
	/** The button to remove the point */
	public JButton btnRemove;
	
	/** The button to add the point */
	public JButton btnAdd;
	
	public JLabel lblNumber ;
	
	
	/**
	 * Full constructor.
	 * @param x the initial x value
	 * @param y the initial y value
	 */
	public Vector2Panel() {
		JLabel lblX = new JLabel("X");
		JLabel lblY = new JLabel("Y");
		
		this.spinnerX = new JSpinner();
		this.spinnerY = new JSpinner();

		
		this.btnAdd = new JButton();
		btnAdd.setIcon(new ImageIcon(Vector2Panel.class.getResource("/info/u250/c2d/box2deditor/ui/res/add-icon.png")));
		this.btnAdd.addActionListener(this);
		this.btnAdd.setActionCommand("add");
		
		this.btnRemove = new JButton();
		btnRemove.setIcon(new ImageIcon(Vector2Panel.class.getResource("/info/u250/c2d/box2deditor/ui/res/remove-icon.png")));
		this.btnRemove.addActionListener(this);
		this.btnRemove.setActionCommand("remove");
		
		lblNumber = new JLabel();
		lblNumber.setForeground(Color.RED);
		
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		
		layout.setAutoCreateGaps(true);
		layout.setHonorsVisibility(true);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addComponent(this.lblNumber)
				.addComponent(this.spinnerX)
				.addComponent(lblX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(this.spinnerY)
				.addComponent(lblY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(this.btnAdd, 25,25,25)
				.addComponent(this.btnRemove, 25,25,25));
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(lblNumber)
						.addComponent(this.spinnerX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblX)
						.addComponent(this.spinnerY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblY)
						.addComponent(this.btnAdd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(this.btnRemove, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)));
	}
	
	/**
	 * Adds an action listener to listen for button events.
	 * @param actionListener the action listener to add
	 */
	public void addActionListener(ActionListener actionListener) {
		this.listenerList.add(ActionListener.class, actionListener);
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		ActionListener[] listeners = this.getListeners(ActionListener.class);
		// set the source to this
		e.setSource(this);
		// forward the event to the listeners on this class
		for (ActionListener listener : listeners) {
			listener.actionPerformed(e);
		}
	}
	
	
	/**
	 * Returns the x value of the point.
	 * @return double
	 */
	public double getValueX() {
		Number number = (Number)this.spinnerX.getValue();
		return number.doubleValue();
	}
	
	/**
	 * Returns the y value of the point.
	 * @return double
	 */
	public double getValueY() {
		Number number = (Number)this.spinnerY.getValue();
		return number.doubleValue();
	}
}
