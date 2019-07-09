package com.nplatform.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shenhui.ysh on 2017/6/17 0017.
 */
public class HttpRequestUtils {
  private HttpRequestUtils() {
  }

  public static String GET  = "GET";
  public static String POST = "POST";

  /**
   * 发送一个get请求
   *
   * @param url 请求URL
   * @return response
   * @throws IOException
   */
  public static String get(String url) throws IOException {
    return get(url, null);
  }

  /**
   * 发送一个get请求
   *
   * @param url 请求URL
   * @param headers http的头，非必填
   * @return response
   * @throws IOException
   */
  static public String get(String url, Map<String, String> headers) throws IOException {
    return fetch(GET, url, null, headers);
  }

  /**
   * 发送一个post请求
   *
   * @param url 请求URL
   * @param body 非必填
   * @param headers http headers，非必填
   * @return response
   * @throws IOException
   */
  static public String post(String url, String body, Map<String, String> headers) throws IOException {
    return fetch(POST, url, body, headers);
  }

  /**
   * 发送一个post请求
   *
   * @param url 请求URL
   * @param body 非必填
   * @return response
   * @throws IOException
   */
  static public String post(String url, String body) throws IOException {
    return post(url, body, null);
  }

  /**
   * 发送一个post表单请求
   *
   * @param url 请求URL
   * @param params map with parameters/values，非必填
   * @return response
   * @throws IOException
   */
  static public String postForm(String url, Map<String, String> params) throws IOException {
    return postForm(url, params, null);
  }

  /**
   * 发送一个post表单请求
   *
   * @param url 请求URL
   * @param params Map with parameters/values，非必填
   * @param headers Optional map with headers，非必填
   * @return response
   * @throws IOException
   */
  static public String postForm(String url, Map<String, String> params, Map<String, String> headers)
      throws IOException {
    // set content type
    if (headers == null) {
      headers = new HashMap<String, String>();
    }
    headers.put("Content-Type", "application/x-www-form-urlencoded");

    if (params == null) {
      return post(url, null, headers);
    }

    // parse parameters
    StringBuilder body = new StringBuilder("");
    if (params != null) {
      boolean first = true;
      for (String param : params.keySet()) {
        if (first) {
          first = false;
        } else {
          body.append("&");
        }
        String value = params.get(param);
        body.append(URLEncoder.encode(param, "UTF-8")).append("=").append(URLEncoder.encode(value, "UTF-8"));
      }
    }

    return post(url, body.toString(), headers);
  }

  /**
   * Send a put request
   *
   * @param url Url as string
   * @param body Request body as string
   * @param headers Optional map with headers
   * @return response Response as string
   * @throws IOException
   */
  public static String put(String url, String body, Map<String, String> headers) throws IOException {
    return fetch("PUT", url, body, headers);
  }

  /**
   * Send a put request
   *
   * @param url Url as string
   * @return response Response as string
   * @throws IOException
   */
  public static String put(String url, String body) throws IOException {
    return put(url, body, null);
  }

  /**
   * Send a delete request
   *
   * @param url Url as string
   * @param headers Optional map with headers
   * @return response Response as string
   * @throws IOException
   */
  public static String delete(String url, Map<String, String> headers) throws IOException {
    return fetch("DELETE", url, null, headers);
  }

  /**
   * Send a delete request
   *
   * @param url Url as string
   * @return response Response as string
   * @throws IOException
   */
  public static String delete(String url) throws IOException {
    return delete(url, null);
  }

  /**
   * Append query parameters to given url
   *
   * @param url Url as string
   * @param params Map with query parameters
   * @return url Url with query parameters appended
   * @throws IOException
   */
  public static String appendQueryParams(String url, Map<String, String> params) throws IOException {
    String fullUrl = url;
    if (params != null) {
      boolean first = (fullUrl.indexOf('?') == -1);
      for (String param : params.keySet()) {
        if (first) {
          fullUrl += '?';
          first = false;
        } else {
          fullUrl += '&';
        }
        String value = params.get(param);
        fullUrl += URLEncoder.encode(param, "UTF-8") + '=';
        fullUrl += URLEncoder.encode(value, "UTF-8");
      }
    }

    return fullUrl;
  }

  /**
   * Retrieve the query parameters from given url
   *
   * @param url Url containing query parameters
   * @return params Map with query parameters
   * @throws IOException
   */
  public static Map<String, String> getQueryParams(String url) throws IOException {
    Map<String, String> params = new HashMap<String, String>();

    int start = url.indexOf('?');
    while (start != -1) {
      // read parameter name
      int equals = url.indexOf('=', start);
      String param = "";
      if (equals != -1) {
        param = url.substring(start + 1, equals);
      } else {
        param = url.substring(start + 1);
      }

      // read parameter value
      String value = "";
      if (equals != -1) {
        start = url.indexOf('&', equals);
        if (start != -1) {
          value = url.substring(equals + 1, start);
        } else {
          value = url.substring(equals + 1);
        }
      }

      params.put(URLDecoder.decode(param, "UTF-8"), URLDecoder.decode(value, "UTF-8"));
    }

    return params;
  }

  /**
   * Returns the url without query parameters
   *
   * @param url Url containing query parameters
   * @return url Url without query parameters
   * @throws IOException
   */
  public static String removeQueryParams(String url) throws IOException {
    int q = url.indexOf('?');
    if (q != -1) {
      return url.substring(0, q);
    } else {
      return url;
    }
  }

  /**
   * 发送请求
   *
   * @param method HTTP method, 如 "GET"，"POST"
   * @param url 请求RUL
   * @param body Request body，非必填
   * @param headers http headers，非必填
   * @return response
   * @throws IOException
   */
  private static String fetch(String method, String url, String body, Map<String, String> headers)
      throws IOException {
    // connection
    URL u = new URL(url);
    HttpURLConnection conn = (HttpURLConnection) u.openConnection();
    conn.setConnectTimeout(10000);
    conn.setReadTimeout(10000);

    // method
    if (method != null) {
      conn.setRequestMethod(method);
    }

    // headers
    if (headers != null) {
      for (String key : headers.keySet()) {
        conn.addRequestProperty(key, headers.get(key));
      }
    }

    // body
    if (body != null) {
      conn.setDoOutput(true);
      OutputStream os = conn.getOutputStream();
      os.write(body.getBytes());
      os.flush();
      os.close();
    }

    // response
    InputStream is = conn.getInputStream();
    String response = streamToString(is);
    is.close();

    // handle redirects
    if (conn.getResponseCode() == 301) {
      String location = conn.getHeaderField("Location");
      return fetch(method, location, body, headers);
    }

    return response;
  }

  /**
   * Read an input stream into a string
   *
   * @param in
   * @return
   * @throws IOException
   */
  private static String streamToString(InputStream in) throws IOException {
    StringBuffer out = new StringBuffer();
    byte[] b = new byte[4096];
    for (int n; (n = in.read(b)) != -1;) {
      out.append(new String(b, 0, n));
    }
    return out.toString();
  }

}
