package ru.innopolis.stc13.magic;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class KindMagicClassLoader extends ClassLoader {
    public KindMagicClassLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (name.equals("ru.innopolis.stc13.magic.Magic")) {
            String dest = "file:D://magic.class";
            byte[] classData = null;
            try (InputStream inputStream =
                         new URL(dest).openConnection().getInputStream();
                 ByteArrayOutputStream byteArrayOutputStream =
                         new ByteArrayOutputStream()) {
                int data = inputStream.read();
                while (data != -1) {
                    byteArrayOutputStream.write(data);
                    data = inputStream.read();
                }
                classData = byteArrayOutputStream.toByteArray();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return defineClass(name, classData, 0, classData.length);
        } else {
            return super.loadClass(name);
        }
    }
}
