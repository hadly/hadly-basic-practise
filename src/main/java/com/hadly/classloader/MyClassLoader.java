package com.hadly.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * Created by hadly on 2017/2/15.
 */
public class MyClassLoader extends ClassLoader {

    //重载findClass方法，在里面读取class文件
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = new File("d:\\Person.class");
//        System.out.println("file=" + file);
        try {
            byte[] bytes = getClassBytes(file);
            Class<?> clazz = defineClass(name, bytes, 0, bytes.length);
//            System.out.println("clazz=" + clazz);
            return clazz;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private byte[] getClassBytes(File file) throws Exception {
        // 这里要读入.class的字节，因此要使用字节流
        FileInputStream fis = new FileInputStream(file);
        FileChannel fc = fis.getChannel();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        WritableByteChannel wbc = Channels.newChannel(baos);
        ByteBuffer by = ByteBuffer.allocate(1024);

        while (true) {
            int i = fc.read(by);
            if (i == 0 || i == -1)
                break;
            by.flip();
            wbc.write(by);
            by.clear();
        }

        fis.close();

        return baos.toByteArray();
    }

}
