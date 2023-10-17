package com.nnk.springboot.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

@Service
@Transactional
public class CurveService {

    private final CurvePointRepository curvePointRepository;

    @Autowired
    public CurveService(CurvePointRepository curvePointRepository) {
        this.curvePointRepository = curvePointRepository;
    }

    public CurvePoint createCurve(CurvePoint curvePoint) {
        return curvePointRepository.save(curvePoint);
    }

    public List<CurvePoint> findAllCurves() {
        return curvePointRepository.findAll();
    }

    public CurvePoint findCurveById(Integer id) {
        return curvePointRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid curve Id:" + id));
    }

    public void updateCurve(CurvePoint curvePoint) {
        curvePointRepository.save(curvePoint);
    }

    @Transactional
    public void deleteCurveById(Integer id) {
        CurvePoint curvePoint = findCurveById(id);
        curvePointRepository.delete(curvePoint);
    }
}

