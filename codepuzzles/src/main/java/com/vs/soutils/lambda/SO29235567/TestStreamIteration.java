package com.vs.soutils.lambda.SO29235567;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;


//Test for: http://stackoverflow.com/questions/29235567/
public class TestStreamIteration {

  public static void testIterationJava7() {

    List<FirstNode> firstNodes = buildFirstNodesList();

    Response response = new Response();
    response.setFirstNodes(firstNodes);

    Result result = new Result();

    //find first match and populate the result object.
    for (FirstNode first : response.getFirstNodes()) {
      boolean found = false;
      for (SndNode snd : first.getSndNodes()) {
        if (snd.isValid()) {
          result.setKey(first.getKey());
          result.setContent(snd.getContent());
          found = true;
          break;
        }
      }
      if (found) break;
    }

    System.out.println(result);
  }

  private static List<FirstNode> buildFirstNodesList() {
    List<SndNode> sndNodes = new ArrayList<>();
    List<FirstNode> firstNodes = new ArrayList<>();
    SndNode sndNode = new SndNode();
    sndNode.setContent("Content 1");
    sndNode.setValid(false);
    sndNodes.add(sndNode);

    sndNode = new SndNode();
    sndNode.setContent("Content 2");
    sndNode.setValid(true);
    sndNodes.add(sndNode);

    sndNode = new SndNode();
    sndNode.setContent("Content 3");
    sndNode.setValid(false);
    sndNodes.add(sndNode);

    sndNode = new SndNode();
    sndNode.setContent("Content 4");
    sndNode.setValid(true);
    sndNodes.add(sndNode);

    sndNode = new SndNode();
    sndNode.setContent("Content 5");
    sndNode.setValid(false);
    sndNodes.add(sndNode);


    FirstNode firstNode = new FirstNode();
    firstNode.setKey("Node 1");
    firstNode.setSndNodes(sndNodes);

    firstNodes.add(firstNode);

    firstNode = new FirstNode();
    firstNode.setKey("Node 2");
    firstNode.setSndNodes(sndNodes);
    firstNodes.add(firstNode);
    return firstNodes;
  }

  public static void testIterationJava8() {

    List<FirstNode> firstNodes = buildFirstNodesList();

    Response response = new Response();
    response.setFirstNodes(firstNodes);

    Result result = new Result();

    response.getFirstNodes().stream()
      .filter(it -> {it.getSndNodes().stream().filter(SndNode::isValid).findFirst(); return true;})
      .findFirst()
      .ifPresent(first -> first.getSndNodes().stream().filter(SndNode::isValid).findFirst().ifPresent(snd -> {
        result.setKey(first.getKey());
        result.setContent(snd.getContent());
      }));


    System.out.println(result);
  }

  public static void main(String[] args) {
    testIterationJava7();
    testIterationJava8();
  }
}
