package com.codesoom.assignment.controllers;

import com.codesoom.assignment.TaskNotFoundException;
import com.codesoom.assignment.application.TaskService;
import com.codesoom.assignment.models.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
public class TaskControllerWebTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Test
    void list() throws Exception {
        this.mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk());
    }

    @Test
    void detailWithValidId() {
        Task task = new Task();
        given(taskService.getTask(1L)).willReturn(task);
    }

    @Test
    void detailWithInvalidId() {
        given(taskService.getTask(100L)).willThrow(new TaskNotFoundException(100L));
    }
}
