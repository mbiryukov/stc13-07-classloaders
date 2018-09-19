package ru.innopolis.stc13.magic;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException,
            IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        ClassLoader parentClassLoader = BadMagic.class.getClassLoader();
        Class magicClass = parentClassLoader
                .loadClass("ru.innopolis.stc13.magic.BadMagic");
        BadMagic badMagic = (BadMagic) magicClass.newInstance();
        badMagic.cast();

        KindMagicClassLoader kindMagicClassLoader =
                new KindMagicClassLoader(parentClassLoader);
        Class kindMagicClass = kindMagicClassLoader
                .loadClass("ru.innopolis.stc13.magic.Magic");
        Object magic = kindMagicClass.newInstance();
        //kindMagicClass.cast(magic).cast();
        kindMagicClass.getMethod("cast")
                .invoke(kindMagicClass.newInstance(), null);
    }
}
