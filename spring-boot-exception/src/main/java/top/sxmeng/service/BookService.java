package top.sxmeng.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import top.sxmeng.common.dto.BookCreateDTO;
import top.sxmeng.common.dto.BookPageQuery;
import top.sxmeng.common.dto.BookUpdateDTO;
import top.sxmeng.common.dto.StockAdjustDTO;
import top.sxmeng.common.vo.BookVO;

public interface BookService {
    // 新增图书
    BookVO create(BookCreateDTO dto);
    // 更新图书
    BookVO update(Long id, BookUpdateDTO dto);
    // 库存调整
    BookVO adjustStock(Long id, StockAdjustDTO dto);
    // 按ID查询
    BookVO getById(Long id);
    // ISBN唯一性检查
    boolean isbnExists(String isbn);
    // 分页查询
    IPage<BookVO> pageQuery(BookPageQuery query);
    // 逻辑删除
    void delete(Long id);
    // 恢复删除
    BookVO restore(Long id);
}