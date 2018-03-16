package com.parse.test;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.betwixt.io.BeanReader;
import org.apache.commons.betwixt.io.BeanWriter;

import com.parse.model.LineDetail;
import com.parse.model.NodeDetail;
import com.parse.model.ProcessDetail;
import com.parse.simple.School;
import com.parse.simple.Score;
import com.parse.simple.Student;
import com.parse.util.BetwixtUtil;

public class SimpleTest {
	public void test_01() {
		// 先创建一个StringWriter，我们将把它写入为一个字符串     
        StringWriter outputWriter = new StringWriter();
        // Betwixt在这里仅仅是将Bean写入为一个片断 
        // 所以如果要想完整的XML内容，我们应该写入头格式 
        //outputWriter.write("<?xml version='1.0′ encoding='UTF-8′ ?> \n");
        // 创建一个BeanWriter，其将写入到我们预备的stream中 
        BeanWriter beanWriter = new BeanWriter(outputWriter);
        // 配置betwixt 
        // 更多详情请参考java docs 或最新的文档 
        beanWriter.getXMLIntrospector().getConfiguration().setAttributesForPrimitives(false);
        beanWriter.getBindingConfiguration().setMapIDs(false);
        beanWriter.enablePrettyPrint();
        // 如果这个地方不传入XML的根节点名，Betwixt将自己猜测是什么 
        // 但是让我们将例子Bean名作为根节点吧
        Score score = new Score();
        score.setMathScore(100);
        try {
			beanWriter.write("score",score);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        //输出结果 
        String xml = outputWriter.toString();
        // Betwixt写的是片断而不是一个文档，所以不要自动的关闭掉writers或者streams， 
        //但这里仅仅是一个例子，不会做更多事情，所以可以关掉 
        try {
			outputWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        System.out.println(">>>>>>>>" + xml + ">>>>>>>>>>>>>>");
        
        
        BeanReader beanReader = new BeanReader();
    	beanReader.getXMLIntrospector().getConfiguration().setAttributesForPrimitives(false);
		beanReader.getBindingConfiguration().setMapIDs(false);
		StringReader xmlReader = new StringReader(xml);
		Score xmlScore = null;
		try {
			beanReader.registerBeanClass("score",Score.class);
			xmlScore = (Score)beanReader.parse(xmlReader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(">>>>>>>>>>>>" + xmlScore.toString() + ">>>>>>>>>>>");
	}
	
	public void test_02() throws Exception {
		//StringReader xmlReader = new StringReader(
		//		"<?xml version='1.0′ encoding='UTF-8′ ?> <person><age>25</age><name>James Smith</name></person>");
		//创建BeanReader
		BeanReader beanReader = new BeanReader();
		//配置reader
		beanReader.getXMLIntrospector().getConfiguration().setAttributesForPrimitives(false);
		beanReader.getBindingConfiguration().setMapIDs(false);
		// 注册beans，以便betwixt知道XML将要被转化为一个什么Bean
		//beanReader.registerBeanClass("person", PersonBean.class);
		// 现在我们对XML进行解析
		//PersonBean person = (PersonBean) beanReader.parse(xmlReader);
		// 输出结果
		//System.out.println(person);
	}

	public void test_03() throws Exception {
		Score score = new Score();
		score.setMathScore(100);
		
		BetwixtUtil betwixtUtil = new BetwixtUtil();
		String xml = betwixtUtil.write(score);
		
		System.out.println(">>>>>>>>>>>>>" + xml + ">>>>>>>>>>>>");
	}

	public void test_04() throws Exception {
		//Score score = new Score();
		//score.setMathScore(100);
		Student student = new Student();
		student.setName("zhouyanyao");
		//student.setScore(score);
		
		System.out.println(">>>>>>>>>>>>>" + student.toString() + ">>>>>>>>>>>>");
	}
	
	
	public void test_05() throws Exception {
		School school = new School();
		school.setId(100);
		school.setName("zhouyanyao");
		
		System.out.println(">>>>>>>>>>>>>" + school.toString() + ">>>>>>>>>>>>");
	}
	
	public void test_06() throws Exception {
		NodeDetail nodeDetail = new NodeDetail();
		nodeDetail.setId("100");
		nodeDetail.setName("起草节点");
		nodeDetail.setX("100");
		nodeDetail.setY("100");
		
		List<NodeDetail> nodeDetails = new ArrayList<NodeDetail>();
		nodeDetails.add(nodeDetail);
		
		LineDetail lineDetail = new LineDetail();
		lineDetail.setId("100");
		lineDetail.setName("起草节点");
		lineDetail.setX("100");
		lineDetail.setY("100");
		
		List<LineDetail> lineDetails = new ArrayList<LineDetail>();
		lineDetails.add(lineDetail);
		
		ProcessDetail processDetail = new ProcessDetail();
		processDetail.setDayOfNotifyPrivileger("10");
		processDetail.setLinesIndex(20);
		processDetail.setNodes(nodeDetails);
		processDetail.setLines(lineDetails);
		
		System.out.println(">>>>>>>>>>>>>" + processDetail.toString() + ">>>>>>>>>>>>");
	}
}
