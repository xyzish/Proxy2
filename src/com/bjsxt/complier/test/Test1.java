package com.bjsxt.complier.test;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import com.bjsxt.proxy.Moveable;
import com.bjsxt.proxy.Tank;

public class Test1 {

    public static void main(String[] args) throws Exception {
        String rt = "\r\n";
        String src =  
                "package com.bjsxt.proxy;" +

                "public class TankTimeProxy implements Moveable {" + rt +
                "     public TankTimeProxy(Moveable t) {" + rt +
                "         super();" + rt +
                "         this.t = t;" + rt +
                "     }" + rt +

                "     Moveable t;" + rt +

                "     @Override" + rt +
                "     public void move() {" + rt +
                "         long start = System.currentTimeMillis();" + rt +
                "         System.out.println(\"starttime: \" + start);" + rt +
                "         t.move();" + rt +
                "         long end = System.currentTimeMillis();" + rt +
                "         System.out.println(\"time: \" + (end - start));" + rt +
                        
                "     }" + rt +

                "}";
        //write file
        String fileName = System.getProperty("user.dir") 
                             + "/src/com/bjsxt/proxy/TankTimeProxy.java";
        
        File f = new File(fileName);
        FileWriter fw = new FileWriter(f);
        fw.write(src);
        fw.flush();
        fw.close();
        
        //compile
        JavaCompiler complier = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileMgr = complier.getStandardFileManager(null, null, null);
        Iterable units = fileMgr.getJavaFileObjects(fileName);
        CompilationTask t = complier.getTask(null, fileMgr, null, null, null, units);
        t.call();
        fileMgr.close();
        
        //load into memory and create an instance
        URL[] urls = new URL[] {new URL("file:/" + System.getProperty("user.dir") + "/src")};
        URLClassLoader ul = new URLClassLoader(urls);
        Class c = ul.loadClass("com.bjsxt.proxy.TankTimeProxy");
        System.out.println(c);
        
        Constructor ctr = c.getConstructor(Moveable.class);
        Moveable m = (Moveable)ctr.newInstance(new Tank());
        m.move();

    }

}
