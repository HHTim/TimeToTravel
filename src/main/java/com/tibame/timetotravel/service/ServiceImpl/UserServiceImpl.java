package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.dto.*;
import com.tibame.timetotravel.entity.User;
import com.tibame.timetotravel.repository.UserRepository;
import com.tibame.timetotravel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.List;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("UserRepository")
    UserRepository userRepository;

    @Autowired
    PageBean<User> pageBean;

    private String sha512(String input) {
        String toReturn = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.reset();
            digest.update(input.getBytes("utf8"));
            toReturn = String.format("%0128x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return toReturn;
    }

    @Override
    public void insertRegisterUser(RegisterUserDto dto) throws Exception {
        if (userRepository.findByUserAccount(dto.getAccount()) != null) {
            throw new Exception("該帳號已存在");
        }

        User user = new User();
        user.setUserAccount(dto.getAccount());
        user.setUserPassword(sha512(dto.getPassword()));
        user.setUserEmail(dto.getEmail());
        user.setUserGender(dto.isGender());
        user.setUserBirthDay(dto.getBirthday());
        user.setUserPhone(dto.getPhone());
        user.setUserName(dto.getName());
        user.setUserAvatar(dto.getAvatar().getBytes());

        // 預設值
        user.setUserNickName("");
        user.setUserStatus(true);
        user.setUserNewsStatus(0);
        user.setUserSignDatetime(new Timestamp(System.currentTimeMillis()));

        userRepository.save(user);
    }

    @Override
    public void insert(User user) {
        userRepository.save(user);
    }

    @Transactional
    @Override
    public String updateByAccount(String account) {
        User user = userRepository.findByUserAccount(account);
        user.setUserNewsStatus(1);
        userRepository.save(user);
        return account;
    }

    @Transactional
    @Override
    public String updateByPassword(String password, Integer id) {
        userRepository.updateUserPassword(password, id);
        return "使用者密碼更新成功";
    }

    @Transactional
    @Override
    public String updateUserStatusByAccount(String account, Integer status) {
        userRepository.updateUserStatus(account, status);
        return "更新User: " + account + "的Status成功";
    }

    @Transactional
    @Override
    public String updateUserNewsStatusByAccount(String account, Integer newsStatus) {
        userRepository.updateUserNewsStatus(account, newsStatus);
        return "更新User: " + account + "的newsStatus成功";
    }

    @Override
    public User findByUserId(Integer userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public int login(LoginUserDto dto) throws Exception {
        User user = userRepository.findByUserAccount(dto.getAccount());
        if (user == null || !sha512(dto.getPassword()).equals(user.getUserPassword())) {
            throw new Exception("登入失敗");
        }

        return user.getUserId();
    }

    @Override
    public UserDetailResponseDto get(int userId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("會員不存在"));

        UserDetailResponseDto dto = new UserDetailResponseDto();
        dto.setAccount(user.getUserAccount());
        dto.setName(user.getUserName());
        dto.setNickName(user.getUserNickName());
        dto.setPhone(user.getUserPhone());
        dto.setEmail(user.getUserEmail());
        dto.setAvatar(new String(user.getUserAvatar()));
        dto.setGender(user.getUserGender());
        dto.setBirthday(user.getUserBirthDay());

        return dto;
    }

    @Override
    public void modify(int userId, ModifyUserDto dto) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("會員不存在"));
        user.setUserEmail(dto.getEmail());
        user.setUserGender(dto.isGender());
        user.setUserBirthDay(dto.getBirthday());
        user.setUserPhone(dto.getPhone());
        user.setUserName(dto.getName());
        user.setUserNickName(dto.getNickName());
        user.setUserAvatar(dto.getAvatar().getBytes());

        userRepository.save(user);
    }

    @Override
    public void modify(int userId, ModifyUserPasswordDto dto) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("會員不存在"));
        if (!sha512(dto.getOriginalPassword()).equals(user.getUserPassword())) {
            throw new Exception("原始密碼不一致");
        }

        user.setUserPassword(sha512(dto.getNewPassword()));

        userRepository.save(user);
    }

    @Override
    public List<User> findByPage(Integer currPage, Integer rows) {
        return userRepository.findByPage(currPage, rows);
    }

    @Override
    public PageBean<User> findByPageRowData(Integer currPage, Integer rows) {
        int start = (currPage - 1) * rows;
        int pageSize = (int) (Math.ceil(userRepository.findAll().size() / (double) rows));
        pageBean.setRows(findByPage(start, rows));
        pageBean.setPageSize(Math.max(pageSize, 1));
        return pageBean;
    }

    @Override
    public List<User> findStatusByPage(Integer status, Integer currPage, Integer rows) {
        return userRepository.findStatusByPage(status, currPage, rows);
    }

    @Override
    public PageBean<User> findStatusByPageRowData(Integer status, Integer currPage, Integer rows) {
        int start = (currPage - 1) * rows;
        int pageSize = (int) (Math.ceil(userRepository.findAllStatus(status) / (double) rows));
        pageBean.setRows(findStatusByPage(status, start, rows));
        pageBean.setPageSize(Math.max(pageSize, 1));
        return pageBean;
    }

    @Override
    public List<User> findKeywordByPage(String keyword, Integer currPage, Integer rows) {
        return userRepository.findKeywordByPage(keyword, currPage, rows);
    }

    @Override
    public PageBean<User> findKeywordByPageRowData(String keyword, Integer currPage, Integer rows) {
        int start = (currPage - 1) * rows;
        int pageSize = (int) (Math.ceil(userRepository.findAllByKeword(keyword) / (double) rows));
        pageBean.setRows(findKeywordByPage(keyword, start, rows));
        pageBean.setPageSize(Math.max(pageSize, 1));
        return pageBean;
    }

    @Override
    public List<User> findDateRangeByPage(String startDate, String endDate, Integer currPage, Integer rows) {
        return userRepository.findDateRangeByPage(startDate, endDate, currPage, rows);
    }

    @Override
    public PageBean<User> findBeanPageByDateRange(String startDate, String endDate, Integer currPage, Integer rows) {
        int start = (currPage - 1) * rows;
        int pageSize = (int) (Math.ceil(userRepository.findAllByDateRange(startDate, endDate) / (double) rows));
        pageBean.setRows(findDateRangeByPage(startDate, endDate, start, rows));
        pageBean.setPageSize(Math.max(pageSize, 1));
        return pageBean;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
