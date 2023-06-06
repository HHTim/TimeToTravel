package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.entity.GiftType;
import com.tibame.timetotravel.repository.GiftTypeRepository;
import com.tibame.timetotravel.service.GiftTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("GiftTypeService")
public class GiftTypeServiceImpl implements GiftTypeService {

    @Autowired
    @Qualifier("giftTypeRepository")
    private GiftTypeRepository giftTypeRepository;

    @Override
    @Transactional
    public void insert(GiftType giftType) {
        giftTypeRepository.save(giftType);
    }

    @Override
    @Transactional
    public void deleteById(Integer giftTypeId) {
        giftTypeRepository.deleteById(giftTypeId);
    }

    @Override
    @Transactional
    public GiftType updateById(Integer giftTypeId, GiftType giftType) {

        GiftType gt = giftTypeRepository.findById(giftTypeId).orElse(null);

        if (gt != null) {
            gt.setGiftTypeName(giftType.getGiftTypeName());
            giftTypeRepository.save(gt);
            return gt;
        } else {
            return null;
        }

    }

    @Override
    public GiftType findById(Integer giftTypeId) {
        return giftTypeRepository.findById(giftTypeId).orElse(null);
    }

    @Override
    public List<GiftType> findAll() {
        return giftTypeRepository.findAll();
    }
}
