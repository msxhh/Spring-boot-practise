package top.sxmeng.util;

import jakarta.annotation.Resource;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;
import top.sxmeng.common.dto.BookCreateDTO;
import top.sxmeng.service.BookService;

import java.util.Locale;
import java.util.Random;
import java.util.stream.IntStream;

@Component
public class DataFakerUtil {
    private static final Faker ZH_FAKER = new Faker(Locale.CHINA);
    private static final Random RANDOM = new Random();

    // 图书分类列表
    private static final String[] CATEGORIES = {
            "计算机科学", "文学小说", "历史人文", "科普读物", "商业管理",
            "艺术设计", "医学健康", "哲学宗教", "法律政治", "生活休闲"
    };

    @Resource
    private BookService bookService;

    /**
     * 生成单本图书的DTO数据（用于创建图书）
     */
    private BookCreateDTO generateBookCreateDTO(int index) {
        BookCreateDTO dto = new BookCreateDTO();

        // 生成书名（随机添加版本号）
        String title = ZH_FAKER.book().title();
        if (RANDOM.nextBoolean()) {
            title += "（第" + (RANDOM.nextInt(10) + 1) + "版）";
        }
        dto.setTitle(title);

        // 生成作者（30%概率为合著）
        StringBuilder author = new StringBuilder(ZH_FAKER.name().fullName());
        if (RANDOM.nextDouble() < 0.3) {
            author.append("、").append(ZH_FAKER.name().fullName());
        }
        dto.setAuthor(author.toString());

        // 生成唯一ISBN
        String isbn;
        do {
            isbn = generateIsbn();
        } while (bookService.isbnExists(isbn)); // 确保ISBN唯一
        dto.setIsbn(isbn);

        // 随机选择分类
        dto.setCategory(CATEGORIES[RANDOM.nextInt(CATEGORIES.length)]);

        // 生成库存（0-50本）
        dto.setStock(RANDOM.nextInt(51));

        return dto;
    }

    /**
     * 批量生成100条图书数据
     */
    public void generateBatch() {
        int total = 100;
        int step = 10;

        for (int start = 0; start < total; start += step) {
            // 生成批次的DTO数据并创建图书
            IntStream.range(start, Math.min(start + step, total))
                    .mapToObj(this::generateBookCreateDTO)
                    .forEach(bookService::create);
        }

        System.out.println("已成功生成100条图书数据");
    }

    /**
     * 生成符合ISBN-13格式的编号
     */
    private String generateIsbn() {
        return String.format("978-%05d-%03d-%05d-%d",
                RANDOM.nextInt(100000),
                RANDOM.nextInt(1000),
                RANDOM.nextInt(100000),
                RANDOM.nextInt(10)
        );
    }
}
