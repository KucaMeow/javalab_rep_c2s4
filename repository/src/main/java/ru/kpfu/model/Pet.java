package ru.kpfu.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
/**
 * Сущность - домашнее животное, у которого может быть много игрушек
 */
public class Pet {
    private Long id;
    private String name;
    private List<Toy> toys;
}
