package provider;

import java.util.HashMap;
import java.util.Map;

public class LocalRegister {
    private static Map<String,Class> LocalMap=new HashMap<String, Class>();
    public static void register(String interfaceNme, Class implClass){
        LocalMap.put(interfaceNme,implClass);
    }

    public static Class get(String interfaceNme) {
       return  LocalMap.get(interfaceNme);
    }
}
