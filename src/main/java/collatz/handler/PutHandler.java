package collatz.handler;

import java.math.BigInteger;
import java.util.Optional;
import collatz.service.Service;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import spark.Request;
import spark.Response;

/**
 * Handle a PUT request with Service and Logger implementations injected.
 */
public class PutHandler implements Handler {

  Service service;
  Logger log;

  public PutHandler(Service service, Logger log) {
    this.service = service;
    this.log = log;
  }

  @Override
  public String handleRequest(Request request, Response response) {
    BigInteger id = new BigInteger(request.params(":id"));
    if (id.compareTo(new BigInteger("1")) < 0) {
      response.status(HttpStatus.BAD_REQUEST_400);
      return INPUT_OUT_OF_RANGE;
    }
    Optional<BigInteger> collatzResult = service.calcCollatzAsync(id);

    if (collatzResult.isPresent()) {
      response.status(HttpStatus.OK_200);
      return collatzResult.get().toString();
    } else {
      log.info("Calculating Collatz for start term: " + id.toString());
      response.status(HttpStatus.ACCEPTED_202);
      return CALCULATING_ANSWER;
    }
  }
}