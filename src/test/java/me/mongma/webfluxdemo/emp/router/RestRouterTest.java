package me.mongma.webfluxdemo.emp.router;

import lombok.extern.slf4j.Slf4j;
import me.mongma.webfluxdemo.emp.handler.EmployeeHandler;
import me.mongma.webfluxdemo.emp.model.Employee;
import me.mongma.webfluxdemo.emp.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.times;

@Slf4j
@ExtendWith(SpringExtension.class)
@WebFluxTest
@Import({EmployeeRouter.class, EmployeeHandler.class})
public class RestRouterTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private EmployeeRepository empRepo;

    @Test
    public void testEmployee() {
        final Employee emp = Employee.builder()
                                    .seq(5)
                                    .name("mongma5")
                                    .salary(121212)
                                    .department("dev1")
                                    .build();

        Mockito.when(empRepo.findById(5L)).thenReturn(Mono.just(emp));

        webTestClient.get().uri("/apis/v1/employee/{id}", 5)
                        .exchange()
                        .expectStatus().isOk()
                        .expectBody(Employee.class);

        Mockito.verify(empRepo, times(1)).findById(5L);
    }
}
