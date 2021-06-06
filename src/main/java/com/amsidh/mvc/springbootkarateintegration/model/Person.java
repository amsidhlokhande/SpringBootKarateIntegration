package com.amsidh.mvc.springbootkarateintegration.model;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Person implements Serializable {
    private Integer id;
    private String name;
    private String email;
}
