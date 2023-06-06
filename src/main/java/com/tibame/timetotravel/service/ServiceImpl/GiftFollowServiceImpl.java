package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.entity.GiftFollow;
import com.tibame.timetotravel.repository.GiftFollowRepository;
import com.tibame.timetotravel.service.GiftFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//
//import java.util.List;
//
@Service("giftFollowService")
public class GiftFollowServiceImpl implements GiftFollowService {

    @Override
    public void insert(GiftFollow giftFollow) {

    }

    @Override
    public void deleteById(Integer giftFollowId) {

    }

    @Override
    public GiftFollow findById(Integer giftFollowId) {
        return null;
    }

    @Override
    public List<GiftFollow> findAll() {
        return null;
    }

    @Override
    public GiftFollow updateById(Integer giftFollowId, GiftFollow giftFollow) {
        return null;
    }
}
