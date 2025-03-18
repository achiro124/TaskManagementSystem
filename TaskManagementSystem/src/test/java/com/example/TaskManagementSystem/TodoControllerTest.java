package com.example.TaskManagementSystem;

import com.example.TaskManagementSystem.controller.TodoController;
import com.example.TaskManagementSystem.dtos.CommentDto;
import com.example.TaskManagementSystem.dtos.TodoRequestDto;
import com.example.TaskManagementSystem.dtos.TodoResponseDto;
import com.example.TaskManagementSystem.entity.CommentEntity;
import com.example.TaskManagementSystem.service.CommentService;
import com.example.TaskManagementSystem.service.TodoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class TodoControllerTest {

    @Mock
    private TodoService todoService;

    @Mock
    private CommentService commentService;

    @InjectMocks
    private TodoController todoController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(todoController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void createTodo_ShouldReturnCreatedTodo() throws Exception {

        TodoRequestDto requestDto = new TodoRequestDto();
        requestDto.setTitle("Test Task");
        requestDto.setDescription("Test Description");
        requestDto.setStatusId(1);
        requestDto.setPriorityId(1);
        requestDto.setAuthorId(1L);
        requestDto.setAssigneeId(2L);

        TodoResponseDto responseDto = new TodoResponseDto();
        responseDto.setId(1L);
        responseDto.setTitle("Test Task");
        responseDto.setDescription("Test Description");

        when(todoService.createTodo(any(TodoRequestDto.class))).thenReturn(responseDto);

        mockMvc.perform(post("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Test Task"))
                .andExpect(jsonPath("$.description").value("Test Description"));
    }

    @Test
    void updateTodo_ShouldReturnUpdatedTodo() throws Exception {

        TodoRequestDto requestDto = new TodoRequestDto();
        requestDto.setTitle("Updated Task");
        requestDto.setDescription("Updated Description");

        TodoResponseDto responseDto = new TodoResponseDto();
        responseDto.setId(1L);
        responseDto.setTitle("Updated Task");
        responseDto.setDescription("Updated Description");

        when(todoService.updateTodo(anyLong(), any(TodoRequestDto.class))).thenReturn(responseDto);

        mockMvc.perform(put("/todos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Updated Task"))
                .andExpect(jsonPath("$.description").value("Updated Description"));
    }

    @Test
    void getTodo_ShouldReturnTodo() throws Exception {

        TodoResponseDto responseDto = new TodoResponseDto();
        responseDto.setId(1L);
        responseDto.setTitle("Test Task");
        responseDto.setDescription("Test Description");

        when(todoService.getTodo(anyLong())).thenReturn(responseDto);

        mockMvc.perform(get("/todos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Test Task"))
                .andExpect(jsonPath("$.description").value("Test Description"));
    }

    @Test
    void getTodosByAuthor_ShouldReturnListOfTodos() throws Exception {

        TodoResponseDto responseDto = new TodoResponseDto();
        responseDto.setId(1L);
        responseDto.setTitle("Test Task");
        responseDto.setDescription("Test Description");

        List<TodoResponseDto> todos = Collections.singletonList(responseDto);

        when(todoService.getTodosByAuthor(anyLong())).thenReturn(todos);

        mockMvc.perform(get("/todos/author/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].title").value("Test Task"))
                .andExpect(jsonPath("$[0].description").value("Test Description"));
    }

    @Test
    void getTodosByAssigneeId_ShouldReturnListOfTodos() throws Exception {

        TodoResponseDto responseDto = new TodoResponseDto();
        responseDto.setId(1L);
        responseDto.setTitle("Test Task");
        responseDto.setDescription("Test Description");

        List<TodoResponseDto> todos = Collections.singletonList(responseDto);

        when(todoService.getTodosByAssignee(anyLong())).thenReturn(todos);

        mockMvc.perform(get("/todos/assignee/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].title").value("Test Task"))
                .andExpect(jsonPath("$[0].description").value("Test Description"));
    }

    @Test
    void deleteTodo_ShouldReturnSuccessMessage() throws Exception {

        mockMvc.perform(delete("/todos/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Task deleted"));
    }

    @Test
    void addComment_ShouldReturnAddedComment() throws Exception {

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setText("Test Comment");

        CommentDto commentDto = new CommentDto();
        commentDto.setId(1L);
        commentDto.setText("Test Comment");

        when(commentService.addComment(anyLong(), any(CommentEntity.class))).thenReturn(commentDto);

        mockMvc.perform(post("/todos/1/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentEntity)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.text").value("Test Comment"));
    }

    @Test
    void changeStatus_ShouldReturnUpdatedTodo() throws Exception {

        TodoResponseDto responseDto = new TodoResponseDto();
        responseDto.setId(1L);
        responseDto.setTitle("Test Task");
        responseDto.setDescription("Test Description");
        responseDto.setStatus("In Progress");

        when(todoService.changeStatus(anyLong(), anyInt())).thenReturn(responseDto);

        mockMvc.perform(post("/todos/1/status")
                        .param("statusId", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Test Task"))
                .andExpect(jsonPath("$.description").value("Test Description"))
                .andExpect(jsonPath("$.status").value("In Progress"));
    }
}