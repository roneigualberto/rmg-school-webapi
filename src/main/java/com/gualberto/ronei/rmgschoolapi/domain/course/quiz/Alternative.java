package com.gualberto.ronei.rmgschoolapi.domain.course.quiz;


import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"question_id", "alternative_order"},
        name = "alternative_order_un"))
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

    @Column(name = "alternative_order", nullable = false)
    private Integer order;
}
