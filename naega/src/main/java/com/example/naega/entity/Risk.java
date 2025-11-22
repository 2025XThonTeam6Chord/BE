package com.example.naega.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Risk {
    DANGER("DANGER","심각"),
    CAUTION("CAUTION", "주의"),
    NOMAL("NOMAL", "보통"),
    GOOD("GOOD","좋음"),
    PERFECT("PERFECT", "완벽");

    private final String key;
    private final String risk;
}
