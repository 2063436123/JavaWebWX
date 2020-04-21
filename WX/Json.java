package WX;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * created on 2020/4/8
 */
public class Json {
    /**
     * 提取JSON中与指定 key相关的 value
     *
     * @param json GET请求传来的JSON数据包
     * @return JSON数据包中 key键对应的值
     */
    public static String getValueFromJson(String json, String key) {
        int index = json.indexOf(key) + 3 + key.length();
        int last = index;
        for (int i = index; i < json.length(); i++) {
            if ('\"' == json.charAt(i)) {
                last = i;
                break;
            }
        }
        return json.substring(index, last);
    }

    /**
     * 将符合格式的字符串转化为JSON数组样式
     *
     * @param data 多个键值对样式如右  "key" : "我爱你","key1" : "一生一世";多组数据由;隔开
     * @return JSON样式的字符串
     */
    public static String toJson(String data, String token, String arrayHead) {
        if (data == null) {
            return null;
        }
        String newData = "{\"" + arrayHead + "\":[";
        int index = -3;
        while ((index = data.indexOf(token, index + 3)) != -1) {
            int last = data.indexOf(token, index + 3);
            if (last == -1) {
                newData += "{\"" + data.substring(index, data.length() - 1) + "}";
                break;
            }
            newData += "{\"" + data.substring(index, last - 2) + "},\n";
        }

        newData += "]" + "}";
        newData = newData.replaceAll(";", "");
        return newData;
    }

    public static String toJson(List<Map<String,String>>gameList, String arrayHead){
        System.out.println(gameList.size());
        StringBuilder sb = new StringBuilder();
        //append HeadName
        sb.append("\""+arrayHead+"\":");
        sb.append("[");

        Iterator<Map<String,String>> iter = gameList.iterator();
        //append object fields
        while(iter.hasNext()){
            sb.append("{");
            Map<String, String> temp = iter.next();
            for(var c : temp.entrySet()){
                sb.append("\""+c.getKey()+"\"");
                sb.append(":");
                sb.append("\""+c.getValue()+"\"");
                sb.append(",");
            }
            //delete the surplus comma
            sb.deleteCharAt(sb.length()-1);
            sb.append("},");
        }
        //delete the surplus comma
        sb.deleteCharAt(sb.length()-1);
        sb.append("]");
        return swapJson(sb);
    }

    private static String swapJson(StringBuilder sb) {
        return "{"+sb.toString()+"}";
    }


    /**
     * 向已有的数组格式的Json添加键值对元素
     */
    public static String addToJson(String json, String key, String value) {
        //System.out.println("加工前的json : " + json);
        //int end = json.indexOf("}]}");
        //String ans = json.substring(end+1,);
        String ans = "";
        ans += ",";
        ans += "\"" + key + "\":";
        ans +=value;
        ans = json.replaceAll("}]","}]"+ans);

        //System.out.println("加工后的json : " + ans);
        return ans;
    }
}
