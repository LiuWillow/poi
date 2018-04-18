package com.lwl.bishe.service;

import com.lwl.bishe.bean.Rect;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * date  2018/3/17
 * author liuwillow
 **/
public interface RectService {
    void splitRects();
    boolean saveRect(Rect rect);
    boolean clearRects();

    @Transactional(isolation = Isolation.SERIALIZABLE)
    List<Rect> listAndUpdateEnabledRects(int begin, int pageSize);

    boolean updateAllRectsEnabled();
}
