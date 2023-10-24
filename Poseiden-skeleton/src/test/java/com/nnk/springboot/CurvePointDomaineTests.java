package com.nnk.springboot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.nnk.springboot.domain.CurvePoint;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Date;

public class CurvePointDomaineTests {

    private CurvePoint curvePoint;

    @BeforeEach
    public void setUp() {
        curvePoint = new CurvePoint();
    }

    @Test
    public void testSetAndGetId() {
        curvePoint.setId(1);
        assertEquals(1, curvePoint.getId());
    }

    @Test
    public void testGetCurveId() {
        curvePoint.setCurveId(2);
        assertEquals(2, curvePoint.getCurveId());
    }

    @Test
    public void testGetAsOfDate() {
        Date date = new Date();
        curvePoint.setAsOfDate(date);
        assertEquals(date, curvePoint.getAsOfDate());
    }

    @Test
    public void testSetAndGetCreationDate() {
        Date date = new Date();
        curvePoint.setCreationDate(date);
        assertEquals(date, curvePoint.getCreationDate());
    }
}
