package com.nerdyninja.college.bp.fe;

import java.io.IOException;

import lombok.extern.apachecommons.CommonsLog;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import org.springframework.stereotype.Component;

@CommonsLog
@Component
public class BPBERequest {

    OkHttpClient client;

    String beHost;
    String bePort;

    void init()  {
        client = new OkHttpClient();
    }

    void init(String bhost, String bport)  {
        beHost = bhost;
        bePort = bport;

        log.debug("\nBE Host:\t"+beHost+"\nBE Port:\t"+bePort+"\n");
        client = new OkHttpClient();
    }


    String runHttpQuery(String data) throws IOException{
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), data);

        String myUrl =  String.format("http://%s:%s/api", beHost, bePort);

        log.info("\nBackend URL: " + myUrl + "\n");
        Request request = new Request.Builder()
                .url(myUrl)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        String info = response.body().string();
        log.info("\nResponse from BE:\n" + info + "\n");
        return info;
    }
}
