package org.iclass.board.dao;

import org.apache.ibatis.annotations.Mapper;
import org.iclass.board.dto.UserDetailsDTO;

import java.util.Map;

@Mapper
public interface UserMapper {
    void save(UserDetailsDTO user);
    UserDetailsDTO findByUsername(String username);

    UserDetailsDTO findByUsernameAndPassword(Map<String, Object> params);
}
