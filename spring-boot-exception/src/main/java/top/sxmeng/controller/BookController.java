package top.sxmeng.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import top.sxmeng.common.Result;
import top.sxmeng.common.dto.BookCreateDTO;
import top.sxmeng.common.dto.BookPageQuery;
import top.sxmeng.common.dto.BookUpdateDTO;
import top.sxmeng.common.dto.StockAdjustDTO;
import top.sxmeng.common.vo.BookVO;
import top.sxmeng.exception.BusinessException;
import top.sxmeng.enums.ErrorCode;
import top.sxmeng.service.BookService;

/**
 * 图书控制器
 */
@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * 新增图书
     */
    @PostMapping
    public Result<BookVO> create(@Valid @RequestBody BookCreateDTO dto) {
        BookVO bookVO = bookService.create(dto);
        return Result.success(bookVO);
    }

    /**
     * 更新图书
     */
    @PutMapping("/{id}")
    public Result<BookVO> update(@PathVariable Long id, @Valid @RequestBody BookUpdateDTO dto) {
        BookVO bookVO = bookService.update(id, dto);
        return Result.success(bookVO);
    }

    /**
     * 库存调整
     */
    @PatchMapping("/{id}/stock/adjust")
    public Result<BookVO> adjustStock(@PathVariable Long id, @Valid @RequestBody StockAdjustDTO dto) {
        if (!"in".equals(dto.getType()) && !"out".equals(dto.getType())) {
            throw new BusinessException(ErrorCode.PARAM_ERROR);
        }
        BookVO bookVO = bookService.adjustStock(id, dto);
        return Result.success(bookVO);
    }

    /**
     * 按ID查询图书
     */
    @GetMapping("/{id}")
    public Result<BookVO> getById(@PathVariable Long id) {
        BookVO bookVO = bookService.getById(id);
        if (bookVO == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND);
        }
        return Result.success(bookVO);
    }

    /**
     * ISBN唯一性检查
     */
    @GetMapping("/exists/isbn/{isbn}")
    public Result<Boolean> isbnExists(@PathVariable String isbn) {
        return Result.success(bookService.isbnExists(isbn));
    }

    /**
     * 分页查询
     */
    @GetMapping("/page")
    public Result<IPage<BookVO>> pageQuery(BookPageQuery query) {
        return Result.success(bookService.pageQuery(query));
    }

    /**
     * 逻辑删除
     */
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return Result.success();
    }

    /**
     * 恢复删除
     */
    @PutMapping("/{id}/restore")
    public Result<BookVO> restore(@PathVariable Long id) {
        BookVO bookVO = bookService.restore(id);
        return Result.success(bookVO);
    }
}