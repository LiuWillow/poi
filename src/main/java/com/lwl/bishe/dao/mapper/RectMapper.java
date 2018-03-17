package com.lwl.bishe.dao.mapper;

import com.lwl.bishe.bean.Rect;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * date  2018/3/17
 * author liuwillow
 **/
@Repository
public interface RectMapper {
    int saveRects(List<Rect> rects);
    int saveRect(Rect rect);
}
