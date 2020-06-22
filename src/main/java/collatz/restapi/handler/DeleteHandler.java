package collatz.restapi.handler;

import java.math.BigInteger;
import collatz.restapi.service.Service;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;

/**
 * Handle a DELETE request with Service implementation injected.
 */
public class DeleteHandler implements Handler {

  Service service;

  public DeleteHandler(Service service) {
    this.service = service;
  }

  @Override
  public String handleRequest(Request request, Response response) {
    BigInteger id = new BigInteger(request.params(":id"));

    if (service.deleteCollatz(id)) {
      response.status(HttpStatus.OK_200);
      return "";
    } else {
      response.status(HttpStatus.NOT_FOUND_404);
      return COLLATZ_NOT_FOUND + id;
    }
  }
}
