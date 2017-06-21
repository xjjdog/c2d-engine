package info.u250.c2d.box2deditor.nulz;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import info.u250.c2d.box2d.model.b2BodyDefModel;
import info.u250.c2d.box2d.model.b2FixtureDefModel;
import info.u250.c2d.box2d.model.b2JointDefModel;
import info.u250.c2d.box2deditor.Main;
import info.u250.c2d.box2deditor.adapter.SceneModelAdapter;

import java.io.*;

public class ModelChangeDe {


    public static void main(String[] args) throws Exception {
        new LwjglApplication(new ApplicationAdapter() {
            @Override
            public void create() {
                super.create();
                try {
                    BufferedReader dr = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream("/examples/examples.def")));
                    String line = dr.readLine();
                    while (line != null) {
                        if (!"".equals(line)) {
                            if ("-".equals(line)) {

                            } else {
                                String name = line.replaceAll(" ", "") + ".an";
                                System.out.println(line);
                                ObjectInputStream in = new ObjectInputStream(ModelChangeDe.class.getResourceAsStream("/examples/" + name));
                                SceneModelAdapter model = (SceneModelAdapter) in.readObject();
                                in.close();
                                for (b2BodyDefModel c : model.bodyDefModels) {
                                    System.out.println(c);
                                    c.mark = "";
                                }
                                for (b2FixtureDefModel c : model.fixtureDefModels) {
                                    System.out.println(c);
                                    c.mark = "";
                                }
                                for (b2JointDefModel c : model.jointDefModels) {
                                    System.out.println(c);
                                    c.mark = "";
                                }
                                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File("D:/x/" + name)));
                                out.writeObject(model);
                                out.close();
                            }
                        }
                        line = dr.readLine();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });
    }

}
