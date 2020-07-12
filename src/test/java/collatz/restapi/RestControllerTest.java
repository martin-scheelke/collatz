package collatz.restapi;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.put;
import static org.hamcrest.Matchers.containsString;

import collatz.RestController;
import collatz.handler.Handler;
import io.restassured.RestAssured;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

//import static collatz.db.Tables.COLLATZ;

/**
 * Integration tests for the REST controller
 */
public class RestControllerTest {

  private static final int COLLATZ_WAIT_MS = 500;

  /**
   * Ensure server is started
   */
  @BeforeAll
  public static void setup() throws Exception {
    RestController.start();
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = 4567;
    Thread.sleep(3000);
//    new DBJooqWrapper().execute(tx -> {
//      tx.deleteFrom(COLLATZ)
    //       .execute();
    //     return null;
    //   });
  }

  @AfterAll
  public static void shutdown() {
    RestController.stop();
  }

  @Test
  public void testInputOutOfRange() {
    put("/collatz/-1")
        .then()
        .statusCode(HttpStatus.BAD_REQUEST_400)
        .assertThat()
        .body(containsString(Handler.INPUT_OUT_OF_RANGE));
  }

  @Test
  public void testPutNewCollatzCalculation() throws InterruptedException {
    put("/collatz/9")
        .then()
        .statusCode(HttpStatus.ACCEPTED_202)
        .assertThat()
        .body(containsString(Handler.CALCULATING_ANSWER));

    Thread.sleep(500);

    put("/collatz/9")
        .then()
        .statusCode(HttpStatus.OK_200)
        .assertThat()
        .body(containsString("19"));
  }

  @Test
  public void testGetCollatzCalculation() throws InterruptedException {
    put("/collatz/32343423141982374912837491823749")
        .then()
        .statusCode(HttpStatus.ACCEPTED_202)
        .assertThat()
        .body(containsString(Handler.CALCULATING_ANSWER));

    Thread.sleep(500);

    get("/collatz/32343423141982374912837491823749")
        .then()
        .statusCode(HttpStatus.OK_200)
        .assertThat()
        .body(containsString("547"));
  }

  @Test
  public void testDeleteAllCollatzCalculation() throws InterruptedException {
    put("/collatz/97")
        .then()
        .statusCode(HttpStatus.ACCEPTED_202)
        .assertThat()
        .body(containsString(Handler.CALCULATING_ANSWER));

    Object o = new Object();

    Thread.sleep(1000);

    get("/collatz/97")
        .then()
        .statusCode(HttpStatus.OK_200)
        .assertThat()
        .body(containsString("118"));

    delete("/collatz")
        .then()
        .statusCode(HttpStatus.OK_200);

    get("/collatz/97")
        .then()
        .statusCode(HttpStatus.NOT_FOUND_404)
        .assertThat()
        .body(containsString(Handler.COLLATZ_NOT_FOUND + 97));
  }

  @Test
  public void testDeleteNewCollatzCalculation() throws InterruptedException {
    put("/collatz/2")
        .then()
        .statusCode(HttpStatus.ACCEPTED_202)
        .assertThat()
        .body(containsString(Handler.CALCULATING_ANSWER));

    Thread.sleep(500);

    get("/collatz/2")
        .then()
        .statusCode(HttpStatus.OK_200)
        .assertThat()
        .body(containsString("1"));

    delete("/collatz/2")
        .then()
        .statusCode(HttpStatus.OK_200);

    get("/collatz/2")
        .then()
        .statusCode(HttpStatus.NOT_FOUND_404)
        .assertThat()
        .body(containsString(Handler.COLLATZ_NOT_FOUND + 2));
  }
}