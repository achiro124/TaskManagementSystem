package com.example.TaskManagementSystem.dtos;

import com.example.TaskManagementSystem.entity.CommentEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Schema(description = "DTO for representing a comment")
public class CommentDto {

    @Schema(description = "Unique identifier of the comment", example = "1")
    private Long id;

    @Schema(description = "Text of the comment", example = "This is an example comment")
    private String text;

    @Schema(description = "Date and time of comment creation", example = "2025-03-11T21:22:23")
    private LocalDateTime date;

    @Schema(description = "Identifier of the task to which the comment belongs", example = "123")
    private Long todoId;

    @Schema(description = "Identifier of the comment author", example = "456")
    private Long authorId;

    @Schema(description = "Email of the comment author", example = "user@mail.ru")
    private String authorEmail;

    public static CommentDto toModel(CommentEntity commentEntity) {
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
