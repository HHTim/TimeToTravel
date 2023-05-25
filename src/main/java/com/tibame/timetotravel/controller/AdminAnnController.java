package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.view.AnnView;
import com.tibame.timetotravel.service.AdminAnnService;
import com.tibame.timetotravel.entity.Ann;
import com.tibame.timetotravel.service.AdminAnnViewService;
import com.tibame.timetotravel.common.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/AdminAnnController")
public class AdminAnnController {

    @Autowired
    @Qualifier("adminAnnService")
    private AdminAnnService adminAnnService;

    @Autowired
    @Qualifier("adminAnnViewService")
    private AdminAnnViewService adminAnnViewService;
      //此為重導寫法，可以指定導到哪個頁面
    @RequestMapping("/redirect")
    public RedirectView redirect(){
        return new RedirectView("/admin_ann");
    }

    @PostMapping("/anns")
    public String insert(@RequestBody Ann ann){
        adminAnnService.insert(ann);
        return "執行資料庫的 Insert 操作";
    }

    @PutMapping("/anns/{annId}")
    public String update(@PathVariable Integer annId,
                         @RequestBody Ann ann){
        System.out.println("執行資料庫的 Update 操作");
        return adminAnnService.update(annId,ann);
    }

    @DeleteMapping("/anns/{annId}")
    public String delete(@PathVariable Integer annId) {
        adminAnnService.deleteById(annId);
        return "執行資料庫的 Delete 操作";
    }

    @GetMapping("/anns/page/{currPage}/{rows}")
    public PageBean<Ann> readByPage(@PathVariable Integer currPage, @PathVariable Integer rows){
        System.out.println("分頁搜尋");
        return adminAnnService.findAnnPageByRowData(currPage,rows);
    }

    @GetMapping("/anns/page/{currPage}/{rows}/{startDate}/{endDate}")
    public PageBean<Ann> readByDateRange(@PathVariable Integer currPage, @PathVariable Integer rows, @PathVariable String startDate, @PathVariable String endDate){
        System.out.println("日期範圍搜尋");
        System.out.println(startDate);
        System.out.println(endDate);
        return adminAnnService.findAnnPageByDateRange(startDate,endDate,currPage,rows);
    }

    @GetMapping("/anns/page/{currPage}/{rows}/keywords/{keyword}")
    public PageBean<Ann> readByKeWords(@PathVariable Integer currPage, @PathVariable Integer rows, @PathVariable String keyword){
        System.out.println("關鍵字搜尋");
        return adminAnnService.findAnnPageByKeyWords(keyword,currPage,rows);
    }

    @GetMapping("/anns/annView")
    public List<AnnView> findAllofAnnView(){
        System.out.println("VIEW全查詢");
        return adminAnnViewService.findAll();
    }

}
