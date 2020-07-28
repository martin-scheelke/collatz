package collatz.restapi;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.put;

import collatz.Util;
import collatz.data.CollatzDAO;
import collatz.handler.DeleteAllHandler;
import collatz.handler.DeleteHandler;
import collatz.handler.GetAllHandler;
import collatz.handler.GetHandler;
import collatz.handler.Handler;
import collatz.handler.PutHandler;
import collatz.service.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;

/**
 * Simple REST API for Collatz calculations. Based on Spark Java.
 * <p>
 * Usage:
 * <p>
 * GET /collatz/:id      - Return the Collatz step number for start term :id if already calculated
 * PUT /collatz/:id      - Calculate async the Collatz step number for start term :id. Return answer
 * if already calculated. DELETE /collatz/:id   - Delete the Collatz step number for start term :id
 * if already calculated
 * <p>
 * Note: Exception handling not implemented
 */
public class RestController {

  public static void main(String[] args) throws Exception {
    start();
  }

  /**
   * Setup the routes - DAO implementation is specified in properties files and injected
   */
  public static void start() throws Exception {
    System.setProperty("log4j.configurationFile", "log4j2.xml");

    var log = LoggerFactory.getLogger(RestController.class);
    log.info("Starting Collatz REST service..");

    // Inject the configured DAO Implementation:
    Class daoImplClass = Class.forName(Util.getProp("dao.impl"));
    var collatzDAO = (CollatzDAO) daoImplClass.getDeclaredConstructor().newInstance();
    var service = new ServiceImpl(collatzDAO);

    var putHandler = new PutHandler(service);
    var getHandler = new GetHandler(service);
    var getAllHandler = new GetAllHandler(service);
    var deleteHandler = new DeleteHandler(service);
    var deleteAllHandler = new DeleteAllHandler(service);

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