package com.ngalain.admin.common;

public enum ResponseStatusEnum {
    SUCCESS(200, "默认成功"),
    ERROR(400, "请求失败"),
    CONFLICT_USER(80081, "用户信息已变更"),
    ILLEGAL_ARGUMENT(80099, "参数不合法");

    private ResponseStatusEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    private final String description; // 错误码描述

    private final int code; // 错误码

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
