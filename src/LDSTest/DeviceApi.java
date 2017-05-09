package LDSTest;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 * This class provides the capability to connect or disconnect device.
 */
public class DeviceApi {
    private OkHttpClient client;
    private JsonParser jsonParser;
    private STFService stfService;
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final Logger LOGGER = Logger.getLogger(Class.class.getName());

    public DeviceApi(STFService stfService) {
        this.client = new OkHttpClient();
        this.jsonParser = new JsonParser();
        this.stfService = stfService;
    }
    
    public void authUser() {
        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer " + stfService.getAuthToken())
                .url(stfService.getStfUrl())
                .build();
        Response response;
        try {
            response = client.newCall(request).execute();
            //JsonObject jsonObject = jsonParser.parse(response.body().string()).getAsJsonObject();
            //String myString = jsonObject.toString();
            System.out.println("************ AUTH USER ****************");
            System.out.println(response);
            System.out.println("************ AUTH USER ****************");

        } catch (IOException e) {
            throw new IllegalArgumentException("STF service is unreachable", e);
        }
        
    }

    public boolean connectDevice(String deviceSerial) {
    	//authUser();
    	System.out.println("Start Connect Device");
        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer " + stfService.getAuthToken())
                .url(stfService.getStfUrl() + "devices/" + deviceSerial)
                .build();
        Response response;
        try {
            response = client.newCall(request).execute();
            JsonObject jsonObject = jsonParser.parse(response.body().string()).getAsJsonObject();
            //String myString = jsonObject.toString();
            //System.out.println("************ TEST ****************");
            //System.out.println("Device Serial: " + deviceSerial);
            //System.out.println(myString);
            //System.out.println(response);
            //System.out.println("************  END TEST ****************");
            if (!isDeviceFound(jsonObject)) {
                return false;
            }

            JsonObject deviceObject = jsonObject.getAsJsonObject("device");
            boolean present = deviceObject.get("present").getAsBoolean();
            boolean ready = deviceObject.get("ready").getAsBoolean();
            boolean using = deviceObject.get("using").getAsBoolean();
            JsonElement ownerElement = deviceObject.get("owner");
            boolean owner = !(ownerElement instanceof JsonNull);

            if (!present || !ready || using || owner) {
                LOGGER.severe("Device is in use");
                return false;
            }

            return addDeviceToUser(deviceSerial);
        } catch (IOException e) {
            throw new IllegalArgumentException("STF service is unreachable", e);
        }
    }

    private boolean isDeviceFound(JsonObject jsonObject) {
        if (!jsonObject.get("success").getAsBoolean()) {
            LOGGER.severe("Device not found");
            return false;
        }
        return true;
    }
    
    
    public List<String> getAvalibleDevices() {
    	List<String> deviceList = new ArrayList<String>();
    	JsonArray myJsonList = new JsonArray();

        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer " + stfService.getAuthToken())
                .url(stfService.getStfUrl() + "devices/")
                .build();
        Response response;
        try {
            response = client.newCall(request).execute();
            JsonObject jsonObject = jsonParser.parse(response.body().string()).getAsJsonObject();

            
            myJsonList = jsonObject.getAsJsonArray("devices");
            int myCounter = myJsonList.size();
            System.out.println("LIST SIZE: " + myCounter);
            for ( int i = 0 ; i < myCounter ; i++ ) {
            	//System.out.println("************ TEST ****************");
            	JsonElement oneJsonObject = myJsonList.get(i);
            	//System.out.println("JSON: " + i + ": " + oneJsonObject);
            	//System.out.println("Serial: " + oneJsonObject.getAsJsonObject().get("serial"));
            	//System.out.println("************  END TEST ****************");
            	String mySerial = oneJsonObject.getAsJsonObject().get("serial").toString();
                boolean present = oneJsonObject.getAsJsonObject().get("present").getAsBoolean();
                boolean ready = oneJsonObject.getAsJsonObject().get("ready").getAsBoolean();
                boolean using = oneJsonObject.getAsJsonObject().get("using").getAsBoolean();
                JsonElement ownerElement = oneJsonObject.getAsJsonObject().get("owner");
                boolean owner = !(ownerElement instanceof JsonNull);
                
                //System.out.println("********** Device ************");
                //System.out.println("Serial: " + mySerial);
                //System.out.println("Present: " + present);
                //System.out.println("Ready: " + ready);
                //System.out.println("Using: " + using);
                //System.out.println("Owner: " + owner);
                
                if (!present || !ready || using || owner) {
                    LOGGER.severe("Device is in use");
                    //return false;
                } else {
                	System.out.println("Device Ready for testing: " + mySerial);
                	mySerial = mySerial.trim();
                	deviceList.add(mySerial);
                }
            	
            }
            
            

            
            
            return deviceList;
			//return addDeviceToUser(deviceSerial);
        } catch (IOException e) {
            throw new IllegalArgumentException("STF service is unreachable", e);
        }
        
    }
    

    private boolean addDeviceToUser(String deviceSerial) {
        RequestBody requestBody = RequestBody.create(JSON, "{\"serial\": \"" + deviceSerial + "\"}");
        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer " + stfService.getAuthToken())
                .url(stfService.getStfUrl() + "user/devices")
                .post(requestBody)
                .build();
        Response response;
        try {
            response = client.newCall(request).execute();
            JsonObject jsonObject = jsonParser.parse(response.body().string()).getAsJsonObject();


            if (!isDeviceFound(jsonObject)) {
                return false;
            }

            LOGGER.info("The device <" + deviceSerial + "> is locked successfully");
            return true;
        } catch (IOException e) {
            throw new IllegalArgumentException("STF service is unreachable", e);
        }
    }
    
    public String remoteControl(String deviceSerial) {
    	String remoteResponce;
        RequestBody requestBody = RequestBody.create(JSON, "{\"serial\": \"" + deviceSerial + "\"}");
        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer " + stfService.getAuthToken())
                .url(stfService.getStfUrl() + "user/devices/" + deviceSerial + "/remoteConnect")
                .post(requestBody)
                .build();
        Response response;
        try {
            response = client.newCall(request).execute();
            JsonObject jsonObject = jsonParser.parse(response.body().string()).getAsJsonObject();

            if (!isDeviceFound(jsonObject)) {
                return "Device Not Found";
            }
            
            remoteResponce = jsonObject.toString();

            LOGGER.info("The device <" + deviceSerial + "> is ready for remote");
            return remoteResponce;
        } catch (IOException e) {
            throw new IllegalArgumentException("STF service is unreachable", e);
        }
    }

    public boolean releaseDevice(String deviceSerial) {
        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer " + stfService.getAuthToken())
                .url(stfService.getStfUrl() + "user/devices/" + deviceSerial)
                .delete()
                .build();
        Response response;
        try {
            response = client.newCall(request).execute();
            JsonObject jsonObject = jsonParser.parse(response.body().string()).getAsJsonObject();

            if (!isDeviceFound(jsonObject)) {
                return false;
            }

            LOGGER.info("The device <" + deviceSerial + "> is released successfully");
            return true;
        } catch (IOException e) {
            throw new IllegalArgumentException("STF service is unreachable", e);
        }
    }
}
