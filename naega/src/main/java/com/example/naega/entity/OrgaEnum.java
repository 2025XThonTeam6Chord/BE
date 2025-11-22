package com.example.naega.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrgaEnum {
    SCHOOL("SCHOOL", "학교"),
    AGENCY("AGENCY", "공공기관"),
    COMPANY("COMPANY", "기업"),
    ETC("ETC", "기타");


    private final String key;
    private final String title;

    public static OrgaEnum fromKey(String key) {
        for (OrgaEnum type : OrgaEnum.values()) {
            if (type.getKey().equalsIgnoreCase(key)) {
                return type;
            }
        }
        throw new IllegalArgumentException("일치하는 기관 타입이 없습니다: " + key);
    }

}
