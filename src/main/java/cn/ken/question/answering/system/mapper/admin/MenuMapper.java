package cn.ken.question.answering.system.mapper.admin;

import cn.ken.question.answering.system.model.admin.Menu;

import java.util.List;

/**
 * author: shangkun <br/>
 * date: 2018/7/13 <br/>
 * what: 菜单数据访问 <br/>
 */
public interface MenuMapper {
    //获取所有
    List<Menu> get();
}