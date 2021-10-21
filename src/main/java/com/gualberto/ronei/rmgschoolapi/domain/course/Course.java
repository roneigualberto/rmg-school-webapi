package com.gualberto.ronei.rmgschoolapi.domain.course;

import com.gualberto.ronei.rmgschoolapi.domain.category.Category;
import com.gualberto.ronei.rmgschoolapi.domain.category.SubCategory;
import com.gualberto.ronei.rmgschoolapi.domain.course.section.Section;
import com.gualberto.ronei.rmgschoolapi.domain.course.tag.TagForm;
import com.gualberto.ronei.rmgschoolapi.domain.tag.Tag;
import com.gualberto.ronei.rmgschoolapi.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @ToString.Exclude
    private List<Section> sections = new ArrayList<>();


    @ManyToMany(cascade = CascadeType.ALL)
    @Builder.Default
    @ToString.Exclude
    private List<Tag> tags = new ArrayList<>();


    public Section createSection(String name, Integer order) {

        Section section = Section.builder()
                .course(this)
                .name(name)
                .order(order)
                .build();

        addSection(section);

        return section;
    }

    public void addSection(Section section) {
        section.setCourse(this);
        this.sections.add(section);
    }

    public boolean hasSection(Section section) {
        return this.sections.contains(section);
    }


    public void addTags(List<TagForm> tagForms) {
        tags.addAll(tagForms.stream()
                .map((form) -> Tag.builder().id(form.getId()).name(form.getName()).build())
                .collect(Collectors.toList()));
    }
}
