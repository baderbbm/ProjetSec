package com.nnk.springboot.unit.service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.RuleNameService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RuleNameServiceTests {

    @Mock
    private RuleNameRepository ruleNameRepository;

    private RuleNameService ruleNameService;

    @BeforeEach
    public void setUp() {
        ruleNameService = new RuleNameService(ruleNameRepository);
    }

    @Test
    public void shouldCreateRuleName() {
        RuleName ruleName = new RuleName();
        when(ruleNameRepository.save(ruleName)).thenReturn(ruleName);
        RuleName createdRuleName = ruleNameService.createRuleName(ruleName);
        assertEquals(ruleName, createdRuleName);
    }

    @Test
    public void shouldFindAllRuleNames() {
        List<RuleName> expectedRuleNames = new ArrayList<>();
        when(ruleNameRepository.findAll()).thenReturn(expectedRuleNames);
        List<RuleName> actualRuleNames = ruleNameService.findAllRuleNames();
        assertEquals(expectedRuleNames, actualRuleNames);
    }

    @Test
    public void shouldFindRuleNameById() {
        Integer ruleNameId = 1;
        RuleName expectedRuleName = new RuleName();
        when(ruleNameRepository.findById(ruleNameId)).thenReturn(Optional.of(expectedRuleName));
        RuleName actualRuleName = ruleNameService.findRuleNameById(ruleNameId);
        assertEquals(expectedRuleName, actualRuleName);
    }

    @Test
    public void shouldUpdateRuleName() {
        RuleName ruleName = new RuleName();
        when(ruleNameRepository.save(ruleName)).thenReturn(ruleName);
        ruleNameService.updateRuleName(ruleName);
        verify(ruleNameRepository, times(1)).save(ruleName);
    }

    @Test
    public void shouldDeleteRuleNameById() {
        Integer ruleNameId = 1;
        RuleName ruleName = new RuleName();
        when(ruleNameRepository.findById(ruleNameId)).thenReturn(Optional.of(ruleName));
        ruleNameService.deleteRuleNameById(ruleNameId);
        verify(ruleNameRepository, times(1)).delete(ruleName);
    }
    @Test
    public void shouldThrowExceptionWhenRuleNameNotFound() {
        Integer ruleNameId = 1;
        when(ruleNameRepository.findById(ruleNameId)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> {
            ruleNameService.findRuleNameById(ruleNameId);
        });
        verify(ruleNameRepository, times(1)).findById(ruleNameId);
    }

    @Test
    public void shouldFindRuleNameWhenRuleNameExists() {
        Integer ruleNameId = 1;
        RuleName expectedRuleName = new RuleName();
        when(ruleNameRepository.findById(ruleNameId)).thenReturn(Optional.of(expectedRuleName));
        assertDoesNotThrow(() -> {
            RuleName actualRuleName = ruleNameService.findRuleNameById(ruleNameId);
            assertEquals(expectedRuleName, actualRuleName);
        });
        verify(ruleNameRepository, times(1)).findById(ruleNameId);
    } 
}

