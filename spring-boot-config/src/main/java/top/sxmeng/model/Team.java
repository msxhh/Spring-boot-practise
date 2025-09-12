package top.sxmeng.model;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Data
@Component
public class Team {
    @Value("${team.name}")
    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 3, max = 20, message = "团队长度必须位于3到20之间")
    private String name;

    @Value("${team.leader}")
    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 3, max = 8, message = "团队负责人长度必须位于3到8之间")
    private String leader;

    @Value("${team.phone}")
    @Pattern(regexp = "^[0-9]{11}$", message = "手机号格式不对")
    private String phone;

    @Value("${team.age}")
    @Min(1)
    @Max(4)
    private Integer age;

    @Past(message = "创建时间必须早于当前时间")
    private LocalDate createTime;

}