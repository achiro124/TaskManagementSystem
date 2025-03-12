package com.example.TaskManagementSystem.dtos;

import com.example.TaskManagementSystem.entity.CommentEntity;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CommentDto {

    private Long id;
    private String text;
    private LocalDateTime date;
    private Long todoId;
    private Long authorId;
    private String authorEmail;

    public static CommentDto toModel(CommentEntity commentEntity){

        CommentDto commentDto = new CommentDto();
        commentDto.setId(commentEntity.getId());
        commentDto.setText(commentEntity.getText());
        commentDto.setDate(commentEntity.getCreatedAt());
        commentDto.setAuthorId(commentEntity.getAuthor().getId());
        commentDto.setAuthorEmail(commentEntity.getAuthor().getEmail());
        commentDto.setTodoId(commentEntity.getTodo().getId());

        return commentDto;
    }

}
