package com.vs.soutils.lambda.SO29235567;

public class Result {
  private String content;
  private String key;

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  @Override
  public String toString() {
    return "Result{" +
           "content='" + content + '\'' +
           ", key='" + key + '\'' +
           '}';
  }
}
