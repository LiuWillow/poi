package com.lwl.bishe.dao.mapper;

import com.lwl.bishe.bean.Key;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * date  2018/3/17
 * author liuwillow
 **/
@Repository
public interface KeyMapper {
    List<Key> listAllKeys();
}
