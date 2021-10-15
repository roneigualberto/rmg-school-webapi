package com.gualberto.ronei.rmgschoolapi.domain.course;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SkillLevelEnum {

    ALL(SkillLevelEnum.ALL_DESCRIPTION),
    BEGINNER(SkillLevelEnum.BEGINNER_DESCRIPTION),
    INTERMEDIATE(SkillLevelEnum.INTERMEDIATE_DESCRIPTION),
    EXPERT(SkillLevelEnum.EXPERT_DESCRIPTION);


    private static final String ALL_DESCRIPTION = "All Levels";
    private static final String BEGINNER_DESCRIPTION = "Beginner";
    private static final String INTERMEDIATE_DESCRIPTION = "Intermediate";
    private static final String EXPERT_DESCRIPTION = "Expert";

    private final String description;

}
