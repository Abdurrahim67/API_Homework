package test_data;

import java.util.HashMap;
import java.util.Map;

public class DeleteTestData {

    public Map<String,Object>deleteTestMethod(int code,String type,String mesage){

        Map<String,Object>deleteDataMap=new HashMap<>();
        deleteDataMap.put("code",code);
        deleteDataMap.put("type",type);
        deleteDataMap.put("message",mesage);

        return deleteDataMap;


    }
}
