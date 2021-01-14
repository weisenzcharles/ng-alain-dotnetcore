package com.weisenz.weiadmin.domain;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString(doNotUseGetters = true)
public class LoginResult {
    public String msg;
    public Map user;
}
