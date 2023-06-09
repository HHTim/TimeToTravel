package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.dto.GiftOrderListDto;
import com.tibame.timetotravel.entity.Company;
import com.tibame.timetotravel.entity.Gift;
import com.tibame.timetotravel.entity.GiftOrderDetails;
import com.tibame.timetotravel.repository.CompanyRepository;
import com.tibame.timetotravel.repository.GiftOrderDetailsRepository;
import com.tibame.timetotravel.repository.GiftRepository;
import com.tibame.timetotravel.service.GiftOrderDetailsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("giftOrderDetailsService")
public class GiftOrderDetailsServiceImpl implements GiftOrderDetailsService {

    @Autowired
    @Qualifier("giftOrderDetailsRepository")
    private GiftOrderDetailsRepository giftOrderDetailsRepository;

    @Autowired
    @Qualifier("giftRepository")
    private GiftRepository giftRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<GiftOrderListDto> findById(Integer giftOrderId) {
        List<GiftOrderListDto> giftOrderLists = new ArrayList<>();
        List<GiftOrderDetails> giftOrderDetailsList = giftOrderDetailsRepository.findByGiftOrderId(giftOrderId);

        for (GiftOrderDetails oldItem : giftOrderDetailsList) {
            GiftOrderListDto newItem = new GiftOrderListDto();
            BeanUtils.copyProperties(oldItem, newItem);
            Integer giftId = newItem.getGiftId();
            Gift gift = giftRepository.findById(giftId).orElse(null);
            BeanUtils.copyProperties(gift, newItem);
            Integer comId = newItem.getComId();
            Company company = companyRepository.findById(comId).orElse(null);
            newItem.setComName(company.getComName());

            giftOrderLists.add(newItem);
        }

        return giftOrderLists;
        //
    }

}
