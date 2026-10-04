package com.example.corporate.pattern;
import org.slf4j.*; import org.springframework.stereotype.Component;
@Component public class LoggingAuditObserver implements AuditObserver { private static final Logger log=LoggerFactory.getLogger(LoggingAuditObserver.class); public void onEvent(String event){log.info("AUDIT: {}",event);} }
