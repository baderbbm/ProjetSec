package com.nnk.springboot.unit.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurveService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CurveServiceTests {

	@Autowired
	private CurveService curveService;

	@Test
	public void testCreateCurvePoint() {
		CurvePoint curvePoint = new CurvePoint(1, 5.0, 10.0);
		CurvePoint savedCurvePoint = curveService.createCurve(curvePoint);
		assertNotNull(savedCurvePoint.getId());
		assertEquals(5.0, savedCurvePoint.getTerm(), 0.001);
		assertEquals(10.0, savedCurvePoint.getValue(), 0.001);
	}

	@Test
	public void testFindCurvePointById() {
		CurvePoint curvePoint = new CurvePoint(1, 5.0, 10.0);
		CurvePoint savedCurvePoint = curveService.createCurve(curvePoint);
		CurvePoint foundCurvePoint = curveService.findCurveById(savedCurvePoint.getId());
		assertNotNull(foundCurvePoint);
		assertEquals(savedCurvePoint.getId(), foundCurvePoint.getId());
		assertEquals(savedCurvePoint.getCurveId(), foundCurvePoint.getCurveId());
		assertEquals(savedCurvePoint.getTerm(), foundCurvePoint.getTerm(), 0.001);
		assertEquals(savedCurvePoint.getValue(), foundCurvePoint.getValue(), 0.001);
	}

	@Test
	public void testUpdateCurvePoint() {
		CurvePoint curvePoint = new CurvePoint(1, 5.0, 10.0);
		CurvePoint savedCurvePoint = curveService.createCurve(curvePoint);
		savedCurvePoint.setTerm(6.0);
		savedCurvePoint.setValue(11.0);
		curveService.updateCurve(savedCurvePoint);
		CurvePoint updatedCurvePoint = curveService.findCurveById(savedCurvePoint.getId());
		assertEquals(6.0, updatedCurvePoint.getTerm(), 0.001);
		assertEquals(11.0, updatedCurvePoint.getValue(), 0.001);
	}

	@Test
	public void testDeleteCurvePoint() {
		CurvePoint curvePoint = new CurvePoint(1, 5.0, 10.0);
		CurvePoint savedCurvePoint = curveService.createCurve(curvePoint);
		curveService.deleteCurveById(savedCurvePoint.getId());
		assertThrows(IllegalArgumentException.class, () -> curveService.findCurveById(savedCurvePoint.getId()));
	}

}
