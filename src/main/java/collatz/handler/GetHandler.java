package collatz.handler;

import java.math.BigInteger;
import java.util.Optional;
import collatz.service.Service;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;

/**
 * Handle a GET request with Service implementation injected.
 */
public class GetHandler implements Handler {

  Service service;

  public GetHandler(Service service) {
    this.service = service;
  }

  @Override
  public String handleRequest(Request request, Response response) {
    BigInteger id = new BigInteger(request.params(":id"));
    Optional<BigInteger> collatzResult = service.getCollatz(id);

    if (collatzResult.isPresent()) {
      response.status(HttpStatus.OK_200);
      return collatzResult.get().toString();
    } else {
      response.status(HttpStatus.NOT_FOUND_404);
      return COLLATZ_NOT_FOUND + id.toString();
    }
  }
}
