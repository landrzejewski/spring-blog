package pl.training.blog.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class Tag {

    private UUID id;
    private String name;

}
