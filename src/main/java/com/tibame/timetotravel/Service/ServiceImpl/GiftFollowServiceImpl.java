//package com.tibame.timetotravel.service.ServiceImpl;
//
//import com.tibame.timetotravel.entity.GiftFollow;
//import com.tibame.timetotravel.repository.GiftFollowRepository;
//import com.tibame.timetotravel.service.GiftFollowService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service("giftFollowService")
//public class GiftFollowServiceImpl implements GiftFollowService {
//
//    @Autowired
//    @Qualifier("giftFollowRepository")
//    private GiftFollowRepository giftFollowRepository;
//
//    @Override
//    @Transactional
//    public void insert(GiftFollow giftFollow) {
//        giftFollowRepository.save(giftFollow);
//    }
//
//    @Override
//    @Transactional
//    public void deleteById(Integer giftFollowId) {
//        giftFollowRepository.deleteById(giftFollowId);
//    }
//
//    @Override
//    public void update(Integer giftFollowId, GiftFollow gift) {
//
//    }
//
//
//    @Transactional
//    public GiftFollow updateById(Integer giftFollowId, GiftFollow giftFollow) {
//
//        GiftFollow gf = giftFollowRepository.findById(giftFollowId).orElse(null);
//
//        if (gf != null) {
//            gf.setGiftId(giftFollow.getGiftId());
//            gf.setUserId(giftFollow.getUserId());
//            giftFollowRepository.save(gf);
//            return gf;
//        } else {
//            return null;
//        }
//
//    }
//
//    @Override
//    public GiftFollow findById(Integer giftFollowId) {
//        return giftFollowRepository.findById(giftFollowId).orElse(null);
//    }
//
//    @Override
//    public List<GiftFollow> findAll() {
//        return giftFollowRepository.findAll();
//    }
//}
