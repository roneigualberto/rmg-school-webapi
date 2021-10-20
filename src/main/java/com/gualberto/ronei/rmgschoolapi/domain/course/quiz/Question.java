package com.gualberto.ronei.rmgschoolapi.domain.course.quiz;

import com.gualberto.ronei.rmgschoolapi.domain.course.section.Section;
import com.gualberto.ronei.rmgschoolapi.domain.shared.exception.DomainException;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"quiz_id", "question_order"},
        name = "question_order_un"))
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

    @Column(name = "question_order", nullable = false)
    private Integer order;

    @ManyToOne
    @ToString.Exclude
    private Alternative correct;

    @Builder.Default
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Alternative> alternatives = new ArrayList<>();


    public void addAlternative(AlternativeForm form) {

        Alternative alternative = Alternative.builder()
                .question(this)
                .order(form.getOrder())
                .statement(form.getStatement())
                .build();

        this.alternatives.add(alternative);

        if (form.isCorrect()) {
            setCorrect(alternative);
        }
    }

    public void updateAlternatives(List<AlternativeForm> alternativeForms) {

        Map<Long, Alternative> alternativeToRemove = alternatives
                .stream()
                .collect(Collectors.toMap(Alternative::getId, Function.identity()));

        for (AlternativeForm alternativeForm : alternativeForms) {
            if (alternativeForm.getId() == null) {
                addAlternative(alternativeForm);
            } else {
                alternativeToRemove.remove(alternativeForm.getId());
                updateAlternative(alternativeForm);
            }
        }

        alternatives.removeAll(alternativeToRemove.values());
    }

    public void updateAlternative(AlternativeForm form) {

        Alternative alternative = this.alternatives.stream()
                .filter((alt) -> alt.getId().equals(form.getId()))
                .findAny().orElseThrow(() -> new DomainException("Alternative not found"));

        alternative.setStatement(form.getStatement());

        if (form.isCorrect()) {
            setCorrect(alternative);
        }
    }


}
