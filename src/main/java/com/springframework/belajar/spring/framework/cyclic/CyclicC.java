package com.springframework.belajar.spring.framework.cyclic;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CyclicC {

    private CyclicA cyclicA;
}
