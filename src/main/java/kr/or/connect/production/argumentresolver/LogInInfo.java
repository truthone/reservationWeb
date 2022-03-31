package kr.or.connect.production.argumentresolver;

import java.util.HashMap;
import java.util.Map;

public class LogInInfo {
  private Map<String, String> map;

  public LogInInfo() {
    map = new HashMap<>();
  }

  public void put(String name, String value) {
    map.put(name, value);
  }

  public String get(String name) {
    return map.get(name);
  }
}
