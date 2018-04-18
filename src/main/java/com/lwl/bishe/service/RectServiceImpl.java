package com.lwl.bishe.service;

import com.lwl.bishe.bean.Rect;
import com.lwl.bishe.constant.RectConstant;
import com.lwl.bishe.dao.mapper.RectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * date  2018/3/17
 * author liuwillow
 **/
@Service
public class RectServiceImpl implements RectService {

    @Autowired
    private SplitService splitService;

    @Autowired
    private RectMapper rectMapper;

    @Override
    public void splitRects() {
        clearRects();
        Rect rectLeft = new Rect(RectConstant.BORDER_POINT_1, RectConstant.BORDER_POINT_2,
                RectConstant.BORDER_POINT_5, RectConstant.BORDER_POINT_6);
        splitService.split(rectLeft);

        Rect rightRect = new Rect(RectConstant.BORDER_POINT_2, RectConstant.BORDER_POINT_3,
                RectConstant.BORDER_POINT_4, RectConstant.BORDER_POINT_5);
        splitService.split(rightRect);
    }

    @Override
    public boolean saveRect(Rect rect) {
        return rectMapper.saveRect(rect) > 0;
    }

    @Override
    public boolean clearRects() {
        rectMapper.clearRects();
        return true;
    }

    @Override
    public List<Rect> listAndUpdateEnabledRects(int begin, int pageSize) {
        List<Rect> list = rectMapper.listRect(begin, pageSize);
        if (!list.isEmpty()){
            rectMapper.updateRectEnablePatch(list);
        }
        return list;
    }

    @Override
    public boolean updateAllRectsEnabled() {
        return rectMapper.updateAllRectsEnabled() > 0;
    }

}
