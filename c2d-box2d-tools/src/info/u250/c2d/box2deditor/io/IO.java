package info.u250.c2d.box2deditor.io;

import java.io.File;
import java.io.InputStream;

public interface IO {
	void reset();
	void save(File file);
	void read(File file); 
	void read(InputStream input);
	
	void exportXML(File file);
	
	public IO INSTANCE = new SerializableIO();
}
