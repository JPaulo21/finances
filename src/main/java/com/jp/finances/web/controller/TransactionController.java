package com.jp.finances.web.controller;

import com.jp.finances.web.dto.TransactionDocs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/transactions", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class TransactionController implements TransactionDocs {
}
