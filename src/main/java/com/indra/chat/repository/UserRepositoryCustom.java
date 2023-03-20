package com.indra.chat.repository;

import com.indra.chat.entity.User;

public interface UserRepositoryCustom {
    void detach(User user);
}
