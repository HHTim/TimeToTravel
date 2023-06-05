//package com.tibame.timetotravel.service.ServiceImpl;
//
//import com.tibame.timetotravel.entity.GiftFollow;
//import com.tibame.timetotravel.repository.GiftFollowRepository;
//import com.tibame.timetotravel.service.GiftFollowService;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service("giftFollowService")
//public class GiftFollowImpl implements GiftFollowService{
//
//    @Autowired
//    @Qualifier("giftFollowRepository")
//    private GiftFollowRepository giftFollowRepository;
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
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
//    @Transactional
//    public void update(Integer giftFollowId, GiftFollow giftFollow) {
//        entityManager.merge(giftFollow);
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
