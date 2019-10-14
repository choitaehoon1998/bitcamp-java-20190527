package com.ohora.greenright.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/app")
@Controller
public class greenrightController {

  @ResponseBody
  @RequestMapping
  public String green() {
    return "greenright";
  }
}
