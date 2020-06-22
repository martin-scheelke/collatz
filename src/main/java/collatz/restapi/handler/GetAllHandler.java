package collatz.restapi.handler;

import com.google.gson.Gson;
import collatz.restapi.service.Service;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;

/**
 * Handle a GET request with Service implementation injected.
 */
public class GetAllHandler implements Handler {

  Service service;

  public GetAllHandler(Service service) {
    this.service = service;
  }

  @Override
  public String handleRequest(Request request, Response response) {
    response.status(HttpStatus.OK_200);
    return (new Gson().toJson(service.getAllCollatz()));
  }
}
