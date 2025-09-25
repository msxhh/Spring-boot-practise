package top.sxmeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.sxmeng.entity.Book;
import top.sxmeng.common.dto.BookCreateDTO;
import top.sxmeng.common.dto.BookPageQuery;
import top.sxmeng.common.dto.BookUpdateDTO;
import top.sxmeng.common.dto.StockAdjustDTO;
import top.sxmeng.common.vo.BookVO;
import top.sxmeng.exception.BusinessException;
import top.sxmeng.enums.ErrorCode;
import top.sxmeng.mapper.BookMapper;
import top.sxmeng.service.BookService;
import top.sxmeng.utils.BeanCopyUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    private final BookMapper bookMapper;

    @Override
    @Transactional
    public BookVO create(BookCreateDTO dto) {
        // 检查ISBN是否已存在
        if (isbnExists(dto.getIsbn())) {
            throw new BusinessException(ErrorCode.PARAM_ERROR);
        }

        Book book = BeanCopyUtils.copyBean(dto, Book.class);
        book.setDeleted(0);
        book.setVersion(0);
        save(book);

        return getBookById(book.getId());
    }

    @Override
    @Transactional
    public BookVO update(Long id, BookUpdateDTO dto) {
        Book book = getBookEntityByIdIncludeDeleted(id);

        if (book == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND);
        } else if (book.getDeleted() == 1) {
            throw new BusinessException(ErrorCode.NOT_FOUND);
        }

        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setCategory(dto.getCategory());

        boolean updated = updateById(book);
        if (!updated) {
            log.error("更新图书失败，ID: {}", id);
            throw new BusinessException(ErrorCode.SERVER_ERROR);
        }

        Book updatedBook = getBookEntityById(id);
        log.info("更新后的图书: {}", updatedBook);
        return BeanCopyUtils.copyBean(updatedBook, BookVO.class);
    }

    @Override
    @Transactional
    public BookVO adjustStock(Long id, StockAdjustDTO dto) {
        Book book = getBookEntityById(id);
        if (book == null || book.getDeleted() == 1) {
            throw new BusinessException(ErrorCode.NOT_FOUND);
        }

        if ("in".equals(dto.getType())) {
            book.setStock(book.getStock() + dto.getAmount());
        } else if ("out".equals(dto.getType())) {
            if (book.getStock() < dto.getAmount()) {
                throw new BusinessException(ErrorCode.PARAM_ERROR);
            }
            book.setStock(book.getStock() - dto.getAmount());
        }

        updateById(book);
        return BeanCopyUtils.copyBean(book, BookVO.class);
    }

    @Override
    public BookVO getById(Long id) {
        Book book = getBookEntityById(id);
        if (book == null || book.getDeleted() == 1) {
            return null;
        }
        return BeanCopyUtils.copyBean(book, BookVO.class);
    }

    @Override
    public boolean isbnExists(String isbn) {
        LambdaQueryWrapper<Book> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Book::getIsbn, isbn)
                .eq(Book::getDeleted, 0);
        return count(wrapper) > 0;
    }

    @Override
    public IPage<BookVO> pageQuery(BookPageQuery query) {
        LambdaQueryWrapper<Book> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Book::getDeleted, 0);

        if (query.getTitle() != null && !query.getTitle().isEmpty()) {
            wrapper.like(Book::getTitle, query.getTitle());
        }
        if (query.getCategory() != null && !query.getCategory().isEmpty()) {
            wrapper.eq(Book::getCategory, query.getCategory());
        }

        Page<Book> page = new Page<>(query.getPageNum(), query.getPageSize());
        Page<Book> bookPage = bookMapper.selectPage(page, wrapper);

        return bookPage.convert(book -> BeanCopyUtils.copyBean(book, BookVO.class));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        // 先检查是否存在（包括已删除的）
        Book book = getBookEntityByIdIncludeDeleted(id);
        if (book == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND);
        }
        if (book.getDeleted() == 1) {
            throw new BusinessException(ErrorCode.PARAM_ERROR);
        }

        boolean removed = removeById(id);
        if (!removed) {
            throw new BusinessException(ErrorCode.SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public BookVO restore(Long id) {
        Book book = bookMapper.selectByIdIncludeDeleted(id);
        if (book == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND);
        }

        if (book.getDeleted() == 0) {
            return BeanCopyUtils.copyBean(book, BookVO.class);
        }

        int updated = bookMapper.restoreById(id);
        if (updated == 0) {
            throw new BusinessException(ErrorCode.SERVER_ERROR);
        }

        Book restoredBook = bookMapper.selectByIdIncludeDeleted(id);
        return BeanCopyUtils.copyBean(restoredBook, BookVO.class);
    }

    /**
     * 私有方法：获取图书实体（不包括已删除的）
     */
    private Book getBookEntityById(Long id) {
        LambdaQueryWrapper<Book> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Book::getId, id)
                .eq(Book::getDeleted, 0);
        return bookMapper.selectOne(wrapper);
    }

    /**
     * 私有方法：获取图书实体（包括已删除的）- 修复版
     */
    private Book getBookEntityByIdIncludeDeleted(Long id) {
        // 直接使用selectById，不添加deleted条件
        return bookMapper.selectById(id);
    }

    /**
     * 私有方法：获取图书VO（不包括已删除的）
     */
    private BookVO getBookById(Long id) {
        Book book = getBookEntityById(id);
        if (book == null) {
            return null;
        }
        return BeanCopyUtils.copyBean(book, BookVO.class);
    }
}