package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.entity.GiftPhotos;
import com.tibame.timetotravel.repository.GiftPhotosRepository;
import com.tibame.timetotravel.service.GiftPhotosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("giftPhotosService")
public class GiftPhotosServiceImpl implements GiftPhotosService {

    @Autowired
    @Qualifier("giftPhotosRepository")
    private GiftPhotosRepository giftPhotosRepository;

    @Override
    @Transactional
    public void insert(GiftPhotos giftPhotos) {
        giftPhotosRepository.save(giftPhotos);
    }

    @Override
    @Transactional
    public void deleteById(Integer giftPhotosId) {
        giftPhotosRepository.deleteById(giftPhotosId);
    }

    @Override
    @Transactional
    public GiftPhotos updateById(Integer giftPhotosId, GiftPhotos giftPhotos) {

        GiftPhotos gp = giftPhotosRepository.findById(giftPhotosId).orElse(null);

        if (gp != null) {
            gp.setGiftId(giftPhotos.getGiftId());
            gp.setGiftPhoto(giftPhotos.getGiftPhoto());
            giftPhotosRepository.save(gp);
            return gp;
        } else {
            return null;
        }

    }

    @Override
    public GiftPhotos findById(Integer giftPhotosId) {
        return giftPhotosRepository.findById(giftPhotosId).orElse(null);
    }

    @Override
    public List<GiftPhotos> findAll() {
        return giftPhotosRepository.findAll();
    }
}
