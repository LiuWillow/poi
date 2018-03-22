package com.lwl.bishe.dao.mapper;

import com.lwl.bishe.App;
import com.lwl.bishe.bean.Location;
import com.lwl.bishe.bean.Rect;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class RectMapperTest {
    @Autowired
    private RectMapper rectMapper;

    @Test
    public void saveRects() throws Exception {
        List<Rect> rectList = Arrays.asList(
                new Rect(new Location(231231, 12),
                        new Location(123, 32),
                        new Location(123, 32),
                        new Location(123, 32)),
                new Rect(new Location(1231231, 12),
                        new Location(123, 32),
                        new Location(123, 32),
                        new Location(123, 32)),
                new Rect(new Location(666553, 12),
                        new Location(123, 32),
                        new Location(123, 32),
                        new Location(123, 32))
        );
        assertEquals(3, rectMapper.saveRects(rectList));
    }

    @Test
    public void testListRects() {
        List<Rect> list = rectMapper.listRect(1, 1000);
        assertEquals(1000, list.size());
    }
}