package collatz.restapi;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.put;

import collatz.Util;
import collatz.restapi.handler.DeleteAllHandler;
import collatz.restapi.handler.DeleteHandler;
import collatz.restapi.handler.GetAllHandler;
import collatz.restapi.handler.GetHandler;
import collatz.restapi.handler.Handler;
import collatz.restapi.handler.PutHandler;
import collatz.restapi.service.Service;
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