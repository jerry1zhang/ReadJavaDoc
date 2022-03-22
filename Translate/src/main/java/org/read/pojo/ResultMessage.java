package org.read.pojo;

import lombok.*;

/**
 * @author 张弘毅
 * @version 1.0
 * @date 2022/3/22 下午1:58
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ResultMessage<T> {
    private Integer code;
    private String message;
    private T messageBody;
}
