package cc.catface.module_apis.nano_httpd.engine;


import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.NanoHTTPD.Response.IStatus;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 *
 * @request /libs/nanohttpd-2.3.1-SNAPSHOT.jar
 */
public class NanoHTTPDServer extends NanoHTTPD {

    public NanoHTTPDServer(int port) {
        super(port);
    }

    public enum Status implements IStatus {

        SWITCH_PROTOCOL(101, "Switching Protocols"), NOT_USE_POST(777, "not use post"), EXCEPTION(5000, "NanoHTTPD server exception");

        private final int requestStatus;
        private final String description;

        Status(int requestStatus, String description) {
            this.requestStatus = requestStatus;
            this.description = description;
        }

        @Override public String getDescription() {
            return null;
        }

        @Override public int getRequestStatus() {
            return 0;
        }
    }


    @Override public Response serve(IHTTPSession session) {
        Response response;

        try {
            if (!Method.POST.equals(session.getMethod()))
                response = newFixedLengthResponse(Status.NOT_USE_POST, MIME_HTML, "please use POST!");
            else {
                Map<String, String> header = session.getHeaders();  // header
                Map<String, String> files = new HashMap<>();
                session.parseBody(files);   // body
                String body = session.getQueryParameterString();

                String clientIp = header.get("http-client-ip");

                System.out.println("catface_debug -> NanoHTTPD client request's msg:\r\n" + "header is: " + header + "\r\nbody is: " + body + "\r\nclient ip is: " + clientIp);

                response = newFixedLengthResponse(NanoHTTPD.Response.Status.OK, MIME_HTML, "恭喜你请求到我了...:");
            }

        } catch (IOException | ResponseException e) {
            response = newFixedLengthResponse(Status.EXCEPTION, MIME_HTML, "NanoHTTPD server exception: " + e.toString());
        }

        return response;
    }
}