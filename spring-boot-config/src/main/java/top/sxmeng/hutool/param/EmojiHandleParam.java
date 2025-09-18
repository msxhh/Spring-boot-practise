package top.sxmeng.hutool.param;

import lombok.Data;

@Data
public class EmojiHandleParam {
    // 待处理文本（必填，示例："今天很开心:smile:，收到了礼物:gift:" 或 "今天很开心😄，收到了礼物🎁"）
    private String content;
    // 处理类型（必填，可选值："keywordToEmoji"=关键词转Emoji，"emojiToUnicode"=Emoji转Unicode）
    private String handleType;
}