package com.empserver.mapper;

import com.empserver.model.User;

public interface UserMapper  {
    User selectByEmail(String email);

    Long insertUser(User t);
}
