package cn.xyh.tree.util.toolImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class GetUrlUtil {
	
	/**
	 * ��ȡurlҳ�������json����
	 * @param url url��ַ
	 * @return json
	 */
	public String loadJson(String url) {

        StringBuilder json = new StringBuilder();
        try {
        	//���ӵ�URL
            URL urlObject = new URL(url);
            URLConnection uc = urlObject.openConnection();
            
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String inputLine = null;
            while ( (inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        }
        return json.toString();
}
}
