package com.gualberto.ronei.rmgschoolapi.domain.tag;

import java.util.Optional;

public interface TagService {

    Optional<Tag> findById(Long id);
}
