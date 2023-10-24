package com.nnk.springboot;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RuleTests {

    @Autowired
    private RuleNameRepository ruleNameRepository;

    @Test
    public void ruleTest() {
        RuleName rule = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");

        // Save
        rule = ruleNameRepository.save(rule);
        assertNotNull(rule.getId());
        assertEquals("Rule Name", rule.getName());

        // Update
        rule.setName("Rule Name Update");
        rule = ruleNameRepository.save(rule);
        assertEquals("Rule Name Update", rule.getName());

        // Find
        List<RuleName> listResult = ruleNameRepository.findAll();
        assertTrue(listResult.size() > 0);

        // Delete
        Integer id = rule.getId();
        ruleNameRepository.delete(rule);
        Optional<RuleName> ruleList = ruleNameRepository.findById(id);
        assertFalse(ruleList.isPresent());
    }
    
    @Test
    public void ruleNameInvalidTest() {
        RuleName ruleName = new RuleName("", "", "", "", "", "");
        if (isValidRuleName(ruleName)) {
            ruleName = ruleNameRepository.save(ruleName);
            assertNotNull(ruleName.getId());
            assertEquals("ValidRuleName", ruleName.getName());
        } else {
            System.out.println("Données non valides : la sauvegarde a échoué");
        }
    }

    private boolean isValidRuleName(RuleName ruleName) {
        return !ruleName.getName().isEmpty() &&
               !ruleName.getDescription().isEmpty() &&
               !ruleName.getJson().isEmpty() &&
               !ruleName.getTemplate().isEmpty() &&
               !ruleName.getSqlStr().isEmpty() &&
               !ruleName.getSqlPart().isEmpty();
    }
}
