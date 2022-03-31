package kr.or.connect.production.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {

  @RequestMapping(path = "mainpage")
  public String productList(
    @RequestParam(
      name = "start",
      required = false,
      defaultValue = "0"
    ) int start,
    ModelMap model
  ) {
    return "mainpage";
  }

  @RequestMapping(path = "/products/{displayInfoId}")
  public String detail(
    @PathVariable(name = "displayInfoId") int displayInfoId,
    ModelMap model
  ) {
    model.addAttribute("displayInfoId", displayInfoId);
    return "detail";
  }

  @RequestMapping(path = "/products/{displayInfoId}/review")
  public String review(
    @PathVariable(name = "displayInfoId") int displayInfoId,
    ModelMap model
  ) {
    model.addAttribute("displayInfoId", displayInfoId);
    return "review";
  }

  @RequestMapping(path = "/myreservation")
  public String myReservation() {
    return "myreservation";
  }

  @RequestMapping(path = "/products/{displayInfoId}/reserve")
  public String reserve(
    @PathVariable(name = "displayInfoId") int displayInfoId,
    ModelMap model
  ) {
    model.addAttribute("displayInfoId", displayInfoId);
    return "reserve";
  }
}
