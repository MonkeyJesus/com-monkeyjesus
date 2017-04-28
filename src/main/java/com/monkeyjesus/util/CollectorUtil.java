package com.monkeyjesus.util;

import com.monkeyjesus.entry.Picture;
import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MacheNike on 2017/4/25.
 */
public class CollectorUtil {

    public static List<Map<String,Object>> getDistinctedMapList(List<Map<String,Object>> mapList,String key){
//        mapList.stream().distinct()
        return mapList;
    }

    public static Map<String, Object> transBean2Map(Object obj) {

        if(obj == null){
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);

                    map.put(key, value);
                }

            }
        } catch (Exception e) {
            System.out.println("transBean2Map Error " + e);
        }

        return map;

    }

    /**
     * Map 转 Bean
     * 利用Introspector,PropertyDescriptor实现 Map 转 Bean
     * @param map
     * @param obj
     */
    public static void transMap2Bean(Map<String, Object> map, Object obj) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (map.containsKey(key)) {
                    Object value = map.get(key);
                    // 得到property对应的setter方法
                    Method setter = property.getWriteMethod();
                    setter.invoke(obj, value);
                }
            }
        } catch (Exception e) {
            System.out.println("transMap2Bean Error " + e);
        }
        return;
    }

    /**
     * List<Map> --> List<Bean>
     * 利用Introspector,PropertyDescriptor实现 List<Map> --> List<Bean>
     * @param mapList
     * @param clzssName
     * @return
     */
    public static List<Object> transMapList2BeanListByClassName(List<Map<String,Object>> mapList, String clzssName){
        try {
            Class clzss = Class.forName(clzssName);
            List<Object> returnList = new ArrayList<Object>();
            for (Map<String, Object> map : mapList) {
                Object object = clzss.newInstance();
                transMap2Bean(map,object);
                returnList.add(object);
            }
            return returnList;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args){
        Map map = new HashMap();
        map.put("pictureName","test");
        map.put("width",23);
        map.put("height",12);

        Map map1 = new HashMap();
        map1.put("pictureName","test2");
        map1.put("width",2323);
        map1.put("height",1223);

        List<Map<String,Object>> mapList = new ArrayList<Map<String, Object>>();
        mapList.add(map);
        mapList.add(map1);

        Picture p = new Picture();
        Picture p2 = new Picture();

        String clzssName = Picture.class.getCanonicalName();

        System.out.println(transMapList2BeanListByClassName(mapList,Picture.class.getName()));
//        try {
////            Class clzss = Class.forName(clzssName);
////            Object o = clzss.newInstance();
////            System.out.println(o.getClass());
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }



//        List<Object> pics = new ArrayList<Picture>();
//        pics.add(p);
//        pics.add(p2);

//        System.out.println(transMapList2BeanList(mapList,pics));

//
//
//        transMap2Bean(map,p);

//        System.out.println(p.getPictureName());

    }

}
