package com.gualberto.ronei.rmgschoolapi.domain.course.quiz;


import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Alternative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    @ToString.Exclude
    private Question question;

    @Column(nullable = false, length = 5000)
    private String statement;
}
