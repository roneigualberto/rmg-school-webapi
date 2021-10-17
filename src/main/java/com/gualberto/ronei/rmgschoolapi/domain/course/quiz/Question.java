package com.gualberto.ronei.rmgschoolapi.domain.course.quiz;

import com.gualberto.ronei.rmgschoolapi.domain.course.section.Section;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    @ToString.Exclude
    private Quiz quiz;

    @Column(nullable = false, length = 5000)
    private String statement;

    @ManyToOne
    @JoinColumn(nullable = false)
    @ToString.Exclude
    private Alternative correct;

    @Builder.Default
    @OneToMany(mappedBy = "question", cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private List<Alternative> alternatives = new ArrayList<>();


    public void addAlternative(AlternativeForm form) {

        Alternative alternative = Alternative.builder()
                .question(this)
                .statement(form.getStatement())
                .build();

        this.alternatives.add(alternative);

        if (form.isCorrect()) {
            setCorrect(alternative);
        }
    }


}
