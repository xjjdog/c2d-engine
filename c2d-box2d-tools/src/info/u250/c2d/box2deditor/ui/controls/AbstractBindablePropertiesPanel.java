package info.u250.c2d.box2deditor.ui.controls;

import info.u250.c2d.box2deditor.ui.Binder;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JPanel;
public abstract class AbstractBindablePropertiesPanel extends JPanel{
	private static final long serialVersionUID = -4111357230247774540L;
	Map<JComponent,Binder> bindMap = new HashMap<JComponent,Binder>();
	protected void bind(final Object obj,final String name,final JComponent textField){
		final Binder support = bindMap.get(textField);
		if(null!=support){
			support.update(obj);
		}else{
			bindMap.put(textField, getBinder(obj, name, textField));
		}	
	}
	protected Object object ;
	final public void bind(Object object){
		this.object = object;
		doBinder();
		for(Binder binder:bindMap.values()){
			binder.enable();
		}
	}
	public abstract void doBinder();
	public abstract Class<?> getType();
	public abstract Binder getBinder(Object obj,String name,final JComponent widget);
}
