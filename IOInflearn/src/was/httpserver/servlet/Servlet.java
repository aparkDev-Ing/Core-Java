package was.httpserver.servlet;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;

public interface Servlet {

    public void service(HttpRequest httpRequest, HttpResponse httpResponse);
}
