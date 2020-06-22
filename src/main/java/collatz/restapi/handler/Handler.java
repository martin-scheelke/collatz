package collatz.restapi.handler;

public interface Handler {

  String COLLATZ_NOT_FOUND = "Collatz series steps not found for start term: ";
  String INPUT_OUT_OF_RANGE = "Start term should be greater than 1";
  String CALCULATING_ANSWER = "we're calculating the answer, please come back in an\n" +
    "unpredictable time...";

  String handleRequest(spark.Request request, spark.Response response);
}
