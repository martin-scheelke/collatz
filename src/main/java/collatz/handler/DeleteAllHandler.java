package collatz.handler;

import collatz.service.Service;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;

/**
 * Handle a DELETE (All) request with Service implementation injected.
 */
public class DeleteAllHandler implements Handler {

  Service service;

  public DeleteAllHandler(Service service) {
    this.service = service;
  }

  @Override
  public String handleRequest(Request request, Response response) {
    service.deleteAllCollatz();
    response.status(HttpStatus.OK_200);
    return "";
  }
}
