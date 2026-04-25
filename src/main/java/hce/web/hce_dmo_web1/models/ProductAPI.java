package hce.web.hce_dmo_web1.models;

import java.net.*;
import java.io.*;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class ProductAPI {

    static final String API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im9jYXRlaXh1enVsd21ydHhzZW9tIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NTYyMTYyMTQsImV4cCI6MjA3MTc5MjIxNH0.w7PLqjLGj4hNNGDh81NwDodEUCCqVSVm_PL0FpYWif8";

    static final String URL_API = "https://ocateixuzulwmrtxseom.supabase.co/rest/v1/product1";

    // =========================
    // COMMON
    // =========================
    private static HttpURLConnection createConnection(String method, String urlStr) throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod(method);
        conn.setRequestProperty("apikey", API_KEY);
        conn.setRequestProperty("Authorization", "Bearer " + API_KEY);
        conn.setRequestProperty("Content-Type", "application/json");

        return conn;
    }

    private static String readResponse(HttpURLConnection conn) throws Exception {
        BufferedReader br;

        int code = conn.getResponseCode();

        if (code >= 200 && code < 300) {
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder response = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null) {
            response.append(line);
        }

        br.close();
        return response.toString();
    }

    private static Product mapToProduct(JSONObject obj) {
        Product p = new Product();

        p.id = obj.optLong("id");
        p.title = obj.optString("title");
        p.price = obj.optDouble("price");
        p.description = obj.optString("description");
        p.category = obj.optString("category");
        p.image = obj.optString("image");
        p.rating_rate = obj.optDouble("rating_rate");
        p.rating_count = obj.optInt("rating_count");

        return p;
    }

    // =========================
    // GET ALL
    // =========================
    public static List<Product> getAll()  {

        List<Product> list = new ArrayList<>();
        try{
        HttpURLConnection conn = createConnection("GET", URL_API);

        String result = readResponse(conn);

        JSONArray arr = new JSONArray(result);
        

        for (int i = 0; i < arr.length(); i++) {
            list.add(mapToProduct(arr.getJSONObject(i)));
        }

        }
        catch(Exception ex)
        {

        }

        return list;
    }

    // =========================
    // GET BY ID
    // =========================
    public static Product getById(long id) throws Exception {

        String url = URL_API + "?id=eq." + id;

        HttpURLConnection conn = createConnection("GET", url);

        String result = readResponse(conn);

        JSONArray arr = new JSONArray(result);

        if (arr.length() == 0) return null;

        return mapToProduct(arr.getJSONObject(0));
    }

    // =========================
    // INSERT
    // =========================
    public static boolean insert(Product p) throws Exception {

        HttpURLConnection conn = createConnection("POST", URL_API);
        conn.setDoOutput(true);

        JSONObject json = new JSONObject();
        json.put("title", p.title);
        json.put("price", p.price);
        json.put("description", p.description);
        json.put("category", p.category);
        json.put("image", p.image);
        json.put("rating_rate", p.rating_rate);
        json.put("rating_count", p.rating_count);

        OutputStream os = conn.getOutputStream();
        os.write(json.toString().getBytes());
        os.flush();

        int code = conn.getResponseCode();
        return (code == 201 || code == 200);
    }

    // =========================
    // UPDATE
    // =========================
    public static boolean update(Product p) throws Exception {

        String url = URL_API + "?id=eq." + p.id;

        HttpURLConnection conn = createConnection("PATCH", url);
        conn.setDoOutput(true);

        JSONObject json = new JSONObject();
        json.put("title", p.title);
        json.put("price", p.price);
        json.put("description", p.description);
        json.put("category", p.category);
        json.put("image", p.image);
        json.put("rating_rate", p.rating_rate);
        json.put("rating_count", p.rating_count);

        OutputStream os = conn.getOutputStream();
        os.write(json.toString().getBytes());
        os.flush();

        int code = conn.getResponseCode();
        return (code == 204 || code == 200);
    }

    // =========================
    // DELETE
    // =========================
    public static boolean delete(long id) throws Exception {

        String url = URL_API + "?id=eq." + id;

        HttpURLConnection conn = createConnection("DELETE", url);

        int code = conn.getResponseCode();
        return (code == 204 || code == 200);
    }
}