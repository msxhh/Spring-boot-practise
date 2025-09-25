package top.sxmeng.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import top.sxmeng.common.Result;
import top.sxmeng.common.dto.BookCreateDTO;
import top.sxmeng.common.dto.BookPageQuery;
import top.sxmeng.common.dto.BookUpdateDTO;
import top.sxmeng.common.dto.StockAdjustDTO;
import top.sxmeng.service.BookService;
import top.sxmeng.common.vo.BookVO;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // 新增图书
    @PostMapping
    public Result<BookVO> create(@Valid @RequestBody BookCreateDTO dto) {
        return Result.success(bookService.create(dto));
    }

    // 更新图书
    @PutMapping("/{id}")
    public Result<BookVO> update(
            @PathVariable Long id,
            @Valid @RequestBody BookUpdateDTO dto) {
        return Result.success(bookService.update(id, dto));
    }

    // 库存调整
    @PatchMapping("/{id}/stock/adjust")
    public Result<BookVO> adjustStock(
            @PathVariable Long id,
            @Valid @RequestBody StockAdjustDTO dto) {
        return Result.success(bookService.adjustStock(id, dto));
    }

    // 按ID查询
    @GetMapping("/{id}")
    public Result<BookVO> getById(@PathVariable Long id) {
        return Result.success(bookService.getById(id));
    }

    // ISBN唯一性检查
    @GetMapping("/exists/isbn/{isbn}")
    public Result<Boolean> isbnExists(@PathVariable String isbn) {
        return Result.success(bookService.isbnExists(isbn));
    }

    // 分页查询
    @GetMapping("/page")
    public Result<IPage<BookVO>> pageQuery(BookPageQuery query) {
        return Result.success(bookService.pageQuery(query));
    }

    // 逻辑删除
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return Result.success();
    }

    // 恢复删除
    @PutMapping("/{id}/restore")
    public Result<BookVO> restore(@PathVariable Long id) {
        return Result.success(bookService.restore(id));
    }
}
