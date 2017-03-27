/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package key.access.manager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;
import java.net.HttpURLConnection;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Arosha D
 */
public class HttpHandler {

    public String connect(String url, ArrayList data) throws IOException {
        try {
            // open a connection to the site
            URL connectionUrl = new URL(url);
            URLConnection con = connectionUrl.openConnection();
            // activate the output
            con.setDoOutput(true);
            PrintStream ps = new PrintStream(con.getOutputStream());
            // send your parameters to your site
            for (int i = 0; i < data.size(); i++) {
                ps.print(data.get(i));
                //System.out.println(data.get(i));
                if (i != data.size() - 1) {
                    ps.print("&");
                }
            }

            // we have to get the input stream in order to actually send the request
            InputStream inStream = con.getInputStream();
            Scanner s = new Scanner(inStream).useDelimiter("\\A");
            String result = s.hasNext() ? s.next() : "";
            System.out.println(result);

            // close the print stream
            ps.close();
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "error";
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

    public boolean sendImage(String url, String employeeId, File imageFile) throws IOException {
        String userHome = System.getProperty("user.home");
        HttpClient httpClient = new DefaultHttpClient();
        httpClient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
        HttpPost httpPost = new HttpPost(url);
        FileBody fileBody = new FileBody(imageFile);
        MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
        reqEntity.addPart("fileToUpload", fileBody);
        reqEntity.addPart("employee_id", new StringBody(employeeId));

        httpPost.setEntity(reqEntity);

        // execute HTTP post request
        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity resEntity = response.getEntity();

        if (resEntity != null) {
            String responseStr = EntityUtils.toString(resEntity).trim();
            System.out.println(responseStr);
            return true;
        } else {
            return false;
        }
    }
}
