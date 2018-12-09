/*
 * Developed by Razil Minneakhmetov on 11/25/18 2:48 PM.
 * Last modified 11/25/18 2:48 PM.
 * Copyright © 2018. All rights reserved.
 */

package context;

import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.CartService;
import services.LoginService;
import services.ProductService;
import services.VKAuthService;

import javax.sql.DataSource;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;

public class MyApplicationContext<T> {
    private static MyApplicationContext myContext;
    private Map<String, Object> components;

    private MyApplicationContext() {
        components = new HashMap<String, Object>();

        ApplicationContext context =
                new ClassPathXmlApplicationContext("beans/context.xml");


//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setUrl("jdbc:postgresql://localhost:5432/stickershop");
//        dataSource.setUsername("postgres");
//        dataSource.setPassword("r1a2z3i4l5");
//        components.put("dataSource", dataSource);
//
//        List<Class<T>> repoList = getClasses("C:\\Users\\razil\\Desktop\\Java Enterprise\\CartShop\\src\\main\\java\\repositories");
//
//        for (int i = 0; i < repoList.size(); i++) {
//            components.put(repoList.get(i).getSimpleName() , loadRepo(repoList.get(i)));
//        }
//
//        List<Class<T>> serviceList = getClasses("C:\\Users\\razil\\Desktop\\Java Enterprise\\CartShop\\src\\main\\java\\services");
//
//        for (int i = 0; i < serviceList.size(); i++){
//            components.put(serviceList.get(i).getSimpleName() , loadService(serviceList.get(i)));
//        }

        components.put("CartService", context.getBean(CartService.class));
        components.put("ProductService", context.getBean(ProductService.class));
        components.put("LoginService", context.getBean(LoginService.class));
        components.put("VKAuthService", context.getBean(VKAuthService.class));
    }

    public static MyApplicationContext getMyContext() {
        if (myContext != null)
            return myContext;
        else {
            myContext = new MyApplicationContext();
            return myContext;
        }
    }

    public <T> T getComponent(Class<T> componentClass) {
        for (Object component : components.values()) {
            if (componentClass.isAssignableFrom(component.getClass())) {
                return (T) component;
            }
        }
        return null;
    }

    public Object getAttribute(String key) {
        return components.get(key);
    }

    public void setAttribute(String key, Object value) {
        components.put(key, value);
    }

    @SneakyThrows
    private <T> T loadRepo(Class<T> userClass) {
        //Class<T> userClass = (Class<T>) Class.forName("repositories." + className);
        Constructor<T> constructor = userClass.getConstructor(DataSource.class);
        T object = constructor.newInstance(components.get("dataSource"));
        return object;
    }



    @SneakyThrows
    private <T> List<Class<T>> getClasses(String path) {

        File[] allClasses = new File(path).listFiles();
        List<Class<T>> classes = new ArrayList<>();
        String root = path.replace("\\", ".").split("java.")[1];

        for (int i = 0; i < allClasses.length; i++) {
            classes.add((Class<T>) Class.forName(root + "." + allClasses[i].getName().split(".java")[0]));
        }

        return classes;
    }

    @SneakyThrows
    private <T> T loadService(Class<T> userClass) {
        //Class<T> userClass = (Class<T>) Class.forName("services." + className);
        Field[] fields = userClass.getDeclaredFields();
        List<Class<T>> classList = new ArrayList<Class<T>>();
        List<Object> instances = new ArrayList<>();
        T object;
        for(int i = 0; i < fields.length; i++){
            classList.add((Class<T>) Class.forName(fields[i].getType().getCanonicalName()));
            instances.add(components.get(fields[i].getType().getSimpleName()));
        }
        if (classList.size() == 0){
            //Class<T>[] classes = new Class[classList.size()];
            Constructor constructor = userClass.getConstructor();
            //Object[] objects = new Object[instances.size()];
            object = (T) constructor.newInstance();
        } else {
            Class<T>[] classes = new Class[classList.size()];
            Constructor constructor = userClass.getConstructor(classList.toArray(classes));
            Object[] objects = new Object[instances.size()];
            object = (T) constructor.newInstance(instances.toArray(objects));
        }
        return object;
    }
}