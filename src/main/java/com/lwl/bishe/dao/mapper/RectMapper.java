package com.lwl.bishe.dao.mapper;

import com.lwl.bishe.bean.Rect;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * date  2018/3/17
 * author liuwillow
 **/
@Repository
public interface RectMapper {
    int saveRects(List<Rect> rects);
    int saveRect(Rect rect);
    void clearRects();
    List<Rect> listRect(@Param("begin") int begin, @Param("pageSize") int pageSize);
    int countRects();

    @Transactional
    int updateRectEnablePatch(List<Rect> rectList);

    int updateAllRectsEnabled();
}
