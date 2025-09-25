package top.sxmeng.boot.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Address implements Serializable {
    @Serial
    private static final long serialVersionUID = -7477986366506064303L;

    private String province;
    private String city;


}

