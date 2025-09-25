package top.sxmeng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import top.sxmeng.entity.Book;

import java.util.List;

@Mapper
public interface BookMapper extends BaseMapper<Book> {

    // 绕过逻辑删除的查询方法
    @Select("SELECT * FROM book WHERE id = #{id}")
    Book selectByIdIncludeDeleted(@Param("id") Long id);

    // 直接SQL恢复方法
    @Update("UPDATE book SET deleted = 0, update_time = NOW() WHERE id = #{id}")
    int restoreById(@Param("id") Long id);

    // 查询所有已删除图书
    @Select("SELECT * FROM book WHERE deleted = 1")
    List<Book> selectAllDeleted();
}