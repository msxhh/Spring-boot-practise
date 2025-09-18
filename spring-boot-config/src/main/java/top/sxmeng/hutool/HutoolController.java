package top.sxmeng.hutool;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.emoji.EmojiUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.sxmeng.hutool.entity.Joke;
import top.sxmeng.hutool.param.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 使用Hutool工具集实现的API接口控制器
 */
@RestController
@RequestMapping("/api/hutool")
public class HutoolController {

    /**
     * 1.Emoji工具接口：支持关键词转Emoji、Emoji转Unicode
     */
    @PostMapping("/emoji/handle")
    public ApiResponse<String> handleEmoji(@RequestBody EmojiHandleParam param) {
        if (StrUtil.isBlank(param.getContent()) || StrUtil.isBlank(param.getHandleType())) {
            return ApiResponse.error(400,"待处理文本和处理类型不能为空");
        }

        String result;
        switch (param.getHandleType()) {
            case "1":
                result = EmojiUtil.toUnicode(param.getContent());
                break;
            case "2":
                result = EmojiUtil.toAlias(param.getContent());
                break;
            default:
                return ApiResponse.error(400,"不支持处理");
        }

        // 3. 返回处理结果
        String successMsg = "keywordToEmoji".equals(param.getHandleType())
                ? "关键词转Emoji成功"
                : "Emoji转Unicode成功";
        return ApiResponse.success(successMsg, result);
    }

    /**
     * 2. 日期格式化接口
     * 将任意格式的日期字符串转换为指定格式
     */
    @PostMapping("/date/format")
    public ApiResponse<String> formatDate(@RequestBody DateFormatParam param) {
        // 参数校验
        if (StrUtil.isBlank(param.getDateStr()) || StrUtil.isBlank(param.getTargetPattern())) {
            return ApiResponse.badRequest("日期字符串和目标格式不能为空");
        }

        try {
            Date date = DateUtil.parse(param.getDateStr());
            String formattedDate = DateUtil.format(date, param.getTargetPattern());
            return ApiResponse.success("日期格式化成功", formattedDate);
        } catch (Exception e) {
            return ApiResponse.badRequest("日期格式错误，示例：dateStr=20240520，targetPattern=yyyy年MM月dd日");
        }
    }

    /**
     * 3. 手机号脱敏接口
     * 将手机号中间4位替换为*
     */
    @PostMapping("/phone/desensitize")
    public ApiResponse<String> desensitizePhone(@RequestBody PhoneDesensitizeParam param) {
        // 参数校验
        if (StrUtil.isBlank(param.getPhone()) || param.getPhone().length() != 11) {
            return ApiResponse.badRequest("请输入11位有效的手机号");
        }

        // 脱敏处理：隐藏第4-7位
        String desensitizedPhone = StrUtil.hide(param.getPhone(), 3, 7);
        return ApiResponse.success("手机号脱敏成功", desensitizedPhone);
    }

    /**
     * 随机段子
     */
    @PostMapping("/joke/random")
    public ApiResponse<Joke> getRandomJoke(@RequestBody JokeGenerateParam param) {
        try {
            String jokeJson = ResourceUtil.readUtf8Str("E:\\xm\\practise\\spring-boot-course\\spring-boot-config\\src\\main\\resources\\joke.json");
            JSONArray jsonArray = JSONUtil.parseArray(jokeJson);
            List<Joke> jokeList = JSONUtil.toList(jsonArray, Joke.class);

            List<Joke> filteredJokes = jokeList;
            if (param.getType() != null && !param.getType().trim().isEmpty()) {
                String targetType = param.getType().trim();
                filteredJokes = jokeList.stream()
                        .filter(joke -> targetType.equals(joke.getType()))
                        .collect(Collectors.toList());
                // 若筛选后无结果，返回提示
                if (filteredJokes.isEmpty()) {
                    return ApiResponse.error(400, "没有找到“" + targetType + "”类型的段子，可选类型：幽默、冷笑话、职场");
                }
            }

            Joke randomJoke = RandomUtil.randomEle(filteredJokes);
            return ApiResponse.success("随机段子生成成功", randomJoke);

        } catch (Exception e) {
            return ApiResponse.error(500, "段子生成失败：" + e.getMessage());
        }
    }

    /**
     * 5. 随机验证码生成
     * 生成1-10位的数字验证码
     */
    @PostMapping("/code/generate")
    public ApiResponse<String> generateVerificationCode(@RequestBody CodeGenerateParam param) {
        // 参数校验
        if (param.getLength() < 1 || param.getLength() > 10) {
            return ApiResponse.badRequest("验证码长度必须在1-10位之间");
        }

        // 生成随机数字验证码
        String code = RandomUtil.randomNumbers(param.getLength());
        return ApiResponse.success("验证码生成成功", code);
    }
}
