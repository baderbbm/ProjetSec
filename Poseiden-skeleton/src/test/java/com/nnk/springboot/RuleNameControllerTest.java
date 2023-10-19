package com.nnk.springboot;

import com.nnk.springboot.controllers.RuleNameController;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;
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

public class RuleNameControllerTest {

    private RuleNameController ruleNameController;

    @Mock
    private RuleNameService ruleNameService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ruleNameController = new RuleNameController(ruleNameService);
    }

    @Test
    public void testHome() {
        List<RuleName> ruleNames = List.of(Mockito.mock(RuleName.class));
        when(ruleNameService.findAllRuleNames()).thenReturn(ruleNames);
        String viewName = ruleNameController.home(model);
        assertEquals("ruleName/list", viewName);
        verify(ruleNameService, times(1)).findAllRuleNames();
        verify(model, times(1)).addAttribute("ruleNames", ruleNames);
    }

    @Test
    public void testAddRuleForm() {
        String viewName = ruleNameController.addRuleForm(Mockito.mock(RuleName.class));
        assertEquals("ruleName/add", viewName);
    }

    @Test
    public void testValidateWithErrors() {
        when(bindingResult.hasErrors()).thenReturn(true);
        String viewName = ruleNameController.validate(Mockito.mock(RuleName.class), bindingResult, model);
        assertEquals("ruleName/add", viewName);
    }

    @Test
    public void testValidateWithoutErrors() {
        when(bindingResult.hasErrors()).thenReturn(false);
        String viewName = ruleNameController.validate(Mockito.mock(RuleName.class), bindingResult, model);
        assertEquals("redirect:/ruleName/list", viewName);
    }

    @Test
    public void testShowUpdateForm() {
        Integer ruleNameId = 1;
        RuleName mockRuleName = Mockito.mock(RuleName.class);
        when(ruleNameService.findRuleNameById(ruleNameId)).thenReturn(mockRuleName);
        String viewName = ruleNameController.showUpdateForm(ruleNameId, model);
        assertEquals("ruleName/update", viewName);
        verify(ruleNameService, times(1)).findRuleNameById(ruleNameId);
        verify(model, times(1)).addAttribute("ruleName", mockRuleName);
    }

    @Test
    public void testUpdateRuleNameWithErrors() {
        Integer ruleNameId = 1;
        RuleName mockRuleName = Mockito.mock(RuleName.class);
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        String viewName = ruleNameController.updateRuleName(ruleNameId, mockRuleName, bindingResult, model);
        assertEquals("ruleName/update", viewName);
        verify(bindingResult, times(1)).hasErrors();
        verify(ruleNameService, never()).updateRuleName(mockRuleName); // Make sure updateRuleName is not called with errors
    }

    @Test
    public void testUpdateRuleNameWithoutErrors() {
        Integer ruleNameId = 1;
        RuleName mockRuleName = Mockito.mock(RuleName.class);
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        String viewName = ruleNameController.updateRuleName(ruleNameId, mockRuleName, bindingResult, model);
        assertEquals("redirect:/ruleName/list", viewName);
        verify(bindingResult, times(1)).hasErrors();
        verify(mockRuleName, times(1)).setId(ruleNameId);
        verify(ruleNameService, times(1)).updateRuleName(mockRuleName);
    }

    @Test
    public void testDeleteRuleName() {
        Integer ruleNameId = 1;
        String viewName = ruleNameController.deleteRuleName(ruleNameId, model);
        assertEquals("redirect:/ruleName/list", viewName);
        verify(ruleNameService, times(1)).deleteRuleNameById(ruleNameId);
    }
}
