package info.u250.c2d.box2deditor.ui;

import info.u250.c2d.box2deditor.io.IO;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.util.EventListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * used auto change of the java entity and the ui
 * @author moonshadow
 *
 */
public abstract class Binder {
	JComponent inputField;
	Object  obj;
	Field field;
	String name;
	EventListener l;
	int count = 0;
	public void enable(){
		this.count = 1;
	}
	void addEventListener(){
		if(inputField instanceof JTextField){
			JTextField.class.cast(inputField).addKeyListener((KeyAdapter)l);
		}else if(inputField instanceof JCheckBox){
			JCheckBox.class.cast(inputField).addItemListener((ItemListener)l);
		}else if(inputField instanceof JSpinner){
			JSpinner.class.cast(inputField).addChangeListener((ChangeListener)l);
		}else if(inputField instanceof JComboBox){
			JComboBox.class.cast(inputField).addItemListener((ItemListener)l);
		}
	}
	public void update(Object object){
		if(object==this.obj){
			count++;
		}else{
			count = 0;
		}
		
		this.obj = object;
		try {
			try{
				//first
				field = obj.getClass().getDeclaredField(name);
			}catch(Exception ex){
				try{
					//second
					field = obj.getClass().getSuperclass().getDeclaredField(name);
				}catch(Exception e){
					//third
					try{
						//second
						field = obj.getClass().getSuperclass().getSuperclass().getDeclaredField(name);
					}catch(Exception exx){
						//four~
						field = obj.getClass().getSuperclass().getSuperclass().getSuperclass().getDeclaredField(name);
					}
				}
			}
			//ok ,let's set it
			if(inputField instanceof JTextField){
				//common text
				JTextField.class.cast(inputField).setText(field.get(obj)+"");
			}else if(inputField instanceof JCheckBox){
				//boolean value
				JCheckBox.class.cast(inputField).setSelected(Boolean.parseBoolean(field.get(obj)+""));
			}else if(inputField instanceof JSpinner){
				//float,integer,short values
				JSpinner.class.cast(inputField).setValue(field.get(obj));
			}else if(inputField instanceof JComboBox){
				JComboBox.class.cast(inputField).setSelectedIndex(field.getInt(obj));
			}
			
		}catch(Exception ex){ex.printStackTrace();}
	}
	public Binder(final Object object,final String name,final JComponent widget){
		this.name = name;
		this.inputField = widget;
		if(inputField instanceof JTextField){
			l = new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					try {
						field.set(obj,JTextField.class.cast(inputField).getText());
						if(count!=0)doPostSolve();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			};
		}else if(inputField instanceof JCheckBox){
			l = new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					try {
						field.set(obj, JCheckBox.class.cast(inputField).isSelected());
						if(count!=0)doPostSolve();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			};
		}else if(inputField instanceof JSpinner){
			l = new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					try{
						field.set(obj, JSpinner.class.cast(inputField).getValue());
						if(count!=0)doPostSolve();
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}
			};
		}else if(inputField instanceof JComboBox){
			l = new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					try{
						field.set(obj, JComboBox.class.cast(inputField).getSelectedIndex());
						if(count!=0)doPostSolve();
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}
			};
		}
		this.addEventListener();
		this.update(object);
	}
	void doPostSolve(){
		count++;
		postSolve();
		IO.INSTANCE.save(null);
	}
	protected abstract void postSolve();
}