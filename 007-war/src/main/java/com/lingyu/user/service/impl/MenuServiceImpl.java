package com.lingyu.user.service.impl;

import com.lingyu.user.mapper.MenuMapper;
import com.lingyu.user.model.Menu;
import com.lingyu.user.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Override
    public List<Menu> getMenu() {
        List<Menu> menuList=menuMapper.getMenu();
        return menuList;
    }
}
