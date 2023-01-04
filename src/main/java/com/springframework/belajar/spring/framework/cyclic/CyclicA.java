package com.springframework.belajar.spring.framework.cyclic;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CyclicA {

    private CyclicB cyclicB;
}
