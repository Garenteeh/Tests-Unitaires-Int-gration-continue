package com.test.exo_spring;

import com.test.exo_spring.objet.TodoItem;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestConfig.class)
public class ControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetTodos() {
        ResponseEntity<TodoItem[]> response = restTemplate.getForEntity("/todos", TodoItem[].class);
        for (TodoItem todo : response.getBody()) {
            System.out.println(todo.getDescription() + " - " + todo.getCreationDate() + " - " + todo.getDescription());
        }
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().length).isGreaterThan(0);
    }
}
