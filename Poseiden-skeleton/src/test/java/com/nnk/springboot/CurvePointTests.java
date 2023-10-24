package com.nnk.springboot;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.CurveService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CurvePointTests {

    @Mock
    private CurvePointRepository curvePointRepository;

  private CurveService curveService;


    @BeforeEach
    public void setUp() {
        curveService = new CurveService(curvePointRepository);
    }
    
    @Test
    public void shouldCreateCurve() {
        CurvePoint curve = new CurvePoint();
        when(curvePointRepository.save(curve)).thenReturn(curve);
        CurvePoint createdCurve = curveService.createCurve(curve);
        assertEquals(curve, createdCurve);
    }

    @Test
    public void shouldFindAllCurves() {
        List<CurvePoint> expectedCurves = new ArrayList<>();
        when(curvePointRepository.findAll()).thenReturn(expectedCurves);
        List<CurvePoint> actualCurves = curveService.findAllCurves();
        assertEquals(expectedCurves, actualCurves);
    }

    @Test
    public void shouldFindCurveById() {
        Integer curveId = 1;
        CurvePoint expectedCurve = new CurvePoint();
        when(curvePointRepository.findById(curveId)).thenReturn(Optional.of(expectedCurve));
        CurvePoint actualCurve = curveService.findCurveById(curveId);
        assertEquals(expectedCurve, actualCurve);
    }

    @Test
    public void shouldUpdateCurve() {
        CurvePoint curve = new CurvePoint();
        when(curvePointRepository.save(curve)).thenReturn(curve);
        curveService.updateCurve(curve);
        verify(curvePointRepository, times(1)).save(curve);
    }

    @Test
    public void shouldDeleteCurveById() {
        Integer curveId = 1;
        CurvePoint curve = new CurvePoint();
        when(curvePointRepository.findById(curveId)).thenReturn(Optional.of(curve));
        curveService.deleteCurveById(curveId);
        verify(curvePointRepository, times(1)).delete(curve);
    }
    
    @Test
    public void shouldThrowExceptionWhenCurveNotFound() {
        Integer curveId = 1;
        when(curvePointRepository.findById(curveId)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> {
            curveService.findCurveById(curveId);
        });
        verify(curvePointRepository, times(1)).findById(curveId);
    }

    @Test
    public void shouldFindCurveWhenCurveExists() {
        Integer curveId = 1;
        CurvePoint expectedCurve = new CurvePoint();
        when(curvePointRepository.findById(curveId)).thenReturn(Optional.of(expectedCurve));
        assertDoesNotThrow(() -> {
            CurvePoint actualCurve = curveService.findCurveById(curveId);
            assertEquals(expectedCurve, actualCurve);
        });
        verify(curvePointRepository, times(1)).findById(curveId);
    }
    
	@Test
	public void curvePointInvalidTest() {
	    CurvePoint curvePoint = new CurvePoint(null, -1.0, -1.0);
	    if (isValidCurvePoint(curvePoint)) {
	        curvePoint = curvePointRepository.save(curvePoint);
	        assertNotNull(curvePoint.getId());
	        assertEquals(10.0, curvePoint.getTerm(), 0.01);
	    } else {
	        System.out.println("Données non valides : la sauvegarde a échoué");
	    }
	}

	private boolean isValidCurvePoint(CurvePoint curvePoint) {
	    return curvePoint.getCurveId() != null &&
	           curvePoint.getTerm() > 0.0 &&
	           curvePoint.getValue() > 0.0;
	}
}
