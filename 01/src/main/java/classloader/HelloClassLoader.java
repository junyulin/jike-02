package classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Base64;

/**
 * 自定义类加载器
 *
 * @author LinJn
 * @since 2021/4/27 23:10
 */
public class HelloClassLoader extends ClassLoader {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
         Class<?> aClass = new HelloClassLoader().findClass("classloader.Hello");
        Method main = aClass.getMethod("main", String[].class);
        main.invoke(null, (Object)new String[]{"123", "456"});
    }

    @Override
    protected Class<?> findClass(String name) {
         String base64 = "yv66vgAAADQAHQoABgAPCQAQABEIABIKABMAFAcAFQcAFgEABjxpbml0PgEAAygpVgEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBAARtYWluAQAWKFtMamF2YS9sYW5nL1N0cmluZzspVgEAClNvdXJjZUZpbGUBAApIZWxsby5qYXZhDAAHAAgHABcMABgAGQEAEkhlbGxvIENsYXNzIExvYWRlcgcAGgwAGwAcAQARY2xhc3Nsb2FkZXIvSGVsbG8BABBqYXZhL2xhbmcvT2JqZWN0AQAQamF2YS9sYW5nL1N5c3RlbQEAA291dAEAFUxqYXZhL2lvL1ByaW50U3RyZWFtOwEAE2phdmEvaW8vUHJpbnRTdHJlYW0BAAdwcmludGxuAQAVKExqYXZhL2xhbmcvU3RyaW5nOylWACEABQAGAAAAAAACAAEABwAIAAEACQAAAB0AAQABAAAABSq3AAGxAAAAAQAKAAAABgABAAAACQAJAAsADAABAAkAAAAlAAIAAQAAAAmyAAISA7YABLEAAAABAAoAAAAKAAIAAAAMAAgADQABAA0AAAACAA4=";
        byte[] bytes = this.decode(base64);
        return defineClass(name, bytes, 0, bytes.length);
    }

    public byte[] decode(String base64) {
        return Base64.getDecoder().decode(base64);
    }
}
