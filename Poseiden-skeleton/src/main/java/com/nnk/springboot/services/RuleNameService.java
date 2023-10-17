package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class RuleNameService {
    private final RuleNameRepository ruleNameRepository;

    @Autowired
    public RuleNameService(RuleNameRepository ruleNameRepository) {
        this.ruleNameRepository = ruleNameRepository;
    }

    public RuleName createRuleName(RuleName ruleName) {
        return ruleNameRepository.save(ruleName);
    }

    public List<RuleName> findAllRuleNames() {
        return ruleNameRepository.findAll();
    }

    public RuleName findRuleNameById(Integer id) {
        return ruleNameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid RuleName Id:" + id));
    }

    public void updateRuleName(RuleName ruleName) {
        ruleNameRepository.save(ruleName);
    }

    @Transactional
    public void deleteRuleNameById(Integer id) {
        RuleName ruleName = findRuleNameById(id);
        ruleNameRepository.delete(ruleName);
    }
}
