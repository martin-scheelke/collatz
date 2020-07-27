package collatz.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import collatz.data.CollatzDAOImpl;
import collatz.service.Service;
import collatz.service.ServiceImpl;
import java.math.BigInteger;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.RequestResponseFactory;
import spark.Response;

public class DeleteHandlerTest {

  @Test
  void testHandleDeleteRequest() throws InterruptedException {

    Service service = new ServiceImpl(new CollatzDAOImpl());
    service.calcCollatzAsync(new BigInteger("3"));

    Thread.sleep(500);

    var deleteHandler = new DeleteHandler(service);

    Request mockRequest = mock(Request.class);
    when(mockRequest.params(":id")).thenReturn("3");

    HttpServletResponse mockHttpServletResponse = mock(HttpServletResponse.class);

    Response response = RequestResponseFactory.create(mockHttpServletResponse);

    assertEquals("", deleteHandler
        .handleRequest(mockRequest, response));

    verify(mockHttpServletResponse).setStatus(eq(HttpStatus.OK_200));
  }

  @Test
  void testHandleDeleteRequestTermNotFound() {

    var deleteHandler = new DeleteHandler(new ServiceImpl(new CollatzDAOImpl()));

    Request mockRequest = mock(Request.class);
    when(mockRequest.params(":id")).thenReturn("10");

    HttpServletResponse mockHttpServletResponse = mock(HttpServletResponse.class);

    Response response = RequestResponseFactory.create(mockHttpServletResponse);

    assertEquals("Collatz series steps not found for start term: 10", deleteHandler
        .handleRequest(mockRequest, response));

    verify(mockHttpServletResponse).setStatus(eq(HttpStatus.NOT_FOUND_404));
  }
}
