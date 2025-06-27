package com.test.TestTrack.domain.dto;

public record ErrorResponse(
   int status,
   String message,
   String details
) {

}
