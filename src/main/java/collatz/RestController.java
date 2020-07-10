package collatz;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.put;

import collatz.handler.DeleteAllHandler;
import collatz.handler.DeleteHandler;
import collatz.handler.GetAllHandler;
import collatz.handler.GetHandler;
import collatz.handler.Handler;
import collatz.handler.PutHandler;
import collatz.service.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;

/**
 * Simple REST API for Collatz calculations. Based on Spark Java.
 * <p>
 * Usage:
 * <p>
 * GET /collatz/:id      - Return the Collatz step number for start term :id if already calculated
 * PUT /collatz/:id      - Calculate async the Collatz step number for start term :id.
 * Return answer if already calculated.
 * DELETE /collatz/:id   - Delete the Collatz step number for start term :id if already calculated
 * <p>
 * Note: Exception handling not implemented
 */
public class RestController {

  public static void main(String[] args) throws Exception {
    start();
  }

  /**
   * Setup the routes - service implementation is specified in properties files and injected
   */
  public static void start() throws Exception {
    System.setProperty("log4j.configurationFile", "log4j2.xml");
    Logger log = LoggerFactory.getLogger(RestController.class);
    log.info("Starting Collatz REST service..");

    // Inject the configured Service Implementation:
    Class serviceImplClass = Class.forName(Util.getProp("service.impl"));
    Service service = (Service) serviceImplClass.getDeclaredConstructor().newInstance();

    Handler putHandler = new PutHandler(service, log);
    Handler getHandler = new GetHandler(service);
    Handler getAllHandler = new GetAllHandler(service);
    Handler deleteHandler = new DeleteHandler(service);
    Handler deleteAllHandler = new DeleteAllHandler(service);

    put("/collatz/:id", putHandler::handleRequest);

    get("/collatz/:id", getHandler::handleRequest);

    get("/collatz", getAllHandler::handleRequest);

    delete("/collatz/:id", deleteHandler::handleRequest);

    delete("/collatz", deleteAllHandler::handleRequest);
  }

  public static void stop() {
    Spark.stop();
  }
}