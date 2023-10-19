package com.nnk.springboot;

import com.nnk.springboot.controllers.CurveController;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurveService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import java.util.List;

public class CurveControllerTest {

    private CurveController curveController;

    @Mock
    private CurveService curveService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        curveController = new CurveController(curveService);
    }

    @Test
    public void testHome() {
        List<CurvePoint> curvePoints = List.of(Mockito.mock(CurvePoint.class));
        when(curveService.findAllCurves()).thenReturn(curvePoints);
        String viewName = curveController.home(model);
        assertEquals("curvePoint/list", viewName);
        verify(curveService, times(1)).findAllCurves();
        verify(model, times(1)).addAttribute("curvePoints", curvePoints);
    }

    @Test
    public void testAddCurveForm() {
        String viewName = curveController.addCurveForm(Mockito.mock(CurvePoint.class));
        assertEquals("curvePoint/add", viewName);
    }

    @Test
    public void testValidateWithErrors() {
        when(bindingResult.hasErrors()).thenReturn(true);
        String viewName = curveController.validate(Mockito.mock(CurvePoint.class), bindingResult, model);
        assertEquals("curvePoint/add", viewName);
    }

    @Test
    public void testValidateWithoutErrors() {
        when(bindingResult.hasErrors()).thenReturn(false);
        String viewName = curveController.validate(Mockito.mock(CurvePoint.class), bindingResult, model);
        assertEquals("redirect:/curvePoint/list", viewName);
    }

    @Test
    public void testShowUpdateForm() {
        Integer curveId = 1;
        CurvePoint mockCurvePoint = Mockito.mock(CurvePoint.class);
        when(curveService.findCurveById(curveId)).thenReturn(mockCurvePoint);
        Model model = Mockito.mock(Model.class);
        String viewName = curveController.showUpdateForm(curveId, model);
        assertEquals("curvePoint/update", viewName);
        verify(curveService, times(1)).findCurveById(curveId);
        verify(model, times(1)).addAttribute("curvePoint", mockCurvePoint);
    }

    @Test
    public void testUpdateCurveWithErrors() {
        Integer curveId = 1;
        CurvePoint mockCurvePoint = Mockito.mock(CurvePoint.class);
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        Model model = Mockito.mock(Model.class);
        String viewName = curveController.updateCurve(curveId, mockCurvePoint, bindingResult, model);
        assertEquals("curvePoint/update", viewName);
        verify(bindingResult, times(1)).hasErrors();
        verify(curveService, never()).updateCurve(mockCurvePoint); // Make sure updateCurve is not called with errors
    }

    @Test
    public void testUpdateCurveWithoutErrors() {
        Integer curveId = 1;
        CurvePoint mockCurvePoint = Mockito.mock(CurvePoint.class);
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        Model model = Mockito.mock(Model.class);
        String viewName = curveController.updateCurve(curveId, mockCurvePoint, bindingResult, model);
        assertEquals("redirect:/curvePoint/list", viewName);
        verify(bindingResult, times(1)).hasErrors();
        verify(curveService, times(1)).updateCurve(mockCurvePoint);
    }

    @Test
    public void testDeleteCurve() {
        Integer curveId = 1;
        Model model = Mockito.mock(Model.class);
        String viewName = curveController.deleteCurve(curveId, model);
        assertEquals("redirect:/curvePoint/list", viewName);
        verify(curveService, times(1)).deleteCurveById(curveId);
    }
}
