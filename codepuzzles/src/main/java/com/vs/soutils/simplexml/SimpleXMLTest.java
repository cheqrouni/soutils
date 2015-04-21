package com.vs.soutils.simplexml;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;


public class SimpleXMLTest {


//  @Root
//  static class ProcessConfiguration {
//
//    @Attribute
//    Long id;
//
//    @ElementMap(name = "EquipmentConfigurations", key="id", attribute = true)
//    Map<Long, EquipmentConfiguration> equipmentConfigurations = new HashMap<>();
//  }
//
//
      @Root
      static class ProcessConfiguration {

        @Attribute
        Long id;

        @ElementList(name = "EquipmentConfigurations")
        List<EquipmentConfiguration> equipmentConfigurations = new ArrayList<>();
      }

  @Root(name = "EquipmentConfiguration")
  static class EquipmentConfiguration {

    @Attribute
    Long id;

    @Element
    String address;
  }

  public static void main(String[] args) {

    //testSerialize();
    testDeserialize();

  }

  private static void testDeserialize() {
    Serializer serializer = new Persister();
    File source = new File("/home/vshukla/dev/soutils/codepuzzles/src/main/java/com/vs/soutils/simplexml/example2.xml");

    try {
      ProcessConfiguration example = serializer.read(ProcessConfiguration.class, source);
      System.out.println(example);
    } catch (Exception e) {
      e.printStackTrace();
    }


  }

  private static void testSerialize() {
    Serializer serializer = new Persister();
    EquipmentConfiguration equipmentConfiguration = new EquipmentConfiguration();
    equipmentConfiguration.id = 123456L;
    equipmentConfiguration.address = "abcd efgh";

    ProcessConfiguration configuration = new ProcessConfiguration();
    configuration.id = 456789L;
    configuration.equipmentConfigurations.add(equipmentConfiguration);
    File result = new File("/home/vshukla/dev/soutils/codepuzzles/src/main/java/com/vs/soutils/simplexml/example2.xml");
    try {
      serializer.write(configuration, result);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
