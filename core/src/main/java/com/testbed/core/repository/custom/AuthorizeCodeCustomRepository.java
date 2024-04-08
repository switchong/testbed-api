package com.testbed.core.repository.custom;

import com.testbed.core.dto.AuthorizeCodeDto;

public interface AuthorizeCodeCustomRepository {
    AuthorizeCodeDto findByState(String findState);
}
