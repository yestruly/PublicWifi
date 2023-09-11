package Service;

import Dao.WifiDao;
import Dto.WifiDto;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class UseWifi {
    public static final  String URLINPUT = "http://openapi.seoul.go.kr:8088/7a6d637775647077353570784c6749/json/TbPublicWifiInfo";


    public int count() throws IOException {
        String newUrl = URLINPUT + "/1/1";
        URL url = new URL(newUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-type", "application/json");

        BufferedReader rd;
        if (con.getResponseCode() >= 200 && con.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        con.disconnect();

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(sb.toString(), JsonObject.class);
        int totalCount = jsonObject.getAsJsonObject("TbPublicWifiInfo").get("list_total_count").getAsInt();
        return totalCount;
    }

    public void save() throws IOException {
        int cnt = 1;

        for (int i = 0; i < count() / 1000 + 1; i++) {
            String newUrl = URLINPUT + "/"+
                    URLEncoder.encode(String.valueOf(cnt),"UTF-8")+"/"
                    +URLEncoder.encode(String.valueOf(cnt+999),"UTF-8");

            URL url = new URL(newUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-type", "application/json");

            BufferedReader rd;
            if (con.getResponseCode() >= 200 && con.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            con.disconnect();

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(sb.toString(), JsonObject.class);
            JsonArray jsonArray = jsonObject.getAsJsonObject("TbPublicWifiInfo").getAsJsonArray("row");

            for (int j = 0; j < jsonArray.size(); j++) {
                JsonObject jObject = jsonArray.get(j).getAsJsonObject();

                WifiDto dto = new WifiDto();
                dto.setWifi_number(jObject.get("X_SWIFI_MGR_NO").getAsString());
                dto.setBorough(jObject.has("X_SWIFI_WRDOFC") ? jObject.get("X_SWIFI_WRDOFC").getAsString() : "");
                dto.setWifiName(jObject.has("X_SWIFI_MAIN_NM") ? jObject.get("X_SWIFI_MAIN_NM").getAsString() : "");
                dto.setStreet(jObject.has("X_SWIFI_ADRESS1") ? jObject.get("X_SWIFI_ADRESS1").getAsString() : "");
                dto.setDetailAddress(jObject.has("X_SWIFI_ADRESS2") ? jObject.get("X_SWIFI_ADRESS2").getAsString() : "");
                dto.setInstallFloar(jObject.has("X_SWIFI_INSTL_FLOOR") ? jObject.get("X_SWIFI_INSTL_FLOOR").getAsString() : "");
                dto.setInstallType(jObject.has("X_SWIFI_INSTL_TY") ? jObject.get("X_SWIFI_INSTL_TY").getAsString() : "");
                dto.setInstallAgancy(jObject.has("X_SWIFI_INSTL_MBY") ? jObject.get("X_SWIFI_INSTL_MBY").getAsString() : "");
                dto.setService(jObject.has("X_SWIFI_SVC_SE") ? jObject.get("X_SWIFI_SVC_SE").getAsString() : "");
                dto.setNet(jObject.has("X_SWIFI_CMCWR") ? jObject.get("X_SWIFI_CMCWR").getAsString() : "");
                dto.setYear(jObject.has("X_SWIFI_CNSTC_YEAR") ? jObject.get("X_SWIFI_CNSTC_YEAR").getAsInt() : 0);
                dto.setInOut(jObject.has("X_SWIFI_INOUT_DOOR") ? jObject.get("X_SWIFI_INOUT_DOOR").getAsString() : "");
                dto.setConnect(jObject.has("X_SWIFI_REMARS3") ? jObject.get("X_SWIFI_REMARS3").getAsString() : "");
                dto.setLat(jObject.has("LAT") ? jObject.get("LAT").getAsDouble() : 0.0);
                dto.setLnt(jObject.has("LNT") ? jObject.get("LNT").getAsDouble() : 0.0);
                dto.setWorkingDate(jObject.has("WORK_DTTM") ? jObject.get("WORK_DTTM").getAsString() : "");

                WifiDao dao = new WifiDao();
                dao.insert(dto);
            }
        }


    }

//    public static void main(String[] args) {
//        UseWifi wifi = new UseWifi();
//        try {
//            wifi.save();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

}