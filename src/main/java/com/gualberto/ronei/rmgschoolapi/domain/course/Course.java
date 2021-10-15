package com.gualberto.ronei.rmgschoolapi.domain.course;

import com.gualberto.ronei.rmgschoolapi.domain.category.Category;
import com.gualberto.ronei.rmgschoolapi.domain.category.SubCategory;
import com.gualberto.ronei.rmgschoolapi.domain.course.section.Section;
import com.gualberto.ronei.rmgschoolapi.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String title;

    @Column(length = 10000)
    private String about;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User instructor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SkillLevelEnum skillLevel;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(nullable = false)
    private SubCategory subCategory;

    @Column
    private Boolean publishDate;

    @Column
    private Boolean published;

    @Column(nullable = false)
    private Double price;

    @OneToMany(mappedBy = "course")
    @Builder.Default
    private List<Section> sections = new ArrayList<>();
}
