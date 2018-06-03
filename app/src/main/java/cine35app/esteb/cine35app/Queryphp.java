package cine35app.esteb.cine35app;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by sergiesalas on 6/3/2018.
 */

public class Queryphp {

    private OkHttpClient client = new OkHttpClient();
    private String phpQuery;

    public Queryphp(String query){
        phpQuery="http://192.168.110.1/"+query;
    }

    public Response returnRequest() throws IOException {
         Request request= new Request.Builder().url(phpQuery).build();
        try {
            return client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setQuery(String query){
        phpQuery="http://192.168.110.1/"+query;
    }

}
