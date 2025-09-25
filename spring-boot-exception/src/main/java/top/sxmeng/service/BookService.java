
package top.sxmeng.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import top.sxmeng.common.dto.BookCreateDTO;
import top.sxmeng.common.dto.BookPageQuery;
import top.sxmeng.common.dto.BookUpdateDTO;
import top.sxmeng.common.dto.StockAdjustDTO;
import top.sxmeng.common.vo.BookVO;

public interface BookService {
    BookVO create(BookCreateDTO dto);
    BookVO update(Long id, BookUpdateDTO dto);
    BookVO adjustStock(Long id, StockAdjustDTO dto);
    BookVO getById(Long id);
    boolean isbnExists(String isbn);
    IPage<BookVO> pageQuery(BookPageQuery query);
    void delete(Long id);
    BookVO restore(Long id);
}