package com.joedev.accountservices.exceptions;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage {
    private String message;
    private int status;
}
