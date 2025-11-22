package com.example.naega.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseType {
    RATING_5("5점 척도"),
    YES_NO("예/아니오"),
    SHORT_TEXT("단답형");

    private final String description;
}
