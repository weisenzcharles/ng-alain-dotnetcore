package org.charles.weiadmin.domain;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString(doNotUseGetters = true)
public class LoginResult {
    public String message;

    public Map user;
}
