package com.nplatform.utils;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Locale;

@Component
public class MessageSourceUtils {

  private String locale;

  @Resource(name = "resource")
  private MessageSource messageSource;

  public String getMSg(String key) {
    Locale loc = Locale.US;
    if ("CHINA".equals(locale)) {
      loc = Locale.CHINA;
    }
    return messageSource.getMessage(key,null, loc);
  }

  public String getLocale() {
    return locale;
  }

  public void setLocale(String locale) {
    this.locale = locale;
  }
}
