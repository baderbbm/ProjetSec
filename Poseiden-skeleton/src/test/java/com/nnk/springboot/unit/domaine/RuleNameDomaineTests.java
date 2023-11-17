package com.nnk.springboot.unit.domaine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.nnk.springboot.domain.RuleName;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RuleNameDomaineTests {

    private RuleName ruleName;

    @BeforeEach
    public void setUp() {
        ruleName = new RuleName("Name123", "Description456", "JSON789", "TemplateABC", "SQL123", "SQLPartXYZ");
        ruleName.setId(1);
        ruleName.setDescription("Description456");
        ruleName.setJson("JSON789");
        ruleName.setTemplate("TemplateABC");
        ruleName.setSqlPart("SQLPartXYZ");
        ruleName.setSqlStr("SQL123");
    }

    @Test
    public void testGetId() {
        assertEquals(1, ruleName.getId());
    }

    @Test
    public void testGetName() {
        assertEquals("Name123", ruleName.getName());
    }

    @Test
    public void testGetDescription() {
        assertEquals("Description456", ruleName.getDescription());
    }

    @Test
    public void testGetJson() {
        assertEquals("JSON789", ruleName.getJson());
    }

    @Test
    public void testGetTemplate() {
        assertEquals("TemplateABC", ruleName.getTemplate());
    }

    @Test
    public void testGetSqlStr() {
        assertEquals("SQL123", ruleName.getSqlStr());
    }

    @Test
    public void testGetSqlPart() {
        assertEquals("SQLPartXYZ", ruleName.getSqlPart());
    }
}
