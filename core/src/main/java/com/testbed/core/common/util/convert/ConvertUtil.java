package com.testbed.core.common.util.convert;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class ConvertUtil {

    /**
     * JSONObject 내 추가
     * @param obj
     * @param param
     * @param type
     * @return
     */
    public JSONObject setAddJSONObjec(JSONObject obj, ArrayList param, String type) {
        if(type == "owner" || type == "creator") {
            for(int i = 0; i < param.size() ; i++) {
                obj.put("username", param.get(i));
            }
        }

        return obj;
    }

    public JSONObject MapConvertJSONObject(Map aList) {
        JSONObject result = new JSONObject(aList);
        return result;
    }
}
