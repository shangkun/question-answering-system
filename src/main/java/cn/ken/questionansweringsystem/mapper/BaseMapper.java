package cn.ken.questionansweringsystem.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * author: shangkun <br/>
 * date: 2018/7/13 <br/>
 * what: 基础Mapper <br/>
 */
public interface BaseMapper<T,P> {
    /**
     * 添加一条数据
     * @param model
     * @return
     */
    int insert(T model);

    /**
     * 通过id删除一条数据
     * @param id
     * @return
     */
    int deleteById(@Param("id")P id);

    /**
     * 更新一条数据
     * @param model
     * @return
     */
    int update(T model);

    /**
     * 通过id查询一条数据
     * @param id
     * @return
     */
    T selectById(@Param("id")P id);
}
