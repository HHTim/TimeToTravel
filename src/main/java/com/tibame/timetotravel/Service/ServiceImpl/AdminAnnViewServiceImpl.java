package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.view.AnnView;
import com.tibame.timetotravel.service.AdminAnnViewService;
import com.tibame.timetotravel.repository.AdminAnnViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("adminAnnViewService")
public class AdminAnnViewServiceImpl implements AdminAnnViewService {
    @Autowired
    @Qualifier("adminAnnViewRepository")
    private AdminAnnViewRepository adminAnnViewRepository;

    public List<AnnView> findAll() {
        return adminAnnViewRepository.findAll();
    }

}
