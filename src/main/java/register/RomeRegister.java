package register;

import framework.Url;

import java.io.*;
import java.util.*;

public class RomeRegister {
    private static Map<String, List<Url>> RomeMap=new HashMap<String, List<Url>>();

    public static void  register(String interfaceName,Url url){
        RomeMap.put(interfaceName, Collections.singletonList(url));
        saveFile();
    }

    public static  Url get(String interfaceName ){
        RomeMap=getFile();
        Random random=new Random();
        List<Url> urls = RomeMap.get(interfaceName);
        int i = random.nextInt(urls.size());
        return urls.get(i);
    }
    private static void saveFile() {
        try {
            FileOutputStream outputStream=new FileOutputStream("/Users/zhaokun/source/rpcstudy/src/main/resources/a.txt");
            ObjectOutputStream oop=new ObjectOutputStream(outputStream);
            oop.writeObject(RomeMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static Map<String, List<Url>> getFile() {
        try {
            FileInputStream fileInputStream=new FileInputStream("/Users/zhaokun/source/rpcstudy/src/main/resources/a.txt");
            ObjectInputStream ois=new ObjectInputStream(fileInputStream);
            Map<String, List<Url>> map = (Map<String, List<Url>>) ois.readObject();
            return  map;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }  catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new HashMap<String, List<Url>>();
    }
}
